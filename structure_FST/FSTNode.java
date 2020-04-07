import java.util.*;
//Classe che rappresenta la struttura di ogni nodo
//presente nell'albero FST

public class FSTNode<T extends Comparable<T>>{
        T value; //valore del nodo
        FSTNode<T> left; //figlio sinistro
        FSTNode<T> right; //figlio destro

        //Costruttore
        public FSTNode(T value){
            this.value = value;
            left = right = null;
        }

    //Funzione che restituisce il valore del nodo
    public T getValue(){
        return value;
    }
    //Funzione che setta il valore del nodo
    public void setValue(T value){
          this.value = value;
    }
    //Funzione che permette di visionare il figlio sx del nodo
    public FSTNode<T> getLeft(){
          return left;
    }
    //Funzione che permette di visionare il figlio dx del nodo
    public FSTNode<T> getRight(){
          return right;
    }
    //Funzione che setta il valore del figlio sx del nodo
    public void setLeft(FSTNode<T> node){
          left = node;
    }
    //Funzione che setta il valore del figlio dx del nodo
    public void setRight(FSTNode<T> node){
          right = node;
    }

    //Funzione che permette la rotazione a destra del nodo come negli
    //alberi AVL
    public FSTNode<T> rightRotate(FSTNode<T> node){
        FSTNode<T> tmp = node.left;
        node.left = tmp.right;
        tmp.right = node;
        return tmp;
    }

    //Funzione che permette la rotazione a sinistra del nodo come negli
    //alberi AVL
    public FSTNode<T> leftRotate(FSTNode<T> node){
    FSTNode<T> tmp = node.right;
    node.right = tmp.left;
    tmp.left = node;
    return tmp;
    }

    //Funzione che inserisce il nodo che si desidera inserire
    //in posizione esatta come se si fosse in un BST.
    public FSTNode<T> addNode(T value){
        //Se l'elemento già è presente non devo fare nulla se non poi richiamare
        //la funzione di climb su tale elemento
        if(value.compareTo(this.value)==0){
            return this; //Non possono essere presenti doppioni
        }
        //Vado a cercare il mio valore a sx del nodo corrente
        else if (value.compareTo(this.value) < 0){
            //Lo inserisco in posizione trovata e faccio climb sull'elemento
            if (left == null){
                left = new FSTNode<T>(value);
                return left;
			}
			else return left.addNode(value); //la sinistra non era libera e continuo a cercare
        }
        //Lo cerco a dx del nodo corrente
        else if (value.compareTo(this.value) > 0){
            if(right == null){
                right = new  FSTNode<T>(value);
                return right;
            }
            else return right.addNode(value); //la destra non era libera
        }
            return null; //Vi è stato un qualche problema se si arriva a questo punto
    }

    //Funzione che rimuove il nodo e gestisce in maniera appropriata i figli
    //restituendo il padre del nodo eliminato
    public FSTNode<T> removeNode(T value2, FSTNode<T> node){
        //cerco a sx del nodo
        if (value2.compareTo(this.value)<0){
           if (left != null) return left.removeNode(value2, this);
           else return null;
           }
        //cerco a destra
        else if (value2.compareTo(this.value)>0){
           if (right != null) return right.removeNode(value2, this);
           else return null;
        }
       else{
           //ho trovato l'elemento e devo gestirne i figli

           //se ha entrambi i figli
           if (left != null && right != null){
               //prendo il minimo di dx
               this.value = right.minValue();
               right.removeNode(this.value, this);
               }
            //casi in cui ne abbia solo uno
           else if (node.left == this){
               node.left = (left != null) ? left : right;
           	}
           else if (node.right == this){
               node.right = (left != null) ? left : right;
               }
               return node;
		   }

	}

    //funzione che trova il minimo
    //cercando sempre più a sx possibile
    public T minValue(){
		if (left == null) return value;
       	return left.minValue();
    }


    // La funzione climb porta la chiave cercata
    // in posizione di radice se la chiave è presente.
    // Se non è presente allora l'ultimo elemento a cui si ha
    // avuto accesso diventerà la nuova radice.
    // Tale funzione quindi modifica la nostra struttura e la sua radice
    // restituendo la nuova radice come valore finale.
    //La strategia adottata è quella della rotazione a sinistra o rotazione a destra come negli alberi AVL
    //tale strategia non prevede però un ribilanciamento della struttura ad ogni passo, tale per cui
    //il nostro caso pessimo sarà O(n) mentre nel caso medio saremo in O(logn).
    public FSTNode<T> climb(FSTNode<T> root, T key){
        // Iniziamo dal caso base ovvero se la radice è nulla
        if (root == null) return root;//Non dobbiamo alterare nulla
        // Altrimenti andiamo a ricercare a sx o dx
        //Iniziamo da sx
        if (root.value.compareTo(key) > 0){
            //La chiave non è presente
            if (root.left == null) return root;
            //Continuamo a cercare
            if (root.left.value.compareTo(key) > 0){
                //Operiamo ricorsivamente
                root.left.left = climb(root.left.left, key);
                root = rightRotate(root);
            }
            else if (root.left.value.compareTo(key) < 0){
                //Come sopra ma operiamo dal lato opposto
                root.left.right = climb(root.left.right, key);
                if (root.left.right != null) root.left = leftRotate(root.left);
            }
            //Controllo se ho finito altrimenti continuo con rightRotate
            return (root.left == null) ?  root : rightRotate(root);
        }
        //Se siamo in tale else allora la chiava è nel sottoalbero dx
        //ripeto i passaggi di sopra ma dal lato opposto
        else{
            //La chiave non c'è
            if (root.right == null) return root;
            if (root.right.value.compareTo(key) > 0){
                //Inizio con le rotazioni
                root.right.left = climb(root.right.left, key);
                if (root.right.left != null) root.right = rightRotate(root.right);
            }
            else if (root.right.value.compareTo(key) < 0){
                root.right.right = climb(root.right.right, key);
                root = leftRotate(root);
            }
            return (root.right == null) ?  root : leftRotate(root);
        }
    }
}