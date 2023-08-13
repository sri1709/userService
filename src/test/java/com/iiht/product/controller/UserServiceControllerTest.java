package com.iiht.product.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import com.iiht.productpp.controller.UserServiceController;
import com.iiht.productpp.model.AuthResponse;
import com.iiht.productpp.model.LoginDetails;
import com.iiht.productpp.model.UserData;
import com.iiht.productpp.service.UserServices;


@ExtendWith(MockitoExtension.class)
class UserServiceControllerTest {
	
	@Mock
	UserServices userServices;
	
	@InjectMocks
	UserServiceController userController;
	
	UserDetails userdetails;
	UserData userData;
	Optional<UserData> user;
	LoginDetails data;
	
	@Test
	void register() {
		
		userData=new UserData("vani", "jinka", "vani", "vani", 0,"customer");
		when(userServices.register(userData)).thenReturn(new ResponseEntity<Object>("Added Successfully",HttpStatus.CREATED));
		assertEquals(201, userController.register(userData).getStatusCodeValue());
	}
	@Test
	void login() {
		data=new LoginDetails("vani","vani12");
		when(userServices.login(data)).thenReturn(new ResponseEntity<>(new AuthResponse(),HttpStatus.OK));
		assertEquals(200, userController.login(data).getStatusCodeValue());
	}
	@Test
	void forgotPassword() {
		data=new LoginDetails("vani","vani12");
		when(userServices.forgotPassword(data)).thenReturn(new ResponseEntity<>(new AuthResponse(),HttpStatus.OK));
		assertEquals(200, userController.forgotPassword("vani", data).getStatusCodeValue());
	}
	@Test
	void validate() {
		when(userServices.validate("token")).thenReturn(new ResponseEntity<>(new AuthResponse(),HttpStatus.OK));
		assertEquals(200, userController.getValidity("token").getStatusCodeValue());
	}
	@Test
	void getAllUsers() {
		
		when(userServices.getAllUsers()).thenReturn(new ResponseEntity<>(new ArrayList<>(),HttpStatus.OK));
		assertEquals(200, userController.getAllUsers().getStatusCodeValue());
	}
	@Test
	void searchuser() {
		
		when(userServices.searchByUsername("van")).thenReturn(new ResponseEntity<>(new ArrayList<>(),HttpStatus.OK));
		assertEquals(200, userController.searchByUsername("van").getStatusCodeValue());
	}
	

}
