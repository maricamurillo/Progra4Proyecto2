
<%-- 
    errorIngreso.jsp


    EIF209 - Programacion 4 - Proyecto #1
    Abril 2019
    Autores: 
                - 113030275  Mariela Cambronero Murillo
                - 111320128 Rodrigo Rodriguez
--%>

<!DOCTYPE HTML>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Error de ingreso</title>
        <meta charset="UTF-8" />
        
        <link rel="stylesheet" href="css/default.css" type="text/css">
        <script src="js/scripts.js" type="text/javascript"></script>
    </head>
    <body>
        <div id="wrapper">
           
            <div id="contents">
                <h2>
                    <span style="color: red; font-weight: bold;">Error de ingreso</span>
                </h2>
                <p><strong>No ha iniciado la sesión.</strong><br />
                    Esto puede deberse a que la sesión ha expirado
                    o que los datos
                    de ingreso son incorrectos.</p>
                <p>
                    <span style="color:red">
                        <%
                            int codError = 0;
                            String mensaje = "(Error sin determinar)";
                            try {
                                codError = Integer.parseInt(request.getParameter("error"));
                            } catch (Exception e) {
                            }
                            switch (codError) {
                                case 1:
                                    mensaje = "La sesión ha expirado.";
                                    break;
                                case 2:
                                    mensaje = "El nombre de usuario o la clave son incorrectos.";
                                    break;
                                default:
                                            ;
                            }
                            out.println(mensaje);
                        %>
                    </span>
                </p>
                <p>Haga clic <a href="index.jsp">aqu&iacute;</a>
                    para ingresar al sitio.<br />
                </p>
            </div>
          
        </div>
    </body>
</html>
