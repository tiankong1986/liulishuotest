package com.liulishuo.server.httpserver;



public abstract class HttpHandler implements Handler {

	@Override
	public void service(Request request, Response response) {
		request.initRequestHeader();
		request.initRequestParam();
		if(request.getMethod().equals(Request.GET)){
			doGet(request,response);
		}else if(request.getMethod().equals(Request.POST)){
			request.initRequestBody();
			doPost(request,response);
		}
	}
	@Override
	public abstract void doGet(Request request, Response response);

	@Override
	public abstract void doPost(Request request, Response response);

	
}
