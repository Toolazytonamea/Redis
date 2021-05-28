package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-redis.xml")
public class JedisPoolTest {

    @Autowired
    private JedisPool jedisPool;

    @Test
    public void testJedisPool(){
        Jedis jedis = jedisPool.getResource();
        jedis.set("address","河南");
        String address = jedis.get("address");
        System.out.println(address);
        jedis.close();
    }

}
