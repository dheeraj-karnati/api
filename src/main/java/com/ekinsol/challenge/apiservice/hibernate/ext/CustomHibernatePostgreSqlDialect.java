package com.ekinsol.challenge.apiservice.hibernate.ext;

import org.hibernate.dialect.PostgreSQL95Dialect;

import java.sql.Types;

public class CustomHibernatePostgreSqlDialect extends PostgreSQL95Dialect {

    public CustomHibernatePostgreSqlDialect() {
        this.registerColumnType(Types.JAVA_OBJECT, "jsonb");
    }
}
