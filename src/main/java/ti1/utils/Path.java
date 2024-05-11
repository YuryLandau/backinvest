package ti1.utils;

import lombok.Getter;

public class Path {
    public static class Web {
        @Getter
        public static final String INDEX = "/index/";
        @Getter
        public static final String LOGIN = "/login/";
        @Getter
        public static final String LOGOUT = "/logout/";

        @Getter
        public static final String FINANCIAL = "/financial/";
    }

    public static class Template {
        public final static String INDEX = "/velocity/index/index.vm";
        public final static String FINANCIAL = "/velocity/financial/financial.vm";
        // public final static String BOOKS_ALL = "/velocity/book/all.vm";
        // public static final String BOOKS_ONE = "/velocity/book/one.vm";
        public static final String NOT_FOUND = "/velocity/notFound.vm";
    }
}
