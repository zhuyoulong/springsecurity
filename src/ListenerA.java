import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

public class ListenerA implements MessageListener {

	public void onMessage(Message message)  {
//		if (message instanceof TextMessage) {
//			TextMessage text = (TextMessage) message;
//			try {
//				System.out.println("Receive:" + text.getText());
//			} catch (JMSException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		System.out.println("onMessage------------");
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"/applicationContext-mq.xml");
		JmsTemplate template = (JmsTemplate) ctx.getBean("jmsTemplate");
		Destination destination = (Destination) ctx.getBean("destination");
		while (true) {
			TextMessage txtmsg = (TextMessage) template.receive(destination);
			if (null != txtmsg)
				try {
					System.out.println("收到消息内容为: " + txtmsg.getText());
				} catch (Exception e) {
					e.printStackTrace();
				}
			else
				break;
		}
	}
}