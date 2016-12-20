-- *****************************
-- Destroy DB and users
-- ******************************

SELECT pg_terminate_backend(pid) FROM pg_stat_activity WHERE datname = 'cmm';

DROP DATABASE cmm;

DROP USER app_owner;
DROP USER app_user;