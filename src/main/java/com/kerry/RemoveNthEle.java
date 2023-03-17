package com.kerry;

public class RemoveNthEle {


    public static void main(String[] args) {


        ListNode node = new ListNode(0);

        ListNode root = removeNthElement(node, 4);

    }

    private static ListNode removeNthElement(ListNode node, int n) {
        //新建假节点
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = node;

        ListNode fastNode = node;
        ListNode slowNode = node;

        //保证快慢指针相差n个元素
        for (int i = 0; i < n; i++) {
            fastNode = fastNode.next;
        }


        while (fastNode.next != null) {
            fastNode = fastNode.next;
            slowNode = slowNode.next;
        }

        //此时slowNode走到了倒数第n个元素的前一个元素。
        slowNode.next = slowNode.next.next;
        return dummyNode.next;


    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
