package du_web;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.common.utils.IOUtils;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.demo.bean.Valid;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.alibaba.fastjson.JSON;

/**
 * @author 爱不留
 * @date 2018年5月9日 下午5:25:48
 **/

/**
 * 调用示例
 {
    "registry": "zookeeper://db:2181",
    "interfaceName": "com.alibaba.dubbo.demo.DemoService",
    "version": "",
    "group": "",
    "method": "submitPersons",
    "param": ["token", 1403525989275, {"id":"1", "name":"xiamen"}, [{"id":"1", "name":"a1", "age":2}, {"id":"1", "name":"a2", "age":22}]]
}
 *
 */
public class DubboToHttpServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory.getLogger(DubboToHttpServlet.class);

	private ApplicationConfig application = new ApplicationConfig("dubbo-to-http");
	private Map<String, GenericService> serviceCache = new HashMap<String, GenericService>();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("servlet start");

		// 1. 解析请求体
		logger.info("parsing request body");
		CallRequest callRequest = JSON.parseObject(IOUtils.read(new InputStreamReader(request.getInputStream())), CallRequest.class);
		logger.info("parsed request body. body json: {}", callRequest);

		// 2. 获取泛化服务接口
		logger.info("fetching generic service");
		GenericService service = this.fetchGenericService(callRequest);
		logger.info("fetched generic service. service: {}", service);

		// 3. 组装调用参数
		String method = callRequest.getMethod();

		// String[] parameterTypes =
		// this.toArray(requestJson.getJSONArray("paramTypes"));
		String[] parameterTypes = getMethodParamType(callRequest.getInterfaceName(), method);

		// 4. 调用接口
		logger.info("invoking remote service");
		String result = JSON.toJSONString(service.$invoke(method, parameterTypes, callRequest.getParam().toArray()));

		// 默认result里带了"class":"com.alibaba.dubbo.demo.model.Person"，去掉这个属性
		Valid valid = JSON.parseObject(result, Valid.class);
		result = JSON.toJSONString(valid);
		logger.info("invoked remote service. return: {}", result);

		// 5. 返回
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.append(result).flush();
		out.close();

		logger.info("servlet end");
	}

	@Override
	public void destroy() {
		RegistryConfig.destroyAll();
	}

	// 获取泛化服务接口. 如有缓存, 从缓存取
	private GenericService fetchGenericService(CallRequest request) {
		String registry = request.getRegistry();
		String interfaceName = request.getInterfaceName();
		String group = request.getGroup();
		String version = request.getVersion();

		String serviceCacheKey = interfaceName + group + version;
		GenericService service = serviceCache.get(serviceCacheKey);
		if (service != null) {
			logger.info("fetched generic service from cache");
			return service;
		}

		logger.info("initing generic service");
		ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();
		reference.setApplication(application);
		reference.setRegistry(new RegistryConfig(registry));
		reference.setInterface(interfaceName);
		reference.setGroup(group);
		reference.setVersion(version);
		reference.setGeneric(true);
		service = reference.get();
		serviceCache.put(serviceCacheKey, service);

		return service;
	}

	// List<Object> -> String[]
	private String[] toArray(List<Object> list) {
		String[] array = new String[list.size()];
		for (int i = 0; i < array.length; i++) {
			array[i] = list.get(i).toString();
		}
		return array;
	}

	public static String[] getMethodParamType(String interfaceName, String methodName) {
		try {
			// 创建类
			Class<?> class1 = Class.forName(interfaceName);
			// 获取所有的公共的方法
			Method[] methods = class1.getMethods();
			for (Method method : methods) {
				if (method.getName().equals(methodName)) {
					Class[] paramClassList = method.getParameterTypes();
					String[] paramTypeList = new String[paramClassList.length];
					int i = 0;
					for (Class className : paramClassList) {
						paramTypeList[i] = className.getTypeName();
						i++;
					}
					return paramTypeList;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}


