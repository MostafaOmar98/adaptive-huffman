public class AdaptiveHuffmanEncoder {

    private AdaptiveHuffmanTree tree;
    private int BITS;
    private int ALPHA_SIZE;
    public AdaptiveHuffmanEncoder(int B){
        BITS = B;
        ALPHA_SIZE = (1<<BITS);
        tree = new AdaptiveHuffmanTree(ALPHA_SIZE);
    }

    public void encode(String s){
        for (int i = 0; i < s.length(); ++i){
            if (!tree.isTransmitted(s.charAt(i))){
                sendCode(getCode(ALPHA_SIZE));
                sendCode(getShortCode(s.charAt(i)));
            }
            else{
                sendCode(getCode(s.charAt(i)));
            }
            tree.updateTree(s.charAt(i));

//            System.out.println("At step: " + i);
//            tree.printTree();
//            System.out.println("Finished step: " + i + '\n');
        }
    }

    String getCode(Node p)
    {
        if (p.getParent() == null)
            return "";
        Node parent = p.getParent();
        if (parent.getLeftChild() == p)
            return getCode(parent) + "0";
        assert(parent.getRightChild() == p);
        return getCode(parent) + "1";
    }

    String getCode(int c){
        return getCode(tree.find(c));
    }

    private void sendCode(String s){
        System.out.print(s + " ");
    }

    private String getShortCode(int x){
        String ret = new String();
        for (int i = 0; i < BITS; ++i){
            ret = (((x&1) == 1) ? "1" : "0") + ret;
            x /= 2;
        }
        return ret;
    }

}
