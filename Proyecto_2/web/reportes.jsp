<%--
    registrarPartidos.jsp

    EIF209 - Programación 4 – Proyecto #2
    Junio 2019

    Autores:
            - 113030275 Mariela Cambronero
            - 111320128 Rodrigo Rodriguez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    
    
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <script src="js/scriptReporte.js" type="text/javascript"></script>
        <link href="css/estilos.css" rel="stylesheet" type="text/css"/>
        <link href="css/tablas.css" rel="stylesheet" type="text/css"/>
         <link href="css/print.css" rel="stylesheet" type="text/css"/>
        <script src="js/loadJSON.js" type="text/javascript"></script>
       
        <title>Reportes</title>
    </head>
    <body onload="initReporte()">
        <header>
            <%@include file="header.jsp" %>
            <%@include file="menuAdmin.jsp" %>
        </header>
        <div id="wrapper">
            <div id="ingreso">
                <div id="DivReporte"></div>
                <div id="DivReporte2"></div>
            </div>
          
        </div>
            <%@include file="footer.jsp" %>
    </body>
</html>