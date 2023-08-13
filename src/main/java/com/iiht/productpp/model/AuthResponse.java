package com.iiht.productpp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
	private String username;
	private boolean valid;
	private String token;
	private String fname;
	private String lname;
	private String role;

}
