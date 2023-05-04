package org.example;

import java.util.concurrent.atomic.AtomicReference;

public class StackingFuckingGood {
    AtomicReference<Node> head;

    public StackingFuckingGood() {
        head = new AtomicReference<>();
    }

    public void push(int num) {
        if (head.get() != null) {
            Node oldHead;
            Node newHead;

            do {
                oldHead = head.get();
                newHead = new Node(num, Math.min(num, oldHead.value), oldHead);
                newHead.next = oldHead;
            } while (!head.compareAndSet(oldHead, newHead));

        } else {
            head.set(new Node(num, num, null));
        }
    }

    public Integer pop() {
        if (head.get() == null) {
            return null;
        }

        Node oldHead;
        Node newHead;

        do {
            oldHead = head.get();
            newHead = oldHead.next;
        } while (!head.compareAndSet(oldHead, newHead));

        return oldHead.value;
    }

    public Integer peek() {
        if (head.get() == null) {
            return null;
        }

        return head.get().value;
    }

    public Integer min() {
        if (head.get() == null) {
            return null;
        }

        return head.get().minSoFar;
    }

    @Override
    public String toString() {
        return "StackingFuckingGood{" +
                "head=" + head.get() +
                '}';
    }
}

/*
push
pop
peek
min
 */