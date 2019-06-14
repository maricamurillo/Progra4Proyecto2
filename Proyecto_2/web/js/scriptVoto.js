function initVoto(){
    console.log("Aplicación inicializada..");
    //actualizaVotacion();
    //actualizaUsuario();
    
    //cargaPartidos();
    actualizaVotacion();
    //actualizaVoto();
    //actualizaUsuario();
   
    
}

function actualizaVotacion(){
     console.log("Actualizando..");
     cargaEstatusVotacion();
     // setTimeout(actualizaVotacion, 1000);   
}
function actualizaVoto(){
    console.log("Actualizando..");
    cargaEstatusVoto();
    //setTimeout(actualizaVoto, 1000);  
}
function actualizaUsuario(){
    console.log("Actualizando..");
    cargaEstatusUsuario();
    //setTimeout(actualizaUsuario, 1000);
}
function actualizaPartidos(){
    console.log("Actualizando..");
    cargaEstatusVoto();
}
function cargaEstatusUsuario(){
    loadJSON(estatusUsuario,"ServicioUsuarioEstatus");
}
function cargaEstatusVotacion(){
    loadJSON(estatusVotacion,"ServicioVotacionEstatus");
}
function cargaPartidos(){
    loadJSON(muestraPartidos,"ServicioVotoPartidos");
}
function cargaEstatusVoto(){
    loadJSON(verificaVoto,"ServicioVotoEstatus"); 
}
function verificaVoto(textoJSON){
    var voto = JSON.parse(textoJSON);
    obj3=document.getElementById("DicVotar2");
     if(voto){
       obj3.style.display = 'block';
    }
    else {
        cargaPartidos();
        obj3.style.display = 'none';     
    }
}
function estatusUsuario(textoJSON){
    var activo = JSON.parse(textoJSON);
    obj1=document.getElementById("DicActivo");
    if(!activo){
       obj1.style.display = 'block';
    }
    else {
         actualizaVoto();
        obj1.style.display = 'none';
     
    }
}
function estatusVotacion(textoJSON){
    var activo1 = JSON.parse(textoJSON);
    obj2=document.getElementById("DicVotar1");
    if(!activo1){
       obj2.style.display = 'block';
    }
    else {
        actualizaUsuario();
        obj2.style.display = 'none';
          
        
    }
}
function muestraPartidos(textoJSON){
    var refDiv = document.getElementById("DivPartidos");
    var tabla = JSON.parse(textoJSON);
    if(refDiv){
         while (refDiv.hasChildNodes()){
            refDiv.removeChild(refDiv.firstChild);
        }
         refDiv.style.display = 'block';
         var tablaGrupo = document.createElement("table");
         tablaGrupo.setAttribute("class","tablaGrupo");
         var tblHead = document.createElement("thead");
         tblHead.setAttribute("class","tablaDatos");
         var tblBody = document.createElement("tbody");
         tblBody.setAttribute("class","tablaDatos");
         var nuevaFila = tblHead.insertRow(-1);
         var nuevaCelda;
         nuevaCelda = nuevaFila.insertCell(-1);
         nuevaCelda.innerText = "Bandera";
         nuevaCelda = nuevaFila.insertCell(-1);
         nuevaCelda.innerText = "Partido ";
         nuevaCelda = nuevaFila.insertCell(-1);
         nuevaCelda.innerText = "Siglas ";
         nuevaCelda = nuevaFila.insertCell(-1);
         nuevaCelda.innerText = "Candidato ";
         nuevaCelda = nuevaFila.insertCell(-1);
         nuevaCelda.innerText = "Foto ";
         nuevaCelda = nuevaFila.insertCell(-1);
         nuevaCelda.innerText = "votar ";
          for (var i = 0; i < tabla.datos.length; i++) {
              var nuevaFila = tblBody.insertRow(-1);
                var nuevaCelda;
                nuevaCelda = nuevaFila.insertCell(-1);
                nuevaCelda.innerHTML = "<img src=\"ServicioProveedorImagen?siglas_partido=" + tabla.datos[i].siglas + "\" width=\"100\" height=\"100\" />";
                nuevaCelda = nuevaFila.insertCell(-1);
                nuevaCelda.innerText = tabla.datos[i].partido;
                nuevaCelda = nuevaFila.insertCell(-1);
                nuevaCelda.innerText = tabla.datos[i].siglas;
                nuevaCelda = nuevaFila.insertCell(-1);
                nuevaCelda.innerText = tabla.datos[i].usuario;
                nuevaCelda = nuevaFila.insertCell(-1);
                nuevaCelda.innerHTML = "<img src=\"ServicioProveedorImagen?cedula_candidato=" + tabla.datos[i].cedula + "\" width=\"100\" height=\"100\" />";
                nuevaCelda = nuevaFila.insertCell(-1); 
                nuevaCelda.innerHTML = "<a href=\"ServicioVoto?partido=" + tabla.datos[i].siglas + "\">X</a>";    
          }
         tablaGrupo.appendChild(tblHead);
         tablaGrupo.appendChild(tblBody);
         refDiv.appendChild(tablaGrupo);  
    }
}