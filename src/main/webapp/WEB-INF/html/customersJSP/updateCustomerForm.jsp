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
            <form action="/customer-updated" method="post">
                <div class="form-group">
                    <label for="customerId">Customer id:</label><br>
                    <input type="number" class="form-control" id="customerId" placeholder="Enter customer id" name="customerId"><br>
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