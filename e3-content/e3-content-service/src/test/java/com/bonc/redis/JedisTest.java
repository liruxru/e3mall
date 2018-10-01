package com.bonc.redis;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedisTest {
	@Test
	public void  testJedis() {
		// 创建一个连接
		Jedis jedis = new Jedis("localhost", 6379);
		// 直接使用jedis操作redis
		jedis.set("test123", "my first test");
		System.out.println(jedis.get("test123"));
		jedis.close();
	}
	
	/**
	 * 使用连接池
	 */
	@Test
	public void testJedisPool() {
		// 创建连接池
		JedisPool jedisPool = new JedisPool("localhost",6379);
		// 从连接池获取一个连接
		Jedis jedis = jedisPool.getResource();
		// 直接使用jedis操作redis
		jedis.set("test123", "my pool test");
		System.out.println(jedis.get("test123"));
		
		// 关闭连接
		jedis.close();
		// 关闭池
		jedisPool.close();
	}
	/**
	 * 测试集群连接
	 * @throws IOException 
	 */
	@Test
	public void testJedisCluster() throws IOException {
		//创建一个jedisCluster 对象
		Set<HostAndPort> nodes = new HashSet();
		HostAndPort node1 = new HostAndPort("192.168.91.131", 7001);
		HostAndPort node2 = new HostAndPort("192.168.91.131", 7002);
		HostAndPort node3 = new HostAndPort("192.168.91.131", 7003);
		HostAndPort node4 = new HostAndPort("192.168.91.132", 7001);
		HostAndPort node5 = new HostAndPort("192.168.91.132", 7002);
		HostAndPort node6 = new HostAndPort("192.168.91.132", 7003);
		nodes.add(node1);
		nodes.add(node2);
		nodes.add(node3);
		nodes.add(node4);
		nodes.add(node5);
		nodes.add(node6);
		
		JedisCluster jedisCluster = new JedisCluster(nodes);
		
		// 直接使用 jedisCluster 操作resis
		jedisCluster.set("test123", "my cluster test");
		System.out.println(jedisCluster.get("test123"));
		
		jedisCluster.close();
		
	}

}
