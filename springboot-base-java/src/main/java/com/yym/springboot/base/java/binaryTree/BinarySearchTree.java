package com.yym.springboot.base.java.binaryTree;

/**
 * 实现二叉搜索树,logn
 *
 * 左小右大
 */
public class BinarySearchTree {
    // 当前节点数据
    int data;
    // 当前节点的左节点
    BinarySearchTree leftTree;
    // 当前节点的右节点
    BinarySearchTree rightTree;

    public BinarySearchTree() { }

    public BinarySearchTree(int data) {
        this.data = data;
    }

    /**
     * 往当前节点插入data
     * @param tree  当前根节点
     * @param data  插入数据
     */
    public void insert(BinarySearchTree tree,int data){
        // 插入到由边
        if(tree.data > data){
            if(tree.leftTree == null){
                tree.leftTree = new BinarySearchTree(data);
            }else {
                insert(tree.leftTree,data);
            }
        }else {
            if(tree.rightTree == null){
                tree.rightTree = new BinarySearchTree(data);
            }else {
                insert(tree.rightTree,data);
            }
        }
    }

    /**
     * 中序遍历
     */
    public void  in(BinarySearchTree tree){
        if(tree != null){
            in(tree.leftTree);
            System.out.println("tree.data = " + tree.data);
            in(tree.rightTree);
        }
    }
    public static void main(String[] args) {
        // 创建根节点
        BinarySearchTree root = new BinarySearchTree(10);
        // 插入5
        root.insert(root,5);
        // 插入3
        root.insert(root,3);
        // 插入9
        root.insert(root,9);
        // 插入7
        root.insert(root,7);

        root.in(root);
    }
}
