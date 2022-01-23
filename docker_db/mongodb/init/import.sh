#! /bin/bash

mongoimport --host mongodb --authenticationDatabase admin --db mediscreendb --collection notes --type json --file /src/data/init_db_prod.json --jsonArray