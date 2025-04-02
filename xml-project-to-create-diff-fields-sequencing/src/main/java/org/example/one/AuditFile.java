package org.example.one;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso({HeaderFlowA.class, HeaderFlowB.class})
@Data
@XmlRootElement(name = "AuditFile")
@XmlAccessorType(XmlAccessType.FIELD)
class AuditFile<T extends BaseHeader> {
    private String version;
    private T header;

    public AuditFile() {}

    public AuditFile(String version, T header) {
        this.version = version;
        this.header = header;
    }
}