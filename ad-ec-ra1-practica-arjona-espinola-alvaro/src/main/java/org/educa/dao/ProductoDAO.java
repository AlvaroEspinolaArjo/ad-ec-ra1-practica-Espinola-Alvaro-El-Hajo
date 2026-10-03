package org.educa.dao;

import generated.Producto;
import generated.Productos;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.educa.entity.ProductoEntity;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    public static List<ProductoEntity> readFile(String fileXml) throws JAXBException { // Crea el metodo raedFile.

        List<ProductoEntity> lista = new ArrayList<>(); // Crea la lista.

        JAXBContext context = JAXBContext.newInstance(Productos.class); // Crea el contexto JAXB.

        Unmarshaller unmarshaller = context.createUnmarshaller(); // Crear el Unmarshaller, que es el que convierte el XML en objetos Java.

        Productos productos = (Productos) unmarshaller.unmarshal(new File(fileXml)); // Leer el fichero XML.

        for (Producto producto : productos.getProducto()) { // Recorrer todos los productos.

            ProductoEntity entity = getProductoEntity(producto);

            lista.add(entity); // Añadir el entity a la lista.
        }
        return lista;
    }

    private static ProductoEntity getProductoEntity(Producto producto) {
        ProductoEntity entity = new ProductoEntity(); // Inicia el entity.

        entity.setProducto(producto); // Guardar el producto.

        BigDecimal precio = producto.getPrecio(); // Coge el dato de precio.
        BigDecimal descuento = producto.getDescuento(); // Coge el dato de descuento.

        BigDecimal precioFinal = precio.subtract(precio.multiply(descuento).divide(new BigDecimal("100"))); // Calcular el precio final.
        entity.setPrecioFinal(precioFinal); // Guardar el precio final.

        BigDecimal coste = producto.getCostes().getCostesEnvio().add(producto.getCostes().getCostesAlmacenaje()); // Calcular el coste.
        entity.setCost(coste); // Guardar el coste.

        entity.setProfit(precioFinal.subtract(coste)); // Calcular el beneficio.
        return entity;
    }
}