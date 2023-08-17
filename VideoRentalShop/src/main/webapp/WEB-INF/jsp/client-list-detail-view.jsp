<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html lang="pl">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
    <title>Lista klientów</title>
</head>
<body>
    <h1>Lista klientów</h1>
        <h2>Szczegóły</h2>
        <table>
            <tr>
                <th>ID</th>
                <th>Imię</th>
                <th>Nazwisko</th>
                <th>Data rejestracji</th>
                <th>Numer telefonu</th>
                <th>Email</th>
            </tr>

            <c:forEach items="${clientsDetails}" var="clientDetail">
            <tr>
                <td>${clientDetail.id}</td>
                <td>${clientDetail.firstName}</td>
                <td>${clientDetail.lastName}</td>
                <td>${clientDetail.registrationDate}</td>
                <td>${clientDetail.phoneNumber}</td>
                <td>${clientDetail.email}</td>
            </tr>
        </c:forEach>
    </table>
<a href="/client-list">Powrót do listy klientów</a>
</body>
</html>