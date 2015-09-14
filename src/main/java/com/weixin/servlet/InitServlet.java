package com.weixin.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import com.weixin.util.TokenThread;
import com.weixin.util.WeixinUtil;

/**
 * @author Jay
 */
public class InitServlet extends HttpServlet {
	
	private static final long serialVersionUID = -6319898826127265611L;
	private static Logger log = Logger.getLogger(WeixinUtil.class);

	public void init() throws ServletException {

		TokenThread.APPID = getInitParameter("APPID");
		TokenThread.APPSECRET = getInitParameter("APPSECRET");

		log.info("weixin api appid:{}" + TokenThread.APPID);
		log.info("weixin api appsecret:{}" + TokenThread.APPSECRET);

		// 未配置appid、appsecret时给出提示
		if ("".equals(TokenThread.APPID) || "".equals(TokenThread.APPSECRET)) {
			log.error("appid and appsecret configuration error, please check carefully.");
		} else {
			// 启动定时获取access_token的线程
			new Thread(new TokenThread()).start();
		}
	}
}
