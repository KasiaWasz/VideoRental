<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html lang="pl">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
    <title>Lista pracowników</title>
</head>
<body>
    <h1>Lista pracowników</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Imię</th>
            <th>Nazwisko</th>
            <th>Numer telefonu</th>
            <th>Edycja</th>
            <th>Usuń</th>
            <th>Szczegóły</th>
        </tr>

        <c:forEach items="${employees}" var="employee">
            <tr>
            <td>${employee.id}</td>
            <td>${employee.firstName}</td>
            <td>${employee.lastName}</td>
            <td>${employee.phoneNumber}</td>
            <td>
                <a href="employee-edit?id=${employee.id}">Edytuj</a></td>
            <td>
                <a href="employee-edit/delete?id=${employee.id}">Usuń</a></td>
            <td>
                <a href="<c:url value='/employee-list/details' />?id=${employee.id}">Szczegóły</a>
            </tr>
        </c:forEach>
    </table>


    <c:if test="${showDetails}">
            <h2>Szczegóły pracownika</h2>
            <table>
                <tr>
                    <th>ID</th>
                    <th>Imię</th>
                    <th>Nazwisko</th>
                    <th>Data dołączenia</th>
                    <th>Numer telefonu</th>
                    <th>Stawka godzinowa</th>
                    <th>Stanowisko</th>
                </tr>


                <tr>
                    <td>${employeeDetail.id}</td>
                    <td>${employeeDetail.firstName}</td>
                    <td>${employeeDetail.lastName}</td>
                    <td>${employeeDetail.joinDate}</td>
                    <td>${employeeDetail.phoneNumber}</td>
                    <td>${employeeDetail.hourSalary}</td>
                    <td>${employeeDetail.role}</td>
                </tr>

            </table>
            <a href="/employee-list">Powrót do listy pracowników</a>
    </c:if>
    <a href="<c:url value='/employee-list/employees-details' />">Pokaż szczegółową listę pracowników</a>
</body>
</html>