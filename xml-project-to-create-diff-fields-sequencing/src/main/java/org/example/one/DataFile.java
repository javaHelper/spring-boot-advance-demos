package org.example.one;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "DataFile")
public class DataFile {
    private String fileName;
    private Long fileSize;
    private Long recordCount;
}