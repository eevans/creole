package org.wikimedia.creole.cli;

import java.io.PrintStream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/***
 * Base class for commands which render output in JSON.
 * @param <T> the return type for the implemented get() operation.
 */
public abstract class JsonCommand <T> extends BaseCommand {


    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    private String mapObject(Object obj) throws JsonProcessingException {
        return mapper.writeValueAsString(obj);
    }

    private void writeJson(PrintStream out, Object obj) throws JsonProcessingException {
        out.println(mapObject(obj));
    }

    /***
     * Implementors will perform all work here and return the value to be serialized as JSON.
     * @return the output of the action.
     * @throws Exception
     */
    public abstract T get() throws Exception;

    /***
     * {@inheritDoc}
     */
    @Override
    public final void run() {
        try {
            writeJson(System.out, get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
