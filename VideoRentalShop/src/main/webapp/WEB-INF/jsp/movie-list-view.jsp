<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html lang="pl">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
    <title>Filmy</title>
</head>
<body>
    <h1>Filmy</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Nazwa</th>
            <th>Cena</th>
            <th>Edycja</th>
            <th>Usuń</th>
        </tr>
        <c:forEach items="${movies}" var="movie">
        <tr>
            <td>${movie.id}</td>
            <td>${movie.name}</td>
            <td>${movie.price}</td>
            <td>
                <a href="movie-edit?id=${movie.id}">Edytuj</a></td>
            <td>
                <a href="movie-edit/delete?id=${movie.id}">Usuń</a></td>
        </tr>
        </c:forEach>
    </table>
</body>
</html>