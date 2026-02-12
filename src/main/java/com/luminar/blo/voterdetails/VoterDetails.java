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

@WebServlet("/voterDetails")
public class VoterDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	final String DRIVER="com.mysql.cj.jdbc.Driver";
	final String URL="jdbc:mysql://localhost:3306/luminar_servlet_project";
	final String USERNAME="root";
	final String PASSWORD="ragil123";
	
	Connection conn=null;
	ResultSet rs=null;
	PreparedStatement pstmt=null;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException{
		try {
			Class.forName(DRIVER);
			conn=DriverManager.getConnection(URL,USERNAME,PASSWORD);
			
			pstmt=conn.prepareStatement("SELECT * FROM voter_information_view;");
			
			rs=pstmt.executeQuery();
			
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			
			//-------------html---------------
			out.print("<html> <body>");
			out.print("<a href='bloHome'>Back to homepage</a><br><br>");
			out.print("<h1>Voter details<h1><br>");
			out.print("<h2><a href='blo/add_voter.html'> Add a new voter</a></h2><br><br>");
			out.print("<table>");
			
			//------------table-head-----------------------
			out.print("<tr>");
			out.print("<th>Sl.no</th>");
			out.print("<th>Voter name</th>");
			out.print("<th>EPIC no</th>");
			out.print("<th>Address</th>");
			out.print("<th>DOB</th>");
			out.print("<th>Adhar no</th>");
			out.print("<th>Mobile no</th>");
			out.print("<th>Booth no</th>");
			out.print("<th>Ward no</th>");
			out.print("<th>Assembly</th>");
			out.print("<th>District</th>");
			out.print("<th>Edit</th>");
			out.print("<th>Delete</th>");
			out.print("</tr>");
			
			//------------table-data-----------------------
			int i=1;
			while(rs.next()) {
				out.print("<tr>");
				out.print("<td>"+i+"</td>");
				out.print("<td>"+rs.getString(2)+"</td>");
				out.print("<td>"+rs.getString(3)+"</td>");
				out.print("<td>"+rs.getString(4)+"</td>");
				out.print("<td>"+rs.getDate(5)+"</td>");
				out.print("<td>"+rs.getString(6)+"</td>");
				out.print("<td>"+rs.getString(7)+"</td>");
				out.print("<td>"+rs.getInt(8)+"</td>");
				out.print("<td>"+rs.getInt(9)+"</td>");
				out.print("<td>"+rs.getString(10)+"</td>");
				out.print("<td>"+rs.getString(11)+"</td>");
				out.print("<td><a href='editVoterDetails?voterId="+rs.getInt(1)+"'>Edit</a></td>");
				out.print("<td><a href='deleteVoterDetails?voterId="+rs.getInt(1)+"'>Delete</a></td>");
				out.print("</tr>");
				i++;
			}
		//---------------table-end-----------------------------
			out.print("</table>");
			out.print("</body> </html>");
			
		}catch(IOException | SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

}
