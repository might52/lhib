#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
	CREATE DATABASE testDB;
	GRANT ALL PRIVILEGES ON DATABASE testDB TO postgres;
EOSQL

#su postgres
#echo "host all all all $POSTGRES_HOST_AUTH_METHOD" >> pg_hba.conf
# in case when you have a problem with execution scripts please add execution rights
# to the script.