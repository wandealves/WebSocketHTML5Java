package javaes.servlet;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import org.apache.catalina.websocket.*;

import javaes.websocket.ConectSocket;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
/**
 * @author http://javaes.wordpress.com/
 * */
@WebServlet("/websocket")
public class JWebSocketServlet extends WebSocketServlet
{
	private static final long serialVersionUID = 1L;
    
    private static final List<ConectSocket> connections = new ArrayList<ConectSocket>();
    @Override
    protected StreamInbound createWebSocketInbound(String subProtocol,HttpServletRequest request) 
    {
        String username = request.getParameter("username");
        return new ConectSocket(username);
    }
     
    public static final List<ConectSocket> getConnections(){
        return connections;
    }
     
    public static final void broadcast(String message)
    {
        for (ConectSocket con : JWebSocketServlet.getConnections()) 
        {
            try {
                con.getWsOutbound().writeTextMessage( CharBuffer.wrap(message)  ) ;
                System.out.println("Enviando mensagem de texto ("  + message + ")");
            } catch (IOException ioe) {
                System.out.println("Aconteceu um erro");
            }
        }
    }
 
}
