package com.hemanshu;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApplicationController {
	
	
	@Autowired
	TeamRepo tt;

	@RequestMapping("/welcome")
		public String Welcome(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_HOME");
		return "welcomepage";
	} 
	
	@RequestMapping("/register")
	public String SELECT(HttpServletRequest request) {
		
		request.setAttribute("mode", "MODE_REGISTER");
		return "welcomepage";
	}
	
	@RequestMapping("/save-team")
	public String select(HttpServletRequest request,@ModelAttribute Team t)
	{ 
		
		Team e=tt.findById(1);
		
		e.setName(t.getName());
		tt.save(e);
		
		request.setAttribute("mode", "MODE_REGISTER");
		
		return "welcomepage";
	}
	
	@RequestMapping("/show")
	public String show(HttpServletRequest request)
	{
		List<String> s1=new ArrayList<String>();
		List<String> s11=new ArrayList<String>();
		Team t1=tt.findById(1);
		String s3=t1.getName();
		System.out.println("sting is "+s3);
		String[] s4=s3.split(",");
		for(String s6:s4)
		{
			String[] s8=s6.split(" ");
			StringBuffer s10=new StringBuffer();
			for(String s9:s8)
			{
				s10.append(s9.charAt(0));
			}
			
			s11.add(s10.toString());
			
		}
		for(String s5:s4)
		{
			s1.add(s5);
			
		}
		request.setAttribute("users", s1);
		request.setAttribute("abbr",s11 );
		request.setAttribute("mode", "MODE_HOME");
		return "welcomepage";
	}
	
	
	
}
