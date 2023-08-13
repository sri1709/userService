package com.iiht.productpp.seviceimpl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.iiht.productpp.exception.UnauthorizedException;
import com.iiht.productpp.model.UserData;
import com.iiht.productpp.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

/**Service class*/
@Service
@Slf4j
public class CustomerDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userdao;

	/**
	 * @param String
	 * @return User 
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String uname) {
		
		try
		{
			log.info("loading the user");
			Optional<UserData> user=userdao.findById(uname);
			if(user.isPresent()) {
				return new User(user.get().getUserName(), user.get().getPassword(), new ArrayList<>());
			}
			else {
				log.info("user not found");
				throw new UsernameNotFoundException("User id not found");
			}
		}
		catch (Exception e) {
			log.info("exception occured");
			throw new UnauthorizedException("Username Not Found Exception");
		}	
		
		
	}

}
