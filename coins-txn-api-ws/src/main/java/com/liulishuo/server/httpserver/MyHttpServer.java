package com.liulishuo.server.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.net.InetSocketAddress;

import javax.management.MBeanOperationInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liulishuo.coins.txn.boot.StartUp;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.spi.HttpServerProvider;
/**
 * 
 */
public class MyHttpServer {
	private static final Logger logger = LoggerFactory.getLogger(StartUp.class);
	public static void start()  {
		logger.debug("jstack server starting!");
		Context.load();
		HttpServerProvider provider = HttpServerProvider.provider();
		HttpServer httpserver = null;
		try {
			httpserver = provider.createHttpServer(new InetSocketAddress(8081), 100);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		httpserver.createContext(Context.contextPath, new MyHttpHandler()); 
		httpserver.setExecutor(null);
		httpserver.start();
		logger.debug("port=8081 jstack server started!");
	}
//	public static void main(String[] args) throws IOException {
//		start();
//	}
}
