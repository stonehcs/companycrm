package com.lichi.increaselimit.security.validate.code.image;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

import com.lichi.increaselimit.security.validate.code.ValidateCode;


/**
 * 图片验证码
 * @author majie
 *
 */
public class ImageCode extends ValidateCode {
	
	private static final long serialVersionUID = 1330569522209298859L;
	
	private BufferedImage image; 
	
	public ImageCode(BufferedImage image, String code, int expireIn){
		super(code, expireIn);
		this.image = image;
	}
	
	public ImageCode(BufferedImage image, String code, LocalDateTime expireTime){
		super(code, expireTime);
		this.image = image;
	}
	
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

}
