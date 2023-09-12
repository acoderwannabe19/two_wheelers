package sn.ept.git.dic2.projetjee;

import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.Schedule;
import jakarta.inject.Inject;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import sn.ept.git.dic2.projetjee.entities.Stock;
import sn.ept.git.dic2.projetjee.facades.StockFacade;

import java.util.List;
import java.util.Properties;

@Singleton
@Startup
public class PeriodicEmail {
    @Inject
    private StockFacade stockFacade;

    private final String to = "salanendeyea@ept.sn";
    private final String from = "dic2-2023@galgit.com";
    private final String username = "dic2-2023@galgit.com";
    private final String password = "sn-ept@GIT2024";
    private final String host = "mail.galgit.com";

//    @Schedule(second = "0", minute = "*/1", hour = "*", persistent = false)
    public void sendStocksReport() {
        try {
            // Fetch the stock data
            List<Stock> stocks = stockFacade.findAll();

            // Create the email content
            StringBuilder messageText = new StringBuilder();
            messageText.append("Stock Report:\n\n");
            for (Stock stock : stocks) {
                messageText.append("Product: ").append(stock.getProduit().getNom())
                        .append(", Store: ").append(stock.getMagasin().getNom())
                        .append(", Quantity: ").append(stock.getQuantite())
                        .append("\n");
            }

            // Set up the JavaMail properties
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "25");

            // Get the Session object
            Session session = Session.getInstance(props,
                    new jakarta.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            // Create a MimeMessage object
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Stock Report");
            message.setText(messageText.toString());

            // Send the message
            Transport.send(message);

            System.out.println("Stock report sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send stock report", e);
        }
    }
}

