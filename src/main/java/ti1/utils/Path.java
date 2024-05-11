package ti1.utils;

import lombok.Getter;

public class Path {
    public static class Web {
        @Getter
        public static final String HOME = "/";
        @Getter
        public static final String INDEX = "/index/";
        @Getter
        public static final String FINANCIAL_REGISTRATION = "/financial-registration/";
        @Getter
        public static final String PERSONAL_REGISTRATION = "/personal-registration/";
        @Getter
        public static final String CALCULATOR = "/calculator/";
        @Getter
        public static final String LOGIN = "/login/";
        @Getter
        public static final String LOGOUT = "/logout/";

    }

    public static class Template {
        public final static String HOME = "/velocity/index/index.vm";
        public final static String INDEX = "/velocity/index/index.vm";
        public final static String FINANCIAL_REGISTRATION = "/velocity/financial-registration/index.vm";
        public final static String PERSONAL_REGISTRATION = "/velocity/personal-registration/index.vm";
        public final static String CALCULATOR = "/velocity/calculator/index.vm";
        public static final String NOT_FOUND = "/velocity/notFound.vm";
    }
}
