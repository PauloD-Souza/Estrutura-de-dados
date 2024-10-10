import java.security.Key;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DequeSearch<Key, Item> implements Iterable<Item> {

    private int n;
    private No Sentinela;

    public DequeSearch() {

        n = 0;
        Sentinela = new No();
        Sentinela.prox = Sentinela;
        Sentinela.ant = Sentinela;

    }

    private class No{

        private Item dado;
        private Key chave;
        private No prox;
        private No ant;
    }

    public void push_front(Key key, Item item) {

        No tmp = new No();
        tmp.dado = item;
        tmp.chave = key;

        tmp.ant = Sentinela;
        tmp.prox = Sentinela.prox;

        Sentinela.prox = tmp;
        tmp.prox.ant = tmp;
        ++n;

    }

    public void push_back(Key key, Item item) {

        No tmp = new No();
        tmp.dado = item;
        tmp.chave = key;

        tmp.ant = Sentinela.ant;
        tmp.prox = Sentinela;

        Sentinela.ant = tmp;
        tmp.ant.prox = tmp;
        n++;

    }

    public boolean contains(Key key) {

        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
        
    }
    
    public Item get(Key key){

        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        for(No x = Sentinela.prox; x != Sentinela; x = x.prox) {
            if (key.equals(x.chave))
            return x.dado;
        }
        return null;
    }
    public void delete(Key key) {

        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        delete (Sentinela.prox, key);
    
    }
    private void remove(No tmp) {

        tmp.ant.prox = tmp.prox;
        tmp.prox.ant = tmp.ant;
        --n;
    
    }
    private void delete (No x, Key key) {

        if (x == Sentinela) return;
        if (key.equals(x.chave)) {
            remove(x);
            return;
    
        }
        delete(x.prox, key);
    }
    public void put(Key key, Item val) {
        if (key == null) throw new IllegalArgumentException("First argument to put() is null");
        if (val == null) {
            delete(key);
            return;
    
        }
        for (No x = Sentinela.prox; x != Sentinela; x = x.prox) {
            if (key.equals(x.chave)) {
                x.dado = val;
                return;
            }
        }
        push_front(key, val);
     
    }

    public Item pop_front() {

        No tmp = Sentinela.prox;
        Item meuDado = tmp.dado;
        tmp.ant.prox = tmp.prox;
        tmp.prox.ant = tmp.ant;
        --n;
        return meuDado;
    }

    public Item pop_back() {

        No tmp = Sentinela.ant;
        Item meuDado = tmp.dado;
        tmp.ant.prox = tmp.prox;
        tmp.prox.ant = tmp.ant;
        --n;
        return meuDado;
    }

    public No first() {

        if (Sentinela == Sentinela.prox) return null;
        return Sentinela.prox;


    }

    public boolean isEmpty() {return n ==0;} 
    public int size() {return n ;}

    public ListIterator<Item>  iterator() {

        return new DequeIterator();

    }

    public class DequeIterator implements ListIterator<Item> {

        private No atual = Sentinela.prox;
        private int indice = 0;
        private No acessadoultimo = null;

        public boolean  hasNext(){return indice < (n);}
        public boolean  hasPrevious(){return indice > 0;}
        public int previousIndex(){return indice - 1;}
        public int nextIndex(){return indice;}

        public Item next(){

            if (!hasNext()) return null;

            Item meuDado = atual.dado;
            acessadoultimo = atual;
            atual = atual.prox;
            indice++;
            return meuDado;

        }

        public Item previous(){

            if(!hasPrevious()) return null;
            atual = atual.ant;

            Item meuDado = atual.dado;
            acessadoultimo = atual;
            indice--;
            return meuDado;
        }   

        public Item get() {

        if(atual == null) throw new IllegalStateException();
        return atual.dado;

        }

        public void set(Item x) {

        if(acessadoultimo == null) throw new IllegalStateException();
        acessadoultimo.dado = x;
        }

        public void remove() {

        if(acessadoultimo == null) throw new IllegalStateException();
        acessadoultimo.ant.prox = acessadoultimo.prox;
        acessadoultimo.prox.ant = acessadoultimo.ant;
        --n;
        if (atual == acessadoultimo)
            atual = acessadoultimo.prox;
        else
            indice --;
        acessadoultimo = null;

        }

        public void add (Item x ) {

        No tmp = new No();
        tmp.dado = x;

        tmp.prox = atual.prox;
        tmp.ant = atual;

        tmp.prox.ant = tmp;
        atual.prox = tmp;
        n++;

        }
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this){
            s.append(item + " ");
        }
        return s.toString();
    }

    public Iterable<Key> keys() {
        Deque<Key> queue = new Deque<Key>();
        for (No x = Sentinela.prox; x != Sentinela; x = x.prox){
            queue.push_back(x.chave);
        }
        return queue;    
    }

    public static void main(String[] args) {
        DequeSearch<String, Integer> st = new DequeSearch<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            StdOut.println( key + " " + i);
            st.put(key, i);

        }
        StdOut.println(st.keys().toString());
        StdOut.println("------------");
        StdOut.println(st.toString());
    }
}    
  




 