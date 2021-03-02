import org.junit.Assert;
import org.junit.Test;

public class ReflectionTest {

    @Test
    public void reflectionTest() {
        ReflectionInfo<TestObject> reflection = new ReflectionInfo<>(TestObject.class);
        reflection.printFields(System.out);
        reflection.printConstructors(System.out);
        reflection.printMethods(System.out);

        TestObject testObject = new TestObject(2);
        Assert.assertTrue(reflection.exec(testObject, "push", 6));
        Assert.assertTrue(reflection.exec(testObject, "push", "T"));
        Assert.assertEquals(6, testObject.y);
        Assert.assertEquals("T", testObject.x);
    }
}
