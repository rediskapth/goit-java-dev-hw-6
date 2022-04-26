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
             <table class="table table-hover">
                <thead>
                    <tr>
                        <td>Name</td>
                        <td>Age</td>
                        <td>Salary</td>
                    </tr>
                </thead>

                <tbody>
                    <tr>
                        <td>
                            <c:out value="${developer.name}"/>
                        </td>
                        <td>
                            <c:out value="${developer.age}"/>
                        </td>
                        <td>
                            <c:out value="${developer.salary}"/>
                        </td>
                    </tr>
                </tbody>
             </table>
        </div>
    </body>
</html>