<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html lang="pl">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
    <title>Witaj na stronie głównej</title>
</head>
<body>
    <h1>Strona główna</h1>
    <p>Co chcesz zrobić?</p>
    <h4>Pracownicy</h4>
        <li><a href="/employee-list">Zobacz wszystkich pracowników</a></li>
        <li><a href="/employee-list/employees-details">Zobacz szczegółowe informacje o pracownikach</a></li>
        <li><a href="/employee-edit">Dodaj pracownika</a></li>
    <h4>Klienci</h4>
        <li><a href="/client-list">Zobacz wszystkich klientów</a></li>
        <li><a href="/client-list/clients-details">Zobacz szczegółowe informacje o klientach</a></li>
        <li><a href="/client-edit">Dodaj klienta</a></li>
    <h4>Dyżury</h4>
        <li><a href="/shift-list">Zobacz wszystkie zmiany</a></li>
        <li><a href="/shift-edit">Dodaj zmianę</a></li>
    <h4>Filmy</h4>
        <li><a href="/movie-list">Zobacz wszystkie filmy</a></li>
        <li><a href="/movie-edit">Dodaj film</a></li>
    <h4>Wypożyczenia</h4>
        <li><a href="/rental-list">Zobacz wszystkie wypożyczenia</a></li>
        <li><a href="/rental-list">Zobacz opłaty za wypożyczenia</a></li>
        <li><a href="/rental-edit">Wypożycz film</a></li>
    </body>
    </html>
