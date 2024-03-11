package com.mehmetgenc.reviewservice.service.impl;

import com.mehmetgenc.reviewservice.entity.User;
import com.mehmetgenc.reviewservice.entity.enums.Gender;
import com.mehmetgenc.reviewservice.exception.UserNotFoundException;
import com.mehmetgenc.reviewservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository mockUserRepository;

    private UserServiceImpl userServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        userServiceImplUnderTest = new UserServiceImpl(mockUserRepository);
    }

    @Test
    void testSave() {
        // given
        final User user = createExampleUser();

        when(mockUserRepository.save(any(User.class))).thenReturn(user);

        // when
        final User result = userServiceImplUnderTest.save(user);

        // then
        assertEquals(user.getEmail(), result.getEmail());
    }

    @Test
    void testFindById() {
        // given
        final User user = createExampleUser();
        when(mockUserRepository.findById(0L)).thenReturn(Optional.of(user));

        // when
        final User result = userServiceImplUnderTest.findById(0L);

        // then
        assertEquals(user.getEmail(), result.getEmail());
        assertEquals(user.getName(), result.getName());
    }

    @Test
    void testFindById_shouldThrowUserNotFoundException() {
        // Setup
        when(mockUserRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> userServiceImplUnderTest.findById(0L)).isInstanceOf(UserNotFoundException.class);
    }

    @Test
    void testFindAll() {
        // given
        final User user = createExampleUser();
        when(mockUserRepository.findAll()).thenReturn(List.of(user));

        // when
        final List<User> result = userServiceImplUnderTest.findAll();

        // then
        assertThat(List.of(user)).isEqualTo(result);
    }

    @Test
    void testFindAll_UserRepositoryReturnsNoItems() {
        // given
        when(mockUserRepository.findAll()).thenReturn(Collections.emptyList());

        // when
        final List<User> result = userServiceImplUnderTest.findAll();

        // then
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testDelete() {
        // given
        final User user = createExampleUser();
        String expectedMessage = "User deleted";
        doNothing().when(mockUserRepository).deleteById(1L);

        // when
        final String result = userServiceImplUnderTest.delete(1L);

        // then
        assertEquals(expectedMessage, result);
    }

    @Test
    void testSaveBatch() {
        // given
        final User user = createExampleUser();
        final List<User> users = List.of(user);
        when(mockUserRepository.saveAll(users)).thenReturn(users);

        // when
        final List<User> result = userServiceImplUnderTest.saveBatch(users);

        // then
        assertThat(result).isEqualTo(users);
    }

    @Test
    void testSaveBatch_UserRepositoryReturnsNoItems() {
        // given
        final List<User> users = Collections.emptyList();
        when(mockUserRepository.saveAll(users)).thenReturn(users);

        // when
        final List<User> result = userServiceImplUnderTest.saveBatch(users);

        // then
        assertThat(result).isEqualTo(users);
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
        return user;}
}
