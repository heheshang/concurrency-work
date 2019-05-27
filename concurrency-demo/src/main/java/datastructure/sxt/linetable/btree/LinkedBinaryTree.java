package datastructure.sxt.linetable.btree;

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

        return null;
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

    @Override
    public void inOrderByStack() {

    }

    @Override
    public void preOrderByStack() {

    }

    @Override
    public void postOrderByStack() {

    }

    @Override
    public void levelOrderByStack() {

    }
}
