package trainLine.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trainLine.services.GeneratePromoCode;

public class PromoCodeGenerator extends HttpServlet{


	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json");
		
		PrintWriter out = response.getWriter();
		String username= request.getParameter("userName");
		String dest= request.getParameter("destination");
		
		try {
			out.print(GeneratePromoCode.generatePromoCode(username , dest));
		} catch (Exception e) {
			e.printStackTrace();
		}  

	}
	
}
