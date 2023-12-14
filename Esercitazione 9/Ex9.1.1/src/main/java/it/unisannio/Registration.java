package it.unisannio;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

@WebServlet("/Registration")
public class Registration extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("./registration.html");
        dispatcher.forward(request, response);
    }
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	writeToFile(request);

        // Set response content type
        response.setContentType("text/html");

        // Create PrintWriter object
        PrintWriter out = response.getWriter();

        // Generate HTML response
        out.println("<html>");
        out.println("<head><title>Registration Confirmation</title></head>");
        out.println("<body>");
        out.println("<p>" + request.getParameter("firstName") + ", your registration has been executed correctly.</p>");
        out.println("</body>");
        out.println("</html>");
    }

    private void writeToFile(HttpServletRequest request) throws IOException{
        // Specify the path to the file where registration data will be stored
        
    		String filePath = "C:\\Users\\renat\\eclipse-workspace\\registrazioni.txt";
        	File f = new File(filePath);
        	FileOutputStream fos = new FileOutputStream(f);
        	PrintStream ps = new PrintStream(fos);
        	       
        	ps.println("nome    :"+request.getParameter("firstName"));
        	ps.println("cognome :"+request.getParameter("lastName"));
        	ps.println("email   :"+request.getParameter("email"));
        	ps.println("password:"+request.getParameter("password"));
        	ps.close();         
    }
	    
}