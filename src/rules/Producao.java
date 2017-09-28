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
public class Producao {
    String nTerm;             //Lado esquerdo
    List<String> prod;        //Lado direito

    public Producao(String nTerm) {
      this.nTerm = nTerm;
      this.prod = new LinkedList<String>();
      
    }
    
    public void addProd(String producao){
        prod.add(producao);
    }
    
    
    
}
