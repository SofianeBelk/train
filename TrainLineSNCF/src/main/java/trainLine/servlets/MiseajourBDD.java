package trainLine.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import trainLine.services.TrainLine;

public class MiseajourBDD  extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		try {
			
			JSONObject re = TrainLine.MiseAJour();
			out.println(re.get("a"));
			out.println(re.get("b"));
			out.println(re.get("c"));
			
		} catch (Exception e) {
			out.print(e.getMessage());
		}
	}
}

