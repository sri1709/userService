package com.iiht.product.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import com.iiht.productpp.exception.UnauthorizedException;
import com.iiht.productpp.model.UserData;
import com.iiht.productpp.repository.UserRepository;
import com.iiht.productpp.seviceimpl.CustomerDetailsService;

@ExtendWith(MockitoExtension.class)
class ServiceTest {

	UserDetails userdetails;
	
	@Mock
	UserRepository userservice;
	
	@InjectMocks
	CustomerDetailsService custdetailservice;

	
	

	@Test
	 void loadUserByUsernameTest() {
		
		UserData user1=new UserData("vani", "jinka", "vani", "vani", 0,"customer");
		Optional<UserData> data =Optional.of(user1) ;
		when(userservice.findById("vani")).thenReturn(data);
		UserDetails loadUserByUsername2 = custdetailservice.loadUserByUsername("vani");
		assertEquals(user1.getUserName(),loadUserByUsername2.getUsername());
	}
	@Test
	 void loadUserByUsernameTestFail() {
		
		Optional<UserData> data =Optional.ofNullable(null) ;
		when(userservice.findById("vani")).thenReturn(data);
		assertThrows( UnauthorizedException.class,()->custdetailservice.loadUserByUsername("vani"));
	}
	
	
	
	@Test
	void userNotFound() {
		
		when(userservice.findById("vani")).thenReturn(null);
		assertThrows( UnauthorizedException.class,()->custdetailservice.loadUserByUsername("vani"));
	}

	
}
