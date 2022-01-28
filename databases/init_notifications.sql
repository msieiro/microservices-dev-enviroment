SELECT 'CREATE DATABASE notifications'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'notifications')\gexec
