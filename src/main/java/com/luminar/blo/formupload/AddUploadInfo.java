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

@WebServlet("/addUploadInfo")
public class AddUploadInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		try {
			int voterId=Integer.parseInt(request.getParameter("voter_id"));
			String distDate=request.getParameter("dist_date");
			String remarks=request.getParameter("remarks");
			String uploadStatus=request.getParameter("upload_status");
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
			
			pstmt=conn.prepareStatement("insert into form_upload(form_id,upload_date,modified_date,upload_status,remarks) values(?,?,?,?,?)");
			pstmt.setInt(1, formId);
			pstmt.setDate(2, java.sql.Date.valueOf(distDate));
			pstmt.setDate(3, java.sql.Date.valueOf(distDate));
			pstmt.setString(4, uploadStatus);
			pstmt.setString(5, remarks);
			pstmt.executeUpdate();
			
			//--------------Updating-form-status-------------------------------------------------
			
			pstmt=conn.prepareStatement("update form_status set form_current_status=?,form_last_updated=? where form_id=?");
			
			if(uploadStatus.toLowerCase()=="success") 
				pstmt.setString(1, "Upload/Success");
			else
				pstmt.setString(1, "Upload/fail");
			
			pstmt.setDate(2, java.sql.Date.valueOf(distDate));
			pstmt.setInt(3, formId);
			pstmt.executeUpdate();
			
			conn.close();
			response.sendRedirect("formUpload");
			
			
		}catch( SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

}
