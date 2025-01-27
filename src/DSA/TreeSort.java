package DSA;
import java.util.ArrayList;
import java.util.List;

public class TreeSort {
    public int timeTreeSort = 0;

    class Node {
        int key;
        Node left, right;

        public Node(int item) {
            key = item;
            left = right = null;
        }
    }

    Node root;

    TreeSort() {
        root = null;
        timeTreeSort++;
    }

    void insert(int key) {
        root = insertRec(root, key);
        timeTreeSort++; 
    }

    Node insertRec(Node root, int key) {
        timeTreeSort++; 
        if (root == null) {
            root = new Node(key);
            timeTreeSort++; 
            return root;
        }

        // Modified condition to allow duplicates to be inserted into the right subtree
        if (key < root.key) {
            root.left = insertRec(root.left, key);
        } else {  // key >= root.key (including duplicates)
            root.right = insertRec(root.right, key);
        }

        return root;
    }

    void inorderRec(Node root, List<Integer> result) {
        timeTreeSort++; 
        if (root != null) {
            inorderRec(root.left, result);
            result.add(root.key);
            timeTreeSort++; 
            inorderRec(root.right, result);
        }
    }

    public void treeSort(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            insert(list.get(i));
            timeTreeSort++; 
        }

        List<Integer> result = new ArrayList<>();
        inorderRec(root, result);

        list.clear();
        list.addAll(result);
    }
}
