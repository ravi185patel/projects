package com.ravidpatel.mybookingapp.config;

import com.ravidpatel.mybookingapp.constant.DbType;

public class DbContextHolder {

    private static final ThreadLocal<DbType> CONTEXT = new ThreadLocal<>();

    public static void useMaster() {
        CONTEXT.set(DbType.MASTER);
    }

    public static void useSlave() {
        CONTEXT.set(DbType.SLAVE);
    }

    public static DbType get() {
        return CONTEXT.get();
    }

    public static void clear() {
        CONTEXT.remove();
    }
}
