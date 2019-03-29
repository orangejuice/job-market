package mdb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.Serializable;

import static util.JMSUtil.JMS_JNDI;
import static util.JMSUtil.JMS_TYPE;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(
                propertyName = "destination",
                propertyValue = JMS_JNDI),
        @ActivationConfigProperty(
                propertyName = "destinationType",
                propertyValue = JMS_TYPE)
})
public class LogFacilityBean implements MessageListener, Serializable {
    private final Logger log = LoggerFactory.getLogger(LogFacilityBean.class);

    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            log.info("Message received: " + textMessage.getText());
        } catch (JMSException e) {
            log.info("Error while trying to consume messages: " + e.getMessage());
        }
    }
}