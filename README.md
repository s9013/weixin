#微信开发
[测试帐号申请](http://mp.weixin.qq.com/debug/cgi-bin/sandbox?t=sandbox/login)
微信帐号扫一下就可以了

![微信登录](http://i.imgur.com/jIH1EoT.png)


配置
启动服务器
token对 来联通服务器



##功能
上海公交卡余额查询
http://220.248.75.36/handapp/PGcardAmtServlet?arg1=18499805812&callback=money


##问题
网页授权获取用户基本信息
http://mp.weixin.qq.com/wiki/17/c0f37d5704f0b64713d5d2c37b468d75.html

	关于网页授权的两种scope的区别说明
	
	1、以snsapi_base为scope发起的网页授权，是用来获取进入页面的用户的openid的，并且是静默授权并自动跳转到回调页的。用户感知的就是直接进入了回调页（往往是业务页面）
	2、以snsapi_userinfo为scope发起的网页授权，是用来获取用户的基本信息的。但这种授权需要用户手动同意，并且由于用户同意过，所以无须关注，就可在授权后获取该用户的基本信息。
	3、用户管理类接口中的“获取用户基本信息接口”，是在用户和公众号产生消息交互或关注后事件推送后，才能根据用户OpenID来获取用户基本信息。这个接口，包括其他微信接口，都是需要该用户（即openid）关注了公众号后，才能调用成功的。
	
	
	
	http://www.cnblogs.com/txw1958/p/weixin71-oauth20.html
	
	
	
	http://my.oschina.net/sucre/blog/375199
	
	
	http://cloudbbs.org/forum.php?mod=viewthread&tid=23867