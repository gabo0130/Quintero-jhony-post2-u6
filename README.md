# Quintero-jhony-post2-u6

Laboratorio de la Unidad 6 sobre antipatrones de diseno. El proyecto `spaghetti-lab` refactoriza un sistema de procesamiento de pedidos que inicialmente tenia Spaghetti Code con condicionales anidados.

## Estructura

- `Pedido`: entidad de dominio con id, tipo de cliente, total y codigo promocional.
- `EstrategiaDescuento`: contrato del patron Strategy.
- `DescuentoVIP`, `DescuentoPremium`, `DescuentoEstandar`: encapsulan las reglas de descuento por tipo de cliente.
- `SelectorEstrategia`: centraliza la seleccion de la estrategia segun el tipo de cliente.
- `ComandoPedido`: contrato del patron Command.
- `ComandoProcesarPedido`: ejecuta el procesamiento de un pedido usando una estrategia de descuento.
- `Main`: crea la lista de pedidos y ejecuta un command por cada uno.

## Antes y despues

- Antes: `ProcesadorPedidos.procesarPedido()` concentraba calculo de descuentos, reglas promocionales, logging y alertas en un solo metodo con 6 niveles de anidamiento.
- Despues: las reglas quedaron distribuidas en estrategias independientes y el flujo de ejecucion se desacoplo mediante commands.
- Resultado: menor complejidad ciclomatica, mejor legibilidad, mas facilidad para probar el comportamiento y mejor cumplimiento de OCP.

## Como ejecutar

Desde la carpeta `spaghetti-lab`:

```powershell
mvn compile
mvn test
mvn exec:java
```

## Salida esperada

```text
Procesando pedido: P001
  Estrategia: VIP | Descuento: 45%
  Total final: $660.00
  [ALERTA] Pedido de alto valor: P001
Pedido P001 procesado.
Procesando pedido: P002
  Estrategia: VIP | Descuento: 30%
  Total final: $420.00
Pedido P002 procesado.
Procesando pedido: P003
  Estrategia: PREMIUM | Descuento: 15%
  Total final: $255.00
Pedido P003 procesado.
Procesando pedido: P004
  Estrategia: ESTANDAR | Descuento: 8%
  Total final: $138.00
Pedido P004 procesado.
Procesando pedido: P005
  Estrategia: ESTANDAR | Descuento: 0%
  Total final: $80.00
Pedido P005 procesado.
```

## Commits solicitados

- `feat: implementar ProcesadorPedidos con Spaghetti Code (version inicial)`
- `feat: crear interfaces y estrategias de descuento con patron Strategy`
- `feat: implementar patron Command y SelectorEstrategia; refactorizar Main`

## Captura de ejecucion

Pendiente de agregar una captura tomada desde la ejecucion local de `mvn exec:java`.
