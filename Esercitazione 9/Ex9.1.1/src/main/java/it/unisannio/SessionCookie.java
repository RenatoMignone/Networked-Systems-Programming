package it.unisannio;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/SessionCookie")
public class SessionCookie extends HttpServlet {
       
    public SessionCookie() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mycontacts = 0;
		HttpSession session = request.getSession();
		if(!session.isNew()) mycontacts = (Integer)session.getAttribute("mycontacts");
		session.setAttribute("mycontacts", ++mycontacts);
		
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Calculator Result</title></head><body>");
        out.println("<h2>Number of times that the Get method is called</h2>");
        out.println("<p>"+"you called this metod "+mycontacts +" times");
        out.println("</body></html>");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
