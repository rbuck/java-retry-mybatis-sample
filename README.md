# java-retry-mybatis-sample

Demonstrates the use of the java-retry library for myBatis transactions.
The demo uses Spring to define the AOP pointcuts and advice so they're
not hard-coded nor intrusive. Any Spring-based myBatis application can
add transaction retry semantics to their DAOs seamlessly without code
changes.

## DDL To Load

```
create table persons (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name STRING
);
```

## Issues

1. None.
