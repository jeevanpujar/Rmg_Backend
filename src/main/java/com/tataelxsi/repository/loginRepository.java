package com.tataelxsi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tataelxsi.entity.logIn;

@Repository
public interface loginRepository extends JpaRepository<logIn, Integer>{
	public logIn findByUserNameAndPassword(String userName,String password);

}
