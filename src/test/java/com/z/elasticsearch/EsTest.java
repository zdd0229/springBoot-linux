package com.z.elasticsearch;

import com.z.config.ElasticSearchConfig;
import com.z.util.JsonUtil;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EsTest {

    @Autowired
    private RestHighLevelClient client;

    @Test
    public void test() throws IOException {

        IndexRequest request = new IndexRequest("users");

        Map<String,Object> obj =new HashMap<>();
        obj.put("name","zdd");
        obj.put("age",25);
        String json= JsonUtil.toStr(obj);

        request.source(json, XContentType.JSON);
        IndexResponse response = client.index(request, ElasticSearchConfig.REQUEST_OPTIONS);

        List<Integer> a = new ArrayList<>();

        System.out.println(response);

    }

}
