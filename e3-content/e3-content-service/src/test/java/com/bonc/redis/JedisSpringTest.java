package com.bonc.redis;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bonc.common.jedis.JedisClient;

/**
 * 集成spring測試
 * @author j
 *
 */
@RunWith(SpringJUnit4ClassRunner.class) // spring 5.07 和 junit 4.12    4.11|(會報錯)
@ContextConfiguration(locations="classpath*:applicationContext-*.xml")
public class JedisSpringTest {
	
	@Autowired
	private JedisClient jedisClient;
	@Test
	public void jedisBeanTest() {
		System.out.println(jedisClient);
	}

}
