import java.util.Objects;

public class Node implements Comparable<Node> {
    private int symbol, counter, number;
    private Node leftChild, rightChild, parent;

    public Node(int symbol, int counter, int number, Node leftChild, Node rightChild, Node parent) {
        this.symbol = symbol;
        this.counter = counter;
        this.number = number;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.parent = parent;
    }

    public Node(){
        symbol = counter = number = 0;
        leftChild = rightChild = parent = null;
    }

    public Node(int number){
        symbol = counter = 0;
        this.number = number;
        leftChild = rightChild = parent = null;
    }

    public int getSymbol() {
        return symbol;
    }

    public int getCounter() {
        return counter;
    }

    public int getNumber() {
        return number;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public Node getParent() {
        return parent;
    }

    public boolean hasLeftChild(){
        return leftChild != null;
    }

    public boolean hasRightChild(){
        return rightChild != null;
    }

    public void setSymbol(int symbol) {
        this.symbol = symbol;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int compareTo(Node other){ // CHANGE
        return this.number - other.number;
    }


    private void swapChild(Node current, Node to){
        if (this.rightChild == current)
            this.rightChild = to;
        else {
            assert (this.leftChild == current);
            this.leftChild = to;
        }
    }

    public void swapNumbers(Node other){
        int temp = this.number;
        this.setNumber(other.getNumber());
        other.setNumber(temp);
    }

    public void swapParent(Node v)
    {
        this.getParent().swapChild(this, v);
        v.getParent().swapChild(v, this);

        Node temp = this.getParent();
        this.setParent(v.getParent());
        v.setParent(temp);
    }

    public String toString(){
        return "" + (symbol == 256 ? "NYT" : (char)symbol) + "," + counter + "," + number;
    }
}