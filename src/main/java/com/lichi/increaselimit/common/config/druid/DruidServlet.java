package com.lichi.increaselimit.common.config.druid;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import com.alibaba.druid.support.http.StatViewServlet;

/**
 * druidServlet
 * 设置登录账号密码为root
 * @author majie
 *
 */
@WebServlet(urlPatterns = { "/druid/*" }, 
			initParams = { @WebInitParam(name = "loginUsername", value = "root"),
							@WebInitParam(name = "loginPassword", value = "root") })		
public class DruidServlet extends StatViewServlet {
	private static final long serialVersionUID = 1L;
}
