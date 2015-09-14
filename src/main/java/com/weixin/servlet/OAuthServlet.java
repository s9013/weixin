package com.weixin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weixin.util.TokenThread;
import com.weixin.util.WeixinUtil;
import com.weixin.vo.Oauth2Token;
import com.weixin.vo.UserInfo;

/**
 * 
 * @author jay
 *
 */
public class OAuthServlet extends HttpServlet {

	private static final long serialVersionUID = -1847238807216447030L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		  
		String code = request.getParameter("code");
		  if (!"authdeny".equals(code)) {
			   System.out.println(code);	  
			   Oauth2Token oauth2Token = WeixinUtil.getOauth2AccessToken(code);
	
			   String openId = oauth2Token.getOpenId();
			   UserInfo userInfo = WeixinUtil.getUser(TokenThread.accessToken.getAccessToken(), openId);
			   
			   request.setAttribute("UserInfo", userInfo);
		  }
		  request.getRequestDispatcher("userInfo.jsp").forward(request, response);
		}
}