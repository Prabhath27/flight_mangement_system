package com.flightmanagementsystem.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.mockito.junit.jupiter.MockitoExtension;

import com.flightmanagementsystem.dto.UserDTO;
import com.flightmanagementsystem.entity.User;
import com.flightmanagementsystem.exception.UserManagementException;
import com.flightmanagementsystem.repository.UserRepository;
import com.flightmanagementsystem.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserUnitTest {

	@Mock
    private UserRepository userRepository;
 
    @Mock
    private ModelMapper modelMapper;
 
    @InjectMocks
    private UserService userService;
 
    private UserDTO userDTO;
 
    @BeforeEach
    public void setUp() {
        userDTO = new UserDTO();
        userDTO.setUserName("testUser");
        userDTO.setPassword("testPassword");
        userDTO.setEmail("test@example.com");
        userDTO.setMobileNumber("1234567890");
        userDTO.setUserRole("Passenger");
    }
 
    @Test
    public void testRegisterUser_Success() {
        User user = new User();
        user.setUserName("testUser");
        user.setPassword("testPassword");
        user.setEmail("test@example.com");
        user.setMobileNumber("1234567890");
        user.setUserRole("Passenger");
 
      when(modelMapper.map(userDTO, User.class)).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(modelMapper.map(user, UserDTO.class)).thenReturn(userDTO);
 
        UserDTO result = userService.registerUser(userDTO);
 
        assertNotNull(result);
        assertEquals("testUser", result.getUserName());
        assertEquals("test@example.com", result.getEmail());
        assertEquals("Passenger", result.getUserRole());
    }
 
    @Test
    public void testRegisterUser_Failure() {
        when(userRepository.save(null)).thenReturn(null);
 
        assertThrows(UserManagementException.class, () -> {
            userService.registerUser(userDTO);
        });
    }
 
    @Test
    public void testSignIn_Success() {
        User user = new User();
        user.setUserName("testUser");
        user.setPassword("testPassword");
        user.setEmail("test@example.com");
        user.setMobileNumber("1234567890");
        user.setUserRole("Passenger");
 
        when(userRepository.findByUserNameAndPasswordAndUserRole("testUser", "testPassword", "Passenger"))
                .thenReturn(user);
        when(modelMapper.map(user, UserDTO.class)).thenReturn(userDTO);
 
        UserDTO result = userService.signIn("testUser", "testPassword", "Passenger");
 
        assertNotNull(result);
        assertEquals("testUser", result.getUserName());
        assertEquals("test@example.com", result.getEmail());
        assertEquals("Passenger", result.getUserRole());
    }
 
    @Test
    public void testSignIn_Failure() {
        when(userRepository.findByUserNameAndPasswordAndUserRole("testUser", "testPassword", "Passenger"))
                .thenReturn(null);
 
        assertThrows(UserManagementException.class, () -> {
            userService.signIn("testUser", "testPassword", "Passenger");
        });
    }
 
    @Test
    public void testSignOut() {
        String signOutMessage = userService.signOut();
 
        assertEquals("You have been logged out.", signOutMessage);
    }
}
