import org.junit.Test;

public class ReflectionTest {

    @Test
    public void reflectionTest() {
        ReflectionInfo reflection = new ReflectionInfo(TestObject.class);
        reflection.printFields(System.out);
        reflection.printConstructors(System.out);
        reflection.printMethods(System.out);

        TestObject testObject = new TestObject(2);
        System.out.println(reflection.exec(testObject, "push", 6));
        System.out.println(reflection.exec(testObject, "push", "T"));

        System.out.println(testObject.y);
        System.out.println(testObject.x);
    }
}
