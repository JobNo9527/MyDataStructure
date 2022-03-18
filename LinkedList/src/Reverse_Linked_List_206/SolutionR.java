package Reverse_Linked_List_206;


public class SolutionR {
    public ListNode reverseList(ListNode head) {

        if (head == null || head.next == null)
            return head;

        // 以传入的这个头结点为链表进行反转，逐渐问题变小的方向移动
        ListNode rev = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return rev;
    }
}
