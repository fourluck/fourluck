package com.zdd.framework;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;

import com.zdd.framework.bean.Data;
import com.zdd.framework.bean.Handler;
import com.zdd.framework.bean.Param;
import com.zdd.framework.bean.View;
import com.zdd.framework.helper.BeanHelper;
import com.zdd.framework.helper.ConfigHelper;
import com.zdd.framework.helper.ControllerHelper;
import com.zdd.framework.helper.RequestHelper;
import com.zdd.framework.helper.ServletHelper;
import com.zdd.framework.helper.UploadHelper;
import com.zdd.framework.util.CodecUtil;
import com.zdd.framework.util.JsonUtil;
import com.zdd.framework.util.ReflectionUtil;
import com.zdd.framework.util.StreamUtil;
import com.zdd.framework.util.StringUtil;

/**
 * 请求转发器
 * @author four-luck
 * @since 1.0.0
 * @date 2017年9月8日 下午2:02:01
 */
@WebServlet(urlPatterns = "/*",loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8818321987803317736L;
	
	
	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		//初始化相关Helper类
		HelperLoader.init();
		ServletContext servletContext = servletConfig.getServletContext();
		//注册处理JSP的Servlet
		ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
		jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");
		//注册处理静态资源的默认Servlet
		ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
		defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");
		UploadHelper.init(servletContext);
	}
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ServletHelper.init(req, res);
		try {
			//获取请求方法和路径
			String requestMethod = req.getMethod().toLowerCase();
			String requestPath = req.getPathInfo();
			
			if(requestPath.equals("/favicon.ico")){
				return;
			}
			//获取 Action 处理器
			Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
			if(handler != null){
				Class<?> controllerClass = handler.getControllerClass();
				Object controllerBean = BeanHelper.getBean(controllerClass);
				
				Param param;
				if(UploadHelper.isMultiPart(req)){
					param = UploadHelper.createParam(req);
				} else{
					param = RequestHelper.createParam(req);
				}
				Method actionMethod = handler.getActionMethod();
				Object result;
				if(param.isEmpty()){
					result = ReflectionUtil.invokeMethod(controllerBean, actionMethod);
				}else{
					result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
				}
				//处理 Action 方法返回值
				if(result instanceof View){
					//返回 页面
					handleViewResult((View)result,req,res);
				}else if(result instanceof Data){
					//返回 JSON 数据
					handleDataResult((Data)result,res);
				}
			}else{
				throw new ServletException("未找到请求的方法---"+requestPath);
			}
		} finally {
			ServletHelper.destory();
		}
		
	}

	private void handleDataResult(Data data, HttpServletResponse res) throws ServletException, IOException{
		Object model = data.getModel();
		if(model != null){
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			PrintWriter writer = res.getWriter();
			String json = JsonUtil.toJson(model);
			writer.write(json);
			writer.flush();
			writer.close();
		}
	}

	private void handleViewResult(View view, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		String path = view.getPath();
		if(StringUtil.isNoEmpty(path)){
			if(path.startsWith("/")){
				res.sendRedirect(req.getContextPath() + path);
			}else{
				Map<String,Object> model = view.getModel();
				for(Map.Entry<String, Object>entry:model.entrySet()){
					req.setAttribute(entry.getKey(), entry.getValue());
				}
				req.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(req, res);
			}
		}
	}
	
}
