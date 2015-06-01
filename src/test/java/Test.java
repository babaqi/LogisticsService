import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.glodon.model.User;
import com.glodon.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml",
		"classpath:mybatis-conf.xml" })
public class Test {

	@Autowired
	private UserService userService;

	@org.junit.Test
	public void testRegister() {
		User user = new User();
//		user.setUsername("1234");
//		user.setPassword("4567");
		String a = "{'userName':'3333','userPwd':'4444'}";
//		userService.register(a);
	}

}
