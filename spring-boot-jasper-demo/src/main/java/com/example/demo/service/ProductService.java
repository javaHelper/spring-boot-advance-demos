package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    List<Product> findProducts(){
        return productRepository.findAll();
    }


    public byte[] downloadInvoice() throws FileNotFoundException, JRException {
        List<Product> products = this.findProducts();

        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(products, false);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("total", "7000");

        JasperReport compileReport = JasperCompileManager
                .compileReport(new FileInputStream("src/main/resources/invoice.jrxml"));

        JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, parameters, beanCollectionDataSource);

        byte data[] = JasperExportManager.exportReportToPdf(jasperPrint);
        System.out.println(new String(data));
        return data;
    }
}
