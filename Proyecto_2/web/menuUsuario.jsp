<%--
    menu.jsp

    EIF209 - Programación 4 – Proyecto #2 
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
        <title>JSP Page</title>
    </head>
    <body>
        <table>
            <td>
                 <form action="voto" method="POST">
                    ${formulario:campoBoton("Votar","botonVotar","0")}
                </form>
             </td>
            <td>
                <form action="ServicioCambiarClave" method="POST">
                    ${formulario:campoBoton("Cambiar clave","botonCambiaClave","0")}
                </form>
            </td>
            <td>
                 <form action="salir" method="POST">
                    ${formulario:campoBoton("Cerrar sesión","botonSalir","0")}
                </form>
             </td>
        </table>
    </body>
</html>
