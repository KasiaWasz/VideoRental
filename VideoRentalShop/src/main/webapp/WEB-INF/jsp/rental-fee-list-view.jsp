<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html lang="pl">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
    <title>Płatności</title>

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
    <c:if test ="${not empty rentalFee}">
    <h2>Podsumowanie płatności</h2>
    <table>
    <thead>
        <tr>
            <th>Klient</th>
            <th>Płatność</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${rentalFee}" var="rental">
            <tr>
                <td><c:out value="${rental.clientFirstName} ${rental.clientLastName}"/></td>
                <td>${rental.fee}zł</td>
            </tr>
        </c:forEach>
    </tbody>
    </table>
    </c:if>
</body>
</html>
