import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
/**
 * ������Ϣ���ͺͽ���
 * @author Administrator
 *
 */
public class ActivemqTest extends TestCase {

	//����
	@Test
	public void testJmsTemplateSend() {

		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"/applicationContext-mq.xml");
		JmsTemplate template = (JmsTemplate) ctx.getBean("jmsTemplate");
		Destination destination = (Destination) ctx.getBean("destination");

		template.send(destination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session
						.createTextMessage("������Ϣ��Hello ActiveMQ Text Message��");
			}
		});
		System.out.println("�ɹ�������һ��JMS��Ϣ");
	}
	
	//����
	@Test
	public void testJmsTemplateReceive() throws JMSException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"/applicationContext-mq.xml");
		JmsTemplate template = (JmsTemplate) ctx.getBean("jmsTemplate");
		Destination destination = (Destination) ctx.getBean("destination");
		while (true) {
			TextMessage txtmsg = (TextMessage) template.receive(destination);
			if (null != txtmsg)
				System.out.println("�յ���Ϣ����Ϊ: " + txtmsg.getText());
			else
				break;
		}
	}

}