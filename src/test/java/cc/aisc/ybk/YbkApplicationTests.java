package cc.aisc.ybk;

import cc.aisc.ybk.content.model.Menu;
import cc.aisc.ybk.content.model.User;
import cc.aisc.ybk.content.service.MenuService;
import cc.aisc.ybk.content.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = YbkApplication.class)
@WebAppConfiguration
public class YbkApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(YbkApplicationTests.class);

	@Autowired
	private UserService userService;

	@Autowired
	private MenuService menuService;

	@Test
	public void test() {
		/*List<Menu> list = new ArrayList<>();
		Menu m1 = new Menu(1, 1, 0, true, 1001, "root", "root menu", null);
		Menu m2 = new Menu(2, 2, 1, true, 1010, "menu1", "menu1 menu", 1);
		Menu m3 = new Menu(3, 2, 2, true, 1020, "menu2", "menu2 menu", 1);
		Menu m4 = new Menu(4, 2, 3, true, 1030, "menu3", "menu3 menu", 1);
		Menu m5 = new Menu(5, 2, 4, true, 1040, "menu4", "menu4 menu", 1);
		Menu m6 = new Menu(6, 2, 5, true, 1050, "menu5", "menu5 menu", 1);
		Menu m7 = new Menu(7, 2, 6, true, 1060, "menu6", "menu6 menu", 1);
		Menu m8 = new Menu(8, 2, 7, true, 1070, "menu7", "menu7 menu", 1);
		Menu m9 = new Menu(9, 2, 8, true, 1080, "menu8", "menu8 menu", 1);
		Menu m10 = new Menu(10, 2, 9, true, 1090, "menu9", "menu9 menu", 1);
		Menu m11 = new Menu(11, 2, 10, true, 1100, "menu10", "menu10 menu", 1);
		Menu m12 = new Menu(12, 3, 0, true, 1012, "root", "root menu", 2);
		Menu m13 = new Menu(13, 3, 0, true, 1013, "root", "root menu", 2);
		Menu m14 = new Menu(14, 3, 0, true, 1014, "root", "root menu", 2);
		Menu m15 = new Menu(15, 3, 0, true, 1021, "root", "root menu", 3);
		Menu m16 = new Menu(16, 3, 0, true, 1022, "root", "root menu", 3);
		Menu m17 = new Menu(17, 3, 0, true, 1023, "root", "root menu", 3);
		Menu m18 = new Menu(18, 3, 0, true, 1024, "root", "root menu", 3);
		Menu m19 = new Menu(19, 3, 0, true, 1042, "root", "root menu", 5);
		Menu m20 = new Menu(20, 3, 0, true, 1052, "root", "root menu", 6);
		Menu m21 = new Menu(21, 3, 0, true, 1062, "root", "root menu", 7);
		Menu m22 = new Menu(22, 3, 0, true, 1063, "root", "root menu", 7);
		Menu m23 = new Menu(23, 3, 0, true, 1071, "root", "root menu", 8);
		Menu m24 = new Menu(24, 3, 0, true, 1081, "root", "root menu", 9);
		Menu m25 = new Menu(25, 3, 0, true, 1082, "root", "root menu", 9);
		list.add(m1);
		list.add(m2);
		list.add(m3);
		list.add(m4);
		list.add(m5);
		list.add(m6);
		list.add(m7);
		list.add(m8);
		list.add(m9);
		list.add(m10);
		list.add(m11);
		list.add(m12);
		list.add(m13);
		list.add(m14);
		list.add(m15);
		list.add(m16);
		list.add(m17);
		list.add(m18);
		list.add(m19);
		list.add(m20);
		list.add(m21);
		list.add(m22);
		list.add(m23);
		list.add(m24);
		list.add(m25);
		for (Menu menu : list){
			menuService.insert(menu);
		}*/
	}

	@Test
	public void test1(){
/*		User user = new User(3, "username", "password", "nickname", "13800138000");

		userService.create(user);*/

		User u = userService.findById(1);

		log.debug("u={}",u.toString());

	}

}
