public class Cons extends List {
    private List next;
    private int elem;
    private int size;

    public Cons(int elem,List first) {
        this.next = first;
        this.elem = elem;
    }

    public boolean empty() {
        return false;
    }
    public int getElem(){
        return this.elem;
    }
    public int size() {
        return 1 + next.size();
    }

    public boolean contains(int x) {
        return x == elem || next.contains(x);
    }

    public List insert(int x){
        //Se x piu' piccolo del primo elem aggiungo x davanti a tutti:
        if (x < elem)
        return new Cons(x, this);
        //Se x uguale al primo elem lascio this come si trova
        else if (x == elem)
        return this;
        //Se x maggiore del primo elemento aggiungo x nel resto della
        //lista
        else //in questo caso x > elem
        return new Cons(elem, next.insert(x));
        }

    public String toString()
    // trasformo il primo elemento poi gli altri
    {
        return elem + " " + next.toString();
    }

    public int sum(){
        return elem + this.next.sum();
    }
    public int get(int i){
        assert i<0:"Errore, indice negativo";
        if(i == 0){
            return this.elem;
        }else{
            return this.next.get(i-1);
        }
    }
    public int eval(int x){
        return this.elem + this.next.eval(x)*x;
    }
    public List succ(){
        List temp = next.succ();
        temp.insert(elem + 1);
        return temp;
    }
    public List filter_le(int x){
        List vi = this.next.filter_le(x);
        if(this.elem<=x){
            vi = vi.insert(this.elem);
        }
        return vi;
    }
    public List filter_gt(int x){
        List vi = this.next.filter_gt(x);
        if(this.elem>x){
            vi = vi.insert(this.elem);
        }
        return vi;
    }
    public List intersect(List list){
        List vi = this.next.intersect(list);
        if(list.contains(this.elem)==true){
            vi = vi.insert(this.elem);
        }
        return vi;
    }
    public static void main(String[] args) {
        Nil n = new Nil();
        List l = new Cons(0,n);
        l = l.insert(1);
        l = l.insert(2);
        List l1 = new Cons(0,n);
        l1 = l1.insert(1);
        l1 = l1.insert(2);
        System.out.println();
        l = l.intersect(l1);
        for (int i = 0; i < l.size(); i++) {
            System.out.println(l.get(i));
        }
    }

    @Override
    public List append(List l) {
        if (l.empty())
            return this;
        else {
            // DOWNCAST: per scrivere l.elem, l.next devo prima fare un downcast
            // ((Cons) l) per spostare l in Cons, dato che elem, next esistono solo in Cons
            // Il downcast e' possibile perche' se l non e' vuota, allora si assume
            // sia di tipo Cons
            int x = ((Cons) l).elem;  // primo elemento di l
            List m = ((Cons) l).next; // successori di l
            // prima aggiungo il primo elemento di l, poi gli altri
            return insert(x).append(m);
        }
        /** NOTA SULL'EFFICIENZA DI append(). 
        * Il nostro append() richiede un numero di usi di new uguali a:
        *      (1/2) * this.size() * l.size()        (circa)
        * Ci sono versioni di append() molto piu' veloci, che richiedono meno
        * usi di new, all'incirca
        *      (this.size() + l.size())
        * Pero' sono piu' difficili: per curiosita', qui sotto includiamo un algoritmo  
        * detto di MERGING: fonde due liste ordinate in una lista ordinata.
        if (l.empty()) 
            return this;
        else if (this.empty())
            return l;
        else {
            int x = ((Cons) l).elem; 
            List m = ((Cons) l).next;
            if (x< elem) 
                return new Cons(x,append(m));
            else if (x == elem) 
                return new Cons(x,next.append(m));
            else
                return new Cons(elem,next.append(l));
        }
        */
    }

    @Override
    public int[] toArray() {
        int[] vi = this.next.toArray();
        int[] nuovo = new int[vi.length + 1];
        copyArray(vi,nuovo);
        nuovo[nuovo.length-1] = this.elem;
        return nuovo;
    }
    /*
     * Copia b in a
     * b <-- a
     */
    private  void copyArray(int[] a, int[] b){
        for (int i = 0; i < b.length-1; i++) {
            b[i] = a[i];
        }
    }


    

    

}
