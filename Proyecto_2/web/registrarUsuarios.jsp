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
        <link href="css/tablas.css" rel="stylesheet" type="text/css"/>
        <link href="css/estilos.css" rel="stylesheet" type="text/css"/>
        <script src="js/scriptUsuarios.js" type="text/javascript"></script>
        <title>Registro de Usuarios</title>
    </head>
    <body onload="init()">
        <header>
            <%@include file="header.jsp" %>
        </header>
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
                        r.append("Archivo cargado.");
                        out.println(String.format("<div class=\"alert success\"><span class=\"closebtn\">&times;</span><strong>%s</strong></div>", r.toString()));
                        break;
                    case 2:
                        r.append("Ocurrio un error durante la carga del archivo.");
                        out.println(String.format("<div class=\"alert\"><span class=\"closebtn\">&times;</span><strong>%s</strong></div>", r.toString()));
                        break;
                    case 3:
                        r.append("No se selecciono ningun archivo.");
                        out.println(String.format("<div class=\"alert\"><span class=\"closebtn\">&times;</span><strong>%s</strong></div>", r.toString()));
                        break;
                    case 4:
                        r.append("El formato del archivo seleccionado no es valido. Formatos validos: XML y CSV.");
                        out.println(String.format("<div class=\"alert\"><span class=\"closebtn\">&times;</span><strong>%s</strong></div>", r.toString()));
                        break;
                }
            }
        %>
        <div id="wrapper">
            <%@include file="menuAdmin.jsp" %>
            <form id="registroUsuario" action="ServicioRegistroUsuarios" method="POST" enctype="multipart/form-data">
                <table class="tablaFormulario">
                    <thead>
                        <tr>
                            <td colspan="2"><h3>Agregar Usuarios</h3></td>
                        </tr>    
                    </thead>
                    ${formulario:campoArchivo("Seleccione archivo:","archivo")}
                    <tr><td colspan="2"><button type="submit">Guardar</button></td></tr>
                </table>
            </form>
        </div>
        <div id="contents">
            <table id="tablaUsuarios">
                <thead>
                    <tr>
                        <td colspan="4"><h3>Listado de Usuarios</h3></td>
                    </tr>
                    <tr>
                        <td>Cedula</td>
                        <td>Apellidos</td>
                        <td>Nombre</td>
                        <td>Estado</td>
                    </tr>
                </thead>
                <tbody id="datosTablaUsuarios"></tbody>
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