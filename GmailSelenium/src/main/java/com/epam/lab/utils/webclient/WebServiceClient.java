package com.epam.lab.utils.webclient;

import com.epam.lab.utils.logging.ITestLog;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import javax.ws.rs.core.MediaType;
import java.util.List;

public class WebServiceClient {

    private final static String SERVICE_URL = "http://localhost:8080/LogService/rest/logs/";
    private final static String SAVE_LOGS_TO_DB_PATH = "saveLogsToDB";


    public static ClientResponse sendLogs(List<ITestLog> logs) {
        ClientConfig config = new DefaultClientConfig();
        config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(config);
        WebResource resource = client.resource(SERVICE_URL + SAVE_LOGS_TO_DB_PATH);
        return resource.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).post(ClientResponse.class, logs);

    }

}
