<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html lang="pl">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
    <title>Lista klientów</title>

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
        <h1>Lista klientów</h1>

        <table>
            <tr>
                <th>ID</th>
                <th>Imię</th>
                <th>Nazwisko</th>
                <th>Numer telefonu</th>
                <th>Email</th>
                <th>Edycja</th>
                <th>Usuń</th>
                <th>Szczegóły</th>
            </tr>

        <c:forEach items="${clients}" var="client">
            <tr>
            <td>${client.id}</td>
            <td>${client.firstName}</td>
            <td>${client.lastName}</td>
            <td>${client.phoneNumber}</td>
            <td>${client.email}</td>
            <td>
                <a href="<c:url value='/client-edit' />?id=${client.id}">Edytuj</a>
            <td>
                <a href="client-edit/delete?id=${client.id}">Usuń</a></td>
            <td>
                <a href="<c:url value='/client-list/details' />?id=${client.id}">Szczegóły</a></td>
            </tr>
        </c:forEach>
    </table>


    <c:if test="${showDetails}">
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

            <tr>
                <td>${clientDetail.id}</td>
                <td>${clientDetail.firstName}</td>
                <td>${clientDetail.lastName}</td>
                <td>${clientDetail.registrationDate}</td>
                <td>${clientDetail.phoneNumber}</td>
                <td>${clientDetail.email}</td>
            </tr>

            </table>
    </c:if>
    <a href="<c:url value='/client-list/clients-details' />">Pokaż szczegółową listę klientów</a>
    <br>
    <a href="/">Wróć do strony głównej</a>
</body>
</html>