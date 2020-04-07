import java.util.*;
//Implementazione della classe fog search tree
//per la prova di esonero di laboratorio di algoritmi A.A 2019/2020
//Michele Sanfilippo

//Utilizziamo una classe che possa lavorare con i tipi generici
public class FST<T extends Comparable<T>>{
    private FSTNode<T> root; //La radice dell'albero
    private static final int SPACE = 8; //Costante per la stampa dell'albero

    //Costruttore
    public FST(T value){
        root = new FSTNode(value);
    }
    //Metodo per avere il valore della radice, essendo quest'ultima private
    public FSTNode<T> getRoot(){
        return root;
    }

    //Funzione di ricerca del nodo che lo va a spostare in posizione
    //di radice qualora il nodo esistesse.
    //In caso contrario sposta l'ultimo nodo visitato.
    //La funzione fa riferimento direttamente alla funzione climb essendo
    //climb stessa una funzione di ricerca e spostamento dell'elemento.
    public FSTNode<T> searchNode(T value){
        if (root.equals(null)) return null;
        return root = root.climb(root,value);
    }

    //Funzione di aggiunta dei nodi
    //se la radice è null la creo, se esiste già richiamo la
    //funzione di aggiunzione nodo come da BST e infine
    //applico la funzione di climb su quel valore in quel determinato albero e 
    //restituisco la nuova radice che ottengo dalla funzione climb
    public FSTNode<T> addNode(T value){
        if (root.equals(null)){
            root = new FSTNode(value);
            return root;
        }
        root.addNode(value);
        return root = root.climb(root,value);
    }

    //Funzione di rimozione di un nodo, se la radice è nulla
    //non ha senso continuare.
    //Altrimenti richiamo la funzione di rimozione come da BST con chiamata successiva
    //alla funzione climb per restituire la nuova radice
    public FSTNode<T> removeNode(T value){
            if (root == null) return null;
            else {
               root.removeNode(value, root);
               return root = root.climb(root,value);
            }
    }

    //Funzione che stampa l'albero come da comune rappresentazione
    public static void printTree(FSTNode root, int space){
        if (root == null) return; //Non stampo
        //Altrimenti aumento lo spazio
        space += SPACE;
        printTree(root.right, space);
        //Stampo
        System.out.print("\n");
        for (int i = SPACE; i < space; i++)
            System.out.print(" ");
        System.out.print(root.value + "\n");
        printTree(root.left, space);
    }

}