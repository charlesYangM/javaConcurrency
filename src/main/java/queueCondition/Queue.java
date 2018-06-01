package queueCondition;

import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by CharlesYang on 2018/5/31.
 */
public class Queue<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int N;

    private class Node {
        Item item;
        Node next;
    }
    public void enqueue(Item item){
        Node oldLast = last;//如果是空队列那么此时oldLast将为Null
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()){
            first = last;
        }else {
            oldLast.next = last;
        }
        N++;
    }
    public Item dequeue(){
        Item item = first.item;
        first = first.next;
        if (isEmpty()){
            last = null;
        }
        N--;
        return item;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public int size(){
        return N;
    }

//
//    private ArrayList<E> arrayList = new ArrayList<>();
//    private int count;
//
//
//    public boolean put(Object<? extends E> ie){
//
//        arrayList.set(count - 1, ie);
//        count = arrayList.size();
//    }
//
//    public E get(){
//        return arrayList.get(0);
//    }

    @Override
    public Iterator<Item> iterator() {
        return null;
    }


}
