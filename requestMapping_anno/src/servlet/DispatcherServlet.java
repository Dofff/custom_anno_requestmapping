package servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.MainController;
import anno.RequestMapping;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Map<String, Method> methodMap;
	Object clsObject;
	
    public DispatcherServlet() {
    	methodMap = new HashMap<String, Method>();
    }
    
    @Override
    public void init() throws ServletException {
    	
    	Class cls;
		try {
			cls = Class.forName("controller.MainController");
			clsObject = cls.newInstance();
			Method[] methods = cls.getDeclaredMethods(); // reflection :: java API
			
			for (Method method : methods) {
				RequestMapping anno = method.getDeclaredAnnotation(RequestMapping.class);
				
				if( anno == null){
					continue ;
				}
				
				methodMap.put(anno.value()+":"+anno.method() , method);	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
    }
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String uri = request.getRequestURI();
		String method = request.getMethod();
		request.setCharacterEncoding("UTF-8");
		
		Method targetMethod = methodMap.get(uri+":"+method);
		
		try {
			if(targetMethod != null){
				targetMethod.invoke(clsObject, request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
	
}
