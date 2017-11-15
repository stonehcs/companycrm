package com.lichi.increaselimit.common.config.druid;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import com.alibaba.druid.support.http.WebStatFilter;

/**
 * druid过滤器
 * @author majie
 *
 */
@WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*", initParams = {
		@WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*") })
public class DruidWebStatFilter extends WebStatFilter {
	
	
}