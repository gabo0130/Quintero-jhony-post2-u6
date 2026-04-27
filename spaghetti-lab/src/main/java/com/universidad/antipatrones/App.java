package com.universidad.antipatrones;

public class App {
    public static void main(String[] args) {
        ProcesadorPedidos procesador = new ProcesadorPedidos();

        Pedido[] pedidos = {
            new Pedido("P001", "VIP", 1200.0, "VIPEXTRA"),
            new Pedido("P002", "VIP", 600.0, "VIP20"),
            new Pedido("P003", "PREMIUM", 300.0, "PREM10"),
            new Pedido("P004", "ESTANDAR", 150.0, "FIRST50")
        };

        for (Pedido pedido : pedidos) {
            procesador.procesarPedido(pedido);
        }
    }
}
