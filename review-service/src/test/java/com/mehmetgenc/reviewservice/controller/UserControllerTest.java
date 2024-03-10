package com.mehmetgenc.reviewservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mehmetgenc.reviewservice.controller.contract.UserControllerContract;
import com.mehmetgenc.reviewservice.dto.UserDTO;
import com.mehmetgenc.reviewservice.entity.enums.Gender;
import com.mehmetgenc.reviewservice.exception.UserNotFoundException;
import com.mehmetgenc.reviewservice.request.UserSaveRequest;
import com.mehmetgenc.reviewservice.request.UserUpdateRequest;
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

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest extends BaseControllerTest {

    @MockBean
    private UserControllerContract userControllerContract;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();

        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    }

    @Test
    void shouldSaveUser() throws Exception {

        //given
        UserSaveRequest userSaveRequest = getExampleUserSaveRequest();
        UserDTO userDTO = getExampleUserDTO();

        //when
        when(userControllerContract.save(userSaveRequest)).thenReturn(userDTO);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userSaveRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();


        assertTrue(mvcResult.getResponse().getContentAsString().contains(userSaveRequest.name()));


        //then
        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }
    @Test
    void shouldFindById() throws Exception {
        //given
        UserDTO userDTO = getExampleUserDTO();

        //when
        when(userControllerContract.findById(1L)).thenReturn(userDTO);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        //then
        assertTrue(mvcResult.getResponse().getContentAsString().contains(userDTO.name()));
        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() throws Exception {
        //given
        UserDTO userDTO = getExampleUserDTO();

        //when
        when(userControllerContract.findById(1L)).thenThrow(new UserNotFoundException("User not found"));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

        //then
        assertTrue(mvcResult.getResponse().getContentAsString().contains("User not found"));
        boolean success = isSuccess(mvcResult);
        assertFalse(success);
    }

    @Test
    void shouldFindAll() throws Exception {
        //given
        UserDTO userDTO = getExampleUserDTO();
        List<UserDTO> userDTOList = Arrays.asList(userDTO);

        //when
        when(userControllerContract.findAll()).thenReturn(userDTOList);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        //then
        assertTrue(mvcResult.getResponse().getContentAsString().contains(userDTO.name()));
        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }

    @Test
    void shouldDeleteUser() throws Exception {
        //given
        String message = "User deleted successfully";

        //when
        when(userControllerContract.delete(1L)).thenReturn(message);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/users/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        //then
        assertTrue(mvcResult.getResponse().getContentAsString().contains(message));
        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }

    @Test
    void shouldUpdateUser() throws Exception {
        //given
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest(
                "Mehmet",
                "Genc",
                "mehmetgenc",
                "123456",
                Gender.MALE,
                10.0,
                10.0);

        UserDTO userDTO = getExampleUserDTO();

        //when
        when(userControllerContract.update(1L, userUpdateRequest)).thenReturn(userDTO);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userUpdateRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        //then
        assertTrue(mvcResult.getResponse().getContentAsString().contains(userUpdateRequest.name()));
        boolean success = isSuccess(mvcResult);
        assertTrue(success);

    }

    @Test
    void shouldSaveBatchUsers() throws Exception {
        //given
        UserSaveRequest userSaveRequest = getExampleUserSaveRequest();
        List<UserSaveRequest> userSaveRequestList = Arrays.asList(userSaveRequest);
        UserDTO userDTO = getExampleUserDTO();
        List<UserDTO> userDTOList = Arrays.asList(userDTO);

        //when
        when(userControllerContract.saveBatch(userSaveRequestList)).thenReturn(userDTOList);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users/saveBatchUsers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userSaveRequestList)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        //then
        assertTrue(mvcResult.getResponse().getContentAsString().contains(userSaveRequest.name()));
        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }

    private UserSaveRequest getExampleUserSaveRequest() {
        UserSaveRequest userSaveRequest = new UserSaveRequest(
                "Mehmet",
                "Genc",
                "mehmetgenc",
                Gender.FEMALE,
                10.0,
                10.0
        );
        return userSaveRequest;
    }

    private UserDTO getExampleUserDTO() {
        UserDTO userDTO = new UserDTO(
                1L,
                "Mehmet",
                "Genc",
                "mehmetgenc");
        return userDTO;
    }

}
