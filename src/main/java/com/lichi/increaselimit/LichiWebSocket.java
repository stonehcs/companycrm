package com.lichi.increaselimit;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import com.lichi.increaselimit.common.utils.ApplicationContextRegister;
import com.lichi.increaselimit.user.entity.LoginUser;
import com.lichi.increaselimit.user.service.LoginUserService;

import lombok.extern.slf4j.Slf4j;

/**
 * websocket
 * 
 * @author majie
 *
 */
@ServerEndpoint(value = "/websocket/{id}")
@Component
@Slf4j
public class LichiWebSocket {
	// 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	private static AtomicInteger onlineCount = new AtomicInteger(0);

	// concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
	private static CopyOnWriteArraySet<LichiWebSocket> webSocketSet = new CopyOnWriteArraySet<LichiWebSocket>();

	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;

	/**
	 * 建立连接以后登陆表中新增一个用户
	 * 
	 */
	@OnOpen
	public void onOpen(@PathParam("id") String id, Session session) {
		this.session = session;
		webSocketSet.add(this); // 加入set中
		addOnlineCount(); // 在线数加1
		log.info("id:{}用户加入！当前在线人数为" + getOnlineCount(),id);

		LoginUser login = new LoginUser();
		login.setId(id);
		LoginUserService loginUserService = ApplicationContextRegister.getApplicationContext()
				.getBean(LoginUserService.class);
		loginUserService.addLoginUser(login);
		// try {
		// sendMessage(CommonConstant.CURRENT_WANGING_NUMBER.toString());
		// } catch (IOException e) {
		// System.out.println("IO异常");
		// }
	}

	/**
	 * 连接关闭调用以后删除登陆的用户
	 */
	@OnClose
	public void onClose(@PathParam("id") String id) {
		webSocketSet.remove(this); // 从set中删除
		subOnlineCount(); // 在线数减1
		log.info("id:{}用户连接关闭！当前在线人数为" + getOnlineCount(),id);
		
		
		LoginUserService loginUserService = ApplicationContextRegister.getApplicationContext()
				.getBean(LoginUserService.class);
		loginUserService.deleteLoginUser(id);
	}

	/**
	 * 收到客户端消息后调用的方法
	 *
	 * @param message
	 *            客户端发送过来的消息
	 */
	@OnMessage
	public void onMessage(@PathParam("id") String id, String message, Session session) {
		log.info("来自id:{}的消息:" + message,id);

		// 群发消息
		// for (LichiWebSocket item : webSocketSet) {
		// try {
		// item.sendMessage(message);
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// }
	}

	/**
	 * 发生错误时调用
	 * 移除连接，并删除登陆用户
	 * 
	 */

	@OnError
	public void onError(@PathParam("id") String id,Throwable error) {
		log.info("id:{}的用户websocket异常",id);
		webSocketSet.remove(this); 
		LoginUserService loginUserService = ApplicationContextRegister.getApplicationContext()
				.getBean(LoginUserService.class);
		loginUserService.deleteLoginUser(id);
	}

	/**
	 * 发送消息
	 * @param message
	 * @throws IOException
	 */
	public void sendMessage(String message) throws IOException {
//		this.session.getBasicRemote().sendText(message);
		this.session.getAsyncRemote().sendText(message);
	}

	public static void sendInfo(String message) throws IOException {
		for (LichiWebSocket item : webSocketSet) {
			try {
				item.sendMessage(message);
			} catch (IOException e) {
				continue;
			}
		}
	}

	public static synchronized int getOnlineCount() {
		return onlineCount.get();
	}

	public static synchronized void addOnlineCount() {
		LichiWebSocket.onlineCount.incrementAndGet();
	}

	public static synchronized void subOnlineCount() {
		LichiWebSocket.onlineCount.decrementAndGet();
	}
}