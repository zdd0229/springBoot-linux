package com.z.framework.logback;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class LogBackConfigLoader {
    public static void load (String externalConfigFileLocation) {
        try {
            LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();

            String pathname = LogBackConfigLoader.class.getResource(externalConfigFileLocation).getPath().replaceFirst("/","");
            File externalConfigFile = new File(pathname);
            if(!externalConfigFile.exists()){
                throw new IOException("Logback External Config File Parameter does not reference a file that exists");
            }else{
                if(!externalConfigFile.isFile()){
                    throw new IOException("Logback External Config File Parameter exists, but does not reference a file");
                }else{
                    if(!externalConfigFile.canRead()){
                        throw new IOException("Logback External Config File exists and is a file, but cannot be read.");
                    }else{
                        JoranConfigurator configurator = new JoranConfigurator();
                        configurator.setContext(lc);
                        lc.reset();
                        configurator.doConfigure(pathname);
                        StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JoranException e) {
            e.printStackTrace();
        }
    }
}
