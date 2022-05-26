package model;

import java.util.LinkedList;

public class PriorityQueue<T> {
    int max = 0;

    private final LinkedList<PriorityNode<T>> pq = new LinkedList<>();

    public void enqueue(int priority, T item) {
        PriorityNode<T> n = new PriorityNode<>(item, priority);
        pq.add(n);

        if (priority > this.max) {
            this.max = priority;
        }
    }

    public T dequeue() {
        int min = this.max;
        PriorityNode<T> minNode = null;
        int index = 0;
        int minIndex = 0;

        for (PriorityNode<T> node : pq) {
            if (node.priority <= min) {
                minNode = node;
                min = node.priority;
                minIndex = index;
            }
            index++;
        }

        if (pq.size() == 0) {
            this.max = 0;
        }

        if (minNode != null) {
            pq.remove(minIndex);
            return minNode.elem;
        }

        return null;
    }

    public int size() {
        return pq.size();
    }

    private record PriorityNode<T>(T elem, int priority) {
    }
}
