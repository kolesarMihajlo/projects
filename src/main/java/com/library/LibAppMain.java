package com.library;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;



@SpringBootApplication
public class LibAppMain{
	
	
	
	@Autowired
	private Environment env;
	
	public static void main(String[] args) {
		SpringApplication.run(LibAppMain.class, args);
		

	}
	
//	@Override
//	@Autowired
//	public void onStartup(ServletContext servletContext)throws ServletException{
//		String pathREal=servletContext.getRealPath("/");
//		String pathCont=servletContext.getContextPath();
//		System.out.println("real: "+pathREal);
//		System.out.println("context: "+pathCont);
//		Resource res=new ClassPathResource("serverpath.txt");
//		System.out.println("CLASS PATH RESOURCE: "+res.toString());
//		File f=null;
//		try {
//			f=res.getFile();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		if(f.exists()){
//			System.out.println("THIS IS PATH I WANT:"+f.getAbsolutePath());
//		}
//		
//		
//		
//	}

}
