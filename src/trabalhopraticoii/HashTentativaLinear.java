package trabalhopraticoii;

/**
 *
 * @author Arthur Passos
 */
public class HashTentativaLinear<Key, Value> {
    private int numPares; // numero de pares de chaves na tabela
    private int tamHashLinear = 16; // Tamanho da tabela hash com tratamento linear
    private Key[] keys; // the keys
    private Value[] values; // the values
    private boolean[] estados; // true = ALOCADO, false = REMOVIDO

    //Cria um vetor de chaves, valores e estados dessas chaves, podendo elas estar ocupadas ou livres;
    //Utiliza o tamanho padrão 16
    public HashTentativaLinear() {
        keys = (Key[]) new Object[tamHashLinear];
        values = (Value[]) new Object[tamHashLinear];
        estados = new boolean[tamHashLinear];
    }
    //Cria um vetor de chaves, valores e estados dessas chaves, podendo elas estar ocupadas ou livres;
    //Utiliza um tamanho variavel passado como parametro
    public HashTentativaLinear(int cap) {
        keys = (Key[]) new Object[cap];
        values = (Value[]) new Object[cap];
        estados = new boolean[cap];
        tamHashLinear = cap;
    }

    //Função que faz o hash Auxiliar em caso de colisão
    private int hashAux(Key key){
        return 1 + (key.hashCode() & 0x7fffffff) % tamHashLinear;
    }

    //Retorna o hash entre 0 e tamHashLinear-1.
    private int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % tamHashLinear; 
    }

    //Redimenciona a tabela para um novo tamanho (de acordo com a quantidade de chaves), recolocando os elementos na nova tabela
    private void resize(int cap) {
        HashTentativaLinear<Key, Value> hash;
        boolean st[] = new boolean[cap];
        hash = new HashTentativaLinear<Key, Value> (cap);

        for (int i = 0; i < keys.length; i++) {
            if (keys[i] != null) {
                hash.put(keys[i], values[i]);
                st[i] = estados[i];
            }
        }
        estados = st;
        keys = hash.keys;
        values = hash.values;

        tamHashLinear = hash.tamHashLinear;
    }

     ////Verifica se está na tabela hash
     public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("Argument to contains() cannot be null");
        }

        return get(key) != null;
     }

    //Insere um novo objeto no Hash 
    public void inserirHashDuplo(Key key, Value val) {
        int i,j;

        if (numPares >= tamHashLinear/2) resize(2*tamHashLinear); 
        // dobra tamHashLinear
        //i é a hash inicial
        //Em caso de colisão a proxima posição testa é a (i + j) % tamHashLinear onde j é o valor da hash auxiliar

        for (i = hash(key), j = hashAux(key); keys[i] != null; i = (i + j) % tamHashLinear) {
            if (keys[i].equals(key)) {            // Caso a chave já esteja na tabela o valor é sobrescrito
                values[i] = val;
                return;
            }
        }
        
        keys[i] = key;
        values[i] = val;
        estados[i] = true;
        //Definimos o estado da chave para true, que significa alocado.
        numPares++;
    }

    //Insere um elemento na tabela utilizando uma hash simples e tentativa linear. Se a posição que a hash cair estiver ocupada
    //Passa para posição i+1 % tamHashLinear, utilizando o resto para o valor não sair do tamanho da tabela;
    //Se a chave  já existir na tabela o valor é sobrescrito.
    public void put(Key key, Value val) {
        int i;
        if (numPares >= tamHashLinear/2)  resize(2*tamHashLinear); // double M 

        for (i = hash(key); keys[i] != null; i = (1 + i) % tamHashLinear)
            if (keys[i].equals(key)) {
                values[i] = val;
                return; 
            }
        //Achou uma posição livre

        keys[i] = key;
        values[i] = val;
        estados[i] = true;
        //Definimos o estado da chave para true, que significa alocado.
        numPares++;
    }

    //Executa a remoção sem deletar o elemento da memória
    //Caso o elemento esteja contido na tabela, calculcamos a sua posição nos pares de chaves e alteramos seus estado para false, ou seja Livre.
    public void deleteNoRemove(Key key){
        if (key == null)
            throw new IllegalArgumentException("Argument to delete() cannot be null");

        if (!contains(key))
            return;

        int i = hash(key);
        int k = hashAux(key);

        while (!key.equals(keys[i]))
            i = (i + k) % tamHashLinear;

        estados[i] = false;

        i = (i + k) % tamHashLinear;

        while (keys[i] != null){
            Key keyToRedo = keys[i];
            Value valToRedo = values[i];
            estados[i] = false;
            numPares--;
            put(keyToRedo, valToRedo);
            i = (i + 1) % tamHashLinear;
        }

        //decrementamos o contador de pares.
        numPares--;
        //Verificamos se existe necessidade de redimensionameto após a remoção da chave.
        if (numPares > 0 && numPares == tamHashLinear/8)
            resize(tamHashLinear/2);
    }

    //Faz a remoção fisica do elemento atribuindo null para chave e valor
    public void delete(Key key){
        if (key == null) 
            throw new IllegalArgumentException("Argument to delete() cannot be null");

        if (!contains(key))
            return;

        int i = hash(key);
        while (!key.equals(keys[i]))
            i = (i + 1) % tamHashLinear;

        keys[i] = null;
        values[i] = null;
        i = (i + 1) % tamHashLinear;

        while (keys[i] != null){
            Key keyToRedo = keys[i];
            Value valToRedo = values[i];
            keys[i] = null;
            values[i] = null;
            numPares--;
            put(keyToRedo, valToRedo);
            i = (i + 1) % tamHashLinear;
        }
        numPares--;
        if (numPares > 0 && numPares == tamHashLinear/8) 
            resize(tamHashLinear/2);
    }

    //Busca um objeto no Hash
    public Value getHashDuplo(Key key) {
        int i, j;
        for (i = hash(key), j = hash(key); keys[i] != null; i = (i + j) % tamHashLinear)
            if (keys[i].equals(key))
                return values[i];
        return null;
    }

    //Percorre a tabela de chaves utilizando tentativa linaar e retorna o valor correspondente a uma chave
    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % tamHashLinear)
            if (keys[i].equals(key)) {
                return values[i];
            }
        return null;
    }
    
    public int quantidadeKeys(){
        return this.numPares;
    }
        
}
