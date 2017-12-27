package com.lichi.increaselimit.common.utils;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import lombok.extern.slf4j.Slf4j;

/**
 * 极光推送util
 * 
 * @author majie
 *
 */
@Slf4j
public class JpushClientUtil {

	private final static String appKey = "9bd15383b65739613ad7d47d";

	private final static String masterSecret = "9c6749875003d85209c8426d";

	/**
	 * 发送给别名用户
	 * @param alias
	 * @return
	 */
    public static PushPayload buildPushObject_all_alias_alert(String alias,String alert) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.alert(alert))
                .build();
    }
    
   /**
    * 推送给所有用户
    * @param alert
    * @param title
    * @param tag
    * @return
    */
    public static void pushToAllAndroid(String alert,String title) {
    	ClientConfig instance = ClientConfig.getInstance();
    	instance.setApnsProduction(false);
    	JPushClient jpushClient = new JPushClient(masterSecret, appKey, null, instance);

        PushPayload build = PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.all())
                .setNotification(Notification.alert(alert))
                .setMessage(Message.content(title))
                .build();
        try {
			jpushClient.sendPush(build);
		} catch (APIConnectionException | APIRequestException e) {
			e.printStackTrace();
			log.error("推送给所有人异常:{}",e.getMessage());
		}
    }
	
    
    /**
     * 推送给所有的ios用户
     * @param alert
     * @param title
     * @param tag
     * @return
     */
    public static void pushToAllIOS(String alert,String title) {
    	
    	ClientConfig instance = ClientConfig.getInstance();
    	instance.setApnsProduction(false);
    	JPushClient jpushClient = new JPushClient(masterSecret, appKey, null, instance);
    	
        PushPayload build = PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.all())
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(alert)
                                .setBadge(5)
                                .setSound("happy")
                                .addExtra("from", "JPush")
                                .build())
                        .build())
                 .setMessage(Message.content(title))
                 .setOptions(Options.newBuilder()
                         .setApnsProduction(true)
                         .build())
                 .build();
        try {
			PushResult sendPush = jpushClient.sendPush(build);
			System.out.println(sendPush);
		} catch (APIConnectionException | APIRequestException e) {
			e.printStackTrace();
		}
    }
    
    public static void pushToAll(String alert,String title) {
		pushToAllAndroid(alert, title);
		pushToAllIOS(alert, title);
    }
    
    public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.newBuilder()
                        .addAudienceTarget(AudienceTarget.tag("tag1", "tag2"))
                        .addAudienceTarget(AudienceTarget.alias("alias1", "alias2"))
                        .build())
                .setMessage(Message.newBuilder()
                        .setMsgContent("")
                        .addExtra("from", "JPush")
                        .build())
                .build();
    }
    
	public static void main(String[] args) {
		pushToAllAndroid("我是description", "我是content");
//		pushToAllIOS("fdsfds", "fdsf");
	}
}
