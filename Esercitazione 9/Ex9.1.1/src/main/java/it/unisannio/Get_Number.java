package it.unisannio;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Counter")
public class Get_Number extends HttpServlet {
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Num+=1;
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Calculator Result</title></head><body>");
        out.println("<h2>Number of times that the Get method is called</h2>");
        out.println("<p>"+"you called this metod "+Num +" times");
        out.println("</body></html>");
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {    	

        // Send the result to the browser

    }
    int Num;
}
