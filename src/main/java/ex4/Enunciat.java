package ex4;

public class Enunciat {
    /*
    1. Copia la classe HastTable de l'exercici anterior així com els seus jocs de proves dins aquest package i modifica
       el codi font perquè el faci servir (canvia "ex3" per "ex4" allà on toqui).
    */

    private int SIZE = 16;
    private int ITEMS = 0;
    private HashEntry[] entries = new HashEntry[SIZE];

    public int count() {
        return this.ITEMS;
    }

    public int size() {
        return this.SIZE;
    }

    // 2. Fes que la taula de hash permeti emmagatzemar altres tipus de dades (object) i no només strings,
    //    per això et caldrà modificar el tipus de dada que emmagatzema la classe "HashEntry" així com alguns paràmetres dels mètodes
    //    que la fan servir.

    public <K, V> void put(K key, V value) {
        int hash = getHash(key.toString());
        final HashEntry hashEntry = new HashEntry(key, value);

        if (entries[hash] == null) {
            entries[hash] = hashEntry;
        } else {
            HashEntry temp = entries[hash];
            while (temp.next != null)
                temp = temp.next;

            temp.next = hashEntry;
            hashEntry.prev = temp;
        }
        ITEMS++;
    }

    public <K> Object get(K key) {
        int hash = getHash(key.toString());
        if (entries[hash] != null) {
            HashEntry temp = entries[hash];

            while (!temp.key.equals(key))
                temp = temp.next;

            return temp.value;
        }

        return null;
    }

    public <K> void drop(K key) {
        int hash = getHash(key.toString());
        if (entries[hash] != null) {

            HashEntry temp = entries[hash];
            while (!temp.key.equals(key))
                temp = temp.next;

            if (temp.prev == null) {
                entries[hash] = null;
            } else {
                if (temp.next != null) temp.next.prev = temp.prev;
                temp.prev.next = temp.next;
            }
            ITEMS--;
        }
    }

    private int getHash(String key) {
        return key.hashCode() % SIZE;
    }

    private static class HashEntry<K, V> {
        K key;
        V value;

        HashEntry next;
        HashEntry prev;

        public HashEntry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
            this.prev = null;
        }

        @Override
        public String toString() {
            return "[" + key.toString() + ", " + value.toString() + "]";
        }
    }

    @Override
    public String toString() {
        int bucket = 0;
        StringBuilder hashTableStr = new StringBuilder();
        for (HashEntry entry : entries) {
            if (entry == null) {
                bucket++;
                continue;
            }

            hashTableStr.append("\n bucket[")
                    .append(bucket)
                    .append("] = ")
                    .append(entry.toString());
            bucket++;
            HashEntry temp = entry.next;
            while (temp != null) {
                hashTableStr.append(" -> ");
                hashTableStr.append(temp.toString());
                temp = temp.next;
            }
        }
        return hashTableStr.toString();
    }

    public static void main(String[] args) {
        Enunciat hashTable = new Enunciat();

        // Put some key values.
        hashTable.put(1, "One"); // Probando con Integer y String
        hashTable.put(2, "Two");
        hashTable.put(3, "Three");

        hashTable.put("four", 4); // Probando con String y Integer
        hashTable.put("five", 5.0); // Probando con String y Double

        // Print the HashTable structure
        System.out.println("****   HashTable  ***");
        System.out.println(hashTable.toString());
        System.out.println("\nValue for key(2) : " + hashTable.get(2));
        System.out.println("Value for key(\"four\") : " + hashTable.get("four"));
    }

    private static void log(String msg) {
        System.out.println(msg);
    }
}

