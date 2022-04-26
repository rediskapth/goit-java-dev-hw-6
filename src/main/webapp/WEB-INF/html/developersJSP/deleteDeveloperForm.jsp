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
            <form action="/developer-deleted">
                <div class="form-group">
                    <label for="developerId">Developer id:</label><br>
                    <input type="number" class="form-control" id="developerId" placeholder="Enter developer id" name="developerId"><br>
                </div>
                     <input type="submit" value="Submit">
             </form>
        </div>
    </body>
</html>