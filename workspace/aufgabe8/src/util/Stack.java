package util;

public class Stack {
    private final LinkedObjects data = new LinkedObjects();
    
    public void push(Object obj) {
        data.addFirst(obj);
    }
    
    public Object pop() {
        return data.removeFirst();
    }
}
