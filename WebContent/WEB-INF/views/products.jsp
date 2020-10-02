<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                <!--  <a href="updateinfo.jsp"><img src="images/updateinfo.png" class="cart"/></a>-->
                <a href="ViewCartServlet?idUser=${idUser}"><img src="images/gotocart.png" class="cart"/></a><br/><br/>
                <form method="get" action="SearchProductServlet" id="searchproduct" data-ajax="false">
                    Search Product:
                    <input type="hidden" id="idUser" name="idUser" value="${idUser}"/> 
                    <input type="text" id="productsearch" name="productsearch"/><br> 
                    <input type="submit" value="Search" />
                    <a href="DisplayProductServlet?idUser=${idUser}">Show All Products</a><br/><br/>
                    Product List:<br/><hr>
                    <p class="error">${param.message}</p>
                </form>
                <div id="productprint">
                    <table data-role="table" class="ui-responsive">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Features</th>
                                <th>Price</th>
                            </tr>
                        </thead><tbody>
                            <c:forEach items="${productList}" var="productList">
                                <tr>       	
                                    <td>${productList.name}<br/><img src="${productList.image}" id="mobileimage" /></td>
                                    <td>${productList.specs}</td>
                                    <td>$${productList.price}</td>
                                    <td>
                                        <form name="addtocart${productList.id}" action="AddCartServlet" method="post"> 
                                            <input type="hidden" id="idUser" name="idUser" value="${idUser}"/> 
                                            <input type="hidden" id="ProductId" name="ProductId" value="${productList.id}"/>
                                            <button type="submit" style="border: 0; background: transparent">
                                                <img src="images/addtocart.png" class="addtocart"/>
                                            </button>
                                        </form>
                                    </td>           
                                </tr>
                            </c:forEach></tbody>
                    </table>
                </div>
            </div>       
        </div>
</html>
