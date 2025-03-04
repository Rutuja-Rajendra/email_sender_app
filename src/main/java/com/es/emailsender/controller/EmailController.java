package com.es.emailsender.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.es.emailsender.pojo.CustomeResponse;
import com.es.emailsender.pojo.EmailRequest;
import com.es.emailsender.service.EmailSenderService;

@RestController
@RequestMapping("api/emailsender")
public class EmailController {

	private EmailSenderService emailSenderService;
	
	public EmailController(EmailSenderService emailSenderService) {
		super();
		this.emailSenderService = emailSenderService;
	}

	@PostMapping("/send")
	public ResponseEntity<?>sendEmail(@RequestBody EmailRequest request)
	{
		emailSenderService.sendEmail(request.getTo(), request.getSubject(), request.getMessage());
		return ResponseEntity.ok(
				CustomeResponse.builder().message("Email Send Successfully! ").httpStatus(HttpStatus.OK).isSuccess(true).build());
	}
	
	@PostMapping("/send-with-file")
	public ResponseEntity<CustomeResponse>sendEmailWithFile(@RequestPart EmailRequest request, @RequestPart MultipartFile file) throws IOException
	{
		emailSenderService.sendEmailWithStream(request.getTo(), request.getSubject(), request.getMessage(), file.getInputStream());
		return ResponseEntity.ok(
				CustomeResponse.builder().message("Email Send Successfully! ").httpStatus(HttpStatus.OK).isSuccess(true).build());
	}
	
}
