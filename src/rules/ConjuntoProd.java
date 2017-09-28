/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rules;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author m93492
 */
public class ConjuntoProd {
    List<Producao> conjProd;
    List<String> conjNterm;
    List<String> conjTerm;
    String termIni;

    public ConjuntoProd() {
        this.conjProd = new LinkedList<>();
        this.conjNterm = new LinkedList<>();
        this.conjTerm = new LinkedList<>();
    }
    
    
    public void setProd(String[] auxText){
        
        for (int i = 0; i < auxText.length; i++) {
            String[] auxProd = auxText[i].split(":");
            
            conjProd.add(new Producao(auxProd[0]));
            
            String[] auxSent = auxProd[1].split(",");
            System.out.println(auxProd[1]);
            for (int j = 0; j < auxSent.length ; j++) {
                conjProd.get(i).addProd(auxSent[j]);
                //System.out.println(auxSent[j]);
            }
        }
    }
    
    public void setConjuntos(String[] auxText){
        for(String s: auxText){
            
        }
    }

    @Override
    public String toString() {
        String text = "";
        
        for (Producao p :conjProd) {
            text += "" + p.nTerm + ":" + p.prod.get(0);
            
            if(p.prod.size()>1){
                for (int i = 1; i < p.prod.size(); i++) {
                    text += "," + p.prod.get(i);
                }
            }
            text += "\n";
        }
        
        return text;
    }
  

    
}
