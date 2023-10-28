package com.tdd.grupo5.medallero.entities;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Athlete {
  @Id @GeneratedValue private UUID id;
  private String name;
  private String country;
}
