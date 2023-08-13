package com.iiht.productpp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iiht.productpp.model.UserData;


@Repository
public interface UserRepository extends JpaRepository<UserData, String> {

}