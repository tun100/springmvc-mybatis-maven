package com.laihuanmin.project.entry;

import com.laihuanmin.common.spring.SpringUtils;
import com.laihuanmin.common.utils.CommonUtils;
import org.apache.commons.io.IOUtils;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.*;
import java.util.Locale;

/**
 * 仅用于开发环境测试Server程序
 */
public class ServerEntry {
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        ServerEntry serverEntry = new ServerEntry();
        serverEntry.initServer();
    }

    public void initServer() throws Exception {
        Integer port = 12345;
        String basepath = "basic";
        Server server = new Server(port);
        Class envClz = this.getClass();
        String tempWebXmlFileAbsolutePath = SpringUtils.getWebXmlAbsolutePath(envClz);
        WebAppContext webAppContext = SpringUtils.getWebAppContext(basepath, tempWebXmlFileAbsolutePath);
        server.setHandler(webAppContext);
        System.out.println("Server Will Start.");
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Server Already Started.");
    }


}
