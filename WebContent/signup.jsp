<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Wonder Cellphones</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="images/favicon.ico" />
        <link rel="stylesheet" href="css/jquery.mobile-1.4.3.css" />
        <link rel="stylesheet" href="css/jquery.mobile-1.4.3.min.css" />    
        <link rel="stylesheet" type="text/css" href="css/style.css"/>
        <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="js/jquery.mobile-1.4.3.js"></script>
        <script type="text/javascript" src="js/jquery.mobile-1.4.3.min.js"></script>
        <script type="text/javascript" src="js/script.js"></script>
    </head>
    <body id="bodytext">
        <div data-role="page">
            <a href="index.html"><div data-role="header" class="logodiv"><img src="images/Logo.jpg" class="logo"/></div></a>
            <div data-role="content">
                <form name ="signupForm" action="SignupServlet" id="signup" method="POST"  data-ajax="false">
                    <p class="enterdetails">Enter Your Details:</p>       
                    First-name:
                    <input type="text" id="firstname" name="firstname"/><br> 
                    Last-name:
                    <input type="text" id="lastname" name="lastname"/><br>
                    Email-Id:
                    <input type="text" id="emailid" name="emailid"/><br>
                    Username:
                    <input type="text" id="username" name="username"/><br>
                    Address:
                    <input type="text" id="address" name="address"/><br>
                    Contact-No:
                    <input type="text" id="contactno" name="contactno"/><br>
                    Password:
                    <input type="password" id="password1" name="password1"/><br/>
                    Re-Enter Password:
                    <input type="password" id="password2" name="password2"/><br/>
                    <input type="submit" value="Submit" />   
                    <p class="error">${param.message}</p>
                </form>
            </div>       
        </div>
    </body>    
</html>
