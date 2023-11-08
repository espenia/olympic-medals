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
  distance: "10k",
  location: "La Rioja",
  date: date("2023-12-22"),
  edition: 2023,
  official_site: "https://ejemplo.com/maraton2023"
})

// Create Classifications
CREATE (classification1: Classification {
  time: duration("PT1H30M55S")
})

// Create relationships between Athletes and Classification
CREATE (athlete1)-[:PARTICIPATED_IN]->(classification1)
CREATE (athlete2)-[:PARTICIPATED_IN]->(classification1)

// Create relationship between Event and Classification
CREATE (event1)-[:HAS_CLASSIFICATION]->(classification1)