package com.lichi.increaselimit.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * redisUtils
 * @author majie
 *
 */
@Component
@SuppressWarnings({"unchecked","rawtypes"})
public class RedisUtils {

	private static final String REDIS_CODE = "UTF-8";

	@Autowired
	private RedisTemplate redisTemplate;

	/**
	 * @param key
	 */
	public Long del(final String... keys) {
		return (Long) redisTemplate.execute(new RedisCallback() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				long result = 0;
				for (int i = 0; i < keys.length; i++) {
					result = connection.del(keys[i].getBytes());
				}
				return result;
			}
		});
	}

	/**
	 * @param key
	 * @param value
	 * @param liveTime
	 */
	public void set(final byte[] key, final byte[] value, final long liveTime) {
		redisTemplate.execute(new RedisCallback() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				connection.set(key, value);
				if (liveTime > 0) {
					connection.expire(key, liveTime);
				}
				return 1L;
			}
		});
	}
	/**
	 * @param string
	 * @param value
	 * @param liveTime
	 */
	public void expire(final String string,final long liveTime) {
		redisTemplate.execute(new RedisCallback() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				if (liveTime > 0) {
					connection.expire(string.getBytes(), liveTime);
				}
				return 1L;
			}
		});
	}
	public void putMap(final String string,Map map) {
		redisTemplate.execute(new RedisCallback() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				connection.hMSet(string.getBytes(), map);
				return 1L;
			}
		});
	}
	
	public Map getMap(final String string) {
		return (Map) redisTemplate.execute(new RedisCallback() {
			@Override
			public Map<byte[], byte[]> doInRedis(RedisConnection connection) throws DataAccessException {
				Map<byte[], byte[]> map = connection.hGetAll(string.getBytes());
				return map;
			}
		});
	}
	
	
	/**
	 * rpush
	 * @param key
	 * @param value
	 */
	public void rPush(final String key,String value) {
		redisTemplate.execute(new RedisCallback() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				connection.rPush(key.getBytes(), value.getBytes());
				return 1L;
			}
		});
	}
	
	/**
	 * sAdd
	 * @param key
	 * @param value
	 */
	public void sadd(final String key,String value) {
		redisTemplate.execute(new RedisCallback() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				connection.sAdd(key.getBytes(), value.getBytes());
				return 1L;
			}
		});
	}
	
	/**
	 * sAdd
	 * @param key
	 * @param value
	 */
	public Set<String> sget(final String key) {
		return (Set<String>) redisTemplate.execute(new RedisCallback() {
			@Override
			public Set<String> doInRedis(RedisConnection connection) throws DataAccessException {
				Set<byte[]> members = connection.sMembers(key.getBytes());
				Set<String> set = new  HashSet<String>();
				for (byte[] bs : members) {
					try {
						set.add(new String(bs, REDIS_CODE));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
				return set;
			}
		});
	}
	
	

	/**
	 * @param key
	 * @param value
	 * @param liveTime
	 */
	public void set(String key, String value, long liveTime) {
		this.set(key.getBytes(), value.getBytes(), liveTime);
	}

	/**
	 * @param key
	 * @param value
	 */
	public void set(String key, String value) {
		this.set(key, value, 0L);
	}

	/**
	 * @param key
	 * @param value
	 */
	public void set(byte[] key, byte[] value) {
		this.set(key, value, 0L);
	}

	/**
	 * @param key
	 * @return
	 */
	public String get(final String key) {
		return (String) redisTemplate.execute(new RedisCallback() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				try {
					byte[] bs = connection.get(key.getBytes());
					if(bs == null) {
						return null;
					}
					return new String(bs, REDIS_CODE);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				return null;
			}
		});
	}

	/**
	 * @param pattern
	 * @return
	 */
	public Set keys(String pattern) {
		return redisTemplate.keys(pattern);

	}

	/**
	 * @param key
	 * @return
	 */
	public boolean exists(final String key) {
		return (boolean) redisTemplate.execute(new RedisCallback() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.exists(key.getBytes());
			}
		});
	}

	/**
	 * @return
	 */
	public String flushDB() {
		return (String) redisTemplate.execute(new RedisCallback() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				connection.flushDb();
				return "ok";
			}
		});
	}

	/**
	 * @return
	 */
	public long dbSize() {
		return (long) redisTemplate.execute(new RedisCallback() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.dbSize();
			}
		});
	}

	/**
	 * @return
	 */
	public String ping() {
		return (String) redisTemplate.execute(new RedisCallback() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {

				return connection.ping();
			}
		});
	}

}
