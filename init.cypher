
// Create Athletes
CREATE (athlete1: Athlete {
  first_name: "name1",
  last_name: "last1",
  country: "arg",
  birth_date: date("2023-01-01")
})

CREATE (athlete2: Athlete {
  first_name: "name2",
  last_name: "last2",
  country: "arg",
  birth_date: date("2023-01-01")
})

// Create Events
CREATE (event1: Event {
  name: "Maraton Unico de la Rioja",
  category: "Running",
<<<<<<< HEAD
  distance: 10000",
=======
  distance: 10000,
>>>>>>> controller
  location: "La Rioja",
  date: date("2023-12-22"),
  edition: 2023,
  official_site: "https://ejemplo.com/maraton2023"
})

// Create Classifications
CREATE (classification1: Classification {
  time: duration("PT1H30M55S"),
  position: 1
})

CREATE (classification2: Classification {
  time: duration("PT1H30M57S"),
  position: 2

})

// Create relationships between Athletes and Classification
CREATE (athlete1)-[:CLASSIFIED_WITH]->(classification1)
CREATE (athlete2)-[:CLASSIFIED_WITH]->(classification2)

// Create relationship between Event and Classification
CREATE (event1)-[:HAS_CLASSIFICATION]->(classification1)
CREATE (event1)-[:HAS_CLASSIFICATION]->(classification2)