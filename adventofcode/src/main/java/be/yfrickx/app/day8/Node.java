package be.yfrickx.app.day8;

public class Node {

    private String leftNode;
    private String rightNode;

    public Node(String leftNode, String rightNode) {
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public String getLeftNode() {
        return leftNode;
    }

    public String getRightNode() {
        return rightNode;
    }
}
