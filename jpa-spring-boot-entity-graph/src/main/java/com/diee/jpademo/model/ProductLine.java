package com.diee.jpademo.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "productlines")
public class ProductLine implements Serializable {

    @Id
    private String productLine;
    private String textDescription;
    private String htmlDescription;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;

    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

    public String getTextDescription() {
        return textDescription;
    }

    public void setTextDescription(String textDescription) {
        this.textDescription = textDescription;
    }

    public String getHtmlDescription() {
        return htmlDescription;
    }

    public void setHtmlDescription(String htmlDescription) {
        this.htmlDescription = htmlDescription;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
