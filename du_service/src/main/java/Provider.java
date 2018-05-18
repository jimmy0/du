import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 爱不留
 * @date 2018年5月8日 下午2:31:37
 **/
public class Provider {
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "provider.xml" });
		context.start();
		System.out.println("启动");
		System.in.read(); // 按任意键退出
	}
}
