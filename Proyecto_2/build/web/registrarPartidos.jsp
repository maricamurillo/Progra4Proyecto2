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
        <title>Registro de Partidos</title>
    </head>
    <body>
        <header><%@include file="header.jsp" %></header>
        <div id="wrapper">
            <%@include file="menuAdmin.jsp" %>
        </div>
        <%
            System.out.println("className.methodName()");
            int status = 0;
            try {
                status = Integer.parseInt(request.getParameter("status"));
            } catch (NullPointerException | NumberFormatException ex) {

            }
            if (status != 0) {
                StringBuilder r = new StringBuilder();
                switch (status) {
                    case 1:
                        r.append("Partido creado.");
                        out.println(String.format("<div class=\"alert success\"><span class=\"closebtn\">&times;</span><strong>%s</strong></div>", r.toString()));
                        break;
                    case 2:
                        r.append("Ocurrio un error durante la creacion del partido. Verifique datos del partido");
                        out.println(String.format("<div class=\"alert\"><span class=\"closebtn\">&times;</span><strong>%s</strong></div>", r.toString()));
                        break;
                }
            }
        %>
        <div>
            <form id="registroPartido" action="ServicioRegistroPartidos" method="POST" enctype="multipart/form-data">
                <table class="tablaFormulario">
                    <thead><h3>Datos del Partido</h3></thead>
                    ${formulario:campoEtiquetado("Nombre","nombre","0")}
                    ${formulario:campoEtiquetado("Siglas","siglas","0")}
                    ${formulario:campoArchivo("Bandera","bandera")}
                    ${formulario:campoTextarea("Observaciones","observaciones","4", "50")}
                </table>
                ${formulario:campoBoton("Registar","botonRegistro","0")}
            </form>
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