<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <c:import url="${contextPath}/WEB-INF/html/header.jsp"/>
    </head>

    <body>
        <c:import url="${contextPath}/WEB-INF/html/navibar.jsp"/>

        <div class="container">
            <form action="/customer-created" method="post">
                <div class="form-group">
                    <label for="customerName">Customer name:</label><br>
                    <input type="text" class="form-control" id="customerName" placeholder="Enter customer name" name="customerName"><br>
                    <label for="customerLocation">Customer location:</label><br>
                    <input type="text" class="form-control" id="customerLocation" placeholder="Enter customer location" name="customerLocation"><br>
                </div>
                    <input type="submit" value="Submit">
            </form>
        </div>
    </body>
</html>