package com.weixin.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.weixin.util.TokenThread;

/**
 * @author Jay
 */
public class InitServlet extends HttpServlet {
	
	private static final long serialVersionUID = -6319898826127265611L;

	private static Logger log = LogManager.getLogger(InitServlet.class);
	
	public void init() throws ServletException {

		TokenThread.APPID = getInitParameter("APPID");
		TokenThread.APPSECRET = getInitParameter("APPSECRET");
		
		log.info("appid:" + TokenThread.APPID);
		log.info("appsecret:" + TokenThread.APPSECRET);
		
		// start token thread
		new Thread(new TokenThread()).start();
		
//		String prefix = getServletContext().getRealPath("/");  
//        String file = getInitParameter("log4j");  
//        if (file != null) {  
//            PropertyConfigurator.configure(prefix + file);  
//        }  
		
		
	}
}
