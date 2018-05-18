package du_web;

import java.io.Serializable;
import java.util.List;

/**
 * @author 爱不留
 * @date 2018年5月10日 下午2:13:54
 **/
public class CallRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String registry;
	private String interfaceName;
	private String method;
	private List<Object> param;
	private String version;
	private String group;

	public String getRegistry() {
		return registry;
	}

	public void setRegistry(String registry) {
		this.registry = registry;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public List<Object> getParam() {
		return param;
	}

	public void setParam(List<Object> param) {
		this.param = param;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	@Override
	public String toString() {
		return "CallRequest{" + "interfaceName='" + interfaceName + '\'' + ", method='" + method + '\'' + ", param=" + param + ", registry='" + registry + '\'' + ", version='" + version + '\'' + '}';
	}
}