#微信开发#
1.[测试帐号申请](http://mp.weixin.qq.com/debug/cgi-bin/sandbox?t=sandbox/login)
	微信帐号扫一下就可以了

##微信只能访问公网80端口，使用ngrok(下载需要翻墙)映射，注册拿到token##
~~~bash
ngrok authtoken 你的token
ngrok http -subdomain=weixin 8080	
~~~

	
###log4j2
http://blog.csdn.net/lu8000/article/details/25754415