package com.basement.web.pxy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.SessionAttributes;
@Lazy
@Component("ctx")
@SessionAttributes({"ctx", "js"})
public class Contextpxy extends Proxy{
	 @Autowired HttpSession session;
	 @Autowired HttpServletRequest request;
	 
	 public void execute() {
		 String ctx = request.getContextPath();
		 session.setAttribute("ctx", ctx);
		 session.setAttribute("js", ctx + "/resources/js/");
	 }
}
