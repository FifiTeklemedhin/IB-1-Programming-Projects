/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlist;

import java.util.Iterator;

/**
 *
 * @author root
 */
public class APLinkedList_Connor<E> implements Iterable 
{

    private Node<E> first = null;

    public E add(E data) {
        if (this.size() == 0) {
            return this.insert(data, 0);
        }
        return this.insert(data, this.size() - 1);
    }

    public E insert(E data, int position) {
        /*        if (this.size() < position || position < 0) {
        return null;
        } else if (position == 0) {
        if (this.first != null) {
        Node nextNode = this.first.next;
        this.first = new Node(data, nextNode);
        } else {
        this.first = new Node(data, null);
        }
        return data;
        } else {
        Node previousNode = this.getNode(position - 1);
        Node nextNode = this.getNode(position);
        Node<E> newNode = new Node<E>(data, nextNode);
        previousNode.next = newNode;
        return data;
        }*/

        Node<E> currentNode = this.first;
        Node<E> nextNode = null;

        if (this.size() == 0) {
            this.first = new Node(data, null);
            return data;
        }
        if (position > this.size() - 1) {
            return null;
        }

        if (position == this.size() - 1) {

            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }

            currentNode.next = new Node(data, null);
            return data;
        } else {
            for (int i = 0; i < (position - 1) && i >= 0; i++) {
                currentNode = currentNode.next;
            }
        }

        nextNode = currentNode.next;
        currentNode.next = new Node(data, nextNode);

        return data;
    }

    public E remove(int position) {
        if (this.size() < position || position < 0) {
            return null;
        } else if (this.size() == position) {
            Node previousNode = this.getNode(position - 1);
            E data = (E) this.getNode(position).data;
            previousNode.next = null;
            return data;
        } else if (position == 0) {
            E data = (E) this.getNode(position).data;
            this.first = this.getNode(position + 1);
            return data;
        } else {
            Node previousNode = this.getNode(position - 1);
            E data = (E) this.getNode(position).data;
            previousNode.next = this.getNode(position + 1);
            return data;
        }
    }

    public E remove(E data) {
        if (this.first == null || this.size() <= 0) {
            return null;
        }

        int index = this.firstIndexOf(data);
        boolean hasData = index != -1;

        while (index != -1) {
            System.out.println(index);
            this.remove(index);
            index = this.firstIndexOf(data);
        }

        if (hasData) {
            return data;
        } else {
            return null;
        }
    }

    public int firstIndexOf(E data) {
        Node currentNode = this.first;
        int index = 0;

        while (currentNode != null) {
            if (currentNode.data.equals(data)) {
                return index;
            }
            currentNode = currentNode.next;
            index++;
        }
        return -1;
    }

    public boolean contains(E data) {
        if (this.firstIndexOf(data) != -1) {
            return true;
        }
        return false;
    }

    public E get(int position) {
        if (this.size() <= position || position < 0) {
            return null;
        }

        Node currentNode = this.first;
        for (int i = 0; i < (position - 1); i++) {
            currentNode = currentNode.next;
        }

        return (E) currentNode.data;
    }

    public int size() {
        if (this.first == null) {
            return 0;
        }
        Node currentNode = this.first;
        int nodeCount = 0;
        while (currentNode != null) {
            currentNode = currentNode.next;
            nodeCount++;
        }

        return nodeCount;
    }

    public String toString() {
        String result = "[";
        if (this.first == null) {
            return null;
        }

        Node currentNode = this.first;
        int nodeCount = 0;
        int size = this.size();

        while (currentNode != null) {
            nodeCount++;
            if (nodeCount != size) {
                result += currentNode.data + ", ";
            } else {
                result += currentNode.data;
            }
            currentNode = currentNode.next;
        }

        return result + "]";
    }

    private Node findLastNode() {
        if (this.first == null) {
            return null;
        }
        Node previousNode = this.first;
        Node currentNode = this.first.next;
        while (currentNode != null) {
            previousNode = currentNode;
            currentNode = currentNode.next;
        }
        return previousNode;
    }

    @Override
    public Iterator iterator() {
        return new LinkedListIterator(this);
    }

    public Node getNode(int position) {
        if (this.size() <= position || position < 0) {
            return null;
        }

        Node currentNode = this.first;
        for (int i = 0; i < (position); i++) {
            currentNode = currentNode.next;
        }

        return currentNode;
    }

    private class LinkedListIterator implements Iterator {

        Node<E> current;
        int index = 0;
        APLinkedList_Connor list;

        public LinkedListIterator(APLinkedList_Connor l) {
            this.list = l;
            this.current = this.list.first;
        }

        public boolean hasNext() {
            return this.current.data != null;
        }

        public E next() {
            if (this.hasNext()) {
                E data = this.current.data;
                this.current = this.current.next;
                this.index++;

                return data;
            } else {
                return null;
            }
        }

        public void remove() {
            if (this.hasNext()) {
                this.list.remove(this.index);
            }
        }
    }
}
