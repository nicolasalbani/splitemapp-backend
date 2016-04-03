package com.splitemapp.commons.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtils {

	public static final String SMTP_HOST = "ec2-52-38-125-216.us-west-2.compute.amazonaws.com";
	public static final String SMTP_PORT = "587";
	public static final String MAIL_DOMAIN = "splitemapp.com";

	/**
	 * Sends the email to the appropriate account
	 * @param username
	 * @param password
	 * @param subject
	 * @param toAddress
	 * @param fromAddress
	 * @param content
	 */
	public static void sendMail(final String username, final String password, String subject, String toAddress, String fromAddress, String content){
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", SMTP_HOST);
		props.put("mail.smtp.port", SMTP_PORT);

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromAddress));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
			message.setSubject(subject);
			message.setContent(content, "text/html");

			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
