package it.unisannio;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Calculator")
public class calculator extends HttpServlet {
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("./calculator.html");
        dispatcher.forward(request, response);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // Retrieve parameters from the form
        String operand1Str = request.getParameter("operand1");
        String operator = request.getParameter("operator");
        String operand2Str = request.getParameter("operand2");

        // Convert parameters to double
        double operand1 = Double.parseDouble(operand1Str);
        double operand2 = Double.parseDouble(operand2Str);

        // Perform the calculation based on the operator
        double result = 0;
        switch (operator) {
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                result = operand1 - operand2;
                break;
            case "*":
                result = operand1 * operand2;
                break;
            case "/":
                if (operand2 != 0) {
                    result = operand1 / operand2;
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Cannot divide by zero");
                    return;
                }
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid operator");
                return;
        }

        // Send the result to the browser
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Calculator Result</title></head><body>");
        out.println("<h2>Calculator Result</h2>");
        out.println("<p>" + operand1 + " " + operator + " " + operand2 + " = " + result + "</p>");
        out.println("</body></html>");
    }
}
