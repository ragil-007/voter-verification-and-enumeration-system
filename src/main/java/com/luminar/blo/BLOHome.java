package com.luminar.blo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/bloHome")
public class BLOHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException{
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) 
			throws IOException,ServletException{
		try {
			HttpSession session=request.getSession(false);
			
			if (session == null || session.getAttribute("user") == null) {
			    response.sendRedirect("index.html");
			    return;
			}
			
			String user=(String) session.getAttribute("user");
			
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			
			out.print("<html> <body>");
			out.print("<img src='blo/images/logo.png' alt='Project Logo' height='100' width='100' />");
			out.print("<h3 align='right'><a href='logout'>Logout</a></h3>");
			out.print("<h2>Welcome "+user.toUpperCase()+"</h2>");

			out.print("<h1 align='center'><u>BLO HOME</u></h1>");
			out.print("<div>");
			out.print("<h3><a href='voterDetails'>[1] Voter information</a></h3>");
			out.print("<h3><a href='formDistribution'>[2] Form distribution details</a></h3>");
			out.print("<h3><a href='formCollection'>[3] Form collection details</a></h3>");
			out.print("<h3><a href='formUpload'>[4] Form upload status</a></h3>");
			out.print("<h3><a href='formStatus'>[5] Track form status</a></h3>");
			out.print("<h3><a href='reports'>[6] Reports</a></h3>");
			out.print("</div>");
			out.print("</body> </html>");
			
			out.close();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
