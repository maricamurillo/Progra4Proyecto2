function init() {
    cargarJson();
}

function cargarJson() {
    loadJSON(solicitarTabla,"ServicioListados?listar=partidos");  
}

function solicitarTabla(textoJSON){
    var tabla = JSON.parse(textoJSON);
    var refTabla = document.getElementById("datosTablaPartidos");
     if(refTabla){
        while (refTabla.rows.length>0){
            refTabla.deleteRow(0);
        }
        for (var i = 0; i < tabla.datos.length; i++) {
            var tr = refTabla.insertRow(-1);
            var td;
            td = tr.insertCell(-1);
            td.innerText = tabla.datos[i].siglas;
            td = tr.insertCell(-1);
            td.innerText = tabla.datos[i].nombre;
            td = tr.insertCell(-1);
            td.innerHTML = "<img src=\"ServicioProveedorImagen?siglas_partido=" + tabla.datos[i].siglas + "\" width=\"100\" height=\"100\" />";
            td = tr.insertCell(-1);
            td.innerText = tabla.datos[i].observaciones;
        }
    }
}