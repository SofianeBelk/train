package trainLine.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import trainLine.services.TrainLine;

public class SearchTrain extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		
		String origin= request.getParameter("origin");
		
		
		try {
			out.print(TrainLine.searchTrain(origin));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
