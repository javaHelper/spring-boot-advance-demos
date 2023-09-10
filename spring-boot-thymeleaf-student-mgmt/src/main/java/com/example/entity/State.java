package com.example.entity;

import lombok.Getter;

public enum State {
    AP("Andhra Pradesh"),
    AR("Arunachal Pradesh"),
    AS("Assam"),
    BR("Bihar"),
    CH("Chhattisgarh"),
    GA("GOA"),
    GJ("Gujrat"),
    HR("Haryana"),
    HP("Himachal Pradesh"),
    JH("Jharkhand"),
    KA("Karnataka"),
    LK("Kerala"),
    MP("Madhya Pradesh"),
    MH("Maharashtra"),
    MN("Manipur"),
    MG("Meghalaya"),
    MZ("Mizoram"),
    NL("Nagaland"),
    OD("Odisha"),
    PB("Panjab"),
    RJ("Rajasthan"),
    SK("Sikkim"),
    TN("Tamil Nadu"),
    TL("Telang"),
    TR("Tripura"),
    UK("Uttarakhand"),
    UP("Uttar Pradesh"),
    WB("West Bengal");

    @Getter
    String label;

    State(String label) {
        this.label = label;
    }
}