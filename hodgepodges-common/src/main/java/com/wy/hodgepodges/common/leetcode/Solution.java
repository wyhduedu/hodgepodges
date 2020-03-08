package com.wy.hodgepodges.common.leetcode;

import java.util.LinkedList;
import java.util.Stack;

public class Solution {
    /*
    给定两个非空链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储单个数字。将这两数相加会返回一个新的链表。
    你可以假设除了数字 0 之外，这两个数字都不会以零开头。
    进阶:
    如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
    示例:
    输入: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
    输出: 7 -> 8 -> 0 -> 7
    7243
     564
     */

    public ListNode addTwoNumbers(ListNode b, ListNode e) {
        Stack<ListNode> stack = new Stack();
        Stack<ListNode> stack1 = new Stack();
        stack.push(b);
        stack1.push(e);
        while (!(b.next == null && e.next == null)) {
            if (b.next != null) {
                stack.push(b.next);
                b = b.next;
            }
            if (e.next != null) {
                stack1.push(e.next);
                e = e.next;
            }
        }

        LinkedList<ListNode> stack2 = new LinkedList<ListNode>() {
        };
        int ll = 0;
        int aa = 0;
        while (!(stack.empty() && stack1.empty())) {
            ListNode pop = stack.empty() ? null : stack.pop();
            ListNode pop1 = stack1.empty() ? null : stack1.pop();
            aa = (pop == null ? 0 : pop.val) + (pop1 == null ? 0 : pop1.val) + ll;
            if (aa >= 10) {
                ll = aa / 10;
                aa = aa % 10;
            } else {
                ll = 0;
            }
            ListNode listNode = new ListNode(aa);
            stack2.push(listNode);
            if (stack.empty() && stack1.empty()&& ll != 0) {
                stack2.push(new ListNode(ll));
            }
            aa=0;
        }



        ListNode listNode = stack2.pollLast();
        ListNode listNode1;
        while (!stack2.isEmpty()) {
            listNode1 = stack2.pollLast();
            listNode1.next = listNode;
            listNode = listNode1;
        }


        return listNode;

    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    public static void main(String[] args) {
        ListNode b = new ListNode(7);
//        ListNode a = new ListNode(4);
//        ListNode d = new ListNode(3);
//        ListNode c = new ListNode(2);
//        a.next = d;
//        c.next = a;
//        b.next = c;

        ListNode e = new ListNode(5);
//        ListNode f = new ListNode(6);
//        ListNode g = new ListNode(4);
//        f.next = g;
//        e.next = f;
        Solution solution = new Solution();
        solution.addTwoNumbers(b, e);




        System.out.println("cc");

    }

}
