package Secondo;

    public class MyList {
        private Node first; // Riferimento al primo nodo della lista

        public MyList() {
            this.first = null;
        }

        public void insert(int elem) {
            first = new Node(elem, first);
        }

        public String toString() {
            String res = "";
            for (Node p = first; p != null; p = p.getNext()) {
                res += p.getElement();
                if (p.getNext() != null)
                    res += ", ";
            }
            return res;
        }
        public void modifica(){
            Node node = this.first;
            Node prev = null;
            while(node!=null){
                if(prev != null){
                    node.setElement(prev.getElement()+node.getElement());
                }
                prev = node;
                node = node.getNext();
            }
        }
        public void pushSomma(){
            pushSomma(first);
        }
        public int pushSomma(Node node){
            if(node == null){
                return 0;
            }else{
                int vi = pushSomma(node.getNext());
                if(node.getElement()>0){
                    vi = vi + node.getElement();
                }
                if(node==this.first){
                    this.insert(vi);
                }
                return vi;
            }
        }
        public static void main(String[] args) {
            MyList m = new MyList();
            m.insert(-4);
            m.insert(-6);
            m.insert(7);
            m.insert(4);
            m.insert(-2);
            m.insert(2);
            m.pushSomma();
            System.out.println(m.toString());
        }
    }

