package com.universidad.antipatrones;

public interface EstrategiaDescuento {
    double calcular(Pedido pedido);

    String getNombre();
}
