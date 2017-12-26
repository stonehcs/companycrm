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


	private static JPushClient jpushClient = new JPushClient(masterSecret, appKey, null, ClientConfig.getInstance());

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
     * 发送给所有安卓用户
     * @param alert
     * @param title
     * @return
     */
    public static PushPayload buildPushObject_android_tag_alertWithTitle(String alert,String title,String tag) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.tag(tag))
                .setNotification(Notification.android(alert, title, null))
                .build();
    }
	
    /**
     * 推送给所有的ios用户
     * @param alert
     * @param title
     * @param tag
     * @return
     */
    public static PushPayload buildPushObject_ios_tagAnd_alertWithExtrasAndMessage(String alert,String title,String tag) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.tag_and(tag, "tag_all"))
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
		PushPayload buildPushObject_all_alias_alert = buildPushObject_all_alias_alert("", null);
		try {
			PushResult result = jpushClient.sendPush(buildPushObject_all_alias_alert);
		} catch (APIConnectionException | APIRequestException e) {
			e.printStackTrace();
		}
	}
}
