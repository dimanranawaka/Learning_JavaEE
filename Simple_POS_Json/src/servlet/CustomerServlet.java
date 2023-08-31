package servlet;

import dto.CustomerDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {

    ArrayList<CustomerDTO> allCustomer = new ArrayList<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String option = req.getParameter("option");





        // Initialize database connection

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Taking connection from DriverManager

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/posapp", "root", "1234");


            if (option.equals("getAll")){

                PreparedStatement preparedStatement = connection.prepareStatement("select * from customer");

                ResultSet rst = preparedStatement.executeQuery(); //executeQuery used for get result set from customer table

                if (allCustomer.isEmpty()){

                    while (rst.next()){

                        String id = rst.getString(1);

                        String name = rst.getString(2);

                        String address = rst.getString(3);

                        double salary = rst.getDouble(4);

                        allCustomer.add(new CustomerDTO(id,name,address,salary));
                    }
                }


                for (CustomerDTO customer : allCustomer) {
                        resp.getWriter().println("<tr><td>"+customer.getId()+"</td><td>"+customer.getName()+"</td><td>"+customer.getAddress()+"</td><td>"+customer.getSalary()+"</td></tr>");
                }

            }



        } catch (Exception e) {

            e.printStackTrace();

        }
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

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");

//        String option = req.getParameter("option");

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/posapp", "root", "1234");


                PreparedStatement statement = connection.prepareStatement("DELETE  FROM customer WHERE id=?");

                statement.setObject(1, id);

                boolean b = statement.executeUpdate()>0;




        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");


    }
}
