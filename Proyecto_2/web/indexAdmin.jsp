<%--
    indexAdmin.jsp

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
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/scriptLoging.js" type="text/javascript"></script>
        <link href="css/estilos_1.css" rel="stylesheet" type="text/css"/>
        <link href="css/estilos.css" rel="stylesheet" type="text/css"/>
        <link href="css/tablas.css" rel="stylesheet" type="text/css"/>
        <title>Menu</title>
    </head>
    <body>
        <header><%@include file="header.jsp" %></header>
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
                        r.append("Datos borrados.");
                        out.println(String.format("<div class=\"alert success\"><span class=\"closebtn\">&times;</span><strong>%s</strong></div>", r.toString()));
                        break;
                    case 2:
                        r.append("Para borrar los datos, es necesario exportar los datos primero.");
                        out.println(String.format("<div class=\"alert\"><span class=\"closebtn\">&times;</span><strong>%s</strong></div>", r.toString()));
                        break;
                    case 3:
                        r.append("Ocurrio un error durante el borrado de datos.");
                        out.println(String.format("<div class=\"alert\"><span class=\"closebtn\">&times;</span><strong>%s</strong></div>", r.toString()));
                        break;
                }
            }
        %>
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
        </div>
       
    </body>
</html>