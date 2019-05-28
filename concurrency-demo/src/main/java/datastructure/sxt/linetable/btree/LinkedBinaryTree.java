package datastructure.sxt.linetable.btree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019-05-27-下午 4:08
 */
public class LinkedBinaryTree implements BinaryTree {

    private Node root;//根节点

    public LinkedBinaryTree() {

    }

    public LinkedBinaryTree(Node root) {

        this.root = root;
    }

    @Override
    public boolean isEmpty() {

        return this.root == null;
    }

    @Override
    public int size() {

        System.out.println("二叉树结点的个数");
        return this.size(this.root);
    }

    private int size(Node root) {

        if (root == null) {
            return 0;
        } else {
            //获取左子树的size
            int nl = this.size(root.leftChild);
            //获取右子树的size
            int nr = this.size(root.rightChild);
            //返回左子树，右子树结点之和并加1
            return nl + nr + 1;
        }
    }

    @Override
    public int getHeight() {

        System.out.println("二叉树的高度");
        return this.getHeight(this.root);
    }

    private int getHeight(Node root) {

        if (root == null) {
            return 0;
        } else {
            //获取左子树的高度
            int nl = this.getHeight(root.leftChild);
            //获取右子树的高度
            int nr = this.getHeight(root.rightChild);
            //返回左子树，右子树较大高度 并加1
            return nl > nr ? nl + 1 : nr + 1;
        }

    }

    @Override
    public Node findKey(int value) {

        return this.findKey(value, this.root);
    }

    public Node findKey(Object value, Node root) {

        if (root == null) {//递归结束条件1.结点为空，可能是整个树的根节点，也可能是递归调用叶子结点左孩子和右孩子
            return null;
        } else if (root != null && root.value == value) {//递归结束条件2 找到了
            return root;
        } else {

            Node node1 = this.findKey(value, root.leftChild);
            Node node2 = this.findKey(value, root.rightChild);

            if (node1 != null && node1.value == value) {
                return node1;
            } else if (node2 != null && node2.value == value) {
                return node2;
            } else {
                return null;
            }
        }
    }

    @Override
    public void preOrderTraverse() {

        System.out.println("前序遍历");
        this.preOrderTraverse(this.root);
        System.out.println();
    }

    private void preOrderTraverse(Node root) {

        if (root != null) {
            //输出根的值
            System.out.print(root.value + "  ");
            // 遍历左子树
            this.preOrderTraverse(root.leftChild);
            //遍历右子树
            this.preOrderTraverse(root.rightChild);
        }
    }

    @Override
    public void inOrderTraverse() {

        System.out.println("中序遍历");
        this.inOrderTraverse(this.root);
        System.out.println();
    }

    private void inOrderTraverse(Node root) {

        if (root != null) {
            // 遍历左子树
            this.inOrderTraverse(root.leftChild);
            //输出根的值
            System.out.print(root.value + "  ");
            //遍历右子树
            this.inOrderTraverse(root.rightChild);
        }

    }

    @Override
    public void postOrderTraverse() {

        System.out.println("后序遍历");
        this.postOrderTraverse(this.root);
        System.out.println();
    }


    @Override
    public void postOrderTraverse(Node root) {

        if (root != null) {
            // 遍历左子树
            this.postOrderTraverse(root.leftChild);
            //遍历右子树
            this.postOrderTraverse(root.rightChild);
            //输出根的值
            System.out.print(root.value + "  ");

        }
    }

    /**
     * 中序遍历：4 5 1 3 2 6 7
     */
    @Override
    public void inOrderByStack() {
        // 栈 后进先出
        System.out.println("中序非递归遍历");
        Deque<Node> stack = new LinkedList<>();

//        Node current = this.root;
//        while (current != null || !stack.isEmpty()) {
//            while (current != null) {
//                stack.push(current);
//                current = current.leftChild;
//            }
//            if (!stack.isEmpty()) {
//                current = stack.pop();
//                System.out.print(current.value + " ");
//                current = current.rightChild;
//            }
//        }
        Node node = this.root;
        while (node != null || stack.size() > 0) {
            if (node != null) {
                stack.push(node);   //直接压栈
                node = node.leftChild;
            } else {
                node = stack.pop(); //出栈并访问
                System.out.print(node.value + "  ");
                node = node.rightChild;
            }
        }
        System.out.println();
    }

    /**
     * 先序/根遍历DLR:根 左子树 右子树 1 4 5 2 3 6 7
     */
    @Override
    public void preOrderByStack() {

        if (this.root == null) {
            return;
        }
        System.out.println("先序/根遍历DLR");
        Deque<Node> stack = new LinkedList<Node>();
        Node node = this.root;
        while (node != null || stack.size() > 0) {  //将所有左孩子压栈
            if (node != null) {   //压栈之前先访问
                System.out.print(node.value + "  ");
                stack.push(node);
                node = node.leftChild;
            } else {
                node = stack.pop();
                node = node.rightChild;
            }
        }
        System.out.println();
    }

    /**
     * 后序/根遍历LRD:左子树 右子树 根
     */
    @Override
    public void postOrderByStack() {

        Deque<Node> stack = new LinkedList<>();
        Deque<Node> output = new LinkedList<>();//构造一个中间栈来存储逆后序遍历的结果
        Node node = this.root;
        while (node != null || stack.size() > 0) {
            if (node != null) {
                output.push(node);
                stack.push(node);
                node = node.rightChild;
            } else {
                node = stack.pop();
                node = node.leftChild;
            }
        }
        System.out.println(output.size());
        while (output.size() > 0) {

            System.out.print(output.pop().value + "  ");
        }
    }

    @Override
    public void levelOrderByStack() {

        System.out.println("按照层次遍历二叉树");
        if (this.root == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(this.root);

        while (queue.size() != 0) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                Node temp = queue.poll();
                System.out.println(temp.value + " ");
                if (temp.leftChild != null)
                    queue.add(temp.leftChild);
                if (temp.rightChild != null) {
                    queue.add(temp.rightChild);
                }
            }

        }
        System.out.println();
    }
}
