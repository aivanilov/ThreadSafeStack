package org.example;



public class Node {
    public int value;
    public int minSoFar;
    Node next;

    public  Node(int value, int minSoFar, Node next){
        this.value = value;
        this.minSoFar = minSoFar;
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", minSoFar=" + minSoFar +
                ", next=" + next +
                '}';
    }
}
