<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html lang="pl">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
    <title>Filmy</title>

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
    <h1>Nieaktywne filmy</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Nazwa</th>
            <th>Cena</th>
            <th>Usuń</th>
        </tr>
        <c:forEach items="${archiveMovies}" var="movie">
        <tr>
            <td>${movie.id}</td>
            <td>${movie.name}</td>
            <td>${movie.price}</td>
            <td>
                <a href="movie-edit/delete?id=${movie.id}">Usuń</a></td>
        </tr>
        </c:forEach>
    </table>
</body>
</html>