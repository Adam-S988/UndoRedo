/**
 * Implememt an application that support undo/redo functionality. Use a linked list to maintain a sequence of states.\
 * Each state change is stored as a node in the list, allowoing for easy navigation
 * 1<>2<>3<>4<>5
 */


 public class UndoRedoManager<T> {
    private class Node {
        private T state;
        private Node prev;
        private Node next;
        private Node (T state) {

            this.state = state;

        }

    }
    private Node currentState;
    //Undo operation
    public T undo(){
       if (currentState != null && currentState.prev != null) {
           System.out.println("Undo: " + currentState.state + ". " + "Currently on: " + currentState.prev.state);
           currentState = currentState.prev;
           return currentState.state;
       }
       System.out.println("Cannot undo. At the earliest state.");
       return null;
    }

    //perform an operation
    public void  addState (T newState) {
            Node node = new Node(newState);
            if(currentState == null) {
                currentState = node;
            } else {
                currentState.next = node; //Pointer from last node pointing to this node
                node.prev = currentState; //Pointer from future node point back to this one
                currentState = node; //The node itself
                }
                System.out.println(newState);
            }

    //Redo Operation
    public T redo(){
        if (currentState != null && currentState.next != null) {
            System.out.println("Redo: " + currentState.next.state);
            currentState = currentState.next;
            return currentState.state;
        }
        System.out.println("Cannot redo. Nothing to redo.");
        return null;
    }

    public static void main(String[] args) {
        UndoRedoManager<String> UndoManager = new UndoRedoManager<>();
        UndoManager.addState("Pizza");
        UndoManager.addState("Burger");
        UndoManager.addState("Spaghetti");

        UndoManager.undo();
        UndoManager.redo();
        UndoManager.redo();

        UndoManager.addState("Candy");

    }
}

