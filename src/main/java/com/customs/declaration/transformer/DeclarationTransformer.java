package com.customs.declaration.transformer;

import org.springframework.util.StreamUtils;
import uk.customs.declaration.beans.Declaration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;

/**
 * Author : Vijay Saradhi
 */
public class DeclarationTransformer {

    public String sendDeclarationDetails() throws IOException {
        return loadFile("/samples/declaration.json");
    }

    public String sendDeclarationResponse() throws IOException {
        return loadFile("/samples/declaration-response.json");
    }

    private String loadFile(String fileName) throws IOException {
        InputStream inputStream = (InputStream) this.getClass().getResource(fileName).getContent();
        return StreamUtils.copyToString(inputStream, Charset.defaultCharset());
    }

    public static String convert(Declaration declaration) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Declaration.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter sw = new StringWriter();
        marshaller.marshal(declaration, sw);
        return sw.toString();
    }


}
