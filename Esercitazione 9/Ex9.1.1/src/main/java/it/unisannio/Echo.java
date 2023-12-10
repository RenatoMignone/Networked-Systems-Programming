package it.unisannio;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Echo")
public class Echo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Echo(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        response.setContentType("text/plain; charset=ISO-8859-1");

        PrintWriter pw = response.getWriter();
        pw.println("Your first name: " + firstName);
        pw.println("Your last name: " + lastName);
    }
}
