package rs.fon.mail.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

public class MailSender {

	private static final String SMTP_HOST_NAME = "smtp.zoho.com";
	private static final String SMTP_PORT = "465";
	private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

	private static final String USERNAME = "human_test1@zoho.com";
	private static final String PASSWORD = "human123451";

	public static void sendMessage(String message ) throws MessagingException {

		boolean debug = true;

		Properties props = new Properties();
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.smtp.port", SMTP_PORT);
		props.put("mail.smtp.socketFactory.port", SMTP_PORT);
		props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.put("mail.smtp.socketFactory.fallback", "false");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USERNAME, PASSWORD);
			}
		});

		session.setDebug(debug);
		Message msg = new MimeMessage(session);
		InternetAddress addressFrom = new InternetAddress("sasa@teodesk.com");
		msg.setFrom(addressFrom);

		InternetAddress addressTo = new InternetAddress("mrhanac@gmail.com");
		
		msg.setRecipient(Message.RecipientType.TO, addressTo);

		// Setting the Subject and Content Type
		msg.setSubject("sdsffds");
		msg.setContent("dsffdsdfsdfs", "text/html");
		Transport.send(msg);
	}

	
}
