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
            <form action="/find-company">
                <div class="form-group">
                    <label for="companyId">Company id:</label><br>
                    <input type="number" class="form-control" id="companyId" placeholder="Enter company id" name="companyId"><br>
                </div>
                     <input type="submit" value="Submit">
             </form>
        </div>
    </body>
</html>