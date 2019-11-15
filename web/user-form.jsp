<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>User Management Application</title>
	<style><%@include file="/css/style.css"%></style>
</head>
<body>
    <div >
		<h1>User Management</h1>
		<h2>
<%--			<a href="insert">Add New User</a>--%>
			&nbsp;&nbsp;&nbsp;
			<a href="list">List All Users</a>

		</h2>

		<c:if  test="${user != null}">
			<form action="update" method="post">
        </c:if>
        <c:if test="${user == null}">
			<form action="insert" method="post">
        </c:if>
        <table  >
            <caption>
            	<h2>
            		<c:if test="${user != null}">
            			Edit User
            		</c:if>
<%--            		<c:if test="${user == null}">--%>
<%--            			Add New User--%>
<%--            		</c:if>--%>
            	</h2>
            </caption>
        		<c:if test="${user != null}">
        			<input type="hidden" name="id" value="<c:out value='${user.id}' />" />
        		</c:if>            
            <tr>
                <th>User Name: </th>
                <td>
                	<input type="text" name="name" size="45"
                			value="<c:out value='${user.name}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>User Email: </th>
                <td>
                	<input type="text" name="email" size="45"
                			value="<c:out value='${user.email}' />"
                	/>
                </td>
            </tr>
            <tr>
                <th>Country: </th>
                <td>
                	<input type="text" name="country" size="15"
                			value="<c:out value='${user.country}' />"
                	/>
                </td>
            </tr>
            <tr>
            	<td colspan="2" align="center">
            		<input type="submit" value="Save" />
            	</td>
            </tr>
        </table>
        </form>
    </div>	
</body>
</html>
