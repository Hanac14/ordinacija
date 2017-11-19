package rs.fon.mail.mail;

public class IMAPAccess {

	private String host;
	private String mailStoreType;
	private String username;
	private String password;
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getMailStoreType() {
		return mailStoreType;
	}
	public void setMailStoreType(String mailStoreType) {
		this.mailStoreType = mailStoreType;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "IMAPAccess [host=" + host + ", mailStoreType=" + mailStoreType + ", username=" + username
				+ ", password=" + password + "]";
	}
			
}
