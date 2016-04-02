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
	public static final String SMTP_PORT = "25";
	public static final String MAIL_DOMAIN = "splitemapp.com";

	/**
	 * Sends the email to the appropriate account
	 * @param user
	 * @param content
	 */
	public static void sendMail(final String username, final String password, String subject, String fromAddress, String content){
		Properties props = new Properties();
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.debug", "true");
//		props.put("mail.transport.protocol", "smtps");
//		props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.smtp.ssl.enable", "true");
//		props.put("mail.smtp.ssl.trust", MAIL_DOMAIN);
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
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(username+"@"+MAIL_DOMAIN));
			message.setSubject(subject);
			message.setText(content);

			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
}
