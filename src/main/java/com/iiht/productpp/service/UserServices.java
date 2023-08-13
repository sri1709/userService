package com.iiht.productpp.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.iiht.productpp.model.AuthResponse;
import com.iiht.productpp.model.LoginDetails;
import com.iiht.productpp.model.UserData;


@Service
public interface UserServices {
	public ResponseEntity<AuthResponse> login(LoginDetails loginDetails);
	public ResponseEntity<Object> register(UserData user);
	public ResponseEntity<Object> forgotPassword(LoginDetails data);
	public ResponseEntity<AuthResponse> validate(String authToken);
	public ResponseEntity<Object> getAllUsers();
	public ResponseEntity<Object> searchByUsername(String username);
}
