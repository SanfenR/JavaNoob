import java.lang.reflect.Method;

public class Main {
    public static void main( String[] args) {

        try {
            Class child = Class.forName("Child");
            boolean annotationPresent = child.isAnnotationPresent(Description.class);
            if (annotationPresent) {
                Description description = (Description) child.getAnnotation(Description.class);
                System.out.println(description.value());
            }
            Method name = child.getMethod("name");
            boolean annotation = name.isAnnotationPresent(Description.class);
            if (annotation) {
                Description annotation1 = name.getAnnotation(Description.class);
                System.out.println(annotation1.value());
            }
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
}
