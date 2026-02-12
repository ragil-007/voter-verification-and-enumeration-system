package com.luminar.blo.reports;

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

@WebServlet("/reports")
public class Reports extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		try {
			int totalVoters=0;
			int distributedForms=0;
			int nonDistributedForms=0;
			int collectedForms=0;
			int nonCollectedForms=0;
			int totalUploadedForms=0;
			int nonUploadedForms=0;
			int uploadSuccess=0;
			int uploadRejected=0;
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/luminar_servlet_project","root","ragil123");
			
			//----------------------totalVoters------------------------------------------------------
			
			PreparedStatement pstmt=conn.prepareStatement("select count(*) as voters_count from voter_details");
			ResultSet rs=pstmt.executeQuery();
			
			while(rs.next()) {
				totalVoters=rs.getInt(1);
			}
			//------------------------distributedForms---------------------------------------------
			
			pstmt=conn.prepareStatement("select count(*) as distribution_count from form_distribution");
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				distributedForms=rs.getInt(1);
			}
			
			//---------------------nonDistributedForms---------------------------------
			
			if(totalVoters!=0)
				nonDistributedForms=totalVoters-distributedForms;
			
			//---------------------collectedForms---------------------------------------
			
			pstmt=conn.prepareStatement("select count(*) as collection_count from form_collection");
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				collectedForms=rs.getInt(1);
			}
			//---------------------nonCollectedForms------------------------------------
			if(totalVoters!=0)
				nonCollectedForms=totalVoters-distributedForms;
			
			//---------------------totalUploadedForms-------------------------------------------
			
			pstmt=conn.prepareStatement("select count(*) as upload_count from form_upload");
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				totalUploadedForms=rs.getInt(1);
			}
			
			//--------------------nonUploadedForms----------------------------------------
			
			if(totalVoters!=0)
				nonUploadedForms=totalVoters-totalUploadedForms;
			
			//---------------------uploadSuccess----------------------------------------
			
			pstmt=conn.prepareStatement("select count(*) as upload_count from form_upload where LOWER(upload_status) = 'success'");
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				uploadSuccess=rs.getInt(1);
			}
			
			//-------------------uploadRejected--------------------------------------
			if(totalUploadedForms!=0)
				uploadRejected=totalUploadedForms-uploadSuccess;
			
			//----------------------HTML----------------------------------------
			
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			
			out.print("<html><body>");
			out.print("<a href='bloHome'>Back to homepage</a><br>");
			out.print("<h1><u>Reports</u></h1><br><br>");
			
			out.print("<h3><u># Voters</u></h3>");
			
			out.print("<table>");
			out.print("<tr>");
			out.print("<td>Total number of Voters : </td>");
			out.print("<td>"+totalVoters+"</td>");
			out.print("</tr>");
			out.print("</table>");
			
			out.print("<br>");
			out.print("<h3><u># Form Distribution</u></h3>");
			
			out.print("<table>");
			out.print("<tr>");
			out.print("<td>Total Distributed forms :</td>");
			out.print("<td>"+distributedForms+"</td>");
			out.print("</tr>");
			
			out.print("<tr>");
			out.print("<td>Non distributed forms :</td>");
			out.print("<td>"+nonDistributedForms+"</td>");
			out.print("</tr>");
			out.print("</table>");
			
			out.print("<br>");
			out.print("<h3><u># Form Collection</u></h3>");
			
			out.print("<table>");
			out.print("<tr>");
			out.print("<td>Total Collected forms :</td>");
			out.print("<td>"+collectedForms+"</td>");
			out.print("</tr>");
			
			out.print("<tr>");
			out.print("<td> Non Collected forms :</td>");
			out.print("<td>"+nonCollectedForms+"</td>");
			out.print("</tr>");
			out.print("</table>");
			
			out.print("<br>");
			out.print("<h3><u># Form Upload</u></h3>");
			
			out.print("<table>");
			out.print("<tr>");
			out.print("<td>Total upload submissions :</td>");
			out.print("<td>"+totalUploadedForms+"</td>");
			out.print("</tr>");
			
			out.print("<tr>");
			out.print("<td>Pending upload submissions :</td>");
			out.print("<td>"+nonUploadedForms+"</td>");
			out.print("</tr>");
			
			out.print("<tr>");
			out.print("<td>Successful form uploads :</td>");
			out.print("<td>"+uploadSuccess+"</td>");
			out.print("</tr>");
			
			out.print("<tr>");
			out.print("<td>Rejected form uploads :</td>");
			out.print("<td>"+uploadRejected+"</td>");
			out.print("</tr>");
			
			out.print("</table>");
			out.print("</body> </html>");
			
			
		}catch(SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		
	}
	

}
