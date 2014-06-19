package memory;

import java.util.NoSuchElementException;

import util.Hashing;;

public class Variables {
    private static Hashing<String,Double> mem = new Hashing<String,Double>();

    public static void store(String name, Double value) {
        mem.put(name, value);
        System.out.print("Variable " + name + " mit dem Wert ");
        System.out.print(mem.get(name));
        System.out.println(" abgespeichert.");
    }
    
    public static Double load(String name) {
        Double result = mem.get(name);
        if (result == null)
            throw new NoSuchElementException(name);
        return result;
    }
}
