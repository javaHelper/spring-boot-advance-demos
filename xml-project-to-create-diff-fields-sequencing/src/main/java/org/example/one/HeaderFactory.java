package org.example.one;

class HeaderFactory {
    public static BaseHeader createHeader(String flow) {
        if ("A".equals(flow)) {
            return new HeaderFlowA();
        } else {
            return new HeaderFlowB();
        }
    }
}