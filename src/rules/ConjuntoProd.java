/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rules;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author m93492
 */
public class ConjuntoProd {
    List<Producao> conjProd;
    List<String> conjNterm;
    List<String> conjTerm;
    String termIni;
    int tipoG;
    
    
    
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

    public int getTipoG() {
        return tipoG;
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
        
        if (fUmNT && !fMaiorQ2)  {
            txt += "\n!!! É uma Gramática Regular";
            this.tipoG = 3;
        }
        else if (fUmNT && !fTemVazio) {
            txt += "\n!!! É uma Gramática Livre de Contexto";
            this.tipoG = 2;
        }
        else if (!fUmNT && !fTemVazio) {
            txt += "\n!!! É uma Gramática Sensível ao Contexto";
            this.tipoG = 1;
        }        
        else {
            txt += "\n!!! É uma Gramática Irrestrita";
            this.tipoG = 0;
        }
        
        
        return txt;
    }
    
    public boolean validaGramatica(){
        boolean f1 = false;
        boolean f2 = true;
        
        for(Producao p : this.conjProd){
            for(String s : this.conjNterm){
                if(p.nTerm.contains(s)) f1 = true;
            }
            if(!f1) f2 = false;
            f1 = false;
        }
        
        return f2;    
    }
    
    public String geraPalavra(){
        String palavra = this.termIni;
        String txt = "\n" + palavra;
        
        Random rand = new Random();
        // rand.nextInt(10)+1
        
        //for (int i = 0; i < rand.nextInt(5); i++) {
        for (int i = 0; i < 15; i++) {
            for(Producao p : this.conjProd){
                
                if(palavra.contains(p.nTerm)){
                    
                    palavra = palavra.substring(0, palavra.length()-1) + p.prod.get(rand.nextInt(p.prod.size()));
                    
                    txt += " -> " + palavra;
                   
                }                
            }            
        }        
        return txt;
    }  

    
}
