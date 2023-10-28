package com.tdd.grupo5.medallero.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Node;
import java.util.ArrayList;
import java.util.List;


@Node
@Getter
@Setter
public class Athlete {

    private List<Result> results;

    public Athlete() {
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
