package com.zym.server.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;

public class FirstHandler extends HttpHandler {

	@Override
	public void doGet(Request request, Response response) {
		String name = ManagementFactory.getRuntimeMXBean().getName();
		String pid = name.split("@")[0];
		StringBuffer threadStack = new StringBuffer();
		threadStack.append("Process pid:" + pid + "\n");
		threadStack.append("\n");
		threadStack.append("\n");
		threadStack.append("\n");
		threadStack.append("\n");
		try {
			threadStack.append(jstack(pid));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.write(threadStack.toString());
	}
	
	@Override
	public void doPost(Request request, Response response) {
		System.out.println("doPost");
		System.out.println(request.getRequestBody());

		response.write("helloWorld.....");
	}

	public String jstack(String pid) throws IOException {
		Runtime rt = Runtime.getRuntime();
		Process proc = rt.exec("jstack " + pid);
		InputStream stdin = proc.getInputStream();
		InputStreamReader isr = new InputStreamReader(stdin);
		BufferedReader br = new BufferedReader(isr);
		StringBuffer sb = new StringBuffer();
		String line = "";
		while ((line = br.readLine()) != null) {
			sb.append(line);
			sb.append("\n");
		}
		return sb.toString();
	}

}
