
package com.customs.declaration.dao;


import uk.customs.declaration.beans.Declaration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeclarationMapper {

    public Map<String, Object> processData(Declaration declaration) {
        Map<String, Object> data = new HashMap<>();

        data.put("identifier", declaration.getIdentifier());
        data.put("firstName", declaration.getFirstName());
        data.put("lastName", declaration.getLastName());
        data.put("address", declaration.getAddress());
        data.put("productCode", declaration.getProductCode());
        data.put("productDetails", declaration.getProductDetails());

        return data;
    }

    public List<Declaration> getDeclarations(List<Map<String, String>> dataList) {
        List<Declaration> declarations = new ArrayList<>();

        for (Map<String, String> data : dataList) {

            Declaration declaration = new Declaration();
            declaration.setIdentifier(data.get("identifier"));
            declaration.setFirstName(data.get("firstName"));
            declaration.setLastName(data.get("lastName"));
            declaration.setAddress(data.get("address"));
            declaration.setProductCode(data.get("productCode"));
            declaration.setProductDetails(data.get("productDetails"));

            declarations.add(declaration);
        }

        return declarations;
    }
}

