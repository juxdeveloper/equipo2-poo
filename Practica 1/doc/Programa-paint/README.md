# Editor de figuras en Java

Aplicación Swing para dibujar rectángulos, óvalos, triángulos y líneas. Permite elegir
color, usar figuras con o sin relleno, deshacer, borrar todo y exportar el lienzo a PNG.

## Compilar y ejecutar

```bash
mkdir -p out
javac -d out src/editor/*.java
java -cp out editor.EditorFrame
```

Mantén presionado el botón izquierdo del ratón y arrastra sobre el lienzo para crear
una figura. La exportación genera una imagen PNG con fondo blanco y no incluye la barra
de herramientas.
