/*
Notes:
NYT Node symbol ID is ALPHA_SIZE
 */

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class AdaptiveHuffmanTree {

    private Node root;
    private int ALPHA_SIZE; // number of symbols
    private boolean[] transmitted;
    private Map<Integer, TreeSet<Node> > block; // NodeCounter -> Sorted Tree of Nodes belonging to the same block (Same weight)


    AdaptiveHuffmanTree(int ALPHA_SIZE){
        this.ALPHA_SIZE = ALPHA_SIZE; // number of symbols = 2^BITS
        root = new Node(ALPHA_SIZE, 0, 2 * ALPHA_SIZE - 1, null, null, null); // make sure 2 * ALPHA_SIZE - 1 is enough
        transmitted = new boolean[ALPHA_SIZE + 1]; // +1 to hold up for NYT node
        block = new HashMap<>();
        addNode(root);
    }


    /*
    Bruteforces on all nodes to find node with symbol c
     */
    private Node find(int c, Node p)
    {
        if (p == null || p.getSymbol() == c)
            return p;
        Node ret = find(c, p.getLeftChild());
        if (ret == null)
            ret = find(c, p.getRightChild());
        return ret;
    }

    public Node find(int c){
        return find(c, root);
    }

    public void updateTree(int c){
        Node p;
        if (!transmitted[c])
        {
            p = find(ALPHA_SIZE);
            assert(p != null);
            Node newNYT = new Node(ALPHA_SIZE, 0, p.getNumber() - 2, null, null, p);
            Node symbolNode = new Node(c, 1, p.getNumber() - 1, null, null, p);
            removeNode(p);
            p.setLeftChild(newNYT);
            p.setRightChild(symbolNode);
            p.setCounter(1);
            p.setSymbol(0);
            addNode(symbolNode);
            addNode(p);
            addNode(newNYT);
        }
        else{
            p = find(c);
            assert(p != null);
        }
        updateTree(p);
        transmitted[c] = true;
    }

    private void updateTree(Node p){
        if (p == root)
            return;
        p = p.getParent();
        checkSwap(p);
        incrementCounter(p);
        updateTree(p);
    }

    private void removeNode(Node p){
        if (block.containsKey(p.getCounter()))
            block.get(p.getCounter()).remove(p);
    }

    private void addNode(Node p){
        if (!block.containsKey(p.getCounter()))
            block.put(p.getCounter(), new TreeSet<>());
        block.get(p.getCounter()).add(p);
    }

    private void incrementCounter(Node p){
        removeNode(p);
        p.setCounter(p.getCounter() + 1);
        addNode(p);
    }

    private boolean checkSwap(Node p){
        Node swapNode = null;
        TreeSet<Node> currentBlock = block.get(p.getCounter());

        /*
        OPTIMIZE this is a treeSet no need to check for all....
         */
        for (Node v : currentBlock){
            if (v.getNumber() > p.getNumber() && v.getLeftChild() != p && v.getRightChild() != p)
            {
                if (swapNode == null || v.getNumber() > swapNode.getNumber())
                    swapNode = v;
            }
        }

        if (swapNode != null){
            p.swapParent(swapNode);
            p.swapNumber(swapNode);
            return true;
        }
        return false;
    }

    public boolean isTransmitted(int c){
        return transmitted[c];
    }
}
