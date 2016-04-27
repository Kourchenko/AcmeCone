package acme.com.acmecone.Adapters;


import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class GMailAuthenticator extends Authenticator {
    String user;
    String password;

    public GMailAuthenticator(String user, String password) {
        super();
        this.user = user;
        this.password = password;
    }

    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(user, password);
    }
}
