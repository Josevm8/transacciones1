function grilla() {
    // valida colores seleccionados
    var ids = [];
    $("input[name='idcolor']:checked").each(function() { //almacena en el arreglo los ids d colores seleccionados
        ids.push($(this).val());
    });
    
    if(ids.length == 0) {
        alert("¡Debe seleccionar Color(es)!");
        return;
    }
    
    // valida Compra / Venta seleccionado
    var tipo = $("input[name='tipo']:checked").val(); //tipo = C ó V
    if(tipo == undefined) {
        alert("¡Debe seleccionar Compra / Venta!");
        return;
    }

    // Ok
    $("#grilla").load("Movimientos?accion=grilla&tipo="
        +tipo+"&idprenda="+$("#idprenda").val()+"&idcolor="+ids.toString());
}       //llama al servlet manda grilla, tipo:C ó V, idprenda, idcolor

function copia(id, ctos) {
    $("#"+id).val(id+"_"+ctos);
}

function valida() {
    var cantidad = [];
    // para compras
    $("input[name='caja']").each(function() {
        cantidad.push($(this).val());
    });
    
    // para ventas
    $("select[name='caja']").each(function() {
        cantidad.push($(this).val());
    });
    
    for(i=0; i<cantidad.length; ++i) {
        if(isNaN(cantidad[i])) {
            alert("Todas las cantidades deben ser números");
            return false;
        }
    }
    
    var ceros = true;
    for(i=0; i<cantidad.length; ++i) {
        if(cantidad[i] != 0) {
            ceros = false;
        }
    }
    
    if(ceros) {
        alert("Todas las cantidades no pueden ser 0");
        return false;
    }
    
    return true;
}