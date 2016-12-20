-- ******************************
-- Create users
-- ******************************

CREATE USER app_owner WITH PASSWORD 'app_owner1';

GRANT ALL 
ON ALL TABLES IN SCHEMA public
TO app_owner;

GRANT ALL 
ON ALL SEQUENCES IN SCHEMA public
TO app_owner; 

CREATE USER app_user WITH PASSWORD 'app_user1';

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO app_user;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO app_user;