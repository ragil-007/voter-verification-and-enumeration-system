package com.luminar.blo.voterdetails;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updatevoterDetails")
public class UpdateVoterDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException{
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException{
		try {
			int voterId=Integer.parseInt(request.getParameter("voter_id"));
			String voterName=request.getParameter("voter_name");
			String epicNo=request.getParameter("epic_no");
			String address=request.getParameter("address");
			String dob=request.getParameter("dob");
			String adharNo=request.getParameter("adhar_no");
			String mobileNo=request.getParameter("mobile_no");
			int boothNo=Integer.parseInt(request.getParameter("booth_no"));
			int boothId=0;
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/luminar_servlet_project","root","ragil123");
			
			PreparedStatement pstmt=conn.prepareStatement("select booth_id from booths where booth_no=?");
			pstmt.setInt(1, boothNo);
			ResultSet rs=pstmt.executeQuery();
			
			if(rs.next()) {
				boothId=rs.getInt(1);
			}else{
				response.sendRedirect("error.html");
				return;
			}
			
			pstmt=conn.prepareStatement("update voter_details set voter_name=?,voter_epic_no=?,voter_address=?,"
					+ "voter_dob=?,voter_adhar_no=?,voter_mobile_no=?,booth_id=? where voter_id=?");	
			pstmt.setString(1, voterName);
			pstmt.setString(2, epicNo);
			pstmt.setString(3, address);
			pstmt.setString(4, dob);
			pstmt.setString(5, adharNo);
			pstmt.setString(6, mobileNo);
			pstmt.setInt(7, boothId);
			pstmt.setInt(8, voterId);
			pstmt.executeUpdate();
			
			conn.close();	
			
			response.sendRedirect("voterDetails");
			
		}catch(IOException | SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		} 
	}

}
