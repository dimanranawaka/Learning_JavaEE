package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        double salary = Double.parseDouble(req.getParameter("salary"));
        String option = req.getParameter("option");

//        System.out.println(id+" "+" "+name+" "+address+" "+salary);

        try {

            // 1. Load JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Create the Connection using Driver Manager

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/posapp", "root", "1234");

            // 3. Create Prepared Statement

            if (option.equals("add")){

                PreparedStatement pstm = connection.prepareStatement("insert into customer values (?,?,?,?)");

                pstm.setObject(1,id);

                pstm.setObject(2,name);

                pstm.setObject(3,address);

                pstm.setObject(4,salary);

                // 4. Execute Query / Execute Update

                boolean b = pstm.executeUpdate() > 0;

                PrintWriter writer = resp.getWriter();

                writer.write("<h1>Customer Added State : "+b+"</h1>");

            }


        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
