package com.liulishuo.server.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.net.InetSocketAddress;

import javax.management.MBeanOperationInfo;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.spi.HttpServerProvider;
/**
 * 
 */
public class MyHttpServer {
	public static void start() throws IOException {
		Context.load();
		HttpServerProvider provider = HttpServerProvider.provider();
		HttpServer httpserver =provider.createHttpServer(new InetSocketAddress(8081), 100);
		httpserver.createContext(Context.contextPath, new MyHttpHandler()); 
		httpserver.setExecutor(null);
		httpserver.start();
		System.out.println("port=8081 jstack server started!");
	}
//	public static void main(String[] args) throws IOException {
//		start();
//	}
}
