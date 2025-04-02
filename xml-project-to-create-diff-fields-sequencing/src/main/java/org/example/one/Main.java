package org.example.one;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

// Example usage
public class Main {
    public static void main(String[] args) {
        try {
            String flow = "B"; // Change to "B" for different ordering
            BaseHeader header = HeaderFactory.createHeader(flow);

            // Populate sample data
            if (header instanceof HeaderFlowA) {
                ((HeaderFlowA) header).setBusinessDate("2025-04-02");
                ((HeaderFlowA) header).setBusinessTime("12:30:00");
                ((HeaderFlowA) header).setAdHoc("Yes");
            } else if (header instanceof HeaderFlowB) {
                ((HeaderFlowB) header).setAdHoc("Yes");
                ((HeaderFlowB) header).setBusinessTime("12:30:00");
                ((HeaderFlowB) header).setBusinessDate("2025-04-02");
            }

            AuditFile<BaseHeader> auditFile = new AuditFile<>("1.0", header);

            // Serialize auditFile to XML using JAXB
            JAXBContext context = JAXBContext.newInstance(AuditFile.class, HeaderFlowA.class, HeaderFlowB.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(auditFile, new FileOutputStream("abc.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}