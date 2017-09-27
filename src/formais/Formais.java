/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formais;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import rules.Producao;

/**
 *
 * @author m93492
 */
public class Formais {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<String> nTerm = new LinkedList<>(Arrays.asList("S","A","B"));
        List<String> term = new LinkedList<>(Arrays.asList("a","b","c"));
        
        String text = "S:aS,bS,c\n"
                    + "A:a,b,&";
        
        List<Producao> conjProd = new LinkedList<>();
        
        String[] auxText = text.split("\n");
        
        for (int i = 0; i < auxText.length; i++) {
            String[] auxProd = auxText[i].split(":");
            
            conjProd.add(new Producao(auxProd[0]));
            
            String[] auxSent = auxProd[1].split(",");
            System.out.println(auxProd[1]);
            for (int j = 0; j < auxSent.length ; j++) {
                conjProd.get(i).addProd(auxSent[j]);
                System.out.println(auxSent[j]);
            }
        }
        
        
        
        
    }
    
}
