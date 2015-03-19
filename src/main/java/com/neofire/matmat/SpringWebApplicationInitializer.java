package com.neofire.matmat;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.annotation.PreDestroy;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class SpringWebApplicationInitializer implements WebApplicationInitializer {

	private final AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		ctx.register(WebMvcConfig.class);
		ctx.setServletContext(servletContext);
		ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
		servlet.addMapping("/");
		servlet.setLoadOnStartup(1);
	}
	
	@PreDestroy
	void onDestroy(){
		ctx.close();
	}
}
