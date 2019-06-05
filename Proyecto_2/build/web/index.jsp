<%--
    index.jsp

    EIF209 - Programación 4 – Proyecto #1 
    Abril 2019

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
        <link href="css/loging.css" rel="stylesheet" type="text/css"/>
        <script src="js/scriptLoging.js" type="text/javascript"></script>
        <title>Login</title>
    </head>
    <body>
        <div id="wrapper">
            <header>
                 <%@include file="header.jsp" %> 
               
            </header>
            <div id="loging">
                <ul class="loging">
                    <li><a href="">Loging</a>
                        <ul>
                            <li><a onclick="show('alministrador')"> Alministrador</a></li>
                            <li><a onclick="show('usuario')"> Usuario</a></li>  
                        </ul>
                    </li>
                </ul>
            </div>
            <div id="usuario" style="display: none">
                <form id="ingreso" action="ServicioIngresoUsuario" method="POST">
                    <table class="tablaFormulario">
                        <thead><h3>Usuario</h3></thead>
                        ${formulario:campoEtiquetado("Id usuario:","campoId","0")}
                        ${formulario:campoEtiquetado("Clave:","campoClave","1")}
                        ${formulario:campoBoton("Ingresar","botonIngreso","1")}
                    </table>
                </form>
            </div>
            <div id="alministrador" style="display: none">
                <form id="ingreso" action="ServicioIngresoAlministrador" method="POST">
                    <table class="tablaFormulario">
                        <thead><h3>Alministrador</h3></thead>
                        ${formulario:campoEtiquetado("Id usuario:","campoId","0")}
                        ${formulario:campoEtiquetado("Clave:","campoClave","1")}
                        ${formulario:campoBoton("Ingresar","botonIngreso","1")}
                    </table>
                </form>
            </div>        
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
