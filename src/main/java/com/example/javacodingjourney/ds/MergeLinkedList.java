package com.example.javacodingjourney.ds;

import java.util.PriorityQueue;

public class MergeLinkedList {


    public static void main(String[] args) {

        ListNode sortedList[] = new ListNode[3];
        sortedList[0] = createSortedList1();
        sortedList[1] = createSortedList2();
        sortedList[2] = createSortedList3();

        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (int i = 0; i < sortedList.length; i++) {
            pq.offer(sortedList[i]);
        }
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            System.out.print(node.val + " ");
            current.next = node;
            current = current.next; // current = node
            if (node.next != null) {
                pq.offer(node.next);
            }
        }
//        ListNode head = dummy.next;
//        while (head != null) {
//            System.out.print(head.val + " ");
//            head = head.next;
//        }
    }
    public ListNode mergeSortedTwoLists(ListNode list1, ListNode list2) {
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        current.next = (list1 != null) ? list1 : list2;
        return dummyHead.next;
    }

    public ListNode mergeSortedLists(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        if (list1.val <= list2.val) {
            list1.next = mergeSortedLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeSortedLists(list1, list2.next);
            return list2;
        }
    }



    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    '}';
        }
    }

    public static ListNode createSortedList1() {
        ListNode l1 = new ListNode(3);
        ListNode l2 = new ListNode(4);
        ListNode l3 = new ListNode(54);
        ListNode l4 = new ListNode(71);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        return l1;
    }

    public static ListNode createSortedList2() {
        ListNode l1 = new ListNode(5);
        ListNode l2 = new ListNode(6);
        ListNode l3 = new ListNode(9);
        ListNode l4 = new ListNode(10);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        return l1;
    }

    public static ListNode createSortedList3() {
        ListNode l1 = new ListNode(8);
        ListNode l2 = new ListNode(22);
        ListNode l3 = new ListNode(13);
        ListNode l4 = new ListNode(51);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        return l1;
    }
}
