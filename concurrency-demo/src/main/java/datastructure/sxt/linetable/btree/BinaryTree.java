package datastructure.sxt.linetable.btree;

/**
 * 二叉树的接口   可以有不同的实现类 每个类可以使用不同的存储结构
 * ex：顺序存储结构 链式存储结构
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019-05-27-下午 3:44
 */
public interface BinaryTree {

    /**
     * 是否空树
     *
     * @return
     */
    public boolean isEmpty();

    /**
     * 树结点的数量
     *
     * @return
     */
    public int size();

    /**
     * 获取二叉树的高度
     */
    public int getHeight();

    /**
     * 查询指定值的结点
     *
     * @param value
     */
    public Node findKey(int value);

    /**
     * 前序遍历递归操作       先序/根遍历DLR:根 左子树 右子树
     */
    public void preOrderTraverse();

    /**
     * 中序遍历递归操作     中序/根遍历LDR:左子树 根 右子树
     */
    public void inOrderTraverse();

    /**
     * 后序遍历递归操作 后序/根遍历LRD:左子树 右子树 根
     */
    public void postOrderTraverse();

    /**
     * 后序遍历递归操作
     *
     * @param node 树根结点
     */
    public void postOrderTraverse(Node node);

    /**
     * 中序遍历非递归操作
     * 1）对于任意结点current，若该结点不为空则将该结点压栈，并将左子树结点置为current，重复此操作，直到
     * 2）若左子树为空，栈顶结点出栈，访问结点后将该结点右子树置为current
     * 3）重复1,2操作，直到current为空且栈内结点为空
     */
    public void inOrderByStack();

    /**
     * 前序遍历非递归操作
     * 1）对于任意结点current，若该结点不为空则访问该结点后再将结点压栈，并将左子树结点置为current，重复此操作，直到
     * 2）若左子树为空，栈顶结点出栈，将该节点的右子树置为current
     * 3）重复1,2操作，直到current为空且栈内结点为空
     */
    public void preOrderByStack();

    /**
     * 后序遍历非递归操作
     * 1）对于任意结点current，若该结点不为空则访问该结点后再将结点压栈，并将左子树结点置为current，重复此操作，直到
     * 2）若左子树为空，取栈顶结点的右子树，如果右子树为空或者右子树刚访问过，则访问该结点，并将preNode置为current
     * 3）重复1,2操作，直到current为空且栈内结点为空
     */
    public void postOrderByStack();

    /**
     * 按照层次遍历二叉树
     */
    public void levelOrderByStack();
}
