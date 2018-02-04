/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author dian
 */
public class Partie {
    private String date;
    private  String mot;
    private  int niveau;
    private int trouve ;
    private int temps;

    public Partie(String date, String mot, int niveau) {
        this.date = date;
        this.mot = mot;
        this.niveau = niveau;
    }

    
        
    public Partie(Element partieElt){
        
        // Lecture de l'attribut date de <game>
        this.date = partieElt.getAttribute("date");
        String s="";
        int l;
                
        // Lecture de l'attribut found de <game>
        if(partieElt.getAttribute("found").isEmpty()){
            this.trouve = 0;
        }
        else{
           
            l=partieElt.getAttribute("found").length();
            for(int i=0;i<l-1;i++){
                s=s+partieElt.getAttribute("found").charAt(i);
            }
            this.trouve = Integer.parseInt(s);
            
        }
        
        // Lecture de <time>
        if((partieElt.getElementsByTagName("time").item(0) == null) || (partieElt.getElementsByTagName("time").item(0).getTextContent().isEmpty())){
            this.temps =0;
        }
        else{
            this.temps = Integer.parseInt(partieElt.getElementsByTagName("time").item(0).getTextContent());
        }
        
        // Lecture de <word> 
        Element mot_dom = (Element)partieElt.getElementsByTagName("word").item(0);
        
        this.mot=mot_dom.getTextContent();
        
        //lecture du niveau
        this.niveau=Integer.parseInt(mot_dom.getAttribute("niveau"));
             
    }
    
    public Element getPartie(Document doc) throws SAXException, ParserConfigurationException, IOException{
        File inputFile = new File("src/xml/profile.xml");
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        doc = docBuilder.parse(inputFile);
            
        return (Element) doc.getElementsByTagName("game").item(0);
    }

    public int getNiveau() {
        return niveau;
    }

     public void setTrouve(int nbLettresRestantes){
        int nblettreTrouve = mot.length() - nbLettresRestantes;
        
        this.trouve = (nblettreTrouve * 100) / mot.length();
    }


    public void setTemps(int temps) {
        this.temps = temps;
    }

    public String getDate() {
        return date;
    }

    public String getMot() {
        return mot;
    }

    public int getTrouve() {
        return trouve;
    }

    public int getTemps() {
        return temps;
    }
    
    
    
    
    @Override
    public String toString() {
        String retour = "======= Resumé d'une partie =======";
        retour += "\nPartie jouée le : "+ date +"\nMot : " + mot +"\nNiveau de difficulté : " + niveau + "\nPourcentage de réussite : " + trouve + "%\nTemps réalisé : " + temps +" secondes";
        retour += "\n======= Fin de la partie =======\n";
        return retour;
    }
    
}
