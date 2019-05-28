package datastructure.sxt.linetable.btree;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019-05-27-下午 4:09
 */
public class Test {

    public static void main(String[] args) {
        //创建一个二叉树
        Node node5 = new Node(5, null, null);
        Node node4 = new Node(4, null, node5);

        Node node3 = new Node(3, null, null);
        Node node7 = new Node(7, null, null);
        Node node6 = new Node(6, null, node7);
        Node node2 = new Node(2, node3, node6);

        Node node1 = new Node(1, node4, node2);

        BinaryTree binaryTree = new LinkedBinaryTree(node1);
//        //判断二叉树是否为空
//        System.out.println("判断二叉树是否为空===>" + binaryTree.isEmpty());
//
//        //先序遍历递归      先序遍历：1 4 5 2 3 6 7
//        binaryTree.preOrderTraverse();
//        System.out.println();
//
//        //中序遍历递归      中序遍历：4 5 1 3 2 6 7
//        binaryTree.inOrderTraverse();
//        System.out.println();
//
//        //后序遍历递归       后序遍历：5 4 3 7 6 2 1
//        binaryTree.postOrderTraverse();
//        System.out.println();

        //中序遍历非递归(借助栈)
        binaryTree.inOrderByStack();
        // 先序遍历
        binaryTree.preOrderByStack();
        binaryTree.postOrderByStack();

//        //按照层次遍历（借助队列）
//        System.out.println("按照层次遍历");
//        binaryTree.levelOrderByStack();
//        // 在二叉树中查找某个值
//        System.out.println("在二叉树中查找某个值" + binaryTree.findKey(1));
//        //二叉树的高度
//        System.out.println("二叉树的高度" + binaryTree.getHeight());
//
//        // 二叉树的结点数量
//        System.out.println("二叉树的结点数量" + binaryTree.size());
    }

}
