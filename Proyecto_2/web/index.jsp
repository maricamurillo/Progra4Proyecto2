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
                    <li><a href="">Log in</a>
                        <ul>
                            <li><a onclick="show('administrador')"> Administrador</a></li>
                            <li><a onclick="show('usuario')"> Usuario</a></li>  
                        </ul>
                    </li>
                </ul>
            </div>
            <div id="usuario" style="display: none">
                <form id="ingreso" action="ServicioIngresoUsuario" method="POST">
                    <table class="tablaFormulario">
                        <thead><h3>Usuario</h3></thead>
                        ${formulario:campoEtiquetado("Cedula:","campoIdUsuario","0")}
                        ${formulario:campoEtiquetado("Clave:","campoClaveUsuario","1")}
                        ${formulario:campoBoton("Ingresar","botonIngresoUsuario","1")}
                    </table>
                </form>
            </div>
            <div id="administrador" style="display: none">
                <form id="ingreso" action="ServicioIngresoAdministrador" method="POST">
                    <table class="tablaFormulario">
                        <thead><h3>Administrador</h3></thead>
                        ${formulario:campoEtiquetado("Usuario:","campoIdAdm","0")}
                        ${formulario:campoEtiquetado("Clave:","campoClaveAdm","1")}
                        ${formulario:campoBoton("Ingresar","botonIngresoAdm","1")}
                    </table>
                </form>
            </div>        
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
