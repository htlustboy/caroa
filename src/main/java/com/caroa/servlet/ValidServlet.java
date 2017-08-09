package com.caroa.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.caroa.util.ValidUtil;

public class ValidServlet extends HttpServlet {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = -4009830537151214084L;

	@Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        doPost(req, resp);
	    }

	    @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        new ValidUtil().getCode(new Date().toString(), req, resp);
	    }
}
