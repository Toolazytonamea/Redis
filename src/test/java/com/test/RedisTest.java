package com.test;

import com.ujiuye.entity.Person;
import com.ujiuye.utils.JsonUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.time.temporal.JulianFields;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RedisTest {

    @Test
    public void addString(){
        Jedis jedis = new Jedis("192.168.80.250",6379);
        jedis.set("name","zhangsan");
        jedis.close();
    }
    @Test
    public void grtString(){
        Jedis jedis = new Jedis("192.168.80.250",6379);
        String age = jedis.get("age");
        System.out.println(age);
        jedis.close();
    }
    @Test
    public void addHash(){
        Jedis jedis = new Jedis("192.168.80.250",6379);
        jedis.hset("person","name","laoda");
        jedis.hset("person","age","12");
        jedis.close();
    }
    @Test
    public void getHash(){
        Jedis jedis = new Jedis("192.168.80.250",6379);
        String name = jedis.hget("person", "name");
        String age = jedis.hget("person", "age");
        System.out.println(name+ " "+age);
        jedis.close();
    }
    @Test
    public void addJsonString(){
        Jedis jedis = new Jedis("192.168.80.250",6379);
        Person person = new Person("张三",12,"男",new Date());
        String person1 = JsonUtils.objectToJson(person);
        jedis.set("person1",person1);
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("李四",13,"女",new Date()));
        personList.add(new Person("老五",23,"女",new Date()));
        String p = JsonUtils.objectToJson(personList);
        jedis.set("personList",p);
        jedis.close();
    }
    @Test
    public void getJsonString(){
        Jedis jedis = new Jedis("192.168.80.250",6379);
        String personJson = jedis.get("person1");
        Person person = JsonUtils.jsonToEntity(personJson, Person.class);
        System.out.println(1+"...."+personJson);
        System.out.println(2+"...."+person);
        String personList = jedis.get("personList");
        List<Person> personList1 = JsonUtils.jsonToList(personList, Person.class);
        System.out.println(3+"...."+personList);
        System.out.println(4+"...."+personList1);
        jedis.close();
    }
}
