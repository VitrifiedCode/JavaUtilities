package com.github.vitrifiedcode.javautilities.propterties;

public final class Properties
{
    private Properties() {}

    public static boolean valueOf(String property)
    {
        return Boolean.parseBoolean(System.getProperty(property));
    }

    public static final class OS
    {
        private OS() {}

        public static final String OS_NAME = System.getProperty("os.name");
        public static final boolean IS_WINDOWS = OS_NAME.toLowerCase().contains("win");
        public static final boolean IS_MAC = OS_NAME.toLowerCase().contains("mac");
        public static final boolean IS_LINUX = OS_NAME.toLowerCase().contains("lin");

        public static final String PATH_SEPERATOR = System.getProperty("path.separator");
        public static final String LINE_SEPERATOR = System.getProperty("line.separator");
        public static final String FILE_SEPERATOR = System.getProperty("file.separator");

        public static final String USER_HOME = System.getProperty("user.home");
        public static final String USER_NAME = System.getProperty("user.name");
        public static final String USER_DIR = System.getProperty("user.dir");
    }

    public static final class JAVA
    {
        private JAVA() {}

        public static final String RUNTIME_NAME = System.getProperty("java.runtime.name");
        public static final String JAVA_HOME = System.getProperty("java.home");
        public static final String JAVA_VERSION = System.getProperty("java.version");

        public static final boolean IS_JAR = JAVA.class.getResource("Properties.class").toString().startsWith("jar:") && !valueOf("devenv") && !valueOf("dev") && !valueOf("notJar");
    }
}
