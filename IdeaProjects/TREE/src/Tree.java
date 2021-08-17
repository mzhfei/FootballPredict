import java.util.LinkedList;

public class Tree{
    public LinkedList<Integer> List;
    public LinkedList<leaves> L;
    public leaves root;

    public Tree(LinkedList<Integer> List){
        this.L = new LinkedList<>();
        for ( int i : List){
            leaves leaf = new leaves(i);
            L.add(leaf);
        }
        this.root = L.getFirst();
        L.removeFirst();
    }


    public int at_end(leaves l) {
        //左右都没有
        if (l.getleft() == null && l.getright() == null) {
            return 1;
        }
        //左有右无
        else if (l.getleft() != null && l.getright() == null) {
            return 2;
        }
        //左无右有
        else if (l.getleft() == null && l.getright() != null) {
            return 3;
        }
        //都有
        else{
            return 4;
        }
    }

    public boolean compare(leaves at, leaves go){
        // go to left
        if (at.getValue() > go.getValue()){
            return true;
        }
        //go right
        else{
            return false;
        }
    }

    public void add(leaves go, leaves root){
        int count = 0 ;
        leaves at = root;

        while (count == 0){
            if (compare(at, go)){
                if (at_end(at) == 1){
                    at.setleft(go);
                    count += 1;
                }
                else if (at_end(at) == 2){
                    at = at.getleft();
                }
                else if (at_end(at) == 3){
                    at.setleft(go);
                    count += 1;
                }
                else if (at_end(at) == 4) {
                    at = at.getleft();
                }}

            else if ( ! compare(at, go)){
                if (at_end(at) == 1){
                    at.setright(go);
                    count += 1;
                }
                else if (at_end(at) == 2){
                    at.setright(go);
                    count += 1;
                }
                else if (at_end(at) == 3){
                    at = at.getright();
                }
                else if (at_end(at) == 4){
                    at = at.getright();
                }}
        }
    }

    public void loop( leaves root,  LinkedList<leaves> L) {
        while (L.size() != 0 ){
            add(L.getFirst(), root);
            L.removeFirst();
        }

    }

    public String Val(leaves l){
        if (at_end(l) == 1){
            return Integer.toString(l.getValue());
        }

        else if (at_end(l) == 2){
            return Val(l.getleft()) + " "+ Integer.toString(l.getValue());
        }
        else if (at_end(l) == 3){
            return Integer.toString(l.getValue()) + " " + Val(l.getright());
        }
        else if (at_end(l) == 4){
            return Val(l.getleft()) +  " " + Integer.toString(l.getValue())+ " " + Val(l.getright());
        }
        else{
            return "";
        }
    }

    public String toString(){
        return Val(this.root);
    }

    public static void main(String[] args) {
        LinkedList<Integer> List = new LinkedList<>();
        List.add(8);
        List.add(5);
        List.add(14);
        List.add(17);
        List.add(21);
        List.add(99);
        List.add(4);
        List.add(111);
        List.add(65);
        List.add(34);

        System.out.println(List);

        Tree t = new Tree(List);
        for ( leaves l : t.L){
            System.out.println(l.toString());
        }
        t.loop(t.root,t.L);
        System.out.println(t.toString());


    }

}
