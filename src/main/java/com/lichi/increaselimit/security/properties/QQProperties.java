package com.lichi.increaselimit.security.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * QQProperties
 * @author majie
 *
 */
public class QQProperties extends SocialProperties {
	
	private String providerId = "qq";

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	
}
