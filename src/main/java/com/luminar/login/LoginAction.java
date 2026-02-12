package com.luminar.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/loginAction")
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public boolean isPasswordValid(String enteredUserName,String enteredPassWord) 
			throws IOException,ServletException {
		final String URL="jdbc:mysql://localhost:3306/luminar_servlet_project";
		final String USER_NAME="root";
		final String PASSWORD="ragil123";
		
		Connection conn=null;
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		String storedPassword="";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection(URL,USER_NAME,PASSWORD);
			
			pstmt=conn.prepareStatement("select login_password from login_details where login_username=?");
			pstmt.setString(1, enteredUserName);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				storedPassword=rs.getString(1);
			}
			
		}catch(SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		
		if(enteredPassWord.equals(storedPassword)) {
			return true;
		}else {
			return false;
		}
	}
	
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) 
			throws IOException,ServletException{
		RequestDispatcher dis=null;
		try {
			String UserName=request.getParameter("username");
			String PassWord=request.getParameter("password");
			
			if(isPasswordValid(UserName,PassWord)) {
				HttpSession session=request.getSession();
				session.setAttribute("user", UserName);
				
				dis=request.getRequestDispatcher("bloHome");
				dis.forward(request, response);
			}else {
				HttpSession session=request.getSession();
				session.setAttribute("user", UserName);
				
				dis=request.getRequestDispatcher("index.html");
				dis.forward(request, response);
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
