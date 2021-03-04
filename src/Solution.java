import java.util.*;


public class Solution {
    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
    }

    /*1.
     * 输入一个链表，反转链表后，输出新链表的表头。
     * */

    public ListNode ReverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = null;
        ListNode p = dummy;
        ListNode q = head;
        while (q != null) {
            ListNode temp = q.next;
            q.next = p;
            p = q;
            q = temp;
        }

        return p;
    }

    /*2.
     * 给定一个数组，请你编写一个函数，返回该数组排序后的形式。
     * */

    public int[] MySort(int[] arr) {
        int len = arr.length;
        Case.quicksort(arr, 0, len - 1);
        return arr;
    }

    /*3.
     * 判定链表中是否有环
     * */

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }

        return false;
    }

    /*4.
     *分别按照二叉树先序，中序和后序打印所有的节点。
     * */

    public int[][] threeOrders(TreeNode root) {
        List<Integer> tempList = new ArrayList<>();

        Case.preOrder(root, tempList);
        int size = tempList.size();
        int[][] result = new int[3][size];
        for (int i = 0; i < size; i++) {
            result[0][i] = tempList.get(i);
        }
        tempList.clear();

        Case.inOrder(root, tempList);
        for (int i = 0; i < size; i++) {
            result[1][i] = tempList.get(i);
        }
        tempList.clear();

        Case.postOrder(root, tempList);
        for (int i = 0; i < size; i++) {
            result[0][i] = tempList.get(i);
        }
        tempList.clear();

        return result;

        // write code here
    }

    /*5.
     * 请实现有重复数字的升序数组的二分查找,
     * 给定一个 元素有序的（升序）整型数组 nums 和一个目标值 target  ，
     * 写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1
     * */

    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) {
                while (mid != 0 && nums[mid] == nums[mid - 1]) {
                    mid--;
                }
                return mid;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    /*6.
     *给定一个数组，找出其中最小的K个数。例如数组元素是4,5,1,6,2,7,3,8这8个数字，
     * 则最小的4个数字是1,2,3,4。如果K>数组的长度，那么返回一个空的数组
     *
     * */

    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        if (input == null || k > input.length) {
            return new ArrayList<Integer>();
        }
        Case.quicksort(input, 0, input.length - 1);
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            list.add(input[i]);
        }

        return list;
    }

    /*7.
     * 给定一个二叉树，返回该二叉树层序遍历的结果，（从左到右，一层一层地遍历）
     * */

    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            ArrayList<Integer> tempList = new ArrayList<>();
            int size = queue.size();
            while (size-- > 0) {
                TreeNode tempNode = queue.poll();
                tempList.add(tempNode.val);
                if (tempNode.left != null) {
                    queue.add(tempNode.left);
                }
                if (tempNode.right != null) {
                    queue.add(tempNode.right);
                }
            }
            result.add(tempList);
        }

        return result;
    }

    /*8.
     * 有一个整数数组，请你根据快速排序的思路，找出数组中第K大的数。
     * 给定一个整数数组a,同时给定它的大小n和要找的K(K在1到n之间)，请返回第K大的数，保证答案存在。
     * */

    public int findKth(int[] a, int n, int K) {
        Case.quicksort(a, 0, n - 1);
        return a[K - 1];
    }

    /*9.
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。
     * 求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果)
     * */

    public int jumpFloor(int target) {
        int[] dp = new int[target + 1];
        if (target == 1 || target == 2) {
            return target;
        }
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < target + 1; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }
        return dp[target];
    }

    /*10.
     * 给出一个整数数组，请在数组中找出两个加起来等于目标值的数，
     * 你给出的函数twoSum 需要返回这两个数字的下标（index1，index2），
     * 需要满足 index1 小于index2.
     * 。注意：下标是从1开始的
     * 假设给出的数组中只存在唯一解
     * */

    public int[] twoSum(int[] numbers, int target) {
        // write code here
        int[] result = new int[2];
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target) {
                    result[0] = i + 1;
                    result[1] = j + 1;
                    return result;
                }
            }
        }
        return result;
    }

    /*11.
     * 给定一个数组arr，返回子数组的最大累加和例如，
     * arr = [1, -2, 3, 5, -2, 6, -1]，所有子数组中，[3, 5, -2, 6]可以累加出最大的和12，所以返回12.
     * 题目保证没有全为负数的数据
     * */

    public int maxsumofSubarray(int[] arr) {
        // write code here
        int len = arr.length;
        int[] dp = new int[len];
        dp[0] = arr[0];
        int max = dp[0];
        for (int i = 1; i < len; i++) {
            dp[i] = Integer.max(dp[i - 1] + arr[i], arr[i]);
            max = Integer.max(max, dp[i]);
        }

        return max;
    }

    /*12.
     * 将两个有序的链表合并为一个新链表，要求新的链表是通过拼接两个链表的节点来生成的，且合并后新链表依然有序
     * */

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//        if (l1 == null) {
//            return l2;
//        }
//        if (l2 == null) {
//            return l1;
//        }
//        if (l1.val < l2.val) {
//            l1.next = mergeTwoLists(l1.next, l2);
//            return l1;
//        } else {
//            l2.next = mergeTwoLists(l1, l2.next);
//            return l2;
//        }

        ListNode head = new ListNode(-1);
        ListNode dummy = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                dummy.next = l1;
                l1 = l1.next;
            } else {
                dummy.next = l2;
                l2 = l2.next;
            }
            dummy = dummy.next;
        }
        if (l1 != null) {
            dummy.next = l1;
        }
        if (l2 != null) {
            dummy.next = l2;
        }

        return head.next;
    }

    /*13 用两个栈实现一个队列*/

    /*14.
     * 给定一个数组arr，返回arr的最长无的重复子串的长度(无重复指的是所有数字都不相同)。
     * */

    public int maxLength(int[] arr) {
        // write code here
        int start = 0;
        int end = 0;
        int max = 0;
        Map<Integer, Integer> map = new HashMap<>();
        while (end < arr.length) {
            if (map.containsKey(arr[end])) {
                start = Math.max(start, map.get(arr[end]));
            }
            max = Math.max(max, end - start + 1);
            map.put(arr[end], end + 1);
            end++;
        }

        return max;
    }

    /*15.
    给出两个有序的整数数组 和 ，请将数组 合并到数组 中，变成一个有序的数组
    * */

    public void merge(int A[], int m, int B[], int n) {

    }
}
