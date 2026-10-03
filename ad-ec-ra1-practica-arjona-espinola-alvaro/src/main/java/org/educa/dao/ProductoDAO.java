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

    public List<ProductoEntity> readFile(String fileXml) throws JAXBException {

        List<ProductoEntity> lista = new ArrayList<>();

        JAXBContext context = JAXBContext.newInstance(Productos.class);

        Unmarshaller unmarshaller = context.createUnmarshaller();

        Productos productos = (Productos) unmarshaller.unmarshal(new File(fileXml));

        for (Producto producto : productos.getProducto()) {

            ProductoEntity entity = new ProductoEntity();

            entity.setProducto(producto);

            BigDecimal precio = producto.getPrecio();
            BigDecimal descuento = producto.getDescuento();

            BigDecimal precioFinal = precio.subtract(
                    precio.multiply(descuento)
                            .divide(new BigDecimal("100"))
            );

            entity.setPrecioFinal(precioFinal);

        }

        return lista;
    }
}