package com.liulishuo.server.httpserver;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


/**
 * 应该从xml中读取。为了简单直接赋值
 */
public class Context {
	private static Map<String,HttpHandler> contextMap = new HashMap<String,HttpHandler>();
	public static String contextPath = "";
	public static void load(){
		try{
			contextPath="/ops";
			Class<?> cls = Class.forName("com.liulishuo.server.httpserver.FirstHandler");
			Object newInstance = cls.newInstance();
			if(newInstance instanceof HttpHandler){
				contextMap.put(contextPath+"/jstack", (HttpHandler)newInstance);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
//		try{
//			System.out.println(Context.class.getResource("").getPath());
//			Document doc = XmlUtils.load(Context.class.getResource("").getPath() + "context.xml");
////			Document doc = XmlUtils.load("D:\\context.xml");
//			Element root = doc.getDocumentElement();
//			
//			contextPath = XmlUtils.getAttribute(root,"context");
//			System.out.println(contextPath);
//			
//			Element[] handlers = XmlUtils.getChildrenByName(root, "handler");
//			for(Element ele : handlers){
//				String handle_class = XmlUtils.getChildText(ele, "handler-class");
//				String url_pattern = XmlUtils.getChildText(ele, "url-pattern");
//				System.out.println(handle_class);
//				System.out.println(url_pattern);
//				Class<?> cls = Class.forName(handle_class);
//				Object newInstance = cls.newInstance();
//				if(newInstance instanceof HttpHandler){
//					contextMap.put(contextPath+url_pattern, (HttpHandler)newInstance);
//				}
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public static HttpHandler getHandler(String key){
		return contextMap.get(key);
	}
	
}
