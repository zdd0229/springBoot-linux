package com.z.maintest;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.List;

public class RedisTest {
//    public static void main(String[] args) {
//        Jedis jedis = new Jedis("101.132.128.127",6379);
//        int i=0;
//        try {
//            long start=System.currentTimeMillis();
//            while (true){
//                long end =System.currentTimeMillis();
//                if(end-start>=1000){
//                    break;
//                }
//                i++;
//                jedis.set("test"+i,i+"");
//            }
//        }finally {
//            jedis.close();
//        }
//        System.out.println("redis 每秒操作："+i+"次");
//    }
    public static void main(String[] args) {
        Jedis jedis = new Jedis("101.132.128.127",6379);
        long start = System.currentTimeMillis();
// 开启流水线
        Pipeline pipeline = jedis.pipelined();
// 这里测试10万条的读/写2个操作
        for (int i = 0; i < 100000; i++) {
            int j = i + 1;
            pipeline.set("pipeline_key_" + j, "pipeline_value_" + j);
            pipeline.get("pipeline_key_" + j);
        }
// pipeline.sync(); //这里只执行同步，但是不返回结果
// pipeline.syncAndReturnAll ();将返回执行过的命令返回的List列表结果
        List result = pipeline.syncAndReturnAll();
        long end = System.currentTimeMillis();
// 计算耗时
        System.err.println("耗时：" + (end - start) + "毫秒");
    }
}
