package du_web;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.demo.DemoService;
import com.alibaba.dubbo.demo.bean.Valid;
import com.alibaba.dubbo.demo.model.City;
import com.alibaba.dubbo.demo.model.Person;

/**
* @author 爱不留   
* @date 2018年5月9日 下午2:53:08 
**/
public class DemoServiceStub implements DemoService { 
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
    private final DemoService demoService;
    // 构造函数传入真正的远程代理对象
    
    public DemoServiceStub(DemoService demoService) {
        this.demoService = demoService;
    }
    
    public String sayHello(String name) {
        // 此代码在客户端执行, 你可以在客户端做ThreadLocal本地缓存，或预先验证参数是否合法，等等
        try {
        	System.out.println("pre invoke, valid the parameter");
            return demoService.sayHello(name);
        } catch (Exception e) {
            // 你可以容错，可以做任何AOP拦截事项
            return "容错数据";
        }
    }

	@Override
	public Valid submitPersons(String token, Date date, City city, List<Person> persons) {
		// 此代码在客户端执行, 你可以在客户端做ThreadLocal本地缓存，或预先验证参数是否合法，等等
        try {
        	System.out.println("pre invoke, valid the parameter");
            return demoService.submitPersons(token, date, city, persons);
        } catch (Exception e) {
            // 你可以容错，可以做任何AOP拦截事项
            return Valid.FAILED;
        }
	}
}