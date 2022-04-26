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
            <form action="/company-created" method="post">
                <div class="form-group">
                    <label for="companyName">Company name:</label><br>
                    <input type="text" class="form-control" id="companyName" placeholder="Enter company name" name="companyName"><br>
                    <label for="companyLocation">Company location:</label><br>
                    <input type="text" class="form-control" id="companyLocation" placeholder="Enter company location" name="companyLocation"><br>
                </div>
                    <input type="submit" value="Submit">
            </form>
        </div>
    </body>
</html>