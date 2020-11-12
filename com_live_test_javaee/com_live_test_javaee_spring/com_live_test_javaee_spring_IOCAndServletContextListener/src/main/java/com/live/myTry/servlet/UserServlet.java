package com.live.myTry.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

import com.live.myTry.service.UserService;

@WebServlet("/user.servlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = -5564006700324994491L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");

		ServletContext sc = request.getServletContext();
		ApplicationContext ac = (ApplicationContext) sc.getAttribute("Spring_ac");
		UserService userService = ac.getBean(UserService.class);
		userService.login(username, pwd);

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("login success:" + username);
		out.flush();
		out.close();
	}

}
