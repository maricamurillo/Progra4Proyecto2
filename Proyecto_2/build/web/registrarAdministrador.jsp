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
        <link href="css/estilos_1.css" rel="stylesheet" type="text/css"/>
        <script src="js/loadJSON.js" type="text/javascript"></script>
        <script src="js/scriptAdministradores.js" type="text/javascript"></script>
        <link href="css/estilos.css" rel="stylesheet" type="text/css"/>
        <title>Registro de Administradores</title>
    </head>
    <body onload="init()">
        <header><%@include file="header.jsp" %></header>
        <div id="wrapper">
            <%@include file="menuAdmin.jsp" %>
        </div>
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
                        r.append("Administrador creado.");
                        out.println(String.format("<div class=\"alert success\"><span class=\"closebtn\">&times;</span><strong>%s</strong></div>", r.toString()));
                        break;
                    case 2:
                        r.append("Ocurrio un error durante la creacion del administrador.");
                        out.println(String.format("<div class=\"alert\"><span class=\"closebtn\">&times;</span><strong>%s</strong></div>", r.toString()));
                        break;
                }
            }
        %>
        <div>
            <form id="registroAdministrador" action="ServicioRegistroAdministradores" method="POST">
                <table class="tablaFormulario">
                    <thead>
                        <tr>
                            <td colspan="2"><h3>Agregar Administrador</h3></td>
                        </tr>    
                    </thead>
                    ${formulario:campoEtiquetado("Cedula:","cedula","0")}
                    ${formulario:campoEtiquetado("Nombre:","nombre","0")}
                    ${formulario:campoEtiquetado("Primer apellido:","apellido1","0")}
                    ${formulario:campoEtiquetado("Segundo apellido:","apellido2","0")}
                    ${formulario:campoEtiquetado("Usuario:","usuario","0")}
                    <tr><td colspan="2"><button type="submit">Guardar</button></td></tr>
                </table>
            </form>
        </div>
        <div id="contents">
            <table id="tablaAdministradores">
                <thead>
                    <tr>
                        <td colspan="4"><h3>Listado de Administradores</h3></td>
                    </tr>
                    <tr>
                        <td>Cedula</td>
                        <td>Apellidos</td>
                        <td>Nombre</td>
                        <td>Usuario</td>
                    </tr>
                </thead>
                <tbody id="datosTablaAdministradores"></tbody>
            </table>
        </div>
        <script>
            var close = document.getElementsByClassName("closebtn");
            var i;
            for (i = 0; i < close.length; i++) {
              close[i].onclick = function(){
                var div = this.parentElement;
                div.style.opacity = "0";
                setTimeout(function(){ div.style.display = "none"; }, 600);
            }
        }
        </script>
        <%@include file="footer.jsp" %>
    </body>
</html>