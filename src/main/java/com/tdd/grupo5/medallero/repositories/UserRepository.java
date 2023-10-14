package com.tdd.grupo5.medallero.repositories;

import com.tdd.grupo5.medallero.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);
//    List<Person> findByTeammatesName(String name);
}