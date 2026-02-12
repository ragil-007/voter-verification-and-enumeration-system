package com.luminar.blo.formcollection;

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

@WebServlet("/addCollectionInfo")
public class AddCollectionInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		try {
			int voterId=Integer.parseInt(request.getParameter("voter_id"));
			String collectDate=request.getParameter("collect_date");
			String collectMode=request.getParameter("collect_mode");
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
			
			pstmt=conn.prepareStatement("insert into form_collection(form_id,form_collection_date,"
					+ "form_collection_mode) values(?,?,?)");
			pstmt.setInt(1, formId);
			pstmt.setDate(2, java.sql.Date.valueOf(collectDate));
			pstmt.setString(3, collectMode);
			pstmt.executeUpdate();
			
			//--------------Updating-form-status-------------------------------------------------
			
			pstmt=conn.prepareStatement("update form_status set form_current_status=?,form_last_updated=? where form_id=?");
			pstmt.setString(1, "Collected");
			pstmt.setDate(2, java.sql.Date.valueOf(collectDate));
			pstmt.setInt(3, formId);
			pstmt.executeUpdate();
			
			conn.close();
			response.sendRedirect("formCollection");
			
			
		}catch( SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

}
