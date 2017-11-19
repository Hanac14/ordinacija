package rs.fon.mail.mail;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

public class MailReceiver {

	public static void check(IMAPAccess imapAccess) {
		try {

			Properties properties = new Properties();

			properties.put("mail.imap.host", imapAccess.getHost());
			properties.put("mail.imap.port", "993");
			properties.put("mail.imap.starttls.enable", "true");
			Session emailSession = Session.getDefaultInstance(properties);

			// create the POP3 store object and connect with the pop server
			Store store = emailSession.getStore("imaps");

			store.connect(imapAccess.getHost(), imapAccess.getUsername(), imapAccess.getPassword());

			// create the folder object and open it
			Folder emailFolder = store.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);

			// retrieve the messages from the folder in an array and print it
			Message[] messages = emailFolder.getMessages();
			System.out.println("messages.length---" + messages.length);

			for (int i = 0, n = messages.length; i < n; i++) {
				Message message = messages[i];
				System.out.println("------------------------------------------------------------");
				System.out.println("Email Number " + (i + 1));
				System.out.println("Subject: " + message.getSubject());
				System.out.println("From: " + message.getFrom()[0]);
				System.out.println("Text: " + message.getContent().toString());

			}

			emailFolder.close(false);
			store.close();

		} catch (NoSuchProviderException e1) {
			e1.printStackTrace();
		} catch (MessagingException e2) {
			e2.printStackTrace();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
	}
}
