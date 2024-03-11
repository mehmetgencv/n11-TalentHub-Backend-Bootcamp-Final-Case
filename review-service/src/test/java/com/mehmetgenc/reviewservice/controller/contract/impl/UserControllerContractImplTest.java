package com.mehmetgenc.reviewservice.controller.contract.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mehmetgenc.reviewservice.dto.UserDTO;
import com.mehmetgenc.reviewservice.entity.User;
import com.mehmetgenc.reviewservice.entity.enums.Gender;
import com.mehmetgenc.reviewservice.exception.UserNotFoundException;
import com.mehmetgenc.reviewservice.request.UserSaveRequest;
import com.mehmetgenc.reviewservice.request.UserUpdateRequest;
import com.mehmetgenc.reviewservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerContractImplTest {

    @Mock
    private UserService mockUserService;

    private UserControllerContractImpl userControllerContractImplUnderTest;

    ObjectMapper objectMapper = new ObjectMapper();
    @BeforeEach
    void setUp() {

        userControllerContractImplUnderTest = new UserControllerContractImpl(mockUserService);
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    void testSave() {
        // given
        final UserSaveRequest userSaveRequest = createExampleUserSaveRequest();
        User user = objectMapper.convertValue(userSaveRequest, User.class);

        when(mockUserService.save(any(User.class))).thenReturn(user);

        // when
        final UserDTO result = userControllerContractImplUnderTest.save(userSaveRequest);

        // then
        assertEquals(result.name(), userSaveRequest.name());
        assertEquals(result.surname(), userSaveRequest.surname());
        assertEquals(result.email(), userSaveRequest.email());

    }

    @Test
    void testFindById() {
        // given
        User user = createExampleUser();
        Long userId = user.getId();

        // then
        when(mockUserService.findById(anyLong())).thenReturn(user);
        UserDTO result = userControllerContractImplUnderTest.findById(userId);

        // then
        assertEquals(result.name(), user.getName());
        assertEquals(result.surname(), user.getSurname());
        assertEquals(result.email(), user.getEmail());

    }

    @Test
    void testFindById_UserServiceReturnsUserNotFoundException() {
        // given
        Long userId = 0L;

        // when
        when(mockUserService.findById(anyLong())).thenThrow(new UserNotFoundException("User not found"));


        // then
        assertThrows(UserNotFoundException.class, () -> {mockUserService.findById(userId);});
    }

    @Test
    void testFindAll() {
       // given
        User user = createExampleUser();
        List<User> users = Collections.singletonList(user);

        // then
        when(mockUserService.findAll()).thenReturn(users);
        List<UserDTO> result = userControllerContractImplUnderTest.findAll();

        // then
        assertEquals(result.get(0).name(), user.getName());
        assertEquals(result.get(0).surname(), user.getSurname());
        assertEquals(result.get(0).email(), user.getEmail());
    }

    @Test
    void testFindAll_UserServiceReturnsNoItems() {
        // given

        // when
        when(mockUserService.findAll()).thenReturn(Collections.emptyList());

        List<UserDTO> result = userControllerContractImplUnderTest.findAll();

        // then
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testDelete() {
       // given
        Long userId = 0L;
        String expectedResult = "User is deleted";
        // then
        when(mockUserService.delete(anyLong())).thenReturn(expectedResult);
        String result = userControllerContractImplUnderTest.delete(userId);

        // then
        assertEquals(result, expectedResult);
    }

    @Test
    void testUpdate() {
        // given
        UserUpdateRequest userUpdateRequest = createExampleUserUpdateRequest();
        User user = createExampleUser();

        when(mockUserService.findById(anyLong())).thenReturn(user);
        when(mockUserService.save(any(User.class))).thenReturn(user);

        // when
        UserDTO result = userControllerContractImplUnderTest.update(0L, userUpdateRequest);

        // then
        assertEquals(result.name(), userUpdateRequest.name());
        assertEquals(result.surname(), userUpdateRequest.surname());
        assertEquals(result.email(), userUpdateRequest.email());
    }

    @Test
    void testSaveBatch() {
        // given
        final List<UserSaveRequest> userSaveRequestList = List.of(createExampleUserSaveRequest());
        final List<User> users = List.of(createExampleUser());

        when(mockUserService.saveBatch(any())).thenReturn(users);


        // when
        final List<UserDTO> result = userControllerContractImplUnderTest.saveBatch(userSaveRequestList);

        // then
        assertEquals(result.get(0).name(), userSaveRequestList.get(0).name());
        assertEquals(result.get(0).surname(), userSaveRequestList.get(0).surname());
    }


    private UserSaveRequest createExampleUserSaveRequest() {
        return new UserSaveRequest(
                "name",
                "surname",
                "email@email.com",
                Gender.MALE,
                0.0,
                0.0);
    }

    private UserUpdateRequest createExampleUserUpdateRequest() {
        return new UserUpdateRequest(
                "name",
                "surname",
                "email@email.com",
                Gender.MALE,
                0.0,
                0.0);
    }


    private User createExampleUser() {
        User user = new User();
        user.setId(0L);
        user.setName("name");
        user.setSurname("surname");
        user.setGender(Gender.MALE);
        user.setEmail("email@email.com");
        user.setLatitude(0.0);
        user.setLongitude(0.0);
        return user;

}}
