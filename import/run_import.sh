#!/bin/bash

# Log file for capturing import output
LOG_FILE="/var/lib/neo4j/import.log"

# Wait for Neo4j to be ready
until cypher-shell -u neo4j -p admin123 "RETURN 1" &> /dev/null; do
  >&2 echo "Neo4j is unavailable - sleeping"
  sleep 1
done

# Run the import commands
neo4j-admin database import full --overwrite-destination --nodes /import/athlete.csv, /import/event.csv, /import/classification.csv --relationships /import/athlete_classification.csv, /import/classification_event.csv neo4j > "$LOG_FILE" 2>&1

# Display the contents of the log file
cat "$LOG_FILE"