package com.iiht.product.service;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.iiht.productpp.exception.UserExistsException;
import com.iiht.productpp.model.LoginDetails;
import com.iiht.productpp.model.ResponseMessage;
import com.iiht.productpp.model.UserData;
import com.iiht.productpp.repository.UserRepository;
import com.iiht.productpp.seviceimpl.CustomerDetailsService;
import com.iiht.productpp.seviceimpl.JwtUtil;
import com.iiht.productpp.seviceimpl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
	
	@Mock
	private UserRepository userRepository;
	@Mock
	private CustomerDetailsService custdetailservice;
	@Mock
	private JwtUtil jwtutil;
	@InjectMocks
	private UserServiceImpl userService;
	
	UserDetails userdetails;
	UserData userData;
	Optional<UserData> user;
	
	@Test
	void loginTest() {
		userdetails=new User("vani", "vani", new ArrayList<>());
		when(custdetailservice.loadUserByUsername("vani")).thenReturn(userdetails);
		userData=new UserData("vani", "jinka", "vani", "vani", 0,"customer");
		when(userRepository.findById("vani")).thenReturn(Optional.of(userData));
		LoginDetails login=new LoginDetails("vani","vani");
		when(jwtutil.generateToken(userdetails)).thenReturn("token");
		assertEquals(200, userService.login(login).getStatusCodeValue());
	}
	@Test
	void loginTestFail() {
		userdetails=new User("vani", "vani", new ArrayList<>());
		when(custdetailservice.loadUserByUsername("vani")).thenReturn(userdetails);
		userData=new UserData("vani", "jinka", "vani", "vani", 0,"customer");
		when(userRepository.findById("vani")).thenReturn(Optional.of(userData));
		LoginDetails login=new LoginDetails("vani","vani12");
		assertEquals(403, userService.login(login).getStatusCodeValue());
	}
	
	@Test
	void registerTest() {
		userData=new UserData("vani", "jinka", "vani", "vani", 0,"customer");
		when(userRepository.findById("vani")).thenReturn(Optional.ofNullable(null));
		when(userRepository.save(userData)).thenReturn(userData);
		assertEquals(201, userService.register(userData).getStatusCodeValue());
	}
	@Test
	void registerTestFail() {
		userData=new UserData("vani", "jinka", "vani", "vani",0, "customer");
		when(userRepository.findById("vani")).thenReturn(Optional.ofNullable(userData));
		assertThrows(UserExistsException.class,()-> userService.register(userData));
	}
	@Test
	void registerTestFail2() {
		userData=new UserData("vani", "jinka", "vani", "vani", 0,"customer");
		lenient().when(userRepository.findById("vani")).thenReturn(Optional.of(userData));
		lenient().when(userRepository.save(userData)).thenThrow(RuntimeException.class);
		assertThrows(UserExistsException.class,()-> userService.register(userData));
	}
	
	@Test
	void getAllUsers() {
		List<UserData> users=new ArrayList<>();
		users.add(new UserData());
		users.add(new UserData());
		when(userRepository.findAll()).thenReturn(users);
		assertEquals(users, userService.getAllUsers().getBody());
	}

	@Test
	void getUsersByUsername() {
		List<UserData> users=new ArrayList<>();
		users.add(new UserData("vani", "jinka", "vani", "vani", 0,"customer"));
		users.add(new UserData("vani", "jinka", "vani", "vani", 0,"customer"));
		when(userRepository.findAll()).thenReturn(users);
		assertEquals(users, ((ResponseMessage) userService.searchByUsername("van").getBody()).getResponse());
	}
	
	@Test
	void forgotPassword() {
		userData=new UserData("vani", "jinka", "vani", "vani", 0,"customer");
		LoginDetails login=new LoginDetails("vani","vani12");
		when(userRepository.findById("vani")).thenReturn(Optional.ofNullable(userData));
		when(userRepository.save(userData)).thenReturn(userData);
		assertEquals(200, userService.forgotPassword(login).getStatusCodeValue());
	}
	@Test
	void forgotPasswordFail() {
		userData=new UserData("vani", "jinka", "vani", "vani", 0,"customer");
		LoginDetails login=new LoginDetails("vani","vani12");
		when(userRepository.findById("vani")).thenReturn(Optional.ofNullable(null));
		assertThrows(UsernameNotFoundException.class,()-> userService.forgotPassword(login));
	}
	@Test
	void validateToken() {
		userData=new UserData("vani", "jinka", "vani", "vani", 0,"customer");
		when(jwtutil.validateToken("token")).thenReturn(true);
		when(jwtutil.extractUsername("token")).thenReturn("vani");
		when(userRepository.findById("vani")).thenReturn(Optional.ofNullable(userData));
		assertEquals(200, userService.validate("Bearer token").getStatusCodeValue());
	}
	@Test
	void validateTokenFail() {
		userData=new UserData("vani", "jinka", "vani", "vani", 0,"customer");
		when(jwtutil.validateToken("token")).thenReturn(true);
		when(jwtutil.extractUsername("token")).thenReturn("vani");
		when(userRepository.findById("vani")).thenReturn(Optional.ofNullable(null));
		assertEquals(204, userService.validate("Bearer token").getStatusCodeValue());
	}
	@Test
	void validateTokenFail2() {
		when(jwtutil.validateToken("token")).thenReturn(false);
		assertEquals(204, userService.validate("Bearer token").getStatusCodeValue());
	}
}
