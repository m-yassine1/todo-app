package com.servme.tes.todoapp.repository.implementation;

import com.servme.tes.todoapp.model.repository.ForgotPasswordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForgotPasswordH2Repository extends JpaRepository<ForgotPasswordEntity, String> {

}
