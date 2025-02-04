package com.es.emailsender.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.es.emailsender.service.EmailSenderService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

	private JavaMailSender javaMailSender;

	public EmailSenderServiceImpl(JavaMailSender javaMailSender) {
		super();
		this.javaMailSender = javaMailSender;
	}
	
	private org.slf4j.Logger logger = LoggerFactory.getLogger(EmailSenderServiceImpl.class);


	@Override
	public void sendEmail(String to, String subject, String message) {
		
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom("rutuvrb@gmail.com");
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(message);
		
		javaMailSender.send(simpleMailMessage);
		
		logger.info("Email has been send!");
	}

	@Override
	public void sendEmail(String[] to, String subject, String message) {
		
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom("rutuvrb@gmail.com");
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(message);
		
		javaMailSender.send(simpleMailMessage);
		
		logger.info("Email has been send to all!");
	}

	@Override
	public void sendEmailWithHtml(String to, String subject, String htmlContent) {
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			mimeMessageHelper.setFrom("rutuvrb@gmail.com");
			mimeMessageHelper.setTo(to);
			mimeMessageHelper.setSubject(subject);
			mimeMessageHelper.setText(htmlContent, true);
			
			javaMailSender.send(mimeMessage);
			
			logger.info("Email with HTML contenet has been send!");
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void sendEmailWithFile(String to, String subject, String message, File file) {
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		
		MimeMessageHelper mimeMessageHelper;
		try {
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			
			mimeMessageHelper.setFrom("rutuvrb@gmail.com");
			mimeMessageHelper.setTo(to);
			mimeMessageHelper.setSubject(subject);
			mimeMessageHelper.setText(message);
			
			FileSystemResource fileSystemResource = new FileSystemResource(file);
			mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), file);
			
			javaMailSender.send(mimeMessage);
			logger.info("Email with file has been send!");
		} catch (MessagingException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	
	@Override
	public void sendEmailWithStream(String to, String subject, String message, InputStream iStream) {
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
			
			mimeMessageHelper.setFrom("rutuvrb@gmail.com");
			mimeMessageHelper.setTo(to);
			mimeMessageHelper.setSubject(subject);
			mimeMessageHelper.setText(message);
			
			File newFile = new File("src/main/resources/HelperFolder/test.jpg");
			Files.copy(iStream, newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			FileSystemResource fileSystemResource = new FileSystemResource(newFile);
			
			mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), newFile);
			
			javaMailSender.send(mimeMessage);
			logger.info("Email with stream has been send!");
			
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
