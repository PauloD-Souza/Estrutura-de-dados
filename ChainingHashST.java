import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Deque;
import java.util.NoSuchElementException;
public class ChainingHashST <Key, Value> {
    private static final int INIT_CAPACITY = 4;

    private int n;
    private int m;
    private DequeSearch<Key,Value>[] st;

    public ChainingHashST(){
        this(INIT_CAPACITY);
    }

    public ChainingHashST(int m){
        this.m = m;
        st = (DequeSearch<Key, Value> []) new DequeSearch[m];
        for (int i = 0; i < m; i++){
            st[i] = new DequeSearch<Key, Value>();
        }
    }

    

}