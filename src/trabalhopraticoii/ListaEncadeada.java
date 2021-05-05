package trabalhopraticoii;

/**
 *
 * @author Arthur Passos
 */
import java.util.LinkedList;
import java.util.Queue;

public class ListaEncadeada<Key, Value> {
    private int numPares; // quantidade de valor-chave
    private Node primeiro; // raiz da lista

    private class Node{
        private Key key;
        private Value value;
        private Node prox;

        public Node(Key key, Value val, Node prox){
            this.key = key;
            this.value = val;
            this.prox = prox;
        }
    }

    public ListaEncadeada(){}

    public int getSize(){
        return this.numPares;
    }

    public Boolean isEmpty(){
        return getSize() == 0;
    }

    public boolean contains(Key key){
        return buscaValor(key) != null;
    }

    public Value buscaValor(Key key){
        for(Node i = primeiro; i != null; i = i.prox){
            if(key.equals(i.key))
                return i.value;
        }
        return null;
    }

    public void inserir(Key key, Value val){
        if (val == null){
            delete(key);
            return;
        }

        for(Node i= primeiro; i != null; i = i.prox){
            if(key.equals(i.key)){
                i.value = val;
                return;
            }
        }
        primeiro = new Node(key, val, primeiro);
        this.numPares++;
    }
    
    public void delete(Key key){
        primeiro = delete(primeiro, key);
    }

    private Node delete(Node x, Key key){
        if(x == null){
            return null;
        }
        if (key.equals(x.key)){
            this.numPares--;
            return x.prox;
        }
        x.prox = delete(x.prox, key);
        return x;
    }

    public Iterable<Key> keys(){
        Queue<Key> queue = new LinkedList<Key>();
        for(Node x = primeiro; x != null; x = x.prox){
            ((LinkedList<Key>) queue).add(x.key);
        }
        return queue;
    }
    
    public int quantidadeKeys(){
        Queue<Key> queue = new LinkedList<Key>();
        for(Node x = primeiro; x != null; x = x.prox){
            ((LinkedList<Key>) queue).add(x.key);
        }
        return queue.size();
    }
}
