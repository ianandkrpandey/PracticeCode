package com.pearson.global.configuration.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;


/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class GlobalConfigLambdaFunctionHandlerTest {

    private static InputStream input;

    @BeforeClass
    public static void createInput() throws IOException {
        // TODO: set up your sample input object here.
        input = null;
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("Your Function Name");

        return ctx;
    }

    @Test
    public void testGlobalConfigLambdaFunctionHandler() throws IOException {
        GlobalConfigLambdaFunctionHandler handler = new GlobalConfigLambdaFunctionHandler();
        Context ctx = createContext();
        OutputStream output =null;
        

        handler.handleRequest(input, output, ctx);

        // TODO: validate output here if needed.
        Assert.assertEquals("Hello from Lambda!", output);
    }
}
