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

@WebServlet("/addDistributionInfo")
public class AddDistributionInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("resource")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		try {
			int voterId=Integer.parseInt(request.getParameter("voter_id"));
			String distDate=request.getParameter("dist_date");
			String distMode=request.getParameter("dist_mode");
			String formCollector=request.getParameter("form_collector_name");
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
			
			pstmt=conn.prepareStatement("insert into form_distribution(form_id,form_distribution_date,form_distribution_mode,"
					+ "form_distribution_collected_by) values(?,?,?,?)");
			pstmt.setInt(1, formId);
			pstmt.setDate(2, java.sql.Date.valueOf(distDate));
			pstmt.setString(3, distMode);
			pstmt.setString(4, formCollector);
			pstmt.executeUpdate();
			
			//--------------Updating-form-status-------------------------------------------------
			
			pstmt=conn.prepareStatement("select form_status_id from form_status where form_id=?");
			pstmt.setInt(1, formId);
			rs=pstmt.executeQuery();
			
			if (rs.next()) {
				int statusId=rs.getInt(1);
				pstmt=conn.prepareStatement("update form_status set form_current_status=?,form_last_updated=? where form_status_id=?");
				pstmt.setString(1, "Collected");
				pstmt.setDate(2, java.sql.Date.valueOf(distDate));
				pstmt.setInt(3, statusId);
				pstmt.executeUpdate();
			} else {
				pstmt=conn.prepareStatement("insert into form_status(form_id,form_current_status,"
						+ "form_last_updated) values(?,?,?)");
				pstmt.setInt(1, formId);
				pstmt.setString(2, "Distributed");
				pstmt.setDate(3, java.sql.Date.valueOf(distDate));
				pstmt.executeUpdate();
			}
			
			
			conn.close();
			response.sendRedirect("formDistribution");
			
			
			
			
		}catch( SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

}
