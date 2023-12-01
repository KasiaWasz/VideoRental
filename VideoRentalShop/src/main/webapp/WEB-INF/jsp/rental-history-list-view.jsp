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
    <h1>Historia wypożyczeń</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Imię i nazwisko</th>
            <th>Nazwa filmu</th>
            <th>Data wypożyczenia</th>
            <th>Data zwrotu</th>
            <th>Ilość dni wypożyczenia</th>
            <th>Cena całkowita</th>
        </tr>
        <c:forEach items="${rentalHistory}" var="rental">
        <tr>
            <td>${rental.id}</td>
            <td>${rental.clientFirstName} ${rental.clientLastName}</td>
            <td>${rental.movieName}</td>
            <td>${rental.startRentDate}</td>
            <td>${rental.endRentDate}</td>
            <td>${rental.numberOfRentedDays}</td>
            <td>${rental.totalPrice}</td>
        </tr>
        </c:forEach>
    </table>
    <br>
    <a href="/">Wróć do strony głównej</a>
</body>
</html>