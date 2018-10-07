package com.bonc.test.publish;

import java.io.IOException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bonc.service.ItemService;

public class PublishServiceTest {
	/**
	 * 不使用tomcat 发布服务
	 * @throws IOException 
	 * 
	 * classpath：只会到你的class路径中查找找文件。

classpath*：不仅包含class路径，还包括jar文件中（class路径）进行查找。

注意： 用classpath*:需要遍历所有的classpath，所以加载速度是很慢的；因此，在规划的时候，应该尽可能规划好资源文件所在的路径，尽量避免使用classpath*。

classpath*的使用：

当项目中有多个classpath路径，并同时加载多个classpath路径下（此种情况多数不会遇到）的文件，*就发挥了作用，如果不加*，则表示仅仅加载第一个classpath路径。
	 */
	@Test
	public void publish() throws IOException {
		/*ApplicationContext app  = new ClassPathXmlApplicationContext("classpath*:applicationContext-*.xml");
		Object bean = app.getBean(ItemService.class);
		System.out.println(bean);
		System.out.println("服务已经启动");
		System.in.read();
		System.out.println("服务终止");*/
	}

}
