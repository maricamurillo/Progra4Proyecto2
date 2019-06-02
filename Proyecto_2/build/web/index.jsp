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
        <title>Login</title>
    </head>
    <body>
        <div id="wrapper">
            <header>
                 <%@include file="header.jsp" %> 
            </header>
            <div>
                <form id="ingreso" action="ServicioIngreso" method="POST">
                    <table class="tablaFormulario">
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
