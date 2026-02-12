package com.luminar.blo.formdistribution;

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

@WebServlet("/editDistributionInfo")
public class EditDistributionInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException{
		try {
			int distId=Integer.parseInt(request.getParameter("distID"));

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/luminar_servlet_project","root","ragil123");
			
			PreparedStatement pstmt=conn.prepareStatement("select * from form_distribution_view where form_distribution_id=?");
			pstmt.setInt(1, distId);
			ResultSet rs=pstmt.executeQuery();
			
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			
			out.print("<html><body>");
			out.print("<a href='formDistribution'>Go back</a><br>");
			out.print("<h3><u>Edit form distribution details</u></h3>");
			out.print("<form name='editDistInfo' method='post' action='updateDistributionInfo'>");
			while(rs.next()) {
				out.print("<input type='hidden' name='dist_id' value='"+rs.getInt(1)+"' /><br><br>");
				out.print("Voter Id:<input type='text' name='voter_id' value='"+rs.getInt(2)+"' /><br><br>");
				out.print("Date of distribution:<input type='text' name='dist_date' value='"+rs.getDate(5)+"' /><br><br>");
				out.print("Mode of distribution:<input type='text' name='dist_mode' value='"+rs.getString(6)+"' /><br><br>");
				out.print("Collected_by:<input type='text' name='form_collector_name' value='"+rs.getString(7)+"' /><br><br>");
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
