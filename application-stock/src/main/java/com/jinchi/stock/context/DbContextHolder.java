package com.jinchi.stock.context;

/**
 * 这里通过determineCurrentLookupKey（）返回的不同key到sqlSessionFactory中获取对应数据源然后使用ThreadLocal来存放线程的变量，将不同的数据源标识记录在ThreadLocal中
 */
public class DbContextHolder {
    public enum DbType {
        MASTER, SLAVE
    }

    private static final ThreadLocal<DbType> contextHolder = new ThreadLocal<>();

    public static void setDbType(DbType dbType) {
        if (dbType == null) {
            throw new NullPointerException();
        }
        contextHolder.set(dbType);
    }

    public static DbType getDbType() {
        return contextHolder.get() == null ? DbType.MASTER : contextHolder.get();
    }

    public static void clearDbType() {
        contextHolder.remove();
    }
}