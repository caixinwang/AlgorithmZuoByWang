package class04_Tree;

import java.util.LinkedList;

public class Code04_IsBST {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 二插搜索树的中序遍历打印的次序一定是非递减的，我们可以把打印行为替代为入队，然后按照从左到右一次检查
     * @param head
     * @return
     */
    public static boolean isBST1(Node head) {
        if (head==null)
            return true;
        LinkedList<Node>list=new LinkedList<>();
        process1(head,list);
        int pre=Integer.MIN_VALUE;
        for (Node p:list){
            if (pre>=p.value)
                return false;
            pre=p.value;
        }
        return true;

    }

    /**
     * 服务isBST，作用是将以head为头的二叉搜索树以中序遍历的顺序入队
     * @param head
     * @param list
     */
    public static void process1(Node head, LinkedList<Node> list) {
        if(head==null){
            return;
        }
        process1(head.left,list);
        list.add(head);
        process1(head.right,list);

    }

    public static boolean isBST2(Node head){
        if (head==null)
            return true;
        return process2(head).isBST;
    }

    /**
     * 一颗树要提供的信息有：它是不是二叉搜索树、它的最大值是多少、它的最小值是多少
     * 它是不是二插搜索树取决于它的左右子树是否都是二叉搜索树。并且根节点的值要大于左子树的最大值，小于右子树的
     * 最小值。这是一个可以递归的获取信息的过程。递归到null是一个基本情况，一个空结点我们认为它是一个二叉搜索树
     * 但是这里最大和最小值我们发现设置成什么都不合适。所以就让空节点返回一个空的Info
     */
    public static class Info{
        public boolean isBST;
        public int max;
        public int min;

        public Info(boolean isBST,int max,int min){
            this.isBST=isBST;
            this.max=max;
            this.min=min;
        }
    }

    public static Info process2(Node head){
        if (head==null)
            return null;
        //获取信息
        Info left=process2(head.left);
        Info right=process2(head.right);
        //head的信息，要根据上面得到的左右子树的信息加工得到
        int min=head.value;//设置为自己，因为左右子树可能为空
        int max=head.value;//设置为自己，因为左右子树可能为空
        boolean isBST=true;
        //最小值，只可能出现在左子树。
        if (left!=null){
            min= left.min;
        }
        //最大值只可能出现在右子树上
        if (right!=null){
            max= right.max;
        }
        //如果出现根节点比左子树的最大值小的话说明不是二叉搜索树
        if (left!=null&&left.max>=head.value){
            isBST=false;
        }
        //如果出现根节点比右子树的最大值大的话说明不是二叉搜索树
        if (right!=null&&right.min<=head.value){
            isBST=false;
        }
        //如果左右子树中有其中一个不是二叉搜索树则整棵树都不是二叉搜索树
        if ((right!=null&& right.isBST==false)||(left!=null&& left.isBST==false)){
            isBST= false;
        }
        return new Info(isBST,max,min);
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isBST1(head) != isBST2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }


}
