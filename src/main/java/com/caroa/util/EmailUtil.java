package com.caroa.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.caroa.constant.Constant;

/**
 * 邮箱工具
 * @author hutao
 *
 */
public class EmailUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(EmailUtil.class);
	
	public static Properties prop = new Properties();
	
	static {
		prop.setProperty("mail.transport.protocol", Constant.Email_Transport_PROTOCOL);
		prop.setProperty("mail.smtp.host", Constant.Email_SMTPHOST);
		prop.setProperty("mail.smtp.auth", Constant.Email_Smtp_AUTH);
	}
	
	//发送邮件
	public static void sendEmail(String toAccount,String username,String content){
		Session session = Session.getDefaultInstance(prop);
		session.setDebug(true);
		try {
			//创建邮件
			MimeMessage message = createEmailMessage(session,Constant.Email_ACCOUNT,toAccount,username,content);
			//获取传输对象
			Transport transport = session.getTransport();
			//测试连接
			transport.connect(Constant.Email_ACCOUNT, Constant.Email_PASSWORD);
			//发送
			transport.sendMessage(message, message.getAllRecipients());
			//关闭资源
			transport.close();
			logger.error("邮件发送成功......................");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(new Date() + ":邮件发送失败！");
		}
	}
	
	//创建邮件
	private static MimeMessage createEmailMessage(Session session, String emailAccount, String toAccount,
			String username,String content) throws Exception {
		
		MimeMessage message = new MimeMessage(session);
		//发件人
		message.setFrom(new InternetAddress(emailAccount, "lustboy", "UTF-8"));
		//收件人
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(toAccount, username, "UTF-8"));
		//主题
		message.setSubject("密码找回激活码");
		//正文
		message.setContent(content,"text/html;charset=UTF-8");
		//发送时间
		message.setSentDate(new Date());
		//保存
		message.saveChanges();
		return message;
	}
}
