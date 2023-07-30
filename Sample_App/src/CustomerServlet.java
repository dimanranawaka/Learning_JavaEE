import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter writer = resp.getWriter(); // This how we send a response
//        writer.write("Customer Saved!");
        writer.write("<h1>Customer Saved!</h1>"); // Send response as <h1>

        String id = req.getParameter("id"); // Takes data from the form

        String name = req.getParameter("name");

        String address = req.getParameter("address");

        String salary = req.getParameter("salary");

        System.out.println(id);

        System.out.println(name);

        System.out.println(address);

        System.out.println(salary);
    }
}
