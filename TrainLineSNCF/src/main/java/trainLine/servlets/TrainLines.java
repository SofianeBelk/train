package trainLine.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trainLine.services.TrainLine;



public class TrainLines extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
    
    public TrainLines() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		response.setContentType("text/JSON");
		PrintWriter out =response.getWriter();
		
	/*	try {
			out.print(TrainLine.getAllTrainLine());
		} catch (Exception e) {
			out.print(e.getMessage());
		} */
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
