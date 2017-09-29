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
    
    
    
    boolean fTemVazio = false;
    boolean fUmNT = true;
    boolean fMaiorQ2 = false;
    boolean fLeMaiorQLd = false;
    int menorSentenca = 99;

    public ConjuntoProd() {
        this.conjProd = new LinkedList<>();
        this.conjNterm = new LinkedList<>();
        this.conjTerm = new LinkedList<>();
    }

    public void setTermIni(String termIni) {
        this.termIni = termIni;
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
    
    public void setConjuntos(String[] nTerm,String[] term){
        for(String x: nTerm){
            conjNterm.add(x);
        }
        
        for(String y: term){
            conjTerm.add(y);
        }
    }

    @Override
    public String toString() {
        String text = "G = ({";
        text += conjNterm.get(0);
        if(conjNterm.size()>1){
            for (int i = 1; i < conjNterm.size(); i++) {
                text += "," + conjNterm.get(i);
            }
        }
        
        text += "}, {" +conjTerm.get(0);
        if(conjTerm.size()>1){
            for (int i = 1; i < conjTerm.size(); i++) {
                text += "," + conjTerm.get(i);
            }
        }
        text += "}, P, "
                + termIni
                + ")\n";
        
        
        text += "P: {";
        for (Producao p :conjProd) {
            text += "" + p.nTerm + "->" + p.prod.get(0);
            
            if(p.prod.size()>1){
                for (int i = 1; i < p.prod.size(); i++) {
                    text += "|" + p.prod.get(i);
                }
                text += "\n";
            }
        }
        text += "}";
        
        return text;
    }
    
    public String reconheceGramatica(){
        String txt = "";
        
        for(Producao p : conjProd){
           if(p.nTerm.length()>1){
               fUmNT = false;
           }
           
            for(String s : p.prod){
                if(s.length()<menorSentenca) menorSentenca = s.length();
                if(s.contains("&")) fTemVazio = true;
                if(s.length()>2) fMaiorQ2 = true;
            }
            
            if(p.nTerm.length()>menorSentenca) ;
     
        }
        
        if (fUmNT) txt += "\n* Tem só um simbolo não-terminal do lado esquerdo";
        if (fTemVazio) txt += "\n* Contém sentença vazia do lado direito";
        if (fMaiorQ2) txt += "\n* Mais que dois simbolos do lado direito";
        
        if (fUmNT && !fMaiorQ2)  txt += "\n!!! É uma Gramática Regular";
        else if (fUmNT && !fTemVazio) txt += "\n!!! É uma Gramática Livre de Contexto";
        else if (!fUmNT && !fTemVazio) txt += "\n!!! É uma Gramática Sensível ao Contexto";        
        else txt += "\n!!! É uma Gramática Irrestrita";
        
        
        return txt;
    }
  

    
}
