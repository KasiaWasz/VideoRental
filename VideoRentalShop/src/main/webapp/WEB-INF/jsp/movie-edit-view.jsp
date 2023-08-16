<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html lang="pl">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
    <title>Dodawanie i edycja filmów</title>
</head>
<body>
    <h1>Dodawanie i edycja filmów</h1>
    <form:form action="/movie-edit" modelAttribute="movieForm" method="post">
        <form:input path ="id" hidden="true" value="${id}"/>
        <table style="width: 50%">
            <tr>
                <td><form:label path = "name">Tytuł</form:label></td>
                <td><form:input path = "name" /></td>
                <td><form:errors path = "name" cssClass="error"/></td>
            </tr>
            <tr>
                <td><form:label path = "price">Cena</form:label></td>
                <td><form:input path = "price" /></td>
                <td><form:errors path = "price" cssClass="error"/></td>
            </tr>

        </table>
        <br>
        <button class = "button" type="submit">Wyślij</button>
        </form:form>
        <br>
        <a href="/movie-list">Powrót do listy filmów</a>
    </body>
</html>