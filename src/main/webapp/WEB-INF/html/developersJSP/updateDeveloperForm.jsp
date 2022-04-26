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
            <form action="/developer-updated" method="post">
                <div class="form-group">
                    <label for="developerId">Developer id:</label><br>
                    <input type="number" class="form-control" id="developerId" placeholder="Enter developer id" name="developerId"><br>
                    <label for="developerName">Developer name:</label><br>
                    <input type="text" class="form-control" id="developerName" placeholder="Enter developer name" name="developerName"><br>
                    <label for="developerAge">Developer age:</label><br>
                    <input type="number" class="form-control" id="developerAge" placeholder="Enter developer age" name="developerAge"><br>
                    <label for="developerSalary">Developer salary:</label><br>
                    <input type="number" class="form-control" id="developerSalary" placeholder="Enter developer salary" name="developerSalary"><br>
                </div>
                    <input type="submit" value="Submit">
            </form>
        </div>
    </body>
</html>