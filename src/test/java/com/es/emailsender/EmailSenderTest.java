package com.es.emailsender;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.es.emailsender.service.EmailSenderService;

@SpringBootTest
class EmailSenderTest {

	@Autowired
	private EmailSenderService emailSenderService;
	
//	@Test
//	void sendEmailTest()
//	{
//		System.out.println("Running test case 1:  ....");
//		emailSenderService.sendEmail("rutub175@gmail.com", "Running Testcase", "Hello, this is the demo email send project using spring boot.");
//	}
//	
//	@Test
//	void sendEmailToAllTest()
//	{
//		String[] to = {"rutub175@gmail.com","pallaviyedale01@gmail.com"};
//		System.out.println("Running test case 2:  ....");
//		emailSenderService.sendEmail(to, "Testing email sender project", "Hello, this is the demo email send project using spring boot.");
//	}
	
//	@Test
//	void sendEmailWithHtmlContentTest()
//	{
//		System.out.println("Running test case 3: ....");
//		
//		String html = ""
//				+ "<h1 style = 'color : Orange;'>Hello, Good Morning!</h1>";
//		
//		emailSenderService.sendEmailWithHtml("poojabudde84@gmail.com", "Email with HTML content", html);
//	}
	
//	@Test
//	void sendEmailWithFileTest()
//	{
//		System.out.println("Running Test case 4: ....");
//		
//		File myFile = new File("C://Users//LENOVO//OneDrive//Pictures//Saved Pictures/Doggy.jpg");
//		emailSenderService.sendEmailWithFile("rutub175@gmail.com", "Email with File attachement", "Hello, this is an email with an attachement", myFile);
//	}
	
	@Test
	void sendEmailWithStream()
	{
		System.out.println("Running Test case 5: ....");
		
		File myFile = new File("C://Users//LENOVO//OneDrive//Pictures//Saved Pictures/Doggy.jpg");
		try {
			InputStream iStream = new FileInputStream(myFile);
			emailSenderService.sendEmailWithStream("rutub175@gmail.com", "Email using input stream", "Hello, this is an email using input stream", iStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
