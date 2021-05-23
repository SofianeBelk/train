package trainLine.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trainLine.services.User;

public class Registration extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json");
		
		PrintWriter out = response.getWriter();
		String nom =  request.getParameter("nom");
		String prenom =  request.getParameter("prenom");
		String pseudo=  request.getParameter("pseudo");
		String password =  request.getParameter("motDePasse");
		String email = request.getParameter("email");
		int age =  Integer.parseInt(request.getParameter("age"));
		String telephone = request.getParameter("telephone");
		
		try {
			out.print(User.addUser(nom, prenom, pseudo, email, password, age,telephone));
			
		} catch (Exception e) {
			out.print(e.getMessage());
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


}
