package com.skillstorm.controllers;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

public class CORSFilter implements Filter {


	    public CORSFilter() {
	        // TODO Auto-generated constructor stub
	    }

	    public void destroy() {
	        // TODO Auto-generated method stub
	    }

	    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
	            throws IOException, ServletException {

	        HttpServletRequest request = (HttpServletRequest) servletRequest;
	        System.out.println("CORSFilter HTTP Request: " + request.getMethod());

	        // Authorize (allow) all domains to consume the content
	        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Origin", "*");
	        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Methods","GET, OPTIONS, DELETE, PUT, POST");
	        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));     

	        HttpServletResponse resp = (HttpServletResponse) servletResponse;

	        // For HTTP OPTIONS verb/method reply with ACCEPTED status code -- per CORS handshake
	        if (request.getMethod().equals("OPTIONS")) {
	            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
	            return;
	        }

	        // pass the request along the filter chain
	        chain.doFilter(request, servletResponse);
	    }

	    /**
	     * @see Filter#init(FilterConfig)
	     */
	    public void init(FilterConfig fConfig) throws ServletException {
	        // TODO Auto-generated method stub
	    }

	}