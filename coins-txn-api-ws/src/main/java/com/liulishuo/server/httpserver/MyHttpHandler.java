package com.liulishuo.server.httpserver;


import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 */
public class MyHttpHandler implements HttpHandler {

	public void handle(HttpExchange httpExchange) throws IOException {
		HttpRequest request = new HttpRequest(httpExchange);
		HttpResponse response = new HttpResponse(httpExchange);
		Handler handler = Context.getHandler(request.getReuestURI().getPath());
		handler.service(request, response);

	}
}
