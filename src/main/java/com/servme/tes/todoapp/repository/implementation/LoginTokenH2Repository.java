package com.servme.tes.todoapp.repository.implementation;

import com.servme.tes.todoapp.model.repository.LoginTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginTokenH2Repository extends JpaRepository<LoginTokenEntity, String> {

}
