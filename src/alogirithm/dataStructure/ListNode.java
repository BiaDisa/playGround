package alogirithm.dataStructure;

import lombok.Data;

public class ListNode<T> {

    public T val;

    public ListNode<T> next;

    public ListNode(T val) {
        this.val = val;
    }
}
