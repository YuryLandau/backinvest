package ti1.utils;

public class Path {
    public static class Web {
        public static final String INDEX = "/index/";
        public static final String LOGIN = "/login/";
        public static final String LOGOUT = "/logout/";
    }

    public static class Template {
        public final static String INDEX = "/velocity/index/index.vm";
        // public final static String LOGIN = "/velocity/login/login.vm";
        // public final static String BOOKS_ALL = "/velocity/book/all.vm";
        // public static final String BOOKS_ONE = "/velocity/book/one.vm";
        public static final String NOT_FOUND = "/velocity/notFound.vm";
    }
}
