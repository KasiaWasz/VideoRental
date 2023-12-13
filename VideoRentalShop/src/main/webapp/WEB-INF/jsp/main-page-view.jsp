<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html lang="pl">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
    <title>Witaj na stronie głównej</title>

<style>
	body {
      font-family: sans-serif;
	  background-color: #eee;
	}
	ul {
	  list-style-type: none;
	  margin: 0;
	  padding: 0;
	  overflow: hidden;
	  background-color: #007bff;
	  text-align: center;
	}

	li {
	   display: inline-block;
	}

	li a, .dropbtn {
	  display: inline-block;
	  color: white;
	  text-align: center;
	  padding: 14px 16px;
	  text-decoration: none;
	}

	li a:hover, .dropdown:hover .dropbtn {
	  background-color: #0062CC;
	}

	li.dropdown {
	  display: inline-block;
	}

	.dropdown-content {
	  display: none;
	  position: absolute;
	  background-color: #f9f9f9;
	  min-width: 160px;
	  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
	  z-index: 1;
	}

	.dropdown-content a {
	  color: black;
	  padding: 12px 16px;
	  text-decoration: none;
	  display: block;
	  text-align: left;
	}

   body{
    text-align: center;
    }

	.dropdown-content a:hover {background-color: #f1f1f1;}

	.dropdown:hover .dropdown-content {
	  display: block;
	}
</style>
</head>
<body>
    <h1>Strona główna</h1>
	<ul>
     <li class="dropdown">
		<a href="javascript:void(0)" class="dropbtn">Pracownicy</a>
		<div class="dropdown-content">
			<a href="/employee-list">Zobacz wszystkich pracowników</a>
			<a href="/employee-list/employees-details">Zobacz szczegółowe informacje o pracownikach</a>
			<a href="/employee-edit">Dodaj pracownika</a>
		</div>
     </li>
     <li class="dropdown">
        <a href="javascript:void(0)" class="dropbtn">Klienci</a>
        <div class="dropdown-content">
            <a href="/client-list">Zobacz wszystkich klientów</a>
            <a href="/client-list/clients-details">Zobacz szczegółowe informacje o klientach</a>
            <a href="/client-edit">Dodaj klienta</a>
        </div>
     </li>
    <li class="dropdown">
        <a href="javascript:void(0)" class="dropbtn">Dyżury</a>
        <div class="dropdown-content">
            <a href="/shift-list">Zobacz wszystkie zmiany</a>
            <a href="/shift-edit">Dodaj zmianę</a>
        </div>
    </li>
    <li class="dropdown">
        <a href="javascript:void(0)" class="dropbtn">Filmy</a>
        <div class="dropdown-content">
            <a href="/movie-list">Zobacz filmy dostępne do wypożyczenia</a>
            <a href="/movie-edit">Dodaj film</a>
        </div>
    </li>
   <li class="dropdown">
       <a href="javascript:void(0)" class="dropbtn">Wypożyczenia</a>
       <div class="dropdown-content">
            <a href="/rental-list">Zobacz wszystkie wypożyczenia</a>
            <a href="/rental-fee-list">Zobacz opłaty za wypożyczenia</a>
            <a href="/rental-edit">Wypożycz film</a>
        </div>
    </li>
    <li class="dropdown">
        <a href="javascript:void(0)" class="dropbtn">Wynagrodzenia</a>
        <div class="dropdown-content">
            <a href="/payroll-list">Podlicz wynagrodzenia</a>
        </div>
    </li>
    <li class="dropdown">
        <a href="javascript:void(0)" class="dropbtn">Historia</a>
        <div class="dropdown-content">
            <a href="/rental-history-list">Historia wypożyczeń</a>
            <a href="/archive-movie-list">Zobacz filmy archiwalne</a>
        </div>
    </li>
</ul>
<br>
<br>
<a href="/login">Zaloguj się</a>
</body>
</html>