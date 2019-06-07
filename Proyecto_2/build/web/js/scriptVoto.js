function initVoto(){
    cargaEstatus();
    console.log("Aplicación inicializada..");
    setTimeout(cargaEstatus,1000);
}
function cargaEstatus(){
    loadJSON(estatus,"ServicioVotoEstatus");
}
function cargaPartidos(){
    loadJSON(actualizaPartidos,"ServicioVotoPartidos");
}
function estatus(textoJSON){
    var activo = JSON.parse(textoJSON);
    if(!activo){
       obj2=document.getElementById("DicActivo");
       obj2.style.display = 'block';
    } 
}
function actualizaPartidos(textoJSON){
    var refDiv = document.getElementById("DivUsuario");
    var tabla = JSON.parse(textoJSON);
    if(refDiv){
         while (refDiv.hasChildNodes()){
            refDiv.removeChild(refDiv.firstChild);
        }
    for (var i = 0; i < tabla.datos.length; i++) {
        var newDiv = document.createElement("div");
            newDiv.setAttribute("class","divTablas");
            var tablaGrupo = document.createElement("table");
            tablaGrupo.setAttribute("class","tablaGrupo");
            var tblBody = document.createElement("tbody");
            tblBody.setAttribute("class","tablasBody");
            var nuevaFila = tblBody.insertRow(-1);
            var nuevaCelda;
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.setAttribute("class","grupoTabla");
            nuevaCelda.innerText = "Grupo "+tabla.datos[i].id;
            }
            tablaGrupo.appendChild(tblBody);
            newDiv.appendChild(tablaGrupo);
            refDiv.appendChild(newDiv);  
    }
}

