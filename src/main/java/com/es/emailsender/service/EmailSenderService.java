package com.es.emailsender.service;

import java.io.File;
import java.io.InputStream;

public interface EmailSenderService {

	//Send email to single person
	void sendEmail(String to, String subject, String message);
	
	//Send email to multiple person
	void sendEmail(String[] to, String subject, String message);
	
	//Send email with HTML
	void sendEmailWithHtml(String to, String subject, String htmlContent);
	
	//Send email with file
	void sendEmailWithFile(String to, String subject, String message, File file);
	
	//Send email with stream
	void sendEmailWithStream(String to, String subject, String message, InputStream iStream);
}
