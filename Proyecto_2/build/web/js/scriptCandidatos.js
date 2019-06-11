function init() {
    cargarCandidatos();
    cargarPartidos();
    cargarVotaciones();
}

function cargarCandidatos() {
    loadJSON(cargarTablaCandidatos,"ServicioListados?listar=candidatos");  
}

function cargarTablaCandidatos(textoJSON){
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
            td.innerHTML = "<img src=\"ServicioProveedorImagen?cedula_candidato=" + tabla.datos[i].cedula + "\" width=\"100\" height=\"100\" />";
            td = tr.insertCell(-1);
            td.innerText = tabla.datos[i].partido;
            td = tr.insertCell(-1);
            td.innerText = tabla.datos[i].votacion;
        }
    }
}

function cargarPartidos() {
    loadJSON(cargarSelectPartido,"ServicioListados?listar=partidos");  
}

function cargarSelectPartido(textoJSON){
    console.log(textoJSON);
    var options = JSON.parse(textoJSON);
    var refSelect = document.getElementById("partido");
    if(refSelect){
        for (var i = 0; i < options.datos.length; i++) {
            var opt = document.createElement("option");
            opt.value = options.datos[i].siglas;
            opt.innerHTML = options.datos[i].nombre;
            refSelect.appendChild(opt);
        }
    }
}

function cargarVotaciones() {
    loadJSON(cargarSelectVotacion,"ServicioListados?listar=votaciones");  
}

function cargarSelectVotacion(textoJSON){
    console.log(textoJSON);
    var options = JSON.parse(textoJSON);
    var refSelect = document.getElementById("votacion");
    if(refSelect){
        for (var i = 0; i < options.datos.length; i++) {
            var opt = document.createElement("option");
            opt.value = options.datos[i].id;
            opt.innerHTML = "Inicio " + options.datos[i].fecha_inicio 
                    + " / Apertura " + options.datos[i].fecha_apertura
                    + " / Cierre " + options.datos[i].fecha_cierre
                    + " / Final " + options.datos[i].fecha_final
                    ;
            refSelect.appendChild(opt);
        }
    }
}