package com.tdd.grupo5.medallero.util.config;

import org.springframework.util.StringUtils;

public final class ScopeUtils {

    public static final String SCOPE_SUFFIX = "SCOPE_SUFFIX";

    public static final String ENV_SCOPE = "SCOPE";

    public static final String LOCAL_SCOPE = "local";

    public static final String TEST_SUFFIX = "test";

    public static final String PROD_SUFFIX = "prod";

    private ScopeUtils() {
        // Hide constructor
    }

    public static void calculateScopeSuffix() {
        String[] tokens = StringUtils.split(getScopeValue(), "-");
        if (tokens != null && tokens.length > 0) {
            System.setProperty(SCOPE_SUFFIX, tokens[tokens.length - 1]);
        } else {
            System.setProperty(SCOPE_SUFFIX, getScopeValue());
        }
    }

    public static boolean isLocalScope() {
        return getScopeValue().contains(LOCAL_SCOPE);
    }

    public static boolean isTestScope() {
        return getScopeValue()
                .contains(TEST_SUFFIX);
    }

    public static boolean isProdScope() {
        return getScopeValue()
                .contains(PROD_SUFFIX);
    }


    public static String getScopeValue() {
        String scope = System.getProperty(ENV_SCOPE);
        if (StringUtils.hasLength(scope)) {
            return scope;
        }
        return LOCAL_SCOPE;
    }

}
