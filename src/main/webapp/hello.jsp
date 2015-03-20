<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

    <c:import url="header.jsp" charEncoding="UTF-8" />
    <body>
        <form:form action="uploadfile" method="post" enctype="multipart/form-data">
            File to upload: <input type="file" name="file"><br /> 
            <input type="submit" value="Upload"/>
        </form:form>
            <h3> ${successmsg}</h3>
            <h4> ${msg}</h4>
            <br/>
             <form:form action="queryfile" method="post">
            From Time: <input type="text" name="from"><br /> 
            To Time: <input type="text" name="to"><br /> 
            <input type="submit" value="Download"/>
        </form:form>
            
    </body>
</html>