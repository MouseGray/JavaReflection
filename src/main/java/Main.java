public class Main {
    public static void main(String[] args) {
        ReflectionInfo reflection = new ReflectionInfo(TestObject.class);
        reflection.printMethods(System.out);
    }
}
