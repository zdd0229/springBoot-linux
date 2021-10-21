package com.z.Middleware.redis;

import com.z.SpringBootLinux;
import com.z.bean.PO.StudentPO;
import com.z.bean.enumm.Gender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={SpringBootLinux.class})
public class MainTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test(){

        StudentPO studentPO=new StudentPO();
        studentPO.setId(2);
        studentPO.setName("z");
        studentPO.setAge(22);
        studentPO.setBirthday(new Date());
        studentPO.setSex(Gender.MALE);
        studentPO.setAddress("淮安");
        studentPO.setDescription("des");
        studentPO.setmUser("z");
        studentPO.setcUser("x");
        studentPO.setcDate(new Date());
        studentPO.setmDate(new Date());

        redisTemplate.opsForValue().set("stringValue",studentPO);

        StudentPO res = (StudentPO) redisTemplate.opsForValue().get("stringValue");

        System.out.println(res.getName()) ;
    }

    @Test
    public void stringTest(){
        // 设值
        String value11="value1";
        String value22="value2";

        redisTemplate.opsForValue().set("key1", value11);
        redisTemplate.opsForValue().set("key2", value22);

        // 通过key删除值
        redisTemplate.delete("key1");
        // 求长度
        Long length = redisTemplate.opsForValue().size("key2");
        System.out.println(length);
        // 设值新值并返回旧值
        String oldValue2 = (String) redisTemplate.opsForValue().getAndSet(
                "key2", "new_value2");
        System.out.println(oldValue2);
        // 通过key获取值.
        String value2 = (String) redisTemplate.opsForValue().get("key2");
        System.out.println(value2);
        // 求子串
        String rangeValue2 = redisTemplate.opsForValue().get("key2", 0, 3);
        System.out.println(rangeValue2);
        // 追加字符串到末尾，返回新串长度
        int newLen = redisTemplate.opsForValue().append("key2", "_app");
        System.out.println(newLen);

        String appendValue1 = (String) redisTemplate.opsForValue().get("key2");
        System.out.println(appendValue1);
    }
    @Test
    public void integerTest(){
        // 设值
        redisTemplate.opsForValue().set("i", 3);

        // 通过key获取值
        printValue("i");

        redisTemplate.opsForValue().increment("i",10l);

        printValue("i");
    }

    @Test
    public void hashTest(){

        String key = "hash";

        Map<String, String> map = new HashMap<String,String>();
        map.put("f1", "val1");
        map.put("f2", "val2");

        //相当于hmset命令
        redisTemplate.opsForHash().putAll(key,map);
        redisTemplate.opsForHash().put(key,"f3",20);

        printValueForhash(key,"f1");
    }

    private void printValue(String key){
        // 通过key获取值
        Object value =redisTemplate.opsForValue().get(key);
        System.out.println(value);
    }

    private void printValueForhash(String key,String field) {
        //相当于hget命令
        Object value =redisTemplate.opsForHash().get(key,field);
        System.out.println(value);
    }
}
