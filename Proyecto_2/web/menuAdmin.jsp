<%--
    menuAdmin.jsp

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
    <table>
        <tr>
            <td>
                <form action="registrarUsuarios" method="POST">
                    ${formulario:campoBoton("Registrar usuarios","botonRegistraUsuarios","0")}
                </form>
            </td>
            <td>
                <form action="registrarPartidos" method="POST">
                    ${formulario:campoBoton("Registrar partidos","botonRegistrarPartidos","0")}
                </form>
            </td>
            <td>
                <form action="registrarVotaciones" method="POST">
                    ${formulario:campoBoton("Registrar votaciones","botonRegistrarVotaciones","0")}
                </form>
            </td>
            <td>
                <form action="registrarCandidatos" method="POST">
                    ${formulario:campoBoton("Registrar candidatos","botonRegistrarCandidatos","0")}
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
</html>