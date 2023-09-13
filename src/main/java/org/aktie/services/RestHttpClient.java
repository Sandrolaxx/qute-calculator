package org.aktie.services;

import org.aktie.dto.ExternalTextDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@RegisterRestClient(baseUri = "https://mocki.io/v1")
public interface RestHttpClient {

    @GET
    @Path("/236d6d03-e99a-4281-b84d-35a135571bca")
    public ExternalTextDTO fetchExternalData();

}
