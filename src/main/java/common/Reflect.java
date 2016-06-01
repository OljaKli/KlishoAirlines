package common;

/**
 * Created by Ola-Mola on 30/05/16.
 */
public interface Reflect {

    static void loadClass(String className) {
        loadClass(className, "Can't find class " + className);
    }

    static void loadClass(String className, String message) {
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(message, e);
        }
    }
}
