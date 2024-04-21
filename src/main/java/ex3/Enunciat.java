package ex3;


// Paso 1: Copia la clase HastTable de l'exercici anterior així com els seus jocs de proves dins aquest package
// i modifica el codi font perquè el faci servir (canvia "ex2" per "ex3" allà on toqui).

public class Enunciat {
    private int SIZE = 16;
    private int ITEMS = 0;
    private HashEntry[] entries = new HashEntry[SIZE];

    public int count() {
        return this.ITEMS;
    }

    public int size() {
        return this.SIZE;
    }

    public void put(String key, String value) {
        int hash = getHash(key);
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
        ITEMS++; // Incrementar el contador de elementos después de agregar uno nuevo
    }

    public String get(String key) {
        int hash = getHash(key);
        if (entries[hash] != null) {
            HashEntry temp = entries[hash];

            while (!temp.key.equals(key))
                temp = temp.next;

            return temp.value;
        }

        return null;
    }

    public void drop(String key) {
        int hash = getHash(key);
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
            ITEMS--; // Decrementar el contador de elementos después de eliminar uno
        }
    }

    private int getHash(String key) {
        return key.hashCode() % SIZE;
    }

    private static class HashEntry {
        String key;
        String value;

        HashEntry next;
        HashEntry prev;

        public HashEntry(String key, String value) {
            this.key = key;
            this.value = value;
            this.next = null;
            this.prev = null;
        }

        @Override
        public String toString() {
            return "[" + key + ", " + value + "]";
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
        for (int i = 0; i < 30; i++) {
            final String key = String.valueOf(i);
            hashTable.put(key, key);
        }

        // Print the HashTable structure
        log("****   HashTable  ***");
        log(hashTable.toString());
        log("\nValue for key(20) : " + hashTable.get("20"));
    }

    private static void log(String msg) {
        System.out.println(msg);
    }



    /*
    2. Aplica-hi el mètode de refacció "extracció de classe" i explica per què creus que ha sigut convenient
       aplicar-los.

    3. Aplica-hi el mètode de refacció "extracció de mètode" i explica per què creus que ha sigut convenient
       aplicar-los.

    4. Torna a executar els jocs de proves per a comprovar que el codi segueix funcionant correctament, ja que al
       fer-hi canvis refactoritzant es corre el risc de trencar el codi.
    */
}
