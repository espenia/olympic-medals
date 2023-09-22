package com.tdd.grupo5.medallero.repositories;

import java.util.List;
import com.tdd.grupo5.medallero.entities.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends Neo4jRepository<Person, Long> {

    Person findByName(String name);
//    List<Person> findByTeammatesName(String name);
}