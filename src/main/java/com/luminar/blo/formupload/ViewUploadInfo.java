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

@WebServlet("/formUpload")
public class ViewUploadInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/luminar_servlet_project","root","ragil123");
			
			PreparedStatement pstmt=conn.prepareStatement("select * from form_upload_view");
			ResultSet rs=pstmt.executeQuery();
			
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			
			//---------HTML---start-----------
			out.print("<html><body>");
			out.print("<a href='bloHome'>Back to homepage</a><br>");
			out.print("<h1><u>Form upload info</u></h1><br><br>");
			out.print("<h2><a href='blo/add_upload_info.html'> Add form upload info</a></h2><br><br>");
			out.print("<table>");
			
			//--------Table----heading----------
			out.print("<tr>");
			out.print("<th>Sl.no</th>");
			out.print("<th>Voter ID</th>");
			out.print("<th>Voter name</th>");
			out.print("<th>Upload date</th>");
			out.print("<th>Modified date</th>");
			out.print("<th>Upload status</th>");
			out.print("<th>Remarks</th>");
			out.print("</tr>");
			
			//--------Table------data---------
			
			int i=1;
			while(rs.next()) {
				out.print("<tr>");
				out.print("<td>"+i+"</td>");
				out.print("<td>"+rs.getInt(2)+"</td>");
				out.print("<td>"+rs.getString(3)+"</td>");
				out.print("<td>"+rs.getDate(4)+"</td>");
				out.print("<td>"+rs.getDate(5)+"</td>");
				out.print("<td>"+rs.getString(6)+"</td>");
				out.print("<td>"+rs.getString(7)+"</td>");
				out.print("<td><a href='editUploadInfo?upId="+rs.getInt(1)+"'>Edit</a></td>");
				out.print("<td><a href='deleteUploadInfo?upId="+rs.getInt(1)+"'>Delete</a></td>");
				out.print("</tr>");
				i++;
			}
			
			//---------Table-content-end-----------------
			
			out.print("</table>");
			out.print("</body> </html>");
			
			//---------HTML-end-------------------------------
			
			out.close();
			conn.close();
			
			
		}catch(IOException | SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		
	}

}
