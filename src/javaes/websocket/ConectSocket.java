package javaes.websocket;
import java.io.IOException;
import java.nio.*;
import javaes.servlet.JWebSocketServlet;
import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.WsOutbound;
/**
 * @author http://javaes.wordpress.com/
 * */
public class ConectSocket extends MessageInbound 
{
	private final String username;
	 
    public ConectSocket(String username)
    {
        this.username = username;
    }
 
    @Override
    protected void onOpen(WsOutbound outbound) 
    {
    	JWebSocketServlet.getConnections().add(this);
        String message = String.format("\"%s\" se conectou.", username);
        JWebSocketServlet.broadcast(message);
    }
 
    @Override
    protected void onBinaryMessage(ByteBuffer arg0) throws IOException 
    {
        throw new RuntimeException("Método não implementado.....");
    }
 
    @Override
    protected void onTextMessage(CharBuffer msg) throws IOException 
    {
        String message = String.format("\"%s\": %s", username, msg.toString());
        JWebSocketServlet.broadcast(message);
    }

}
