<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html lang="pl">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
    <title>Dodawanie i edycja klientów</title>

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
    <h1>Dodawanie i edycja klientów</h1>
    <form:form action="/client-edit" modelAttribute="clientForm" method="post">
        <form:input path ="id" hidden="true" value="${id}"/>
        <table style="width: 50%">
            <tr>
                <td><form:label path = "firstName">Imię</form:label></td>
                <td><form:input path = "firstName" /></td>
                <td><form:errors path = "firstName" cssClass="error"/></td>
            </tr>
            <tr>
                <td><form:label path = "lastName">Nazwisko</form:label></td>
                <td><form:input path = "lastName" /></td>
                <td><form:errors path = "lastName" cssClass="error"/></td>
            </tr>
            <tr>
                <td><form:label path = "registrationDate">Data rejestracji</form:label></td>
                <td><form:input path = "registrationDate" type = "date"
                min = "2022-01-01" /> </td>
                <td><form:errors path = "registrationDate" cssClass="error"/></td>
            </tr>
            <tr>
                <td><form:label path = "phoneNumber">Telefon</form:label></td>
                <td><form:input path = "phoneNumber" /></td>
                <td><form:errors path = "phoneNumber" cssClass="error"/></td>
            </tr>
            <tr>
                <td><form:label path = "email">Email</form:label></td>
                <td><form:input path = "email" /></td>
                <td><form:errors path = "email" cssClass="error"/></td>
            </tr>

        </table>
        <br>
        <button class = "button" type="submit">Wyślij</button>
        </form:form>
        <br>
        <a href="/client-list">Wróć do listy klientów</a>
        <br>
        <a href="/">Wróć do strony głównej</a>
    </body>
</html>