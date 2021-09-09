package com.z.io;

import com.z.bean.entity.Category;
import com.z.logback.LogBackConfigLoader;
import com.z.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.util.ResourceUtils;

import java.io.*;

@Slf4j
public class BIOTest {

    public static void main(String[] args) {

        LogBackConfigLoader.load("logback-spring.xml");

        Category category = new Category();
        category.setCatId(1l);
        category.setName("电脑硬件");

        File target = null;
        FileOutputStream fileOutputStream = null;
        try {
            String pathname = ResourceUtils.getURL("classpath:").getPath() + "/target.txt";
            target = new File(pathname);

            fileOutputStream = new FileOutputStream(target);

            fileOutputStream.write(JsonUtil.toStr(category).getBytes("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(fileOutputStream);
        }

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(target);
            int read;
            while ((read = fileReader.read()) != -1) {
                System.out.println(String.format("[\033[32m%s\033[0m:\033[33m%s\033[0m]", (char) read, read));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(fileReader);
        }

        log.info("运行结束");
    }

}
