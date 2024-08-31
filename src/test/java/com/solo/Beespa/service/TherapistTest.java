package com.solo.Beespa.service;

import com.solo.Beespa.dtos.request.GetAvailableTherapistRequest;
import com.solo.Beespa.dtos.request.ViewAvailabilityAvailabilityRequest;
import com.solo.Beespa.dtos.response.AvailabilityResponse;
import com.solo.Beespa.dtos.response.ViewAvailabilityResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;


import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = {"/db/data.sql"})
public class TherapistTest {
    @Autowired
    TherapistService therapistService;

    @Test
    public void testClientCanAvailabilityForTherapist(){
        ViewAvailabilityAvailabilityRequest request = new ViewAvailabilityAvailabilityRequest();
        request.setTherapistId(9L);
        request.setRequestedDate(LocalDate.of(2024, 6, 30));
        AvailabilityResponse response = therapistService.viewAvailability(request);
        assertNotNull(response);
        assertEquals(9L, response.getTherapistId());
        assertEquals("Alice Johnson", response.getTherapistName());

    }

    @Test
    public void testToGetAvailableTherapists(){
        GetAvailableTherapistRequest request = new GetAvailableTherapistRequest();
        request.setRequestedDate(LocalDate.of(2024, 6, 30));
        List<ViewAvailabilityResponse> responses = therapistService.getAvailableTherapists(request);
        assertNotNull(responses);
        assertFalse(responses.isEmpty());
        assertEquals(2, responses.size());
        responses.sort(Comparator.comparing(ViewAvailabilityResponse::getName));
        assertEquals("Alice Johnson", responses.get(0).getName());
        assertEquals("Mon-Fri: 9am-5pm", responses.get(0).getAvailability());
        assertEquals("Bob Smith", responses.get(1).getName());
        assertEquals("Tue-Sat: 10am-6pm", responses.get(1).getAvailability());
    }

}
