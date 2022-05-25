package class04_Tree;

public class Code09_SuccessorNode {

    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 函数功能为找到任意一个结点x的后继结点。一种很显而易见的方法就是利用parent一直向上找，一直找到头节点，然后中序遍历找到x
     * 进而找到x的后继。但是这个一种递归序，复杂度一定是N。有一种想法就是我们能不能直接利用parent直接找到它的后继。
     * 注意到中序遍历的打印顺序是左中右。1. 如果一个结点有右孩子，那么打印完了这个结点一定是去打印右树的最左的结点。
     * 2.如果一个结点没有右孩子，那么打印完它之后就得往上找它的后继结点了，此时它是某一个结点的左子树的最后一个结点。也就是说
     * 我们要一直向上找，如果一个结点是父亲结点的右孩子就一直向上找，知道一个结点是父亲结点的左孩子就停，这个时候这个父亲节点
     * 就是之前结点的后继结点。因为这个结点是这个父亲结点的左树的最右的位置，也就是最后一个打印的位置。
     * @param node:返回node结点的后继节点
     * @return
     */
    public static Node getSuccessorNode(Node node) {
        if (node==null)
            return null;
        if (node.right!=null){
            return getLeftMost(node.right);
        }else{
            while(node.parent!=null&&node.parent.right==node){//除了循环说明当前的node是它父亲的左孩子
                node=node.parent;
            }
            return node.parent;
        }
    }

    public static Node getLeftMost(Node node) {
        if (node==null)
            return null;
        while(node.left!=null){
            node= node.left;
        }
        return node;
    }

    public static void main(String[] args) {
        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.left.parent = head;
        head.left.left = new Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new Node(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new Node(9);
        head.right.parent = head;
        head.right.left = new Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new Node(10);
        head.right.right.parent = head.right;

        Node test = head.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.right; // 10's next is null
        System.out.println(test.value + " next: " + getSuccessorNode(test));
    }
}
