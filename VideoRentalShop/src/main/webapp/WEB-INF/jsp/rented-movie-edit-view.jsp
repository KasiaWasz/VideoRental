<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html lang="pl">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
    <title>Wypożyczalnia</title>

<style>
    table{
    font-family: Arial, Helvetica, sans-serif;
    border-collapse: collapse;
    width:50%;
    margin-left: auto;
    margin-right: auto;
    }

    th, td{
    border: 1px solid #ddd;
    padding: 8px;
    }

    body{
    text-align: center;
    font-family: sans-serif;
    background-color: #eee;
    }
</style>
</head>
<body>
    <h1>Wypożyczenie filmu</h1>
    <form:form action="/rental-edit" modelAttribute="rentedMovieForm" method="post">
        <form:input path ="id" hidden="true" value="${id}"/>
        <table style="width: 50%">
            <tr>
                <td><form:label path = "clientId">Klient</form:label></td>
                <td>
                    <form:select path = "clientId">
                        <c:forEach items = "${clients}" var="client">
                            <form:option value = "${client.id}"><c:out value="${client.firstName} ${client.lastName}"/></form:option>
                        </c:forEach>
                    </form:select>
                </td>
                <td><form:errors path="clientId" cssClass="error"/></td>
            </tr>
            <tr>
                <td><form:label path = "movieId">Film</form:label></td>
                <td>
                    <form:select path = "movieId">
                        <c:forEach items = "${movies}" var="movie">
                            <form:option value = "${movie.id}"><c:out value="${movie.name}"/></form:option>
                        </c:forEach>
                    </form:select>
                </td>
                <td><form:errors path="movieId" cssClass="error"/></td>
            </tr>
            <tr>
                <td><form:label path = "rentDate">Data wypożyczenia</form:label></td>
                <td><form:input path = "rentDate" type = "date"
                min = "2022-01-02" /> </td>
                <td><form:errors path = "rentDate" cssClass="error"/></td>
            </tr>
         </table>
        <br>
        <button class = "button" type="submit">Wyślij</button>
        </form:form>
        <br>
        <a href="/rental-list">Wróć do listy z wypożyczeniami</a>
        <br>
        <a href="/">Wróć do strony głównej</a>
    </body>
</html>