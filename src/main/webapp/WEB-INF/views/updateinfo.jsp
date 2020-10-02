<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@ include file="./include.jsp"%>
<%@page import="java.util.*"%>
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
                <img src="images/logout.png" class="cart"/>
                <form action="./" id="updateinf" method="get" data-ajax="false">                    
                    <p class="enterdetails">Update Details:</p>          
                    Address:
                    <input type="text" id="address"/><br>
                    Contact-No:
                    <input type="text" id="contactno"/><br>
                    <p class="enterdetails">Change Password:</p>
                    Old Password:
                    <input type="password" id="password" name="password"/><br/>
                    New Password:
                    <input type="password" id="password1" name="password1"/><br/>
                    Re-Enter Password:
                    <input type="password" id="password2" name="password2"/><br/>
                    <input type="submit" value="Submit" />   
                </form>
                <a href="products.html">Cancel</a>
            </div>       
        </div>
    </body>
</html>
