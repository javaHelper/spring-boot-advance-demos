package org.example.one;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Data
@XmlRootElement(name = "header")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"adHoc", "businessTime", "businessDate"})
class HeaderFlowB extends BaseHeader {
    private String adHoc;
    private String businessTime;
    private String businessDate;
}