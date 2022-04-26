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
            <form action="/skill-updated" method="post">
                        <div class="form-group">
                            <label for="skillId">Skill id:</label><br>
                            <input type="number" class="form-control" id="skillId" placeholder="Enter skill id" name="skillId"><br>
                            <label for="skillLanguage">Skill language:</label><br>
                            <input type="text" class="form-control" id="skillLanguage" placeholder="Enter skill language" name="skillLanguage"><br>
                            <label for="skillSkill">Skill skill:</label><br>
                            <input type="text" class="form-control" id="skillSkill" placeholder="Enter skill skill (junior, middle, etc.)" name="skillSkill"><br>
                        </div>
                            <input type="submit" value="Submit">
            </form>
        </div>
    </body>
</html>