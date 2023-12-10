package it.unisannio;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedWriter;
import java.io.Console;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Registrate")
public class Registration extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Some processing...

        // Get the RequestDispatcher and forward the request
        RequestDispatcher dispatcher = request.getRequestDispatcher("./registration.html");
        dispatcher.forward(request, response);
    }
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters
        firstName = request.getParameter("firstName");
        lastName = request.getParameter("lastName");
        email = request.getParameter("email");

        // Write form data to a file
        writeToFile(firstName, lastName, email);

        // Set response content type
        response.setContentType("text/html");

        // Create PrintWriter object
        PrintWriter out = response.getWriter();

        // Generate HTML response
        out.println("<html>");
        out.println("<head><title>Registration Confirmation</title></head>");
        out.println("<body>");
        out.println("<p>" + firstName + ", your registration has been executed correctly.</p>");
        out.println("</body>");
        out.println("</html>");
    }

    private void writeToFile(String firstName, String lastName, String email) throws IOException{
        // Specify the path to the file where registration data will be stored
        String filePath = getServletContext().getRealPath("./registration_data.txt");

        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            // Append the registration data to the file
            writer.write("First Name: " + firstName);
            writer.newLine();
            writer.write("Last Name: " + lastName);
            writer.newLine();
            writer.write("Email: " + email);
            writer.newLine();
            writer.newLine(); // Add an empty line for separation
        }
    
    String firstName;
    String lastName;
    String email;
}