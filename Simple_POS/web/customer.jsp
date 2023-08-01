<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Diman Ranawaka
  Date: 8/1/2023
  Time: 1:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String name = "IJSE";

    int age = 26;

    ArrayList allColors = new ArrayList<>();

//    System.out.println("Hello!");

%>
<h1>Hello, there !</h1>

<h2><%=name%></h2> <%--// This is how we Inject Java variable value to html syntax for printing--%>

<%-- Expression --%>

<%=name%>

</body>


</html>
