package com.luminar.blo.voterdetails;

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

@WebServlet("/editVoterDetails")
public class EditVoterDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException{
		try {
			int voterId=Integer.parseInt(request.getParameter("voterId"));

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/luminar_servlet_project","root","ragil123");
			
			PreparedStatement pstmt=conn.prepareStatement("select * from voter_information_view where voter_id=?");
			pstmt.setInt(1, voterId);
			ResultSet rs=pstmt.executeQuery();
			
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			
			out.print("<html><body>");
			out.print("<a href='voterDetails'>Go back</a><br>");
			out.print("<h3><u>Edit voter details</u></h3>");
			out.print("<form name='editVoterDetails' method='post' action='updatevoterDetails'>");
			while(rs.next()) {
				out.print("<input type='hidden' name='voter_id' value='"+rs.getInt(1)+"' /><br><br>");
				out.print("<input type='text' name='voter_name' value='"+rs.getString(2)+"' /><br><br>");
				out.print("<input type='text' name='epic_no' value='"+rs.getString(3)+"' /><br><br>");
				out.print("<input type='text' name='address' value='"+rs.getString(4)+"' /><br><br>");
				out.print("<input type='date' name='dob' value='"+rs.getString(5)+"' /><br><br>");
				out.print("<input type='text' name='adhar_no' value='"+rs.getString(6)+"' /><br><br>");
				out.print("<input type='text' name='mobile_no' value='"+rs.getString(7)+"' /><br><br>");
				out.print("<input type='text' name='booth_no' value='"+rs.getString(8)+"' /><br><br>");
				out.print("<input type='text' name='ward_no' value='"+rs.getString(9)+"' /><br><br>");
				out.print("<input type='text' name='assembly' value='"+rs.getString(10)+"' /><br><br>");
				out.print("<input type='text' name='district' value='"+rs.getString(11)+"' /><br><br>");
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
