package com.luminar.blo.formupload;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/editUploadInfo")
public class EditUploadInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException{
		try {
			int upId=Integer.parseInt(request.getParameter("upId"));

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/luminar_servlet_project","root","ragil123");
			
			PreparedStatement pstmt=conn.prepareStatement("select * from form_upload_view where upload_id=?");
			pstmt.setInt(1, upId);
			ResultSet rs=pstmt.executeQuery();
			
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			
			out.print("<html><body>");
			out.print("<a href='formUpload'>Go back</a><br>");
			out.print("<h3><u>Edit form upload details</u></h3>");
			out.print("<form name='editUploadInfo' method='post' action='updateUploadInfo'>");
			while(rs.next()) {
				out.print("<input type='hidden' name='upload_id' value='"+rs.getInt(1)+"' /><br><br>");
				out.print("Voter Id: <input type='text' name='voter_id' value='"+rs.getInt(2)+"' /><br><br>");
				out.print("Upload status: <input type='text' name='upload_status' value='"+rs.getString(6)+"' /><br><br>");
				out.print("remarks: <input type='text' name='remarks' value='"+rs.getString(7)+"' /><br><br>");
			}
			out.print("<input type='submit' name='update' />");
			out.print("</form>");
			out.print("</body></html>");
			out.close();
			conn.close();
			
		}catch(IOException | SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		
	}

}
