package com.weixin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.DocumentException;

import com.weixin.util.CheckUtil;
import com.weixin.util.MessageUtil;
import com.weixin.util.TokenThread;
import com.weixin.util.WeixinUtil;

/**
 *  @author   Jay
 */
public class WeixinServlet extends HttpServlet{

	private static final long serialVersionUID = -2836724667530087285L;

	private static Logger log = LogManager.getLogger(InitServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("验证");
		String signature = request.getParameter("signature");	
		String timestamp = request.getParameter("timestamp");	
		String nonce = request.getParameter("nonce");	
		String echostr = request.getParameter("echostr");	
		PrintWriter out =  response.getWriter();
		if(CheckUtil.checkSignature(signature, timestamp, nonce)){
			out.print(echostr);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out =  response.getWriter();
		try {
			Map<String, String> map = MessageUtil.xmlToMap(request);
			
			log.info(JSONObject.fromObject(map).toString());
			
			String toUserName = map.get("ToUserName");
			String fromUserName = map.get("FromUserName");
			String createTime = map.get("CreateTime");
			String msgType = map.get("MsgType");
			String content = map.get("Content");
			String msgId = map.get("MsgId");
			System.out.println(content);
			String message = null;
			
			String nickName = "";//WechatUtil.getUser( WechatUtil.getAccessToken().getAccessToken(), fromUserName).getString("nickname");
			
			String msg = nickName;
			
			if("text".equals(msgType)){
				if("hi".equals(content)){
					msg +=  " hi";
				}else if("你好".equals(content)){
					msg += " 你也好☺";
				}else if(content.contains("公交卡")){
					String result = WeixinUtil.doGetStr2("http://220.248.75.36/handapp/PGcardAmtServlet?arg1="+ content.replaceAll("[^x00-xff]*", ""));
					result = result.substring(6,result.length()-3);
					msg += "公交卡余额" + result;
				}else if(content.contains("123")){
					//msg += " 页面跳转 <a href='http://scorpioner.xicp.net/wechat/index.html?id="+ fromUserName 
					//		+" &token= "+ WechatUtil.getAccessToken().getAccessToken()+"&nickName="+  nickName +"'>跳转</a>" ;
					System.out.println(msg);
				}else{
					msg += "OMG!!!";
					log.info(msg);
				}
				message = MessageUtil.initText(toUserName, fromUserName, msg);
			}else if("event".equals(msgType) && map.get("Event").equals("CLICK")){
			
				msg += "您好！您还没有绑定，请先绑定！ <a href='https://open.weixin.qq.com/connect/oauth2/authorize?appid="+ TokenThread.APPID +"&redirect_uri=http://weixin.ngrok.io/weixin/oauth.do&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect'>绑定</a>";
				//msg += "您好！您还没有绑定，请先绑定！ <a href='https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx8dfda79a073efa18&redirect_uri=http%3A%2F%2Fwind%2Engrok%2Eio%2Fwechat%2FoauthServlet&response_type=code&scope=snsapi_base&state=123#wechat_redirect'>绑定</a>";
				//msg += " <a href='https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx520c15f417810387&redirect_uri=http%3A%2F%2Fchong.qq.com%2Fphp%2Findex.php%3Fd%3D%26c%3DwxAdapter%26m%3DmobileDeal%26showwxpaytitle%3D1%26vb2ctag%3D4_2030_5_1194_60&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect'>click base</a>";
				//msg += " <a href='https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx520c15f417810387&redirect_uri=http%3A%2F%2Fchong.qq.com%2Fphp%2Findex.php%3Fd%3D%26c%3DwxAdapter%26m%3DmobileDeal%26showwxpaytitle%3D1%26vb2ctag%3D4_2030_5_1194_60&response_type=code&scope=snsapi_base&state=123#wechat_redirect'>click</a>";
				log.info(msg);
				message = MessageUtil.initText(toUserName, fromUserName, msg);
			
			}else if("location".equals(msgType)){// 地址
				msg += "您的地址是：" + map.get("Label");
				message = MessageUtil.initText(toUserName, fromUserName, msg);
			}
			
			log.info("token: "+TokenThread.accessToken.getAccessToken());
			
			out.print(message);
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
	}
	
}
