<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html lang="pl">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
    <title>Lista pracowników</title>
</head>
<body>
    <h1>Lista pracowników</h1>
        <h2>Szczegóły</h2>
        <table>
            <tr>
                <th>ID</th>
                <th>Imię</th>
                <th>Nazwisko</th>
                <th>Data dołączenia</th>
                <th>Numer telefonu</th>
                <th>Stawka godzinowa</th>
                <th>Stanowisko</th>
            </tr>

            <c:forEach items="${employeesDetails}" var="employeeDetail">
            <tr>
                <td>${employeeDetail.id}</td>
                <td>${employeeDetail.firstName}</td>
                <td>${employeeDetail.lastName}</td>
                <td>${employeeDetail.joinDate}</td>
                <td>${employeeDetail.phoneNumber}</td>
                <td>${employeeDetail.hourSalary}</td>
                <td>${employeeDetail.role}</td>
            </tr>
        </c:forEach>
    </table>
<a href="/employee-list">Powrót do listy pracowników</a>
</body>
</html>