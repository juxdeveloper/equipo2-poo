# Simulador de Sistema Hospitalario

Proyecto de POO, etapa 2. Requiere JDK 17 o posterior.

Desde esta carpeta:

```bash
./compilar.sh
java -jar lib/hospital-fase2.jar
```

El menú muestra hospital y paciente activos. Las opciones 1 a 5 permiten crear hospital, especialidades, médicos, enfermeros y pacientes. H cambia el hospital; P cambia el paciente; D precarga una demo independiente. En cualquier captura, 0 cancela sin guardar. En el menú principal, 0 sale. Enter regresa al menú y limpia la pantalla.

Cada hospital tiene su `Map<Integer, String>` de especialidades. El ID 1 es medicina general; los IDs 2 a 99 se asignan al crear especialidades. Los números pueden no ser consecutivos y se muestran al elegir.

La atención sigue espera → consulta → tratamiento. Cada médico conserva hasta 10 pacientes y una consulta activa; cada enfermero especialista atiende hasta 3 pacientes de su área. Cada especialidad admite hasta 2 supervisores. El jefe ve y cambia turnos, cambia el enfermero de un paciente y autoriza medicamentos controlados de su área. No se cuenta dentro del equipo que supervisa ni recibe pacientes como enfermero de apoyo.

La necesidad de medicamento se decide al azar al crear el paciente. Si hace falta, el enfermero pide autorización a un jefe del área y este la aprueba. Sin supervisor o sin enfermero con cupo, el paciente permanece en consulta.

## Demostración rápida

1. D: precargar datos; Enter.
2. 6: iniciar consulta del paciente activo; Enter.
3. 7: escribir tratamiento; Enter.
4. 14: ver estado, tratamiento y autorización; Enter.
5. 16: seleccionar jefe, enfermero y nuevo turno; Enter.
6. 17: seleccionar jefe y nuevo enfermero; Enter.
7. 14: comprobar el traslado; Enter.

El jefe de medicina general es el primero de la demo. Los dos primeros enfermeros de su equipo permiten mostrar el traslado. Las opciones 15 y 12 muestran turnos y asignaciones.

## Entrega

- `src/hospital/`: fuente principal, organizada por paquetes.
- `test/hospital/`: clases compiladas.
- `test/PruebasHospital.java`: 74 comprobaciones de reglas; `java -cp test PruebasHospital`.
- `test/probar-menu.py`: prueba de los recorridos interactivos; `python3 test/probar-menu.py`.
- `lib/hospital-fase2.jar`: ejecutable independiente.
- `lib/atencion-medica.jar`: interfaz empaquetada por separado; se usa al recompilar la aplicación en `compilar.sh`.
- `doc/Javadoc/General/index.html`: Javadoc completo. También hay documentación individual por clase.
- `doc/Diagramas/`: UML y flujos editables `.drawio`, con imágenes `.png`.
- `doc/Pseudocodigos/`: algoritmos por clase.
- `doc/Reporte/reporte.tex` y `reporte.pdf`: reporte y anexos.
- `doc/Presentacion/Proyecto_Fase2.pptx` y `.pdf`: presentación con notas del ponente en el PPTX.
- `doc/Capturas/`: transcript real, imagen de ejecución y resultado de pruebas.

Las copias de clases de las carpetas superiores mantienen el mismo contenido que `src/hospital/`; la compilación toma únicamente `src/`.
