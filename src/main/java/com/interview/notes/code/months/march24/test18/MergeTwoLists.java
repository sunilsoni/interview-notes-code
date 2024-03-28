package com.interview.notes.code.months.march24.test18;

/**
 * Given an array of k linked-lists, with each list sorted in ascending order,
 * * merge all the lists into one sorted list and return it.
 */

class ListNode {
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

/**
 * The runtime complexity of the Divide and Conquer algorithm for merging `k` sorted linked lists is O(N log k), where:
 * <p>
 * - `N` is the total number of nodes across all linked lists combined.
 * - `k` is the number of linked lists.
 * <p>
 * Let's break down why the time complexity is O(N log k):
 * <p>
 * 1. **Division of Lists (Divide Step)**: We split the `k` lists into pairs and merge them. Each division takes constant time, but we do this in a binary divide manner, which occurs `log k` times because with each step, the number of lists we are merging reduces by half (much like a binary search).
 * <p>
 * 2. **Merging Lists (Conquer Step)**: Each merge operation of two individual lists has a linear runtime concerning the number of nodes in those two lists because we are comparing nodes one by one from each list until all nodes are in the merged list. So, if we are merging two lists with `m` and `n` nodes, the merge operation takes O(m + n) time.
 * <p>
 * 3. **Total Merge Operations**: The total number of merge operations will be done `k - 1` times (since we need to merge `k` lists into 1, each merge reduces the number of lists by one). However, because of the divide and conquer strategy, the height of the division is `log k`, and at each level, we perform O(N) operations because we merge all the nodes at each level of the merge step.
 * <p>
 * So, the total runtime for all merge operations across all levels of the division is:
 * <p>
 * - O(N) for each level (since we go through all the nodes at each level)
 * - Done for log k levels (since we are dividing the lists in half at each step)
 * <p>
 * Thus, the overall time complexity is O(N log k).
 * <p>
 * ### Space Complexity
 * The space complexity for this algorithm is O(log k) due to the recursion stack. In the worst case, there are log k recursive calls (at the height of the division tree), and each call needs space on the call stack. The nodes themselves are not duplicated; their pointers are just rearranged, so the space used for nodes does not contribute to the space complexity.
 */
public class MergeTwoLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right) return lists[left];
        if (left < right) {
            int mid = left + (right - left) / 2;
            ListNode l1 = merge(lists, left, mid);
            ListNode l2 = merge(lists, mid + 1, right);
            return mergeTwoLists(l1, l2);
        } else {
            return null;
        }
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}
