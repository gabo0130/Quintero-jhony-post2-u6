package com.universidad.antipatrones;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;

public class RefactorizacionPedidosTest {
    @Test
    public void debeCalcularDescuentosEsperados() {
        SelectorEstrategia selector = new SelectorEstrategia();

        assertEquals(0.45, selector.seleccionar("VIP").calcular(new Pedido("P001", "VIP", 1200.0, "VIPEXTRA")), 0.001);
        assertEquals(0.30, selector.seleccionar("VIP").calcular(new Pedido("P002", "VIP", 600.0, "VIP20")), 0.001);
        assertEquals(0.15, selector.seleccionar("PREMIUM").calcular(new Pedido("P003", "PREMIUM", 300.0, "PREM10")), 0.001);
        assertEquals(0.08, selector.seleccionar("ESTANDAR").calcular(new Pedido("P004", "ESTANDAR", 150.0, "FIRST50")), 0.001);
        assertEquals(0.0, selector.seleccionar("ESTANDAR").calcular(new Pedido("P005", "ESTANDAR", 80.0, null)), 0.001);
    }

    @Test
    public void debeUsarEstandarComoFallback() {
        EstrategiaDescuento estrategia = new SelectorEstrategia().seleccionar("DESCONOCIDO");

        assertEquals("ESTANDAR", estrategia.getNombre());
        assertEquals(0.05, estrategia.calcular(new Pedido("P006", "DESCONOCIDO", 200.0, "SAVE5")), 0.001);
    }

    @Test
    public void comandoDebeGenerarSalidaEsperada() {
        ByteArrayOutputStream salida = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(salida));

        try {
            Pedido pedido = new Pedido("P001", "VIP", 1200.0, "VIPEXTRA");
            EstrategiaDescuento estrategia = new SelectorEstrategia().seleccionar(pedido.getTipoCliente());

            new ComandoProcesarPedido(pedido, estrategia).ejecutar();
        } finally {
            System.setOut(original);
        }

        String texto = salida.toString();
        assertTrue(texto.contains("Procesando pedido: P001"));
        assertTrue(texto.contains("Estrategia: VIP | Descuento: 45%"));
        assertTrue(texto.contains("Total final: $660.00"));
        assertTrue(texto.contains("[ALERTA] Pedido de alto valor: P001"));
        assertTrue(texto.contains("Pedido P001 procesado."));
    }
}
