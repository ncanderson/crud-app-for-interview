#!/bin/bash

BASEDIR=$(dirname $0)
psql -U postgres -f "$BASEDIR/set_up.sql" &&
createdb -U postgres cmm &&
psql -U postgres -d cmm -f "$BASEDIR/schema.sql" &&
psql -U postgres -d cmm -f "$BASEDIR/users.sql"