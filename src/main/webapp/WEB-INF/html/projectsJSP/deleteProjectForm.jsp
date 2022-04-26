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
            <form action="/project-deleted">
                <div class="form-group">
                    <label for="projectId">Project id:</label><br>
                    <input type="number" class="form-control" id="projectId" placeholder="Enter project id" name="projectId"><br>
                </div>
                     <input type="submit" value="Submit">
             </form>
        </div>
    </body>
</html>