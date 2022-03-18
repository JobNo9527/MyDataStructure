package Remove_LinkedList_Elements_No_203;

public class ListNode {
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

    public ListNode(int[] arr) {

        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("arr cannot be empty.");
        }

        this.val = arr[0];
        ListNode cur = this;
        for (int i = 1; i < arr.length; i++) {
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
    }

    //以当前的节点为头结点的信息（因为这个不是链表类，而是一个链表的节点类）
    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        ListNode cur = this;
        while (cur != null) {
            res.append(cur.val).append("->");
            cur = cur.next;
        }

        res.append("NULL");
        return res.toString();
    }
}