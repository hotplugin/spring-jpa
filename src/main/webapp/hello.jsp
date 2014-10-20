<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
  
    <c:import url="header.jsp" charEncoding="UTF-8" />
    <body>
         <h3>${name}</h3>
        <h3>${msg}</h3>
        <c:forEach var="listValue" items="${lists}">
        <li>${listValue}</li>
        </c:forEach>
    <h1>2. Test CSS</h1>

    <h2>2. Test JS</h2>
    <div id="msg"></div>
    <table>
        <thead><th>First </th><th>Last </th></thead>
    <c:forEach items="${list}" var="s">
        
        <tr>
            <td>
        <c:out value="${s.firstName}"/></td>
        <td><c:out value="${s.lastName}"/></td>
        </tr>
    </c:forEach>
        </table>
    <form:form action="add" method="post" modelAttribute="student">
        <form:input path="firstName"/>
            <form:input path="lastName"/>
        <input type="submit" value="go"/>
        
    </form:form>
</body>
</html>