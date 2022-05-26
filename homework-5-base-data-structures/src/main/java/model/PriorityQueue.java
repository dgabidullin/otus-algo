package model;

import java.util.LinkedList;

public class PriorityQueue<T> {
    int max = 0;

    LinkedList<PriorityNode<T>> queue = new LinkedList<>();

    public void enqueue(int priority, T item) {
        PriorityNode<T> n = new PriorityNode<>(item, priority);
        queue.add(n);

        if (priority > this.max) {
            this.max = priority;
        }
    }

    public T dequeue() {
        int min = this.max;
        PriorityNode<T> minNode = null;
        int index = 0;
        int minIndex = 0;

        for (PriorityNode<T> node : queue) {
            if (node.priority <= min) {
                minNode = node;
                min = node.priority;
                minIndex = index;
            }

            index++;
        }

        if (queue.size() == 0) {
            this.max = 0;
        }

        if (minNode != null) {
            queue.remove(minIndex);

            return minNode.elem;
        }

        return null;
    }

    public int size() {
        return queue.size();
    }

    private record PriorityNode<T>(T elem, int priority) {
    }
}
