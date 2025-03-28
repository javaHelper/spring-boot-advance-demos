package com.example;

import com.example.entity.User;
import com.example.exception.UserNotFoundException;
import com.example.repository.UserRepository;
import com.example.service.AuditLogger;
import com.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Mock
    private AuditLogger auditLogger;

    // Captor for User objects
    @Captor
    private ArgumentCaptor<User> userCaptor;

    // Captor for String messages
    @Captor
    private ArgumentCaptor<String> stringCaptor;

    @Test
    void testRegisterUserWithAnswer() {
        // Create a list to track saved users
        final User[] savedUser = new User[1];

        // Using doAnswer with explicit Answer<Void> implementation
        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                User user = invocation.getArgument(0);
                savedUser[0] = user; // Capture the user object
                return null; // Void method returns null
            }
        }).when(userRepository).save(any(User.class));

        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                String email = invocation.getArgument(0);
                System.out.println("Mocked email sent to: " + email);
                return null;
            }
        }).when(userRepository).sendActivationEmail(anyString());

        // Test execution
        User newUser = new User("123", "john_doe", "john@example.com");
        userService.registerUser(newUser);

        // Verification
        assertNotNull(savedUser[0]);
        assertEquals("john_doe", savedUser[0].getUsername());
        assertEquals("john@example.com", savedUser[0].getEmail());

        // Verify interactions
        verify(userRepository).save(any(User.class));
        verify(userRepository).sendActivationEmail("john@example.com");
    }


    @Test
    void testActivateUserWithAnswer() {
        // Track if user was activated
        final boolean[] wasActivated = {false};

        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                User user = invocation.getArgument(0);
                wasActivated[0] = user.isActivated(); // Check activation status
                return null;
            }
        }).when(userRepository).update(any(User.class));

        // Execute
        userService.activateUser("user123");

        // Verify
        assertTrue(wasActivated[0], "User should be activated before update");
    }

    @Test
    void testExceptionHandlingWithAnswer() {
        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                throw new RuntimeException("Database connection failed");
            }
        }).when(userRepository).save(any(User.class));

        User newUser = new User("123", "john_doe", "john@example.com");

        assertThrows(RuntimeException.class, () -> {
            userService.registerUser(newUser);
        });
    }

    @Test
    void testRegisterUserCapturesArguments() {
        // Arrange
        User testUser = new User("123", "john_doe", "john@example.com");

        // Act
        userService.registerUser(testUser);

        // Assert - Capture the User object passed to save()
        verify(userRepository).save(userCaptor.capture());
        User savedUser = userCaptor.getValue();

        assertEquals("john_doe", savedUser.getUsername());
        assertEquals("john@example.com", savedUser.getEmail());

        // Capture both arguments passed to logEvent()
        verify(auditLogger).logEvent(stringCaptor.capture(), stringCaptor.capture());

        List<String> logValues = stringCaptor.getAllValues();
        assertEquals("USER_REGISTERED", logValues.get(0));
        assertTrue(logValues.get(1).contains("john_doe"));
    }

    @Test
    void testUpdateUserEmailCapturesMultipleArguments() {
        // Arrange
        String userId = "user123";
        String newEmail = "new@example.com";
        User mockUser = new User(userId, "old_user", "old@example.com");

        when(userRepository.findById(userId)).thenReturn(mockUser);

        // Act
        userService.updateUserEmail(userId, newEmail);

        // Assert - Capture the updated User (only one save expected)
        verify(userRepository).save(userCaptor.capture());

        User savedUser = userCaptor.getValue();
        assertEquals("new@example.com", savedUser.getEmail());

        // Capture audit log arguments
        verify(auditLogger).logEvent(stringCaptor.capture(), stringCaptor.capture());
        List<String> logArgs = stringCaptor.getAllValues();
        assertEquals("EMAIL_UPDATED", logArgs.get(0));
        assertTrue(logArgs.get(1).contains("new@example.com"));
    }

    @Test
    void testArgumentCaptorWithoutAnnotation() {
        // Alternative approach without @Captor annotation
        User testUser = new User("456", "jane_doe", "jane@example.com");

        // Create captor manually
        ArgumentCaptor<User> manualUserCaptor = ArgumentCaptor.forClass(User.class);
        ArgumentCaptor<String> manualStringCaptor = ArgumentCaptor.forClass(String.class);

        userService.registerUser(testUser);

        verify(userRepository).save(manualUserCaptor.capture());
        verify(auditLogger).logEvent(manualStringCaptor.capture(), manualStringCaptor.capture());

        assertEquals("jane_doe", manualUserCaptor.getValue().getUsername());
        assertEquals("USER_REGISTERED", manualStringCaptor.getAllValues().get(0));
    }
}