<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html lang="pl">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
    <title>Dodawanie i edycja zmian</title>

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
    <h1>Dodawanie i edycja zmian</h1>
    <form:form action="/shift-edit" modelAttribute="shiftForm" method="post">
        <form:input path ="id" hidden="true" value="${id}"/>
        <table style="width: 50%">
            <tr>
                <td><form:label path = "employeeId">Pracownik</form:label></td>
                <td>
                    <form:select path = "employeeId">
                        <c:forEach items = "${employees}" var="employee">
                            <form:option value = "${employee.id}"><c:out value="${employee.firstName} ${employee.lastName}"/></form:option>
                        </c:forEach>
                    </form:select>
                </td>
                <td><form:errors path="employeeId" cssClass="error"/></td>
            </tr>
            <tr>
                <td><form:label path = "date">Data zmiany</form:label></td>
                <td><form:input path = "date" type = "date"
                min = "2022-01-02" /> </td>
                <td><form:errors path = "date" cssClass="error"/></td>
            </tr>
            <tr>
                <td><form:label path = "hours">Ilość godzin</form:label></td>
                <td><form:input path = "hours" /></td>
                <td><form:errors path = "hours" cssClass="error"/></td>
            </tr>

        </table>
        <br>
        <button class = "button" type="submit">Wyślij</button>
        </form:form>
        <br>
        <a href="/shift-list">Wróć do listy zmian</a>
        <br>
        <a href="/">Wróć do strony głównej</a>
    </body>
</html>