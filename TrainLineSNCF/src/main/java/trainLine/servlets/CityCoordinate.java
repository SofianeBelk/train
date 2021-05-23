package trainLine.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import trainLine.services.*;

public class CityCoordinate extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		String cities= request.getParameter("cities");
		//System.out.println(cities);
		String listCities[]=cities.split(",");
		
		try {
			out.print(CitiesCoordinate.getCoordinates(listCities));
		} catch (Exception e) {
			e.printStackTrace();
		}  
		
		
	}

}
