package com.solo.Beespa.service;


import com.solo.Beespa.dtos.request.AssignTherapistRequest;
import com.solo.Beespa.dtos.request.UpdateServicePriceRequest;
import com.solo.Beespa.dtos.response.AssignTherapistResponse;
import com.solo.Beespa.dtos.response.UpdatePriceResponse;
import com.solo.Beespa.exceptions.InvalidPriceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = {"/db/data.sql"})
public class ServiceTest {
    @Autowired
    ServiceService service;


    @Test
    public void testUpdateServicePrice() {
        UpdateServicePriceRequest request = new UpdateServicePriceRequest();
        request.setServiceId(1L);
        request.setNewPrice(BigDecimal.valueOf(600.00));
        UpdatePriceResponse response = service.updatePrice(request);
        assertNotNull(response);
        assertEquals("Service price updated successfully.", response.getMessage());
        assertEquals(response.getNewPrice(), BigDecimal.valueOf(600.00));


    }

    @Test
    public void testInvalidServicePriceUpdate() {
        UpdateServicePriceRequest request = new UpdateServicePriceRequest();
        request.setServiceId(1L);
        request.setNewPrice(BigDecimal.valueOf(-150.00));

        Exception exception = assertThrows(InvalidPriceException.class, () -> {
            service.updatePrice(request);
        });

        assertEquals("Price cannot be negative", exception.getMessage());
    }

    @Test
    public void testAssignTherapistToService() {
        AssignTherapistRequest request = new AssignTherapistRequest();
        request.setServiceId(1L);
        request.setTherapistId(9L);

        AssignTherapistResponse response = service.assignTherapist(request);

        assertNotNull(response);
        assertEquals("Therapist assigned successfully.", response.getMessage());
        assertEquals(response.getTherapistId(), 9L);

    }

    @Test
    public void testMultipleServicesForSameTherapist() {
        AssignTherapistRequest firstRequest = new AssignTherapistRequest();
        firstRequest.setServiceId(1L);
        firstRequest.setTherapistId(9L);
        AssignTherapistRequest secondRequest = new AssignTherapistRequest();
        secondRequest.setServiceId(2L);
        secondRequest.setTherapistId(9L);
        AssignTherapistResponse firstResponse = service.assignTherapist(firstRequest);
        AssignTherapistResponse secondResponse = service.assignTherapist(secondRequest);
        assertNotNull(firstResponse);
        assertNotNull(secondResponse);
        assertEquals("Therapist assigned successfully.", firstResponse.getMessage());
        assertEquals("Therapist assigned successfully.", secondResponse.getMessage());
    }
}