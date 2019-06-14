<%--
    header.jsp

    EIF209 - Programación 4 – Proyecto #2
    Junio 2019

    Autores:
            - 113030275 Mariela Cambronero
            - 111320128 Rodrigo Rodriguez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="formulario" uri="/WEB-INF/tlds/formulario" %>
<!DOCTYPE html>
<html>
    <head>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link href="css/estilos.css" rel="stylesheet" type="text/css"/>
        <script src="js/loadJSON.js" type="text/javascript"></script>
        <script src="js/scriptVoto.js" type="text/javascript"></script>
        <link href="css/tablas.css" rel="stylesheet" type="text/css"/>
        <title>voto</title>
    </head>
    <body onload="initVoto()">
       <div id="wrapper">
            <header>
                 <%@include file="header.jsp" %>
                 <%@include file="menuUsuario.jsp" %>   
            </header>
            <div id="DicActivo" style="display: none">
                <table class="tablaFormulario">
                     <thead><h3>Estatus del perfil</h3></thead>
                     ${formulario:campoEtiquetado("Usuario:","inactivo","2")}
                     ${formulario:campoEtiquetado("Se requiere:","cambio de contraseña","2")}
                </table>
            </div>
            <div id="DicVotar1" style="display: none">
                <table class="tablaFormulario">
                     <thead><h3>Estado de la votacion</h3></thead>
                     ${formulario:campoEtiquetado("Votacion:","cerrada","2")}
                </table>
            </div>
            <div id="DicVotar2" style="display: none">
                <table class="tablaFormulario">
                     <thead><h3>Estado de la votacion</h3></thead>
                     ${formulario:campoEtiquetado("Votacion:","inactiva","2")}
                     ${formulario:campoEtiquetado("Usuario:","ya voto","2")}
                </table>
            </div>
                <div id="contents">
                     <div id="DivPartidos" style="display: none">
                      </div>
                </div>   
        </div>
               
                       <%@include file="footer.jsp" %>  
               
    </body>
</html>
