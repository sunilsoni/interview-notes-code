package com.interview.notes.code.datastructure.Set;

import java.util.Arrays;

public class CustomHashSet1<T> {
    private int capacity = 16;
    private double loadFactor = 0.75d;
    private int size = 0;
    private int countForConvert = 5;
    private int countForBack = 4;
    private NodeSet[] buckets;

    public CustomHashSet1() {
        buckets = new NodeSet[capacity];
    }

    public CustomHashSet1(int capacity) {
        this.capacity = capacity;
        buckets = new NodeSet[capacity];
    }

    public CustomHashSet1(int capacity, double loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        buckets = new NodeSet[capacity];
    }

    public static void main(String[] args) {
        CustomHashSet1<Integer> set = new CustomHashSet1<>(4);
        set.add(new Integer(3));
        set.debug();
        set.add(new Integer(3));
        set.debug();
        set.add(new Integer(10));
        set.debug();
        set.add(new Integer(5));
        set.debug();
        set.add(new Integer(111));
        set.debug();
        set.add(new Integer(50));
        set.debug();
        set.delete(5);
        set.debug();
        set.add(null);
        set.debug();
        set.add(new Integer(23));
        set.debug();
        set.add(null);
        set.debug();
        System.out.println(set);
        System.out.println("Length: " + set.length());
        set.delete(50);
        set.debug();
        System.out.println(set);
        System.out.println("Length: " + set.length());
        System.out.println("Contains result: " + set.contains(23));
    }

    private int getBucket(T elem) {
        int hashCode = elem.hashCode();
//        return 0; // bad hashcode (adding elem in 0 index)
        return hashCode % this.capacity;
    }

    private void resizeSet(T elem) {
        int indexNewArray;
        T[] array;
        if (contains(elem)) {
            array = (T[]) new Object[size];
            indexNewArray = 0;
        } else {
            array = (T[]) new Object[size + 1];
            array[0] = elem;
            indexNewArray = 1;
        }

        for (int i = 0; i < capacity; i++) {
            if (buckets[i] != null) {
                NodeSet<T> node = buckets[i];
                while (node != null) {
                    array[indexNewArray] = node.value;
                    indexNewArray++;
                    node = node.next;
                }
            }
        }
        this.capacity = this.capacity * 2;
        buckets = new NodeSet[this.capacity];
        for (int i = 0; i < array.length; i++) {
            add(array[i]);
        }
        size = array.length;
    }

    private NodeSet<T> convertListIntoTree(NodeSet<T> head, T elem) {
        NodeSet<T> node = head;
        T[] array;
        int length = 0;
        while (node != null) {
            node = node.next;
            length++;
        }

        // переписываем лист в массив в зависимости от того, пришел ли null или есть ли уже null в списке. Сортируем масиив и обратно в список
        if (head.value == null) {
            array = (T[]) new Object[length];
            node = head.next;
        } else {
            if (elem == null) {
                array = (T[]) new Object[length];
            } else {
                array = (T[]) new Object[length + 1];
            }
            node = head;
        }
        if (elem != null) {
            array[0] = elem;
            for (int i = 1; i < array.length; i++) {
                array[i] = node.value;
                node = node.next;
            }
        } else {
            for (int i = 0; i < array.length; i++) {
                array[i] = node.value;
                node = node.next;
            }
        }

        Arrays.sort(array);

        NodeSet<T> tree = new NodeSet<>(array[0], null);
        NodeSet<T> newHead = tree;

        for (int i = 1; i < array.length; i++) {
            tree.next = new NodeSet<>(array[i], null);
            tree = tree.next;
        }

        if (head.value == null) {
            NodeSet<T> buf = newHead;
            head.next = buf;
            newHead = head;
            size = array.length + 1;
        } else {
            if (elem == null) {
                NodeSet<T> newElem = new NodeSet<>(elem, null);
                newElem.next = newHead;
                newHead = newElem;
                size = array.length + 1;
            } else {
                size = array.length;
            }
        }
        return newHead;
    }

    private NodeSet<T> convertTreeIntoList(NodeSet<T> head) {
        NodeSet<T> node = head;
        T[] array = (T[]) new Object[countForBack];
        int index = 0;
        while (node != null) {
            array[index] = node.value;
            node = node.next;
            index++;
        }

        T buf = array[1];
        array[1] = array[2];
        array[2] = buf;

        NodeSet<T> tree = new NodeSet<>(array[countForBack - 1], null);
        NodeSet<T> newHead = tree;
        for (int i = array.length - 2; i >= 0; i--) {
            tree.next = new NodeSet(array[i], null);
            tree = tree.next;
        }
        return newHead;
    }

    private NodeSet<T> needBackConvert(NodeSet<T> head) {
        int countBack = 0;
        NodeSet<T> iter = head;
        while (iter != null) {
            countBack++;
            iter = iter.next;
        }
        if (countBack == this.countForBack) {
            head = convertTreeIntoList(head);
        }
        return head;
    }

    public void add(T elem) {
        int countForResize = 0;
        for (int i = 0; i < capacity; i++) {  // проверяем нужно ли делать ресайз
            if (buckets[i] != null) {
                countForResize++;
            }
        }
        if (countForResize >= Math.round(capacity * loadFactor)) {
            resizeSet(elem);
            return;
        }

        int index;
        if (elem == null) {
            index = 0;
        } else {
            index = Math.abs(getBucket(elem));
        }
        boolean isEqual = false;
        int countForTree = 1;
        if (buckets[index] != null) {
            NodeSet<T> node = buckets[index];
            if (elem == null) {
                if (node.value != null) {  // если приходящий null и его нет в списке, то считаем количество для перестройки в дерево и добавляем первым. Если такой есть, то return
                    NodeSet<T> iter = node;
                    while (iter != null) {
                        iter = iter.next;
                        countForTree++;
                    }
                    if (countForTree < this.countForConvert) {
                        node = new NodeSet<>(elem, null);
                        node.next = buckets[index];
                        buckets[index] = node;
                        size++;
                    } else {
                        buckets[index] = convertListIntoTree(buckets[index], elem);
                    }
                }
                return;
            }
            while (node != null) {
                if (node.value != null) {  // если элемент не null, то нужно проверить первый в списке на null (если бакет нулевой выпадет), чтобы не вывалилось nullpoiner
                    if (!elem.equals(node.value) && elem.hashCode() != node.value.hashCode()) { // стандартно сравниваем и считаем количество для перестройки в дерево
                        node = node.next;
                        countForTree++;
                    } else {
                        node.value = elem;
                        isEqual = true;
                        break;
                    }
                } else {
                    node = node.next;
                    countForTree++;
                }
            }
            if (isEqual == false) { // если не нашли совпадений, то нужно добавлять в список (в голову), проверив количество для перестройки в дерево. Нужно проверить первый на null, чтобы его не потерять в итоге (если бакет нулевой)
                if (countForTree < this.countForConvert) {
                    if (buckets[index].value == null) {
                        NodeSet<T> buf = new NodeSet<>(elem, null);
                        NodeSet<T> next = buckets[index].next;
                        buckets[index].next = buf;
                        buf.next = next;
                        size++;
                    } else {
                        NodeSet buf = new NodeSet<>(elem, null);
                        buf.next = buckets[index];
                        buckets[index] = buf;
                        size++;
                    }
                } else {
                    buckets[index] = convertListIntoTree(buckets[index], elem);
                }
            }
        } else { //  если бакет изначально пуст
            buckets[index] = new NodeSet<>(elem, null);
            size++;
        }
    }

    public void delete(T elem) {
        if (!contains(elem)) {
            return;
        }
        if (elem == null) { // метод needBackConvert проверяет нужно ли делать конверт из дерева обратно в список
            buckets[0] = buckets[0].next;
            size--;
            buckets[0] = needBackConvert(buckets[0]);
            return;
        }
        for (int i = 0; i < capacity; i++) {
            if (buckets[i] != null) {
                NodeSet<T> node;
                if (buckets[i].value == null) { // если элемент не null и удаляется с нулевого бакета, то пропускаем первый, чтобы не выпало nullpointer
                    node = buckets[i].next;
                    if (node == null) {      //  если вышло так, что проверяем в нулевом бакете, и есть только null, то выходим из цикла во избежании nullpointer
                        continue;
                    }
                } else {
                    node = buckets[i];
                }
                if (!node.value.equals(elem)) {  // стандартная сверка для удаления
                    while (node.next != null) {
                        if (!node.next.value.equals(elem)) {
                            node = node.next;
                        } else {
                            node.next = node.next.next;
                            size--;
                            buckets[i] = needBackConvert(buckets[i]);
                            return;
                        }
                    }
                } else {
                    if (node.value.equals(elem) && i == 0 && buckets[0].value == null) { // проверка если удаляемый идет сразу же за null в нулевом бакете, чтобы не потерять null элемент
                        buckets[i].next = buckets[i].next.next;
                        size--;
                        buckets[i] = needBackConvert(buckets[i]);
                        return;
                    } else {
                        buckets[i] = buckets[i].next;
                        size--;
                        buckets[i] = needBackConvert(buckets[i]);
                        return;
                    }
                }
            }
        }
    }

    public boolean contains(T elem) {
        if (elem == null && buckets[0].value == null) {
            return true;
        }
        for (int i = 0; i < capacity; i++) {
            if (buckets[i] != null) {
                NodeSet<T> node = buckets[i];
                while (node != null) {
                    if (node.value != null) {
                        if (node.value.equals(elem)) {
                            return true;
                        }
                        node = node.next;
                    } else {
                        node = node.next;
                    }
                }
            }
        }
        return false;
    }

    public int length() {
        return size;
    }

    @Override
    public String toString() {
        String result = "[";
        for (int i = 0; i < capacity; i++) {
            if (buckets[i] != null) {
                NodeSet<T> node = buckets[i];
                while (node != null) {
                    result = result + node.value + ", ";
                    node = node.next;
                }
            }
        }
        result = result.substring(0, result.length() - 2) + "]";
        return result;
    }

    /// ///////DEBUG///////////////////////////
    // просто для проверки что куда добавляет
    private void debug() {
        String result = "[";
        for (int i = 0; i < capacity; i++) {
            if (buckets[i] != null) {
                result = result + "{";
                NodeSet node = buckets[i];
                while (node != null) {
                    result = result + node.value + "->";
                    node = node.next;
                }
                result = result + "}, ";
            } else {
                result = result + "null, ";
            }
        }
        result = result.substring(0, result.length() - 2) + "]";
        System.out.println(result);
    }

    private class NodeSet<T> {
        T value;
        NodeSet next;

        NodeSet(T elem, NodeSet next) {
            this.value = elem;
            this.next = next;
        }
    }
}