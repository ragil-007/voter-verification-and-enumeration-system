package com.luminar.blo.formupload;

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

@WebServlet("/deleteUploadInfo")
public class DeleteUploadInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException{
		try {
			int upId=Integer.parseInt(request.getParameter("upId"));
			int statusId=0;
			java.sql.Date modifiedDate = new java.sql.Date(System.currentTimeMillis());
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/luminar_servlet_project","root","ragil123");
			
			//--------------Collecting form_status_id--------------------------------------------
			PreparedStatement pstmt=conn.prepareStatement("select form_status_id from form_status as fs "
					+ "join forms as f on fs.form_id=f.form_id "
					+ "join form_upload as fu on f.form_id=fu.form_id "
					+ "where upload_id=?; ");
			
			pstmt.setInt(1, upId);
			ResultSet rs=pstmt.executeQuery();
			
			if (rs.next()) {
				statusId = rs.getInt(1);
			} else {
			    response.sendRedirect("error.html");
			    return;
			}
			
			//-----------------------------------deletion-------------------------------
			
			
			pstmt=conn.prepareStatement("delete from form_upload where upload_id=?");
			pstmt.setInt(1, upId);
			pstmt.executeUpdate();
			
			//--------------Updating-form-status-------------------------------------------------
			
			pstmt=conn.prepareStatement("update form_status set form_current_status=?,form_last_updated=? where form_status_id=?");
			pstmt.setString(1, "collected");
			pstmt.setDate(2, modifiedDate);
			pstmt.setInt(3, statusId);
			pstmt.executeUpdate();
			
			conn.close();
			response.sendRedirect("formUpload");
			
		}catch(IOException | SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

}
