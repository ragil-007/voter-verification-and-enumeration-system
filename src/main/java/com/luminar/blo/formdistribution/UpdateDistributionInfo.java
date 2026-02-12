package com.luminar.blo.formdistribution;

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

@WebServlet("/updateDistributionInfo")
public class UpdateDistributionInfo extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException{
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException{
		try {
			int distId=Integer.parseInt(request.getParameter("dist_id"));
			int voterId=Integer.parseInt(request.getParameter("voter_id"));
			String distDate=request.getParameter("dist_date");
			String distMode=request.getParameter("dist_mode");
			String collectorName=request.getParameter("form_collector_name");
			int formId=0;
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/luminar_servlet_project","root","ragil123");
			
			PreparedStatement pstmt=conn.prepareStatement("select form_id from forms where voter_id=?");
			pstmt.setInt(1, voterId);
			ResultSet rs=pstmt.executeQuery();
			
			if (rs.next()) {
			    formId = rs.getInt(1);
			} else {
			    response.sendRedirect("error.html");
			    return;
			}
			
			pstmt=conn.prepareStatement("update form_distribution set form_id=?,form_distribution_date=?,"
					+ "form_distribution_mode=?,form_distribution_collected_by=? where form_distribution_id=?");	
			pstmt.setInt(1, formId);
			pstmt.setDate(2, java.sql.Date.valueOf(distDate));
			pstmt.setString(3, distMode);
			pstmt.setString(4, collectorName);
			pstmt.setInt(5, distId);
			pstmt.executeUpdate();
			
			conn.close();	
			response.sendRedirect("formDistribution");
			
		}catch(IOException | SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		} 
	}

}
