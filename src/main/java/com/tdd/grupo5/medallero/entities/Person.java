package com.tdd.grupo5.medallero.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.ArrayList;
import java.util.List;

@Node
@Getter
@Setter
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    private Long born;

    private String name;
    private List<Result> results;

    public Person(String name, long born) {
        this.born = born;
        this.name = name;
        this.results = new ArrayList<>();
    }

    public void addResult(Result new_result){

        this.results.add(new_result);

    }

    public int getStandingResultFrom(Event event){

        for (int i = 0; i < results.size(); i++){

            if(results.get(i).getEvent() == event){

                return results.get(i).getStanding();

            }

        }

        return 0;

    }
}
