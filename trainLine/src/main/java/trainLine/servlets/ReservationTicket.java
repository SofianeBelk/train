package trainLine.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import trainLine.services.Reservation;


public class ReservationTicket  extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		
		String key= request.getParameter("key");
		String idTrain= request.getParameter("idTrain");
		
		
		
		try {
			out.print(Reservation.bookticket(idTrain,key));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
