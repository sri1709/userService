package com.iiht.productpp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="user_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserData {
	
	    @Column
	    @Schema(description = "First name of the user",required = true,example="sri")
	    @NotBlank(message="First name should not be empty")
	    private String firstName;
	    @Column
	    @Schema(description = "Last name of the user",required = true,example="kanth")
	    @NotBlank(message="Last name should not be empty")
	    private String lastName;
	    @Id
	    @Schema(description = "user name of the user",required = true,example="sri@gmail.com")
	    @Pattern(regexp = "[a-zA-Z0-9@.]*$", message = "user name should contain only alphabets and digits")
	    private String userName;
	    @Column
	    @Schema(description = "Password of the user",required = true,example="sri12345")
	    @NotBlank(message="Password should not be empty")
	    @Size(min = 8, message = "minimum 8 Characters required")
	    private String password;
	    @Column
	    @Schema(description = "Contact Number of the user",required = true,example="9698979542")
	    private long contactNo;
	    @SuppressWarnings("deprecation")
		@NotEmpty
	    private String role = "ROLE_CUSTOMER";
	    
}
