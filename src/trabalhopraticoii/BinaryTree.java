package trabalhopraticoii;

/**
 *
 * @author Arthur Passos
 */
//Árvore Binária
public class BinaryTree<Key extends Comparable<Key>, Value> {

    public class Node{
        Value valor;
        Key chave;
        Node noEsquerda;
        Node noDireita;
    
        Node(Key chave, Value valor) {
            this.chave = chave;
            this.valor = valor;
        }
    }
    
    private Node raiz;

    public int altura() {
        return altura(raiz);
    }

    private int altura(Node no) {
        if (no != null) {
            int he, hd;
            he=altura(no.noEsquerda);
            hd=altura(no.noDireita);
            
            if(he > hd) return he + 1;
            else return hd + 1;
        }

        return 0;
    }

    public boolean isEmpty() {
        return altura(raiz) == 0;
    }    
    
    public void inserir(Key key, Value val) {
        if(isEmpty()){
            raiz = new Node(key, val);
        }else
            inserir(raiz, key, val);
    }

    public void inserir(Node node, Key key, Value val) {
        //verifica se a árvore já foi criada
        if (node != null) {
            //Verifica se o valor a ser inserido é menor que o nodo corrente da árvore, se sim vai para subárvore esquerda
            int cmp = key.compareTo(node.chave);
            if (cmp < 0) {
                //Se tiver elemento no nodo esquerdo continua a busca
                if (node.noEsquerda != null) {
                    inserir(node.noEsquerda, key, val);
                } else {
                    //Se nodo esquerdo vazio insere o novo nodo aqui
                    node.noEsquerda = new Node(key, val);
                }
            //Verifica se o valor a ser inserido é maior que o nodo corrente da árvore, se sim vai para subárvore direita
            } else {
                //Se tiver elemento no nodo direito continua a busca
                if (node.noDireita != null) {
                    inserir(node.noDireita, key, val);
                } else {
                    //Se nodo direito vazio insere o novo nodo aqui
                    node.noDireita = new Node(key, val);
                }
            }
        }
    }
    
    public boolean balanceada(){
        return balanceada(raiz);
    }

    private boolean balanceada(Node node) {
        int hd, he;
        if (node != null) {
            if(!balanceada(node.noEsquerda)) return false;
            if(!balanceada(node.noDireita)) return false;
            
            hd = altura(node.noDireita);
            he = altura(node.noEsquerda);
            
            if((he - hd)<-1 || 1<(he - hd)){
                return false;
            }
        }
        return true;
    }
}
