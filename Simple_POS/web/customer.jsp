
<%@ page import="java.util.ArrayList" %>
<%@ page import="dto.CustomerDTO" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: MALIDHUWA KV
  Date: 8/1/2023
  Time: 1:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Customer Manage</title>
    <meta content="width=device-width initial-scale=1" name="viewport">
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/css/styles.css" rel="stylesheet">
    <link crossorigin="anonymous" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
          integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" rel="stylesheet">
    <style>
        ul > li {
            cursor: pointer;
        }
    </style>
</head>
<body>

    <%
        ArrayList<CustomerDTO> allCustomer = new ArrayList<>();

//        allCustomer.add(new CustomerDTO("C001","Dasun","Galle",10000));
//        allCustomer.add(new CustomerDTO("C002","Hanaska","Haraduwa",20000));
//        allCustomer.add(new CustomerDTO("C003","Ravindu","Wanduramba",30000));
//        allCustomer.add(new CustomerDTO("C004","Sanchana","Karandeniya",40000));
//        allCustomer.add(new CustomerDTO("C005","Diman","Matara",50000));

        // Initialize database connection

        Class.forName("com.mysql.cj.jdbc.Driver");

        // Taking connection from DriverManager

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pos", "root", "1234");

        PreparedStatement preparedStatement = connection.prepareStatement("select * from customer");

        ResultSet rst = preparedStatement.executeQuery(); //executeQuery used for get result set from customer table

       while (rst.next()){

           String id = rst.getString(1);

           String name = rst.getString(2);

           String address = rst.getString(3);

           double salary = rst.getDouble(4);

           allCustomer.add(new CustomerDTO(id,name,address,salary));
       }


    %>

<!--header-->
<header class="jumbotron bg-primary text-white p-3">
    <h1 class="position-absolute" id="nav"></h1>
    <ul class="list-group list-group-horizontal text-danger justify-content-end font-weight-bold">
        <li class="list-group-item bg-white" id="lnkHome"><a href="index.html">Home</a></li>
        <li class="list-group-item bg-danger text-white" id="lnkCustomer"><a class="text-white" href="customer.jsp">Customer</a>
        </li>
        <li class="list-group-item bg-white" id="lnkItem"><a href="item.html">Item</a></li>
        <li class="list-group-item bg-white" id="lnkOrders"><a href="purchase-order.html">Orders</a></li>
    </ul>
</header>

<!--customer content-->
<main class="container-fluid" id="customerContent">
    <section class="row">
        <div class="col-12 col-lg-4">
            <h1>Customer Registration</h1>

            <form id="customerForm">
            <div class="form-group">
                <label for="txtCustomerID">Customer ID</label>
                <input class="form-control" id="txtCustomerID" required type="text" name="id">
                <span class="control-error" id="lblcusid"></span>
            </div>
            <div class="form-group">
                <label for="txtCustomerName">Customer Name</label>
                <input class="form-control" id="txtCustomerName" type="text" name="name">
                <span class="control-error" id="lblcusname"></span>
            </div>
            <div class="form-group">
                <label for="txtCustomerAddress">Customer Address</label>
                <input class="form-control" id="txtCustomerAddress" type="text" name="address">
                <span class="control-error" id="lblcusaddress"></span>
            </div>
            <div class="form-group">
                <label for="txtCustomerSalary">Customer Salary</label>
                <input class="form-control" id="txtCustomerSalary" type="text" name="salary">
                <span class="control-error" id="lblcussalary"></span>
            </div>

            </form>
            <div class="btn-group">
                <button class="btn btn-primary" id="btnCustomer" form="customerForm" formaction="customer" formmethod="post">Save Customer</button>
                <button class="btn btn-danger" id="btnCusDelete">Remove</button>
                <button class="btn btn-warning" id="btnUpdate">Update</button>
                <button class="btn btn-success" id="btnGetAll" form="customerForm" formaction="customer.jsp">Get All</button>
                <button class="btn btn-danger" id="btn-clear1">Clear All</button>
            </div>

        </div>
        <div class="col-12 col-lg-8 mt-3">
            <table class="table table-bordered table-hover">
                <thead class="bg-danger text-white">
                <tr>
                    <th>Customer ID</th>
                    <th>Customer Name</th>
                    <th>Customer Address</th>
                    <th>Customer Salary</th>
                </tr>
                </thead>
                <tbody id="tblCustomer">
                    <%
                        for (CustomerDTO customer : allCustomer) {
                    %>

                    <tr>
                        <td><%=customer.getId()%></td>
                        <td><%=customer.getName()%></td>
                        <td><%=customer.getAddress()%></td>
                        <td><%=customer.getSalary()%></td>

                    </tr>

                    <%
                        }

                    %>
                </tbody>
            </table>
        </div>
    </section>
</main>


<script src="assets/js/jquery-3.6.1.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>

<script>
    //select the button and add an event to it
    // document.getElementById("btnCustomer").addEventListener("click", function () {
    //
    //     //get typed values of input fields
    //     let id = document.getElementById("txtCustomerID").value;
    //     let name = document.getElementById("txtCustomerName").value;
    //     let address = document.getElementById("txtCustomerAddress").value;
    //     let salary = document.getElementById("txtCustomerSalary").value;
    //
    //     //let's print it
    //     console.log(id,name,address,salary);
    //
    //     //catch the table body
    //     let tBody=document.getElementById("tblCustomer");
    //
    //     // let's see how we should insert a table row using html format
    //     //<tr>
    //     // <td>id</td>
    //     // <td>name</td>
    //     // <td>address</td>
    //     // <td>salary</td>
    //     // </tr>
    //
    //     //ok then, let's create the above row
    //    let tr= document.createElement("tr");
    //
    //    //let's create another four columns
    //    let col1= document.createElement("td");
    //    let col2= document.createElement("td");
    //    let col3= document.createElement("td");
    //    let col4= document.createElement("td");
    //
    //    //set input fields values to the above created columns
    //     col1.textContent=id;
    //     col2.textContent=name;
    //     col3.textContent=address;
    //     col4.textContent=salary;
    //
    //     //set 4 columns to the previously created row
    //     tr.appendChild(col1);
    //     tr.appendChild(col2);
    //     tr.appendChild(col3);
    //     tr.appendChild(col4);
    //
    //     //set the row to the table body
    //     tBody.appendChild(tr);
    //
    //     //Work done;
    // });
</script>
</body>

</html>
