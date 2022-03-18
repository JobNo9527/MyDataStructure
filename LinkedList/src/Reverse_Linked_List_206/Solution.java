package Reverse_Linked_List_206;


public class Solution {

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;

        while (cur != null){
            ListNode next = cur.next;   //注意小心cur为空情况，会报空指针异常   //这里也发生了next指针后移
            cur.next = pre; //反转
            pre = cur;
            cur = next;
        }

        // 当跳出循环的时候，cur = null，此时pre指向第一个反转后的头结点
        return pre;
    }
}
