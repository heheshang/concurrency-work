package datastructure.sxt.linetable.btree;

/**
 * 二叉树的结点
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019-05-27-下午 3:37
 */
public class Node {

    /**
     * 结点的值
     */
    Object value;

    /**
     * 左子树的引用
     */
    Node leftChild;

    /**
     * 右子树的引用
     */
    Node rightChild;

    public Node(Object value) {

        super();
        this.value = value;
    }

    public Node(Object value, Node leftChild, Node rightChild) {

        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder("Node{");
        sb.append("value=").append(this.value);
        sb.append(", leftChild=").append(this.leftChild);
        sb.append(", rightChild=").append(this.rightChild);
        sb.append('}');
        return sb.toString();
    }
}
