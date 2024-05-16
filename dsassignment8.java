// Name: Chidera Anamege


import java.util.Arrays;

// Node class for the binary tree
class TreeNode<T> {
    T data;
    TreeNode<T> left, right;

    // Constructor for the node class
    public TreeNode(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

// dsassignment8 class
class dsassignment8<T> {
    private TreeNode<T> root;

    // Constructor for the dsassignment8 class
    public dsassignment8() {
        this.root = null;
    }

    // Method to build the tree from pre-order and in-order traversals
    public void buildFromNodeLists(T[] preOrder, T[] inOrder, int size) {
        this.root = buildFromNodeListsHelper(preOrder, inOrder, 0, size - 1, 0, size - 1);
    }

    // Helper method to recursively build the tree
    private TreeNode<T> buildFromNodeListsHelper(T[] preOrder, T[] inOrder, int preStart, int preEnd, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd)
            return null;

        TreeNode<T> root = new TreeNode<>(preOrder[preStart]);

        int rootIndexInOrder = findIndex(inOrder, preOrder[preStart]);

        int leftTreeSize = rootIndexInOrder - inStart;

        root.left = buildFromNodeListsHelper(preOrder, inOrder, preStart + 1, preStart + leftTreeSize, inStart, rootIndexInOrder - 1);
        root.right = buildFromNodeListsHelper(preOrder, inOrder, preStart + leftTreeSize + 1, preEnd, rootIndexInOrder + 1, inEnd);

        return root;
    }

    // Method to find the index of a value in an array
    private int findIndex(T[] arr, T value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    // Method to check if the tree is a binary search tree
    public boolean isBinarySearchTree() {
        return isBinarySearchTreeUtil(this.root, null, null);
    }

    // Helper method to recursively check if the tree is a binary search tree
    private boolean isBinarySearchTreeUtil(TreeNode<T> node, T min, T max) {
        if (node == null)
            return true;

        if ((min != null && ((Comparable<T>) node.data).compareTo(min) <= 0) || (max != null && ((Comparable<T>) node.data).compareTo(max) >= 0))
            return false;

        return isBinarySearchTreeUtil(node.left, min, node.data) && isBinarySearchTreeUtil(node.right, node.data, max);
    }

    // Method to find the depth of the tree
    public int depth() {
        return depthUtil(this.root);
    }

    // Helper method to recursively find the depth of the tree
    private int depthUtil(TreeNode<T> node) {
        if (node == null)
            return -1;
        else {
            int leftDepth = depthUtil(node.left);
            int rightDepth = depthUtil(node.right);

            return Math.max(leftDepth, rightDepth) + 1;
        }
    }

    // Method to find the maximum value in the tree
    public T max() {
        if (root == null)
            throw new IllegalStateException("Tree is empty.");

        return maxUtil(this.root);
    }

    //  method to recursively find the maximum value in the tree
    private T maxUtil(TreeNode<T> node) {
        T max = node.data;
        T leftMax = node.left != null ? maxUtil(node.left) : null;
        T rightMax = node.right != null ? maxUtil(node.right) : null;

        if (leftMax != null && ((Comparable<T>) leftMax).compareTo(max) > 0)
            max = leftMax;
        if (rightMax != null && ((Comparable<T>) rightMax).compareTo(max) > 0)
            max = rightMax;

        return max;
    }

    // Method to find the sum of all elements in the tree
    public double treeSum() {
        return treeSumUtil(this.root);
    }

    // Helper method to recursively find the sum of all elements in the tree
    private double treeSumUtil(TreeNode<T> node) {
        if (node == null)
            return 0;

        double sum = ((Number) node.data).doubleValue();
        sum += treeSumUtil(node.left);
        sum += treeSumUtil(node.right);

        return sum;
    }

    // Method to find the average value of all elements in the tree
    public double treeAverage() {
        return treeSum() / size();
    }

    // Method to check if the tree is balanced
    public boolean treeIsBalanced() {
        return treeIsBalancedUtil(this.root) != -1;
    }

    // Helper method to recursively check if the tree is balanced
    private int treeIsBalancedUtil(TreeNode<T> node) {
        if (node == null)
            return 0;

        int leftHeight = treeIsBalancedUtil(node.left);
        if (leftHeight == -1)
            return -1;

        int rightHeight = treeIsBalancedUtil(node.right);
        if (rightHeight == -1)
            return -1;

        if (Math.abs(leftHeight - rightHeight) > 1)
            return -1;

        return Math.max(leftHeight, rightHeight) + 1;
    }

    // Method to find the size of the tree
    public int size() {
        return sizeUtil(this.root);
    }

    // Helper method to recursively find the size of the tree
    private int sizeUtil(TreeNode<T> node) {
        if (node == null)
            return 0;

        return 1 + sizeUtil(node.left) + sizeUtil(node.right);
    }
}

//
class Main {
    public static void main(String[] args) {
        // Pre-order traversals
        Integer[][] preOrders = {
                { 1 },
                { 1, 2 },
                { 1, 2, 3 },
                { 1, 2, 3, 4 },
                { 1, 2, 3, 4, 5 },
                { 1, 2, 3, 4, 5, 6 }
        };

        // In-order traversals
        Integer[][] inOrders = {
                { 1 },
                { 2, 1 },
                { 2, 1, 3 },
                { 4, 2, 1, 3 },
                { 4, 2, 5, 1, 3 },
                { 4, 2, 5, 1, 6, 3 }
        };

        // Loop through the trees
        for (int i = 0; i < Math.max(preOrders.length, inOrders.length); i++) {
            dsassignment8<Integer> binaryTree = new dsassignment8<>();
            binaryTree.buildFromNodeLists(preOrders[i], inOrders[i], preOrders[i].length);
            System.out.println("Tree_Name: " + (i + 1));
            System.out.println("Binary search tree ? Yes or no  " + binaryTree.isBinarySearchTree());
            System.out.println("Depth: " + binaryTree.depth());
            System.out.println("Max: " + binaryTree.max());
            System.out.println("Sum: " + binaryTree.treeSum());
            System.out.println("Average: " + binaryTree.treeAverage());
            System.out.println("Is balanced? " + binaryTree.treeIsBalanced());
            System.out.println("Size of the tree: " + binaryTree.size());
            System.out.println();
        }
    }
}
