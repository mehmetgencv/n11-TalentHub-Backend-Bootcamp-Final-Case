package com.mehmetgenc.reviewservice.controller.contract.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mehmetgenc.reviewservice.dto.UserDTO;
import com.mehmetgenc.reviewservice.entity.User;
import com.mehmetgenc.reviewservice.entity.enums.Gender;
import com.mehmetgenc.reviewservice.request.UserSaveRequest;
import com.mehmetgenc.reviewservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerContractImplTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserControllerContractImpl userControllerContractImpl;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    protected ObjectMapper objectMapper;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    void shouldSaveUser() {
        //given
        UserSaveRequest userSaveRequest = getExampleUserSaveRequest();
        User user = objectMapper.convertValue(userSaveRequest, User.class);
        user.setId(1L);

        // when
        when(userService.save(any(User.class))).thenReturn(user);
        UserDTO userDTO = userControllerContractImpl.save(userSaveRequest);

        // then
        Mockito.verify(userService).save(userArgumentCaptor.capture());
        User capturedUser = userArgumentCaptor.getValue();

        assertEquals(userSaveRequest.name(), capturedUser.getName());
        assertEquals(userSaveRequest.surname(), capturedUser.getSurname());
        assertEquals(userSaveRequest.gender(), capturedUser.getGender());

    }

    private User getExampleUser() {
        User user = new User();
        user.setName("name");
        user.setSurname("surname");
        user.setGender(Gender.MALE);
        user.setEmail("email");
        user.setLatitude(40.0);
        user.setLongitude(40.0);
        return user;
    }

    private UserSaveRequest getExampleUserSaveRequest() {
        UserSaveRequest userSaveRequest =
                new UserSaveRequest(
                        "Mehmet",
                        "Genc",
                        "mehmetgenc",
                        Gender.MALE,
                        10.0,
                        10.0);
        return userSaveRequest;}

}