<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html lang="pl">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
    <title>Zmiany</title>
</head>
<body>
    <h1>Lista dyżurów</h1>
    <table>
        <tr>
            <th>Data</th>
            <th>Pracownik</th>
            <th>Liczba godzin</th>
            <th>Edycja</th>
            <th>Usuń</th>
        </tr>
        <c:forEach items="${shifts}" var="shift">
        <tr>
            <td>${shift.date}</td>
            <td>${shift.employeeFirstName} ${shift.employeeLastName}</td>
            <td>${shift.hours}</td>
            <td>
                <a href="shift-edit?id=${shift.id}">Edytuj</a></td>
            <td>
                <a href="shift-edit/delete?id=${shift.id}">Usuń</a></td>
        </tr>
        </c:forEach>
    </table>
</body>
</html>