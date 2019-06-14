function initReporte() {
    cargarJson();
}

function cargarJson() {
    loadJSON(solicitarTabla,"ServicioVotacionCerrada");  
}
function cargarReporte(votacion) {
      loadJSON(reporte,"ServicioReporte?votacion="+votacion);  
}
function solicitarTabla(textoJSON){
    var refDiv = document.getElementById("DivReporte");
    var tabla = JSON.parse(textoJSON);
    if(refDiv){
        while (refDiv.hasChildNodes()){
            refDiv.removeChild(refDiv.firstChild);
        }
        //refDiv.style.display = 'block';
         var tablaGrupo = document.createElement("table");
         tablaGrupo.setAttribute("class","tablaGrupo");
         var tblHead = document.createElement("thead");
         tblHead.setAttribute("class","tablaDatos");
         var tblBody = document.createElement("tbody");
         tblBody.setAttribute("class","tablaDatos");
         var nuevaFila = tblHead.insertRow(-1);
         var nuevaCelda;
         nuevaCelda = nuevaFila.insertCell(-1);
         nuevaCelda.innerText = "Fecha inicio";
         nuevaCelda = nuevaFila.insertCell(-1);
         nuevaCelda.innerText = "Fecha apertura";
         nuevaCelda = nuevaFila.insertCell(-1);
         nuevaCelda.innerText = "Fecha cierre";
         nuevaCelda = nuevaFila.insertCell(-1);
         nuevaCelda.innerText = "Fecha final  ";
         nuevaCelda = nuevaFila.insertCell(-1);
         nuevaCelda.innerText = "Reporte ";
        for (var i = 0; i < tabla.datos.length; i++) {
            var nuevaFila = tblBody.insertRow(-1);
            var nuevaCelda;
            var id= tabla.datos[i].id;
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.innerText = tabla.datos[i].fecha_inicio;
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.innerText = tabla.datos[i].fecha_apertura;
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.innerText = tabla.datos[i].fecha_cierre;
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.innerText = tabla.datos[i].fecha_final;
            nuevaCelda = nuevaFila.insertCell(-1);
             var button = document. createElement("button");
             button.setAttribute("onclick", "manejaClick()");
             button.setAttribute("id",id);
             button.innerText = "generar";
             nuevaCelda.appendChild(button);
        }
        tablaGrupo.appendChild(tblHead);
        tablaGrupo.appendChild(tblBody);
        refDiv.appendChild(tablaGrupo); 
    }
    
}
function manejaClick(){
    var datos = event.target;
    var id = datos.id;
    cargarReporte(id);
}

function reporte(textoJSON){
    var refDiv = document.getElementById("DivReporte2");
    var tabla = JSON.parse(textoJSON);
    if(refDiv){
        while (refDiv.hasChildNodes()){
            refDiv.removeChild(refDiv.firstChild);
        }
         var newDiv = document.createElement("div");
         newDiv.setAttribute("class","divTablas");
         var tablas =document.createElement("table");
         tablas.setAttribute("class","tablaGrupo");
         var tblBodyTabla=document.createElement("tbody");
         tblBodyTabla.setAttribute("class","tablaDatos");
         var nuevaFila = tblBodyTabla.insertRow(-1);
         var nuevaCelda;
         nuevaCelda = nuevaFila.insertCell(-1);
         nuevaCelda.innerText = "votantes registrados:";
         nuevaCelda = nuevaFila.insertCell(-1);
         nuevaCelda.innerText = tabla.usuarios;
       
         var nuevaFila = tblBodyTabla.insertRow(-1);
         var nuevaCelda;
         nuevaCelda = nuevaFila.insertCell(-1);
         nuevaCelda.innerText = "voto efectivo :";
         nuevaCelda = nuevaFila.insertCell(-1);
         nuevaCelda.innerText = tabla.efectivos;
         nuevaCelda = nuevaFila.insertCell(-1);
         nuevaCelda.innerText = "porcentaje:";
         nuevaCelda = nuevaFila.insertCell(-1);
         nuevaCelda.innerText = tabla.porcentajeEfectivo;
         
         
         var nuevaFila = tblBodyTabla.insertRow(-1);
         var nuevaCelda;
         nuevaCelda = nuevaFila.insertCell(-1);
         nuevaCelda.innerText = "abstencionismo:";
         nuevaCelda = nuevaFila.insertCell(-1);
         nuevaCelda.innerText = tabla.abstencionismo;
         nuevaCelda = nuevaFila.insertCell(-1);
         nuevaCelda.innerText = "porcentaje:";
         nuevaCelda = nuevaFila.insertCell(-1);
         nuevaCelda.innerText = tabla.porcentajeAbstencionismo;
         
         var nuevaFila = tblBodyTabla.insertRow(-1);
         var nuevaCelda;
         nuevaCelda = nuevaFila.insertCell(-1);
         nuevaCelda.innerText = " ganador:";
         nuevaCelda = nuevaFila.insertCell(-1);
         nuevaCelda.innerText = tabla.ganador;
         
         tablas.appendChild( tblBodyTabla);
         newDiv.appendChild(tablas)
         refDiv.appendChild(newDiv);
         
         var tablaGrupo = document.createElement("table");
         tablaGrupo.setAttribute("class","tablaGrupo");
         var tblHead = document.createElement("thead");
         tblHead.setAttribute("class","tablaDatos");
         var tblBody = document.createElement("tbody");
         tblBody.setAttribute("class","tablaDatos");
         var nuevaFila = tblHead.insertRow(-1);
         var nuevaCelda;
          nuevaCelda = nuevaFila.insertCell(-1);
         nuevaCelda.innerText = "Siglas:";
         nuevaCelda = nuevaFila.insertCell(-1);
         nuevaCelda.innerText = "Partido";
         nuevaCelda = nuevaFila.insertCell(-1);
         nuevaCelda.innerText = "candidato";
         nuevaCelda = nuevaFila.insertCell(-1);
         nuevaCelda.innerText = "votos";
         nuevaCelda = nuevaFila.insertCell(-1);
         nuevaCelda.innerText = "porcentaje";
        for (var i = 0; i < tabla.datos.length; i++) {
            var nuevaFila = tblBody.insertRow(-1);
            var nuevaCelda;
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.innerText = tabla.datos[i].siglas;
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.innerText = tabla.datos[i].partido;
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.innerText = tabla.datos[i].usuario;
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.innerText = tabla.datos[i].votos;
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.innerText = tabla.datos[i].porcentajeVotos;
        }
        tablaGrupo.appendChild(tblHead);
        tablaGrupo.appendChild(tblBody);
        newDiv.appendChild(tablaGrupo);
        refDiv.appendChild(newDiv); 
    }
    
}


