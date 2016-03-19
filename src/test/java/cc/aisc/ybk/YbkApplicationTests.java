package cc.aisc.ybk;

import cc.aisc.ybk.content.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = YbkApplication.class)
@WebAppConfiguration
public class YbkApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void test1(){
		User user = new User();

	}

}
