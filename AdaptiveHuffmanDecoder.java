public class AdaptiveHuffmanDecoder {

    private AdaptiveHuffmanTree tree;
    private int BITS;
    private int ALPHA_SIZE;
    public AdaptiveHuffmanDecoder(int B){
        BITS = B;
        ALPHA_SIZE = (1<<BITS);
        tree = new AdaptiveHuffmanTree(ALPHA_SIZE);
    }

    public void decode(String s){
        String temp = "";
        for (int i = 0; i < s.length(); ++i){
            temp += s.charAt(i);
            Node p = tree.find(temp);
            if (i == 0 || p.getSymbol() != 0){
                int c;
                if (i == 0 || p.getSymbol() == ALPHA_SIZE){
                    int add = (i == 0) ? 0 : 1;
                    String character = s.substring(i + add, i + add + 8);
                    i += 7 + add;
                    c = getCharacter(character);
                    System.out.print((char)c);
                }
                else{
                    c = p.getSymbol();
                    System.out.print((char)c);
                }
                temp = "";
                tree.updateTree(c);
            }
        }
    }


    private int getCharacter(String s){
        int c = 0;
        for (int i = s.length() - 1, p = 1; i >= 0; --i, p *= 2){
            if (s.charAt(i) == '1')
                c += p;
        }
        return c;
    }
}
