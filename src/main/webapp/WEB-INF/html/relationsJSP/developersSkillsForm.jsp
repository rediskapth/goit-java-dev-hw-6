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
            <form action="/developers-skills-created" method="post">
                <div class="form-group">
                    <label for="skillId">Select skill:</label><br>
                        <c:forEach items="${skills}" var="skill">
                            <input type="checkbox" name="skillId" value="${skill.skillId}">
                                <c:out value="${skill.language}"/>
                                <c:out value="${skill.skill}"/><br>
                        </c:forEach>
                    <label for="developerId">Select developer:</label><br>
                        <c:forEach items="${developers}" var="developer">
                            <input type="checkbox" name="developerId" value="${developer.developerId}">
                                <c:out value="${developer.name}"/>
                                <c:out value="${developer.age}"/><br>
                        </c:forEach>
                </div>
                    <input type="submit" value="Submit">
            </form>
        </div>
    </body>
</html>