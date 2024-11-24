import java.awt.Color;

public class BST_Insert
{
    private final BSTVisualizer visualizer;

    public BST_Insert(BSTVisualizer visualizer)
    {
        this.visualizer = visualizer;
    }

    public BinaryNode insert(BinaryNode binaryNode, int value)
    {
        if(binaryNode == null)
        {
            binaryNode = new BinaryNode(value);
            if(visualizer.recentBinaryNode != null)
            {
                visualizer.recentBinaryNode.color = Color.GRAY;
            }
            binaryNode.color = Color.RED;
            visualizer.setRecentNode(binaryNode);
            visualizer.repaint();
            visualizer.sleep(1000);
            return binaryNode;
        }
        else
        {
            binaryNode.color = Color.green;
            visualizer.repaint();
            visualizer.sleep(1000);
        }

        if (value < binaryNode.value)
        {
            binaryNode.left = insert(binaryNode.left, value);
        }
        else if(value > binaryNode.value)
        {
            binaryNode.right = insert(binaryNode.right, value);
        }

        return binaryNode;
    }
}
