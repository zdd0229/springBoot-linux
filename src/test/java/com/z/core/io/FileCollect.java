package com.z.core.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

public class FileCollect {

    public static void main(String[] args) {

        //几个配置所在的文件夹
        String cachePath = "D:\\project\\payrollService3\\cache\\src\\main\\resources";
        String corePath = "D:\\project\\payrollService3\\core\\src\\main\\resources";
        String flinklauncherPath = "D:\\project\\payrollService3\\flinklauncher\\src\\main\\resources";
        String integrationPath = "D:\\project\\payrollService3\\integration\\src\\main\\resources";


        List<String> path = Arrays.asList(cachePath, corePath, flinklauncherPath, integrationPath);

        for (String s : path) {
            File source = new File(s);
            String[] list = source.list();
            for (String s1 : list) {
                //移动环境
                copyFile(s, s1, "ali", "d");
                copyFile(s, s1, "ali", "q");
                copyFile(s, s1, "ali", "s");
                copyFile(s, s1, "ali", "pt");
                copyFile(s, s1, "ali", "a");
                copyFile(s, s1, "hw", "d");
                copyFile(s, s1, "hw", "q");
                copyFile(s, s1, "hw", "s");
                copyFile(s, s1, "hw", "pt");
                copyFile(s, s1, "hw", "a");
            }
        }

    }

    private static void copyFile(String module, String source, String cloud, String profile) {

        String s = cloud + "-" + profile;
        if (source.contains(s)) {
            String targetPath = "C:\\Users\\Thomas.Zhao\\Desktop\\配置中心接入";
            String[] pathSplit = module.split("\\\\");

            String[] filenameSplit = source.split("\\.");
            File target = new File(targetPath + "\\" + s + "\\" + filenameSplit[0] + "-" + pathSplit[3] + "." + filenameSplit[1]);
            try {
                Files.copy(new File(module + "\\" + source).toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
