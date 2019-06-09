function init() {
    cargarJson();
}

function cargarJson() {
    loadJSON(solicitarTabla,"ServicioListados?listar=candidatos");  
}

function solicitarTabla(textoJSON){
    console.log(textoJSON);
    var tabla = JSON.parse(textoJSON);
    var refTabla = document.getElementById("datosTablaCandidatos");
     if(refTabla){
        while (refTabla.rows.length>0){
            refTabla.deleteRow(0);
        }
        for (var i = 0; i < tabla.datos.length; i++) {
            var tr = refTabla.insertRow(-1);
            var td;
            td = tr.insertCell(-1);
            td.innerText = tabla.datos[i].cedula;
            td = tr.insertCell(-1);
            td.innerText = tabla.datos[i].apellidos;
            td = tr.insertCell(-1);
            td.innerText = tabla.datos[i].nombre;
            td = tr.insertCell(-1);
            td.innerText = tabla.datos[i].estado;
            td = tr.insertCell(-1);
            td.innerText = tabla.datos[i].foto;
            td = tr.insertCell(-1);
            td.innerText = tabla.datos[i].partido;
        }
    }
}