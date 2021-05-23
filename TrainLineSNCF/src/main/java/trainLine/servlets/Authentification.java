package trainLine.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trainLine.services.User;

public class Authentification extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json");
		
		PrintWriter out = response.getWriter();

		String pseudo=  request.getParameter("pseudo");
		String password =  request.getParameter("motDePasse");
		
		try {
			System.out.println("j'ai recu request authentification");
			out.print(User.checkUser(pseudo,password));
			
		} catch (Exception e) {
			out.print(e.getMessage());
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


}
