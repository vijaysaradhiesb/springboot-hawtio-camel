package com.customs.integration.services.declaration;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.model.ModelCamelContext;
import org.apache.camel.test.spring.DisableJmx;
import org.apache.camel.test.spring.ShutdownTimeout;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;

import static org.junit.Assert.assertEquals;

/**
 * Created by Saradhi on 17/10/2018.
 */

@DisableJmx
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:/test-connectivity/broker1.xml",
        "classpath:/test-connectivity/connection-jms1.xml",
        "classpath:META-INF/spring/tx-config.xml",
        "classpath:META-INF/spring/jms-config.xml",
        "classpath:META-INF/spring/camel-context-bordereaux.xml",
        "classpath:/test-context.xml"

})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@ShutdownTimeout(120)
public class DeclarationServiceRouteTest {

    @EndpointInject(uri = "direct:start")
    private ProducerTemplate start;

    @EndpointInject(uri = "mock:result")
    private MockEndpoint result;

    @Autowired
    protected ModelCamelContext context;

    private static boolean INITIALISED = false;

    @Before
    public void setup() throws Exception {
        MockEndpoint.resetMocks(context);

        if (!INITIALISED) {
            INITIALISED = true;

            context.setUseMDCLogging(true);
            context.setTracing(true);

            context.addRoutes(new RouteBuilder() {
                public void configure() throws Exception {
                    from("direct:start").transacted("PROPAGATION_REQUIRES_NEW").to("jmstx:queue:bordereaux.service.request");
                    from("jmstx:queue:bordereaux.service.response").transacted("PROPAGATION_MANDATORY").to("mock:result");
                }
            });
        }
    }

    @Test
    public void testBordereauxRequestWithValidContent() throws IOException, URISyntaxException, ParseException, InterruptedException {
        result.expectedMessageCount(1);
        String underTest = loadFile("/payload/sample-request.xml");
        start.sendBody(underTest);

        result.assertIsSatisfied();
        String expectedResponse = loadFile("/payload/bordereaux-response.xml");
        assertEquals(expectedResponse, result.getExchanges().get(0).getIn().getBody());
    }

    private String loadFile(String fileName) throws IOException, URISyntaxException {
        URI uri = this.getClass().getResource(fileName).toURI();
        return FileUtils.readFileToString(new File(uri));
    }


}
