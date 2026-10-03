package org.educa.service;

import jakarta.xml.bind.JAXBException;
import org.educa.dao.ProductoDAO;
import org.educa.entity.ProductoEntity;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class ProductoService {

    public List<ProductoEntity> readFile(String fileXml) throws JAXBException {
        //TODO: Implementar
        return ProductoDAO.readFile(fileXml); // Llama al metodo readFile del ProductoDAO pasandole el XML.
    }

    public void exportSummary(String path, String fileXml) throws JAXBException, IOException {
        //TODO: Implementar

    }

    public void exportExcel(String path, String fileXml) throws JAXBException, IOException, ParseException {
        //TODO: Implementar
    }
}