<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html lang="pl">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
    <title>Wypożyczenia</title>

<style>
    table{
    font-family: Arial, Helvetica, sans-serif;
    border-collapse: collapse;
    width: 50%;
    margin-left: auto;
    margin-right: auto;
    }

    th, td{
    border: 1px solid #ddd;
    padding: 8px;
    }

    body{
    text-align: center;
    }
</style>
</head>
<body>
    <h1>Wypożyczenia</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Imię i nazwisko</th>
            <th>Nazwa filmu</th>
            <th>Data wypożyczenia</th>
            <th>Usuń</th>
        </tr>
        <c:forEach items="${rentals}" var="rental">
        <tr>
            <td>${rental.id}</td>
            <td>${rental.clientFirstName} ${rental.clientLastName}</td>
            <td>${rental.movieName}</td>
            <td>${rental.rentDate}</td>
            <td>
                <a href="rental-edit/delete?id=${rental.id}">Usuń</a></td>
        </tr>
        </c:forEach>
    </table>
</body>
</html>