package xyz.guqing.app.dao;

import org.hibernate.dialect.InnoDBStorageEngine;
import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.dialect.MySQLStorageEngine;

public class MySQLDialect extends MySQL5Dialect {
    @Override
    protected MySQLStorageEngine getDefaultMySQLStorageEngine() {
        return InnoDBStorageEngine.INSTANCE;
    }
}
