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
        JAXBContext jaxbContext = JAXBContext.newInstance(ProductoEntity.class); // Creas el contexto JAXB
        Unmarshaller unsmarshaller = jaxbContext.createUnmarshaller(); // Creas el Unmarshaller
        Productos productos = (Productos) unsmarshaller.unmarshal(new File(fileXml)); //Lees el XML

        for (Producto producto : productos.getProducto()) { // recorres los productos
            ProductoEntity productoEntity = new ProductoEntity(); //Crea el producto Entity
            productoEntity.setProducto(producto); //guardas el producto

            BigDecimal precio=producto.getPrecio(); // Coge el precio
            BigDecimal descuento=producto.getPrecio(); // Coge el descuento

            BigDecimal PrecioFinal = precio.subtract(
                precio.multiply(descuento).divide(new BigDecimal(100)) //Calcular el precio final
            );
            productoEntity.setPrecioFinal(PrecioFinal); // Guardas el precioFinal



        }
        return lista;
    }


}
