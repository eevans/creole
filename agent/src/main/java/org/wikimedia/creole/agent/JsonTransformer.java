package org.wikimedia.creole.agent;

import com.fasterxml.jackson.databind.ObjectMapper;

import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String render(Object arg0) throws Exception {
        return mapper.writeValueAsString(arg0);
    }

}
