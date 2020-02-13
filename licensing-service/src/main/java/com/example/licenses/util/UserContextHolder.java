package com.example.licenses.util;

import org.springframework.util.Assert;

public class UserContextHolder {
    private static final ThreadLocal<UserContext> userContextThreadLocal = new ThreadLocal<UserContext>();

    public static final UserContext getContext() {
        UserContext context = userContextThreadLocal.get();
        if (context == null) {
            context = createEmptyContext();
            userContextThreadLocal.set(context);
        }
        return userContextThreadLocal.get();
    }

    public static final void setContext(UserContext context) {
        Assert.notNull(context, "Only non-null UserContext instances are permitted");
        userContextThreadLocal.set(context);
    }

    public static final UserContext createEmptyContext() {
        return new UserContext();
    }
}