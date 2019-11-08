package net.javaguides.usermanagement.web;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class CountUserListener
 *
 */
@WebListener
public class CountUserListener implements HttpSessionListener {
	ServletContext ctx = null;	
	static int current=0;
    /**
     * Default constructor. 
     */
    public CountUserListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub    	
    	current++;
    	ctx = se.getSession().getServletContext();    	
    	ctx.setAttribute("currentusers", current);
    	
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    	current--;
    	ctx.setAttribute("currentusers", current);
    }
	
}
