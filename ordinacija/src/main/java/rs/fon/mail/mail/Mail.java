package rs.fon.mail.mail;



import javax.mail.MessagingException;



public class Mail {

//	IMAPAccess imapAccess = new IMAPAccess();
//	imapAccess.setHost("imap.zoho.com");
//	imapAccess.setMailStoreType("imap");
//	imapAccess.setUsername("human_test1@zoho.com");
//	imapAccess.setPassword("human123451");
	
	public static void mailReceiver(IMAPAccess imapAccess) {
		MailReceiver.check(imapAccess);
	}

//	public static void mailSender(TicketMessage message) {
//		try {
//			MailSender.sendMessage(message);
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		}
//	}
	

}
