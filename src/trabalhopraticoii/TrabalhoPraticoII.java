/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhopraticoii;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author Arthur Passos
 */
public class TrabalhoPraticoII {

    public static void main(String[] args) throws FileNotFoundException {
        RBTree rb = new RBTree();
        BinaryTree bin = new BinaryTree();
        HashTentativaLinear tab = new HashTentativaLinear(1000);
        HashListaEncadeada tab2 = new HashListaEncadeada(1000);
        
        if(lerArquivoHashTentativaLinear(tab, 1000)){
            String aux = "tnbcckdlfbkzvmrpdmzu";
            Object valor = tab.get(aux);

            System.out.println("Valor associado a "+aux+": " + valor);
            
            //System.out.println("Quantidade: "+tab.quantidadeChaves()+"\n");
        }
        
        if(lerArquivoHashListaEncadeada(tab2, 1000)){
            String aux = "tnbcckdlfbkzvmrpdmzu";
            Object valor = tab2.get(aux);

            System.out.println("Valor associado a "+aux+": " + valor);
            
            System.out.println("Quantidade: "+tab2.quantidadeKeys()+"\n");
        }
        
        if(lerArquivoRBTree(rb)){
            System.out.println("Porcentagem de Vermelhos: "+rb.percentualRED()+"\n");
            
            if(rb.balanceada())
                System.out.println("RBTree: Balanceada");
            else
                System.out.println("RBTree: Nao balanceada");
        }
        System.out.println();
            
        if(lerArquivoBinaryTree(bin)){
            if(bin.balanceada())
                System.out.println("Binary Tree: Balanceada");
            else
                System.out.println("Binary Tree: Nao balanceada");
        }  
    }
    
    public static boolean lerArquivoRBTree(RBTree rb) throws FileNotFoundException{
        int count = 0;
        
        File arquivo = new File("tabela2.txt"); //selecione o arquivo string_double de tamanho qualquer
        
        if(arquivo.exists()){   
            if(arquivo.canRead()){
                try{
                    Scanner input = new Scanner(arquivo).useDelimiter("\\,|\\r\\n");
                    while (input.hasNext() && count<100) {//lendo atributos do arquivo
                        String chave = input.next();
                        double valor = Double.parseDouble(input.next());
                        rb.inserir(chave, valor);
                        count++;
                    }
                    
                    return true;
                }catch(IOException e){
                    System.out.println(e);
                }catch(NoSuchElementException e){
                    System.out.println("Arquivo não condiz com os dados a serem analisados!");
                }
            }
        }else{
            System.out.println("O arquivo não existe!");
        }
        return false;
    }
    
    public static boolean lerArquivoBinaryTree(BinaryTree bin) throws FileNotFoundException{
        int count = 0;
        
        File arquivo = new File("tabela.txt"); //selecione o arquivo string_double de tamanho qualquer
        
        if(arquivo.exists()){   
            if(arquivo.canRead()){
                try{
                    Scanner input = new Scanner(arquivo).useDelimiter("\\,|\\r\\n");
                    while (input.hasNext() && count<100) {//lendo atributos do arquivo
                        String chave = input.next();
                        double valor = Double.parseDouble(input.next());
                        bin.inserir(chave, valor);
                        count++;
                    }
                    
                    return true;
                }catch(IOException e){
                    System.out.println(e);
                }catch(NoSuchElementException e){
                    System.out.println("Arquivo não condiz com os dados a serem analisados!");
                }
            }
        }else{
            System.out.println("O arquivo não existe!");
        }
        return false;
    }
    
    public static boolean lerArquivoHashTentativaLinear(HashTentativaLinear tab, int tam) throws FileNotFoundException{
        int count = 0;
        
        File arquivo = new File("tabela.txt"); //selecione o arquivo string_double de tamanho qualquer
        
        if(arquivo.exists()){   
            if(arquivo.canRead()){
                try{
                    Scanner input = new Scanner(arquivo).useDelimiter("\\,|\\r\\n");
                    while (input.hasNext() && count<tam) {//lendo atributos do arquivo
                        String chave = input.next();
                        double valor = Double.parseDouble(input.next());
                        tab.putDoubleHash(chave, valor);
                        count++;
                    }
                    
                    return true;
                }catch(IOException e){
                    System.out.println(e);
                }catch(NoSuchElementException e){
                    System.out.println("Arquivo não condiz com os dados a serem analisados!");
                }
            }
        }else{
            System.out.println("O arquivo não existe!");
        }
        return false;
    }
    
    public static boolean lerArquivoHashListaEncadeada(HashListaEncadeada list, int tam) throws FileNotFoundException{
        int count = 0;
        
        File arquivo = new File("tabela.txt"); //selecione o arquivo string_double de tamanho qualquer
        
        if(arquivo.exists()){   
            if(arquivo.canRead()){
                try{
                    Scanner input = new Scanner(arquivo).useDelimiter("\\,|\\r\\n");
                    while (input.hasNext() && count<tam) {//lendo atributos do arquivo
                        String chave = input.next();
                        double valor = Double.parseDouble(input.next());
                        list.put(chave, valor);
                        count++;
                    }
                    
                    return true;
                }catch(IOException e){
                    System.out.println(e);
                }catch(NoSuchElementException e){
                    System.out.println("Arquivo não condiz com os dados a serem analisados!");
                }
            }
        }else{
            System.out.println("O arquivo não existe!");
        }
        return false;
    }
}
