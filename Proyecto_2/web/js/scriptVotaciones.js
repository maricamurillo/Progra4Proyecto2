function init() {
    cargarJson();
}

function cargarJson() {
    loadJSON(solicitarTabla,"ServicioListados?listar=votaciones");  
}

function solicitarTabla(textoJSON){
    console.log(textoJSON);
    var tabla = JSON.parse(textoJSON);
    var refTabla = document.getElementById("datosTablaVotaciones");
     if(refTabla){
        while (refTabla.rows.length>0){
            refTabla.deleteRow(0);
        }
        for (var i = 0; i < tabla.datos.length; i++) {
            var tr = refTabla.insertRow(-1);
            var td;
            td = tr.insertCell(-1);
            td.innerText = tabla.datos[i].fecha_inicio;
            td = tr.insertCell(-1);
            td.innerText = tabla.datos[i].fecha_apertura;
            td = tr.insertCell(-1);
            td.innerText = tabla.datos[i].fecha_cierre;
            td = tr.insertCell(-1);
            td.innerText = tabla.datos[i].fecha_final;
            td = tr.insertCell(-1);
            td.innerText = tabla.datos[i].estado;
        }
    }
}