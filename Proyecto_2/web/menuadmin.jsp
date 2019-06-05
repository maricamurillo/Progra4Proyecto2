<%--
    menuadmin.jsp

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
                <table>
                    <tr>
                        <td>
                            <form action="registrarUsuarios" method="POST">
                                ${formulario:campoBoton("Registrar usuarios","botonRegistrarUsuarios","0")}
                            </form>
                        </td>
                        <td>
                            <form action="registrarPartidos" method="POST">
                                ${formulario:campoBoton("Registrar partidos","botonRegistrarPartidos","0")}
                            </form>
                        </td>
                        <td>
                            <form action="registrarAdministradores" method="POST">
                                ${formulario:campoBoton("Registrar administradores","botonRegistrarAdministradores","0")}
                            </form>
                        </td>
                        <td>
                            <form action="reportes" method="POST">
                                ${formulario:campoBoton("Reportes","botonReportes","0")}
                            </form>
                        </td>
                        <td>
                            <form action="salir" method="POST">
                                 ${formulario:campoBoton("Cerrar sesión","botonSalir","0")}
                            </form>
                        </td>
                    </tr>
                </table>
            </header>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>

