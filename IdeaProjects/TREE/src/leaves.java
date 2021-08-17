public class leaves {
    leaves left;
    leaves right;
    int i;

    public leaves(int i){
        left = null;
        right = null;
        this.i = i;
    }

    public leaves getleft(){
        return this.left;
    }
    public leaves getright(){
        return this.right;
    }

    public void setleft(leaves l){
        this.left = l;
    }
    public void setright (leaves r){
        this.right = r;
    }

    public int getValue(){
        return this.i;
    }

    @Override
    public String toString() {
        return "leaves " + (this.i) + "\n";
}

    public static void main(String[] args) {


    }
}
