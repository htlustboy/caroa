package test;


import org.junit.Test;

import com.caroa.util.BaseUtil;
import com.caroa.util.EmailUtil;

public class UtilTest {

	@Test
	public void testEmail(){
		
		EmailUtil.sendEmail("838533527@qq.com", "hutao", "邮件测试");
	}
	
	@Test
	public void testUUID(){
		System.out.println(BaseUtil.getRamdomUUID(6));
	}
}
