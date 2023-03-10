package com.nv.userauthenticationservicespringboot.repository;


import com.nv.userauthenticationservicespringboot.model.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Integer> {

}
