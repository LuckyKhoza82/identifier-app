package org.apps;

import io.quarkus.test.junit.QuarkusTest;
import org.apps.indentifier.dto.ClientDto;
import org.apps.indentifier.service.ClientService;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class ClientServiceTest {

    @Inject
    ClientService clientService;

    @Test
    public void testGetClientWithIdNumber() {
        assertEquals("Khoza",
                ((ClientDto)clientService.findClient("9202438957084","","").build()
                        .getEntity()).getSurname());
    }

    @Test
    public void testGetClientWithName() {
        assertEquals("Khoza",
                ((ClientDto)clientService.findClient("","Lucky","").build()
                        .getEntity()).getSurname());
    }

    @Test
    public void testGetClientWithPhoneNumber() {
        assertEquals("Lucky",
                ((ClientDto)clientService.findClient("","","0683457891").build()
                        .getEntity()).getName());
    }

    @Test
    public void testCreateClientWithShortIDLengths() {
        ClientDto client = getClient();

        assertEquals("Saved Successfully",
                clientService.createClient(client).build().getEntity());
    }

    @Test
    public void testCreateClientWithShortIDLength() {
        ClientDto client = getClient();
        client.setIdNumber("92024389570");

        assertTrue(clientService.createClient(client).build().getEntity().toString()
                .contains("Invalid ID Number"));
    }

    @Test
    public void testCreateClientWithExistingID() {
        ClientDto client = getClient();
        client.setIdNumber("8203235678081");

        assertTrue(clientService.createClient(client).build().getEntity().toString()
                .contains("ID Number already exists"));
    }

    @Test
    public void testCreateClientWithExistingPhoneNumber() {
        ClientDto client = getClient();
        client.setMobileNumber("0683457890");

        assertTrue(clientService.createClient(client).build().getEntity().toString()
                .contains("Phone Number already exists"));
    }

    @Test
    public void testUpdateClientWithShortIdNumber() {
        ClientDto client = getClient();
        client.setIdNumber("92024389570");

        assertTrue(clientService.updateClient(2L, client).build().getEntity().toString()
                .contains("Invalid ID Number"));
    }

    @Test
    public void testUpdateClientWithExistingIdNumber() {
        ClientDto client = getClient();
        client.setIdNumber("8203235678081");

        assertTrue(clientService.updateClient(2L, client).build().getEntity().toString()
                .contains("Updated Successfully"));
    }

    @Test
    public void testUpdateClientWithExistingPhoneNumber() {
        ClientDto client = getClient();
        client.setMobileNumber("0683457890");

        assertTrue(clientService.updateClient(2L, client).build().getEntity().toString()
                .contains("Updated Successfully"));
    }

    @Test
    public void testUpdateClientWithUnkownId() {
        ClientDto client = getClient();

        assertTrue(clientService.updateClient(23L, client).build().getEntity().toString()
                .contains("No client found"));
    }



    private ClientDto getClient(){
        return new ClientDto("9202438957084", "Lucky", "Khoza",
                "08377", "Centurion");
    }
}
