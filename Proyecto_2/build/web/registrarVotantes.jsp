<%--
    registrarUsuarios.jsp

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
        <link href="css/estilos.css" rel="stylesheet" type="text/css"/>
        <link href="css/loging.css" rel="stylesheet" type="text/css"/>
        <script src="js/scriptLoging.js" type="text/javascript"></script>
        <title>Registro de Votantes</title>
    </head>
    <body>
        <header><%@include file="header.jsp" %></header>
        <div id="wrapper">
            <%@include file="menuAdmin.jsp" %>
            <form id="form1" action="ServicioRegistroVotantes" method="POST" enctype="multipart/form-data">
                <table class="tablaFormulario">
                    <tr>
                        <td colspan="2" style="border-bottom: 1px solid black;">
                            <strong>Seleccionar archivo de votantes:</strong>
                        </td>
                    </tr>
                    <tr>
                        <td>Archivo:&nbsp;</td>
                        <td>
                            <input type="file" id="archivo" name="archivo" />
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <button type="submit">Enviar</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>