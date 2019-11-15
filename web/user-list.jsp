<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>User Management Application</title>
<%--    <jsp:useBean id="someModel" scope="request" type="model.User"/>--%>
    <STYLE type="text/css">
        html, body {
            width: 100%;
        }
        table , tr , th , td {
            margin: 0 auto;
            border: 1px solid black;
            text-align: center;
            justify-content: center;
            padding: 5px ;
        }
        div {
            text-align: center;
            justify-content: center
        }
    </STYLE>
    <link rel="stylesheet"   href="style.css">

</head>
<body>

    <div >

        <h1>User Management</h1>
        <h2>
            <a href="new">Add New User</a>
            &nbsp;&nbsp;&nbsp;
            <a href="list">List All Users</a>

        </h2>

        <table    >
            <caption><h2>List of Users</h2></caption>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Country</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="user" items="${listUser}">
                <tr>
                    <td> ${user.id}</td>
                    <td> ${user.name}</td>
                    <td> ${user.email} </td>
                    <td> ${user.country} </td>
                    <td>
                    	<a href="edit?id=${user.id}">Edit</a>
                    	&nbsp;&nbsp;&nbsp;&nbsp;
                    	<a href="delete?id=${user.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>	
</body>
</html>
