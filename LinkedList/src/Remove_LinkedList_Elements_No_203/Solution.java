package Remove_LinkedList_Elements_No_203;

/**
 * @author 将晖
 */


public class Solution {
    public ListNode removeElements(ListNode head, int val) {

        //删除完头结点之后，可能头结点依旧是那个传来的val，因此使用while一直把他删除干净即可
        while (head != null && head.val == val) {
            ListNode delNode = head;
            head = head.next;
            delNode.next = null;
        }

        if (head == null)
            return head;

        ListNode prev = head;
        while (prev.next != null) {
            if (prev.next.val == val) {
                ListNode delNode = prev.next;
                prev.next = prev.next.next;
                delNode.next = null;
            } else {
                prev = prev.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {

        int[] nums = {1,2,6,3,4,5,6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = new Solution().removeElements(head, 6);
        System.out.println(res);

        ListNode res1 = new Solution2().removeElements(head, 1);
        System.out.println(res1);
    }
}
