/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function cargaDatosServicio(n, id) {
    document.getElementById("nombre").value = n;
    document.getElementById("btnServicio").value = "Modificar";
    document.getElementById("formServicios").action = "AgregarServicio.do?idServicio=" + id;

}

function cargaDatosProducto(id, s, sc, desc) {
    document.getElementById("codigoProducto").innerHTML = id;
    document.getElementById("stock").value = s;
    document.getElementById("stockCritico").value = sc;
    document.getElementById("descripcionProducto").value = desc;
    document.getElementById("btnProducto").value = "Modificar";
    document.getElementById("formProducto").action = "AgregarProducto.do?idProducto=" + id;
}

function cargaDatosTipoProd(n, id) {
    document.getElementById("nombreTipo").value = n;
    document.getElementById("btnTipo").value = "Modificar";
    document.getElementById("formTipo").action = "AgregarProducto.do?opcion=tipo&idTipo=" + id;

}

function cargaDatosFamilia(n, id) {
    document.getElementById("nombreFamilia").value = n;
    document.getElementById("btnFamilia").value = "Modificar";
    document.getElementById("formFamilia").action = "AgregarProducto.do?opcion=familia&idFamilia=" + id;
}

function eliminaServicio(id) {
    var confirmacion = confirm("¿Desea eliminar el servicio?");

    if (confirmacion) {
        window.location.href = "EliminarServicio.do?idServicio=" + id;
    }
}

function eliminaTipo(id) {
    var confirmacion = confirm("¿Desea eliminar el tipo de producto?.");

    if (confirmacion) {
        window.location.href = "EliminarProducto.do?idTipo=" + id;
    }
}

function eliminaFamilia(id) {
    var confirmacion = confirm("¿Desea eliminar la familia de producto?.");

    if (confirmacion) {
        window.location.href = "EliminarProducto.do?idFamilia=" + id;
    }
}

function eliminaProducto(id) {
    var confirmacion = confirm("¿Desea eliminar el producto?.");

    if (confirmacion) {
        window.location.href = "EliminarProducto.do?idProducto=" + id;
    }
}

function mostrar(seleccion) {
    mostrarFormulario(seleccion);
    mostrarTabla(seleccion);
}



function mostrarFormulario(f) {
    document.getElementById("formFamilia").hidden = true;
    document.getElementById("formTipo").hidden = true;
    document.getElementById("formProducto").hidden = true;

    document.getElementById("form" + f).hidden = false;

}

function mostrarTabla(t) {
    document.getElementById("tablaFamilia").hidden = true;
    document.getElementById("tablaTipo").hidden = true;
    document.getElementById("tablaProducto").hidden = true;

    document.getElementById("tabla" + t).hidden = false;
}

