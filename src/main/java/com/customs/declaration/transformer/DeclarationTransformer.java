package com.customs.declaration.transformer;

import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

public class DeclarationTransformer {

    public String sendDeclarationDetails() throws IOException {
        return loadFile("/samples/declaration.json");
    }
    public String sendDeclarationResponse() throws IOException {
        return loadFile("/samples/declaration-response.json");
    }

    private String loadFile(String fileName) throws IOException {
        InputStream inputStram = (InputStream) this.getClass().getResource(fileName).getContent();
        return StreamUtils.copyToString(inputStram, Charset.defaultCharset());
    }

}
