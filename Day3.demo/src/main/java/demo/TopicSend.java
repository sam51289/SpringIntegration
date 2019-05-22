package demo;
import java.io.*;
import java.util.*;
import javax.transaction.*;
import javax.naming.*;
import javax.jms.*;
import javax.rmi.PortableRemoteObject;
public class TopicSend
{

public final static String JMS_FACTORY="ConnectionFactory";
public final static String TOPIC="topic/MyTopic";
 
protected TopicConnectionFactory tconFactory;
protected TopicConnection tcon;
protected TopicSession tsession;
protected TopicPublisher tpublisher;
protected Topic topic;
protected TextMessage msg;
 
public void init(Context ctx, String topicName) throws NamingException, JMSException
{
tconFactory = (TopicConnectionFactory) PortableRemoteObject.narrow(ctx.lookup(JMS_FACTORY),TopicConnectionFactory.class);
tcon = tconFactory.createTopicConnection();
tsession = tcon.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
topic = (Topic) PortableRemoteObject.narrow(ctx.lookup(topicName), Topic.class);
tpublisher = tsession.createPublisher(topic);
msg = tsession.createTextMessage();
tcon.start();
}
 
public void send(String message) throws JMSException {
msg.setText(message);
tpublisher.publish(msg);
}
 
public void close() throws JMSException {
tpublisher.close();
tsession.close();
tcon.close();
}
 
public static void main(String[] args) throws Exception {

InitialContext ic =  new InitialContext();
TopicSend ts = new TopicSend();
ts.init(ic, TOPIC);
readAndSend(ts);
ts.close();
}
 
protected static void readAndSend(TopicSend ts)throws IOException, JMSException
{
BufferedReader msgStream = new BufferedReader (new InputStreamReader(System.in));
String line=null;
System.out.println("TopicSender Started ... Enter message ('quit' to quit): n");
do {
System.out.print("Topic Sender Says > ");
line = msgStream.readLine();
if (line != null && line.trim().length() != 0) {
ts.send(line);
}
} while (line != null && ! line.equalsIgnoreCase("quit"));
}
 

}