/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhopraticoii;

/**
 *
 * @author arthu
 */
public class TrabalhoPraticoII {

    public static void main(String[] args) {
        RBTree rb = new RBTree();
        String[] arr = Leitor.leitor1(20000);
        for(int i = 0; i<20000;i++){
            rb.insere(arr[i],i);
        }

        Object valor = rb.getValue(arr[10]);

        System.out.println("Busca em rb com 20000 elementos:");
        System.out.println("Valor:" + valor);

        System.out.println("Porcentagem de Vermelhos: ");
        System.out.println(rb.PercentRed());
        
        BinaryTree bin = new BinaryTree();
        
        bin.inserir("c",3);
        bin.inserir("b",1);
        bin.inserir("a",0);
        bin.inserir("d",2);
        bin.inserir("e",4);
        
        if(bin.balanceada())
            System.out.println("Balanceada");
        else
            System.out.println("Nao balanceada");
        //AVLTree avl = bin.GetAVL();
       
        //System.out.println("\nBinaria e' AVL? "+avl.isAVL());

    }
}
