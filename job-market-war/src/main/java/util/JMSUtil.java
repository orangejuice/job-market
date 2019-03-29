package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

public class JMSUtil {
    public final static String JMS_CONNECTION_FACTORY_JNDI = "jms/SimpleConnectionFactory";
    public final static String JMS_JNDI = "marketQueue";
    public final static String JMS_TYPE = "javax.jms.Queue";

    private final static Logger log = LoggerFactory.getLogger(JMSUtil.class);

    public static void sendMessage(String text) {
        try {
            Context ic = new InitialContext();
            ConnectionFactory cf = (ConnectionFactory) ic.lookup(JMS_CONNECTION_FACTORY_JNDI);
            Queue queue = (Queue) ic.lookup("jms/" + JMS_JNDI);
            Connection connection = cf.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer publisher = session.createProducer(queue);

            connection.start();

            TextMessage message = session.createTextMessage(text);
            publisher.send(message);
        } catch (NamingException | JMSException e) {
            log.info("Error while trying to send {} message: {}", text, e.getMessage());
        }
        log.info("Message sent: {}", text);
    }
}
