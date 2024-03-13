package com.mehmetgenc.reviewservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mehmetgenc.reviewservice.controller.contract.RecommendationControllerContract;
import com.mehmetgenc.reviewservice.dto.RestaurantRecommendInfoDTO;
import com.mehmetgenc.reviewservice.general.KafkaProducerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RecommendationController.class)
class RecommendationControllerTest extends BaseControllerTest{

    @MockBean
    private RecommendationControllerContract mockRecommendationControllerContract;

    @MockBean
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();

        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    }

    @Test
    void testGetRecommendations() throws Exception {
        // given
        final RestaurantRecommendInfoDTO restaurantRecommendInfoDTO = new RestaurantRecommendInfoDTO();
        restaurantRecommendInfoDTO.setId(1L);
        restaurantRecommendInfoDTO.setName("name");
        restaurantRecommendInfoDTO.setRate(3.0);
        restaurantRecommendInfoDTO.setLatitude(10.0);
        restaurantRecommendInfoDTO.setLongitude(10.0);
        when(mockRecommendationControllerContract.getRecommendations(1L)).thenReturn(Collections.singletonList(restaurantRecommendInfoDTO));

        // when
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/recommendations/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // then

        assertTrue(mvcResult.getResponse().getContentAsString().contains("name"));

}

}
