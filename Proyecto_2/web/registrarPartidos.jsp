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
        <script src="js/scriptLoging.js" type="text/javascript"></script>
        <title>Registro de Partidos</title>
    </head>
    <body>
        <header><%@include file="header.jsp" %></header>
        <div id="wrapper">
            <%@include file="menuAdmin.jsp" %>
        </div>
        <div>
            <form id="registroPartido" action="ServicioRegistroPartidos" method="POST" enctype="multipart/form-data">
                <table class="tablaFormulario">
                    <thead><h3>Datos del Partido</h3></thead>
                    ${formulario:campoEtiquetado("Nombre","nombrePartido","0")}
                    ${formulario:campoEtiquetado("Siglas","siglasPartido","0")}
                    ${formulario:campoArchivo("Bandera","banderaPartido")}
                    <thead><h3>Datos del Candidato</h3></thead>
                    ${formulario:campoEtiquetado("Cedula","cedulaCandidato","0")}
                    ${formulario:campoEtiquetado("Nombre","nombreCandidato","0")}
                    ${formulario:campoEtiquetado("Primer Apellido","apellido1Candidato","0")}
                    ${formulario:campoEtiquetado("Segundo Apellido","apellido2Candidato","0")}
                    ${formulario:campoArchivo("Foto","fotoCandidato")}
                    ${formulario:campoTextarea("Observaciones","observaciones","4", "50")}
                </table>
                ${formulario:campoBoton("Registar","botonRegistro","0")}
            </form>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>