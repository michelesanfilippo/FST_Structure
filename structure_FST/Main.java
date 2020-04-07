import java.util.*;
import java.lang.*;
import java.io.*;

public class Main{
    public  static void main(String[] args) {
        try{
            File file = new File("tale.txt");
            FileReader filereader = new FileReader(file);
            BufferedReader bufferedreader = new BufferedReader(filereader);
            StringBuffer stringbuffer = new StringBuffer();
            String string = bufferedreader.readst();
            int i =0;
            //Setto il timer da ora
            double init = System.currentTimeMillis();
            FST<Character> tree = new FST<Character>(string.charAt(i));
            while((string = bufferedreader.readst()) != null){
                for(i = 1; i < string.length(); i++) {
                    //Inserisco nell'albero i caratteri
                    tree.addNode(string.charAt(i));
                }
            }
            filereader.close();
            //Fine degli inserimenti
            double end = System.currentTimeMillis();
            double time = end - init ;
            //stamp albero
            FST.printTree(tree.getRoot(), 0);
            //tempo di aggiunzione di ogni elemento
            System.out.println("\nTime in ms: " + time);
            //cerco la lettera c nel file di testo
            tree.addNode('c');
            //stampo la lettera trovata
            //stampo il nuovo albero con la lettera trovata come radice
            FST.printTree(tree.getRoot(), 0);
            //cerco un nuovo elemento
            tree.searchNode('s');
            FST.printTree(tree.getRoot(), 0);
            //Adesso rimuoviamo s.
            //Come da consegna l'albero ottenuto avrà come radice
            //il padre di s(in questo caso r) ed s verrà eliminato dall'albero
            tree.removeNode('s');
            FST.printTree(tree.getRoot(), 0);

            /***************************************************************************
             * Considerazioni finali sui tempi:                                        *
             *                                                                         *
             * Se dovessimo considerare i tempi per l'inserimento in diverse strutture *
             * quali BST,FST(fog search tree), AVL e RB tree, avremo dei tempi diversi *
             * Nel BST basterebbe inserire l'elemento nella corretta posizione, questo *
             * farebbe sí che il BST sia il più veloce nell'inserimento, perchè in FST *
             * dovremmo fare il climb ad ogni inserimento, in AVL dovremmo bilanciare  *
             * se ad un inserimento l'albero diventasse sbilanciato mentre per i RB    *
             * dovremmo occuparci degli archi rossi e neri.                            *
             * *************************************************************************
             * Michele Sanfilippo.*/
        }
        catch(IOException exception){
            exception.printStackTrace();
        }

    }
}