<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

    <c:import url="header.jsp" charEncoding="UTF-8" />
    <body>
        <div style="background-color: #fff;padding: 10px 20px;">
            <h1 style="text-decoration: underline;"><a href="<c:url value="/"/>" style="float:right;">Home</a></h1>
        <h2>Upload Section</h2>
        <form:form action="uploadfile" method="post" enctype="multipart/form-data">
            File to upload: <input type="file" name="file"><br /> 
            <input type="submit" value="Upload"/>
        </form:form>
        <h3> ${successmsg}</h3>
        <h4> ${msg}</h4>
        <br/>
        <hr/>
        <h2>Download Section</h2>
        <form:form action="queryfile" method="post">
            <table>
                <tr>
                    
                    <td>From Time:</td> <td><input type="text" name="from" value="2015.01.04 22:00:03.845" size="35"></td>
                </tr>
                <tr><td>To Time: </td><td><input type="text" name="to" value="2015.01.04 22:00:05.350" size="35"></td></tr>
                <tr><td>File Location and Name:</td><td> <input type="text" name="flocation" value="F:\\ask.csv" size="35"/></td></tr>
                <tr><td></td><td><input type="submit" value="Download"/></td></tr>
            </table>
        </form:form>
            <h4> ${msgd}</h4>
            </div>
    </body>
</html>