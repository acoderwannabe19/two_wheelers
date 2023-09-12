package sn.ept.git.dic2.projetjee;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;


import java.util.Properties;

public class ServerEventListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Server startup logic
        sendEmail("started");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Server shutdown logic
        sendEmail("stopped");
    }

    private void sendEmail(String status) {
        // Recipient's email ID needs to be mentioned.
        String to = "salanendeyea@ept.sn";

        // Sender's email ID needs to be mentioned
        String from = "dic2-2023@galgit.com";
        final String username = "dic2-2023@galgit.com";//change accordingly
        final String password = "sn-ept@GIT2024";//change accordingly

        // Assuming you are sending email through relay.jangosmtp.net
        String host = "mail.galgit.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "25");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("GlassFish Server Status");

            // Set the actual message
            message.setText("GlassFish server is " + status);

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
