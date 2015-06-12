# java-retry-mybatis-sample

Demonstrates the use of the java-retry library for myBatis transactions.
The demo uses Spring to define the AOP pointcuts and advice so they're
not hard-coded nor intrusive. Any Spring-based myBatis application can
add transaction retry semantics to their DAOs seamlessly without code
changes.

## Database Credentials

A previously created database having the following credentials and name must
exist:

    database-name: person
    database-user: dba
    database-pass: dba
    database-host: localhost
    database-port: 48004

## DDL To Load

```
create table persons (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name STRING
);
```

## Demonstrating

With the application running, perform a kill -9 of a transaction engine for
a managed database. In milliseconds the management tier will restart the TE
and myBatis will retry the transaction successfully. No transactions are
lost.

## Issues

1. None.
