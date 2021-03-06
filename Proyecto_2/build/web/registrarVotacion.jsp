<%--
    registrarVotacion.jsp

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
        <link href="css/estilos_1.css" rel="stylesheet" type="text/css"/>
        <script src="js/loadJSON.js" type="text/javascript"></script>
        <script src="js/scriptVotaciones.js" type="text/javascript"></script>
        <link href="css/estilos.css" rel="stylesheet" type="text/css"/>
        <title>Registro de Votaciones</title>
    </head>
    <body onload="init()">
        <header>
            <%@include file="header.jsp" %>
        </header>
        <div id="wrapper">
            <%@include file="menuAdmin.jsp" %>
             <%
            int status = 0;
            try {
                status = Integer.parseInt(request.getParameter("status"));
            } catch (NullPointerException | NumberFormatException ex) {

            }
            if (status != 0) {
                StringBuilder r = new StringBuilder();
                switch (status) {
                    case 1:
                        r.append("Votacion creada.");
                        out.println(String.format("<div class=\"alert success\"><span class=\"closebtn\">&times;</span><strong>%s</strong></div>", r.toString()));
                        break;
                    case 2:
                        r.append("Ocurrio un error durante la creacion de la votacion.");
                        out.println(String.format("<div class=\"alert\"><span class=\"closebtn\">&times;</span><strong>%s</strong></div>", r.toString()));
                        break;
                    case 3:
                        r.append("Las fechas seleccionadas no cumplen la siguiente condicion: fecha_inicio ≤ fecha_apertura < fecha_cierre ≤ fecha_final.");
                        out.println(String.format("<div class=\"alert\"><span class=\"closebtn\">&times;</span><strong>%s</strong></div>", r.toString()));
                        break;
                }
            }
        %>
        <div>
            <form id="registroVotacion" action="ServicioRegistroVotaciones" method="POST">
                <table class="tablaFormulario">
                    <thead>
                        <tr>
                            <td colspan="2"><h3>Agregar Votacion</h3></td>
                        </tr>    
                    </thead>
                    ${formulario:campoFecha("Fecha Inicio","fechaInicio")}
                    ${formulario:campoFecha("Fecha Apertura","fechaApertura")}
                    ${formulario:campoFecha("Fecha Cierre","fechaCierre")}
                    ${formulario:campoFecha("Fecha Final","fechaFinal")}
                    <tr><td colspan="2"><button type="submit">Guardar</button></td></tr>
                </table>
            </form>
        </div>
        <div id="contents">
            <table id="tablaVotaciones">
                <thead>
                    <tr>
                        <td colspan="5"><h3>Listado de Votaciones</h3></td>
                    </tr>
                    <tr>
                        <td>Fecha Inicio</td>
                        <td>Fecha Apertura</td>
                        <td>Fecha Cierre</td>
                        <td>Fecha Final</td>
                        <td>Estado</td>
                    </tr>
                </thead>
                <tbody id="datosTablaVotaciones"></tbody>
            </table>
        </div>
        <script>
            var close = document.getElementsByClassName("closebtn");
            var i;
            for (i = 0; i < close.length; i++) {
                close[i].onclick = function () {
                    var div = this.parentElement;
                    div.style.opacity = "0";
                    setTimeout(function () {
                        div.style.display = "none";
                    }, 600);
                }
            }
        </script>
            <%@include file="footer.jsp" %>
        </div>
       
     
    </body>
</html>