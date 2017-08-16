package com.example.controller;

import com.example.Demo.MobileDemo;
import com.example.Utils.JsonUtils;
import com.example.entity.AbstractEntity;
import com.example.entity.CommonEntity;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by apple on //.
 */
@ServerEndpoint("/websocket")
@Component
public class WebSocketController {

	private static int onlineCount = 0;

	private static CopyOnWriteArraySet<WebSocketController> webSocketSet = new CopyOnWriteArraySet<>();

	private static Map<String,CommonEntity> map = new HashMap<>();
	private Session session;

	@OnOpen
	public void onOpen (Session session){
		this.session = session;
		webSocketSet.add(this);
		addOnlineCount();
	//	System.out.println("有新链接加入!当前在线人数为" + getOnlineCount());
	}

	@OnClose
	public void onClose (){
		webSocketSet.remove(this);
		map.remove(this.session.getId());
		subOnlineCount();
		//System.out.println("有一链接关闭!当前在线人数为" + getOnlineCount());
	}

	@OnMessage
	public void onMessage (String message, Session session) throws IOException {
		System.out.println("来自客户端的消息:" +message);
		String[] str = message.split(",");
		CommonEntity ab = JsonUtils.json2Bean(message,CommonEntity.class);
		if (ab.getCode() == null){
			map.put(session.getId(),ab);
			AbstractEntity e = ab.getAbstractE();
			MobileDemo mobileDemo = new MobileDemo();
			mobileDemo.startService(e,session);
		}
		if (ab.getCode() != null){
			map.put(session.getId(),ab);
			getCode(session);
		}

		for ( WebSocketController item : webSocketSet ){
			if (item.session.getId().equals(session.getId())){
				item.sendMessage("请等待",session);
			}
		}
	}


	public static void sendMessage (String message,Session session) throws IOException {
		session.getBasicRemote().sendText(message);
	}

	public static synchronized  String getCode (Session session){
		ThreadPoolExecutor executor = new ThreadPoolExecutor(5,10,200, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(5));
		MyTask myTask = new MyTask(session,map);
		executor.execute(myTask);
		executor.shutdown();
		return myTask.get();

	}

	public static synchronized void addOnlineCount (){
		WebSocketController.onlineCount++;
	}

	public static synchronized void subOnlineCount (){
		WebSocketController.onlineCount--;
	}
}

class MyTask implements Runnable{
	public Thread t;
	private Session session;
	private boolean suspend = true;
	private Map<String,CommonEntity> map;

	public MyTask(Session session,Map<String,CommonEntity> map){
		this.session = session;
		this.map = map;
	}
	@Override
	public void run() {
		try {
			while (suspend){
				if (map.get(session.getId())==null) {
					suspend = true;
					wait();
				}else {
					suspend = false;
					notify();
				}
			}

		}catch (InterruptedException e){
			e.printStackTrace();
		}
	}

	public String get(){
		notify();
		CommonEntity c = map.get(session.getId());
		return c.getCode();
	}
}