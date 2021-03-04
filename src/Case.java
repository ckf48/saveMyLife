import java.util.ArrayList;
import java.util.List;

public class Case {
    public static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }


    /*题目2工具*/
    public static void quicksort(int[] A, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivot = A[left];
        int i = left;
        int j = right;
        while (i < j) {
            while (i < j && A[j] >= pivot) {
                j--;
            }
            while (i < j && A[i] <= pivot) {
                i++;
            }
            swap(A, i, j);
        }
        A[left] = A[i];
        A[i] = pivot;

        quicksort(A, left, i - 1);
        quicksort(A, i + 1, right);

    }

    /*题目4工具*/

    public static void preOrder(Solution.TreeNode node, List<Integer> list) {
        if (node != null) {
            list.add(node.val);
            preOrder(node.left, list);
            preOrder(node.right, list);
        }
    }

    public static void inOrder(Solution.TreeNode node, List<Integer> list) {
        if (node != null) {
            inOrder(node.left, list);
            list.add(node.val);
            inOrder(node.right, list);
        }
    }

    public static void postOrder(Solution.TreeNode node, List<Integer> list) {
        if (node != null) {
            postOrder(node.left, list);
            postOrder(node.right, list);
            list.add(node.val);
        }
    }
}
