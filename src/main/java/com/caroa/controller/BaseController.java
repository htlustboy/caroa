package com.caroa.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.caroa.common.JavaMailSendImp;
import com.caroa.constant.Constant;

@ControllerAdvice
public class BaseController implements EnvironmentAware{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected Environment environment;
	
	@Autowired
	protected JavaMailSendImp javaMailSender;
	
	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.METHOD})
	public @interface FormData{
		boolean save() default false;
		boolean remove() default false;
	}
	
	   /** 取得HttpServletRequest */
    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    /** 取得HttpServletResponse */
    public HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }
    
    protected static class R{
    	public static final String LAYOUT_PAGE = "layout";
    	public static final String REDIRECT_PAGE = "redirect";
    	
    	public static String view(String viewPath){
    		return viewPath;
    	}
    	
	    public static String view(String viewPath, boolean isRediredt) {
           return String.format("%s%s", isRediredt ? "redirect:" : "", viewPath);
        }
    }
    
    //=======================================================================================
    
    //文件输出
    public Object readFile(String filePath){
    	StringBuilder sb = new StringBuilder("");
    	  File file = new File(filePath);
          BufferedReader reader = null;
          try {
              reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "gbk"));
              String tempString = null;
              while ((tempString = reader.readLine()) != null) {
                  sb.append(tempString+"<br>");
              }
              reader.close();
          } catch (IOException e) {
              e.printStackTrace();
          } finally {
              if (reader != null) {
                  try {
                      reader.close();
                  } catch (IOException e1) {
                	  logger.warn(e1.getMessage());
                  }
              }
          }
          return sb.toString();
    }
    
    //文件下载
    public ResponseEntity<byte[]> download(String filePath){
    	filePath = filePath+"111";
    	File file = new File(filePath);
    	if(file.exists() && file.isFile()){
    		HttpHeaders headers = new HttpHeaders();
        	headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        	headers.setContentDispositionFormData("attachment", filePath);
        	try {
    			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File(filePath)),headers, HttpStatus.CREATED);
    		} catch (IOException e) {
    			e.printStackTrace();
    			logger.error(e.getMessage());
    			return null;
    		}
    	}else{
    		try {
				throw new Exception(filePath+" 文件不存在");
			} catch (Exception e) {
				e.printStackTrace();
			}
    		return null;
    	}
    }
    
    /**
     * 发送普通邮件
     * @param sendTo 接收人
     * @param content 正文
     */
    public void sendMail(String sendTo,String content){
    	SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(Constant.Email_ACCOUNT);
		message.setTo(sendTo);
		message.setSubject("密码找回");
		message.setText(content);
		message.setSentDate(new Date());
		javaMailSender.send(message);
		logger.info("邮件发送成功...");
    }
    
    /**
     * 带附件的邮件发送
     * @param sendTo
     * @param content
     * @param filePath
     */
    public void sendAttachmentEmail(String sendTo,String content,String filePath){
		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom("hutao1187@163.com");
			helper.setTo(sendTo);
			helper.setSubject("带附件的邮件");
			helper.setText(content);
			helper.setSentDate(new Date());
			//添加附件
			FileSystemResource file = new FileSystemResource(filePath);
			helper.addAttachment(file.getFilename(), file);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		javaMailSender.send(message);
		logger.info("邮件发送成功...");
	}
    //=======================================================================================
    
    //异常页面处理,500
    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView processException(RuntimeException exception){
    	ModelAndView mav = new ModelAndView();
    	mav.addObject("message", exception.getMessage());
    	mav.setViewName("/common/500");
    	return mav;
    }
    
    //异常页面处理,404
    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView NotFoundException(RuntimeException exception){
    	ModelAndView mav = new ModelAndView();
    	mav.addObject("message", exception.getMessage());
    	mav.setViewName("/common/404");
    	return mav;
    }
}
