package com.weixin.util;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.http.ParseException;
import org.junit.Test;

import com.weixin.util.WeixinUtil;
import com.weixin.vo.AccessToken;

/**
 * @author       Jay
 */
public class WechatUtilTest {

	@Test
	public void testGetAccessToken() {
		AccessToken token = WeixinUtil.getAccessToken();
		System.out.println(token.toString());
	}
	
	@Test
	public void testCreateMenu() {
		String menu = JSONObject.fromObject(WeixinMenuUtil.initMenu()).toString();
		
		//String token = "hs5Grd6xNWDAfD_tVOWUMa36NT6X7jYZ5BuQ0dzA2m7MTAHyZCjRENGvxdM5ezgYclHfJ2NvJAAfFE13IAbiZgCC9uBxBIa195bugDgXxBI";
		// WechatUtil.getAccessToken().getAccessToken()
		try {
			int result =  WeixinMenuUtil.createMenu(WeixinUtil.getAccessToken().getAccessToken(), menu);
			if(result == 0){
				System.out.println("init menu success");
			}else{
				System.out.println("init menu fail");
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetMenu() throws ParseException, IOException {
//		AccessToken token = WechatUtil.getAccessToken();
//		System.out.println(token.toString());
		System.out.println(WeixinMenuUtil.getMenu(WeixinUtil.getAccessToken().getAccessToken()));
	}
	
	@Test
	public void testgetUsers() throws ParseException, IOException {
//		AccessToken token = WechatUtil.getAccessToken();
//		System.out.println(token.toString());
//		System.out.println(WechatUtil.getUsers(token.getAccessToken()));
	}
	
	@Test
	public void testgetUser() throws ParseException, IOException {
//		AccessToken token = WechatUtil.getAccessToken();
//		System.out.println(token.toString());
//		System.out.println(WechatUtil.getUser(token.getAccessToken(),"oSyaCuI0o1friUVzMsyvNLWM1oLY"));
	}

	
	@Test
	public void testGet(){
		String obj = WeixinUtil.doGetStr2("http://220.248.75.36/handapp/PGcardAmtServlet?arg1=18499805812&callback=money");
		System.out.println(obj);
	}
	
	@Test
	public void testGetMaterial(){
		WeixinUtil.getMaterial(WeixinUtil.getAccessToken().getAccessToken());
		
	}
	
}
