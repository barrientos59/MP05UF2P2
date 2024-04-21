import ex2.Enunciat;

public class EnunciatTest {
    public static void main(String[] args) {
        testPut();
        testGet();
        testDrop();
        testCount();
        testSize();
    }

    private static void testPut() {
        Enunciat hashTable = new Enunciat();
        hashTable.put("1", "One");
        hashTable.put("2", "Two");
        hashTable.put("3", "Three");
        // Verificar que los elementos se agreguen correctamente
        System.out.println("Test Put: " + hashTable.toString());
    }

    private static void testGet() {
        Enunciat hashTable = new Enunciat();
        hashTable.put("1", "One");
        hashTable.put("2", "Two");
        hashTable.put("3", "Three");
        // Verificar que se pueda recuperar un elemento correctamente
        System.out.println("Test Get: " + hashTable.get("2"));
    }

    private static void testDrop() {
        Enunciat hashTable = new Enunciat();
        hashTable.put("1", "One");
        hashTable.put("2", "Two");
        hashTable.put("3", "Three");
        hashTable.drop("2");
        // Verificar que se elimine un elemento correctamente
        System.out.println("Test Drop: " + hashTable.toString());
    }

    private static void testCount() {
        Enunciat hashTable = new Enunciat();
        hashTable.put("1", "One");
        hashTable.put("2", "Two");
        hashTable.put("3", "Three");
        // Verificar que se cuente el número de elementos correctamente
        System.out.println("Test Count: " + hashTable.count());
    }

    private static void testSize() {
        Enunciat hashTable = new Enunciat();
        hashTable.put("1", "One");
        hashTable.put("2", "Two");
        hashTable.put("3", "Three");
        // Verificar que se obtenga el tamaño correcto
        System.out.println("Test Size: " + hashTable.size());
    }
}
