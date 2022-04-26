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
            <form action="/find-skill">
                <div class="form-group">
                    <label for="skillId">Skill id:</label><br>
                    <input type="number" class="form-control" id="skillId" placeholder="Enter skill id" name="skillId"><br>
                </div>
                     <input type="submit" value="Submit">
             </form>
        </div>
    </body>
</html>