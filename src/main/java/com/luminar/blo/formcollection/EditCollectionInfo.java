package com.luminar.blo.formcollection;

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

@WebServlet("/editCollectionInfo")
public class EditCollectionInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException{
		try {
			int collectId=Integer.parseInt(request.getParameter("collectID"));

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/luminar_servlet_project","root","ragil123");
			
			PreparedStatement pstmt=conn.prepareStatement("select * from form_collection_info where form_collection_id=?");
			pstmt.setInt(1, collectId);
			ResultSet rs=pstmt.executeQuery();
			
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			
			out.print("<html><body>");
			out.print("<a href='formCollection'>Go back</a><br>");
			out.print("<h3><u>Edit form collection details</u></h3>");
			out.print("<form name='editCollectInfo' method='post' action='updateCollectionInfo'>");
			while(rs.next()) {
				out.print("<input type='hidden' name='collect_id' value='"+rs.getInt(1)+"' /><br><br>");
				out.print("Voter Id:<input type='text' name='voter_id' value='"+rs.getInt(2)+"' /><br><br>");
				out.print("Date of collection:<input type='text' name='collect_date' value='"+rs.getDate(4)+"' /><br><br>");
				out.print("Form collection mode:<input type='text' name='collect_mode' value='"+rs.getString(5)+"' /><br><br>");
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
