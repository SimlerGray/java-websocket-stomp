package com.example.config;

import org.springframework.util.Assert;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by apple on 17/8/22.
 */
public class SocketSessionRegistry {

	//this map save every session
	//这个集合存储session
	private final ConcurrentMap<String, Set<String>> userSessionIds = new ConcurrentHashMap();
	private final Object lock = new Object();

	public SocketSessionRegistry() {
	}

	/**
	 *
	 * 获取sessionId
	 * @param user
	 * @return
	 */
	public Set<String> getSessionIds(String user) {
		Set set = (Set)this.userSessionIds.get(user);
		return set != null?set: Collections.emptySet();
	}

	/**
	 * 获取所有session
	 * @return
	 */
	public ConcurrentMap<String, Set<String>> getAllSessionIds() {
		return this.userSessionIds;
	}

	/**
	 * register session
	 * @param user
	 * @param sessionId
	 */
	public void registerSessionId(String user, String sessionId) {
		Assert.notNull(user, "User must not be null");
		Assert.notNull(sessionId, "Session ID must not be null");
		Object var3 = this.lock;
		synchronized(this.lock) {
			Object set = (Set)this.userSessionIds.get(user);
			if(set == null) {
				set = new CopyOnWriteArraySet();
				this.userSessionIds.put(user, (Set<String>) set);
			}else {
				this.userSessionIds.remove(user);
				set = new CopyOnWriteArraySet();
				this.userSessionIds.put(user,(Set<String>)set);
			}

			((Set)set).add(sessionId);
		}
	}

	public void unregisterSessionId(String userName, String sessionId) {
		Assert.notNull(userName, "User Name must not be null");
		Assert.notNull(sessionId, "Session ID must not be null");
		Object var3 = this.lock;
		synchronized(this.lock) {
			Set set = (Set)this.userSessionIds.get(userName);
			if(set != null && set.remove(sessionId) && set.isEmpty()) {
				this.userSessionIds.remove(userName);
			}

		}
	}
}
