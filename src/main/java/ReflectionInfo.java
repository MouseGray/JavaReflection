import java.io.PrintStream;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ReflectionInfo<A> {
    Class<A> clazz;

    ReflectionInfo(Class<A> clazz) {
        this.clazz = clazz;
    }

    public void printFields(PrintStream printStream) {
        printStream.println("Fields:");
        for (Field field: clazz.getDeclaredFields()) {
            printStream.println(Modifier.toString(field.getModifiers()) + " " + field.getType().getName() + " " + field.getName());
        }
    }

    public void printConstructors(PrintStream printStream) {
        printStream.println("Constructors:");
        for (Constructor<?> constructor: clazz.getConstructors()) {
            printStream.println(Modifier.toString(constructor.getModifiers()) + " " + constructor.getName() + parametersToString(constructor));
        }
    }

    public void printMethods(PrintStream printStream) {
        printStream.println("Methods:");
        for (Method method: clazz.getDeclaredMethods()) {
            printStream.println(Modifier.toString(method.getModifiers()) + " " + method.getReturnType().getName() + " " + method.getName() + parametersToString(method));
        }
    }

    public boolean exec(A obj, String method, Object ...args) {
        for (Method m: clazz.getDeclaredMethods()) {
            if (m.getName().equals(method)) {
                try {
                    m.setAccessible(true);
                    m.invoke(obj, args);
                    return true;
                }
                catch (IllegalAccessException |
                        IllegalArgumentException |
                        InvocationTargetException |
                        NullPointerException |
                        ExceptionInInitializerError ignored) {  }
            }
        }
        return false;
    }

    private String parametersToString(Executable method) {
        String params = Arrays.stream(method.getParameterTypes())
                .map(Class::getName)
                .collect(Collectors.joining(", "));
        return "(" + params + ")";
    }
}
