<%-- 
    Document   : cambiarclave
    Created on : 06/06/2019, 06:57:36 PM
    Author     : Rodrigo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="formulario" uri="/WEB-INF/tlds/formulario" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/estilos.css" rel="stylesheet" type="text/css"/>
        <title>CambioClave</title>
    </head>
    <body>
         <div id="wrapper">
            <header id="header">
                 <%@include file="header.jsp" %> 
                 <%@include file="menuUsuario.jsp" %>
            </header>
            <div id="contents">
                <form id="ingreso" action="ServicioCambiarClave" method="POST">
                    <table >
                        ${formulario:campoEtiquetado("Nueva clave:","clave","1")}
                        ${formulario:campoBoton("Actualizar","botonActualizar","1")}
                    </table>
                </form>           
                <%
                   int status = 0;
                    try {
                        status = Integer.parseInt(request.getParameter("status"));
                    } catch (NullPointerException | NumberFormatException ex) {
                        System.out.println("className.methodName()"+status);
                    }
                    if (status != 0) {
                        StringBuilder r = new StringBuilder();
                        switch (status) {
                            case 1:
                                r.append("Su clave fue cambiada exitosamente");
                                break;
                            case 2:
                                r.append("Error actualizando clave");
                                break;
                           
                        }
                        out.println(String.format("<p class=\"mensajeError\">%s</p>", r.toString()));
                    }
                %>
            </div>
         </div>
         <%@include file="footer.jsp" %>
    </body>
</html>
