package com.weixin.util;

import org.apache.log4j.Logger;

import com.weixin.vo.AccessToken;

/**
 * @author       Jay
 */
public class TokenThread implements Runnable{
	
	private static Logger log = Logger.getLogger(TokenThread.class); 
	
    public static String APPID = "wxf72b2d5b2c0038d4";    
    public static String APPSECRET = "a661c50ea12ef2d43476fc4485b66468";
    public static AccessToken accessToken = null;    
    
    public void run() {    
        while (true) {    
        	log.info("get token");
            try {    
                accessToken = WeixinUtil.getAccessToken();  
                if (null != accessToken) {    
                    log.info(("get access_token success" + accessToken.getExpiresIn() + accessToken.getAccessToken()));   
                    // sleep 7000s    
                    Thread.sleep((accessToken.getExpiresIn() - 200) * 1000);    
                } else {    
                    // if access_token is null，60s will get again    
                    Thread.sleep(60 * 1000);    
                }    
            } catch (InterruptedException e) {    
                try {    
                    Thread.sleep(60 * 1000);    
                } catch (InterruptedException e1) {    
                    log.error("{}", e1);    
                }    
                log.error("{}", e);    
            }    
        }    
    }    
}
