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
    font-family: sans-serif;
    background-color: #eee;
    }
</style>
</head>
<body>
    <h1>Wybierz datę aby wygenerować wynagrodzenia</h1>
    <form action="/payroll-list" method="GET">
        <label for="startDate">Od:</label>
        <input type="date" id="startDate" name="startDate" min="2023-10-01" value="2023-10-01">
        <br>
        <br>
        <label for="endDate">Do:</label>
        <input type="date" id="endDate" name="endDate" min="2023-11-01" value="2023-11-01">
        <br>
        <br>
        <button class="button" type="submit">Wyślij</button>
    </form>
    <c:if test="${not empty payrolls}">
        <h2>Wypłaty</h2>
            <table>
                <caption>Wynagrodzenia w podanym terminie</caption>
                <thead>
                    <tr>
                        <th>Imię i nazwisko pracownika</th>
                        <th>Wynagrodzenie</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${payrolls}" var="p">
                        <tr>
                            <td><c:out value="${p.employeeFirstName} ${p.employeeLastName}"/></td>
                            <td>${p.payroll}zł</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <br>
        <br>
        <a href="/">strona główna</a>
    </body>
</html>
