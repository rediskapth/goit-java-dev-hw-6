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
            <form action="/project-created" method="post">
                <div class="form-group">
                    <label for="projectName">Project name:</label><br>
                    <input type="text" class="form-control" id="projectName" placeholder="Enter project name" name="projectName"><br>
                    <label for="projectDescription">Project description:</label><br>
                    <input type="text" class="form-control" id="projectDescription" placeholder="Enter project description" name="projectDescription"><br>
                    <label for="projectCreationDate">Project creation date:</label><br>
                    <input type="date" class="form-control" id="projectCreationDate" placeholder="Enter project creation date (yyyy-mm-dd)" name="projectCreationDate"><br>
                </div>
                    <input type="submit" value="Submit">
            </form>
        </div>
    </body>
</html>