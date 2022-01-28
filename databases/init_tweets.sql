SELECT 'CREATE DATABASE tweets'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'tweets')\gexec
