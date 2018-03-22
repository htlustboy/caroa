package com.caroa.common;

import java.util.Properties;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import com.caroa.constant.Constant;

@Component
public class JavaMailSendImp extends JavaMailSenderImpl{

	public JavaMailSendImp() {
		this.setHost(Constant.Email_SMTPHOST);
		this.setPassword(Constant.Email_PASSWORD);
		this.setUsername(Constant.Email_ACCOUNT);
		this.setProtocol(Constant.Email_Transport_PROTOCOL);
		this.setDefaultEncoding(Constant.Email_DefaultEncode);
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", Constant.Email_Smtp_AUTH);
		properties.put("mail.smtp.timeout", Constant.Email_Smtp_Timeout);
		this.setJavaMailProperties(properties);
	}
}
