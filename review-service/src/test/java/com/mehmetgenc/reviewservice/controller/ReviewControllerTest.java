package com.mehmetgenc.reviewservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mehmetgenc.reviewservice.controller.contract.ReviewControllerContract;
import com.mehmetgenc.reviewservice.dto.ReviewDTO;
import com.mehmetgenc.reviewservice.entity.enums.Rate;
import com.mehmetgenc.reviewservice.exception.ReviewNotFoundException;
import com.mehmetgenc.reviewservice.request.ReviewSaveRequest;
import com.mehmetgenc.reviewservice.request.ReviewUpdateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ReviewController.class)
class ReviewControllerTest extends BaseControllerTest{

    @MockBean
    private ReviewControllerContract reviewControllerContract;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();

        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    void shouldSaveReview() throws Exception {
        //given
        ReviewSaveRequest reviewSaveRequest = getExampleReviewSaveRequest();
        ReviewDTO reviewDTO = getExampleReviewDTO();
        when(reviewControllerContract.save(reviewSaveRequest)).thenReturn(reviewDTO);

        //when

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reviewSaveRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        //then
        assertTrue(mvcResult.getResponse().getContentAsString().contains(reviewSaveRequest.comment()));
        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }

    @Test
    void shouldFindReviewById() throws Exception {
        //given
        ReviewDTO reviewDTO = getExampleReviewDTO();
        when(reviewControllerContract.findById(1L)).thenReturn(reviewDTO);

        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/reviews/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        //then
        assertTrue(mvcResult.getResponse().getContentAsString().contains(reviewDTO.comment()));
        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }

    @Test
    void shouldThrowExceptionWhenReviewNotFound() throws Exception {
        //given
        when(reviewControllerContract.findById(1L)).thenThrow(new ReviewNotFoundException("Review not found"));

        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/reviews/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

        //then
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Review not found"));
        boolean success = isSuccess(mvcResult);
        assertFalse(success);
    }

    @Test
    void shouldFindAllReviewsByUserId() throws Exception {
        //given
        ReviewDTO reviewDTO = getExampleReviewDTO();
        when(reviewControllerContract.findAllReviewsByUserId(1L)).thenReturn(java.util.List.of(reviewDTO));

        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/reviews/findAllReviewsByUserId/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        //then
        assertTrue(mvcResult.getResponse().getContentAsString().contains(reviewDTO.comment()));
        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }

    @Test
    void shouldFindAllReviewsByRestaurantId() throws Exception {
        //given
        ReviewDTO reviewDTO = getExampleReviewDTO();
        when(reviewControllerContract.findAllReviewsByRestaurantId(1L)).thenReturn(java.util.List.of(reviewDTO));

        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/reviews/findAllReviewsByRestaurantId/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        //then
        assertTrue(mvcResult.getResponse().getContentAsString().contains(reviewDTO.comment()));
        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }

    @Test
    void shouldDeleteReviewById() throws Exception {
        //given
        when(reviewControllerContract.deleteById(1L)).thenReturn(true);

        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/reviews/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        //then
        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }

    @Test
    void shouldUpdateComment() throws Exception {
        //given
        ReviewDTO reviewDTO = getExampleReviewDTO();
        when(reviewControllerContract.updateComment(1L, "new comment")).thenReturn(reviewDTO);
        String comment = "new comment";
        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/api/v1/reviews/updateComment/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(comment))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        //then
        assertTrue(mvcResult.getResponse().getContentAsString().contains(reviewDTO.comment()));
        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }

    @Test
    void shouldUpdateRating() throws Exception {
        //given
        ReviewDTO reviewDTO = getExampleReviewDTO();
        when(reviewControllerContract.updateRating(1L, Rate.FOUR)).thenReturn(reviewDTO);

        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/api/v1/reviews/updateRating/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Rate.FOUR)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        //then
        assertTrue(mvcResult.getResponse().getContentAsString().contains(reviewDTO.rate().toString()));
        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }

    @Test
    void shouldUpdateReview() throws Exception {
        //given
        ReviewDTO reviewDTO = getExampleReviewDTO();
        when(reviewControllerContract.updateReview(1L, getExampleReviewUpdateRequest())).thenReturn(reviewDTO);

        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/reviews/updateReview/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getExampleReviewSaveRequest())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        //then
        assertTrue(mvcResult.getResponse().getContentAsString().contains(reviewDTO.comment()));
        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }

    @Test
    void shouldSaveBatchReview() throws Exception {
        //given
        ReviewSaveRequest reviewSaveRequest = getExampleReviewSaveRequest();
        ReviewDTO reviewDTO = getExampleReviewDTO();
        when(reviewControllerContract.saveBatch(java.util.List.of(reviewSaveRequest))).thenReturn(java.util.List.of(reviewDTO));

        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/reviews/saveBatchReviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(java.util.List.of(reviewSaveRequest))))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        //then
        assertTrue(mvcResult.getResponse().getContentAsString().contains(reviewSaveRequest.comment()));
        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }

    private ReviewUpdateRequest getExampleReviewUpdateRequest() {
        return new ReviewUpdateRequest("comment", Rate.FIVE);
    }

    private ReviewDTO getExampleReviewDTO() {
        return new ReviewDTO(1L, 1L,1L, "comment", Rate.FIVE);
    }

    private ReviewSaveRequest getExampleReviewSaveRequest() {
        return new ReviewSaveRequest(1L, 1L, "comment", Rate.FIVE);
    }

}