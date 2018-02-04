/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author diallo1
 */
public class Dico {
    
    private final ArrayList<String> listLevel1;
    private final ArrayList<String> listLevel2;
    private final ArrayList<String> listLevel3;
    private final ArrayList<String> listLevel4;
    private final ArrayList<String> listLevel5;
    
    private final String pathToDicoFile;
   

    public Dico(String pathToDicoFile) throws Exception {
        listLevel1=new ArrayList<String>();
        listLevel2=new ArrayList<String>();
        listLevel3=new ArrayList<String>();
        listLevel4=new ArrayList<String>();
        listLevel5=new ArrayList<String>();
        
        
        this.pathToDicoFile = pathToDicoFile;
        this.readDictionnary(pathToDicoFile);
        
    }

    public String getWordFromListLevel(int level) {
        String resultat ="";
        double r;
        switch (level) {
            case 1:
                r = Math.random()*(double)listLevel1.size();
                resultat = resultat+listLevel1.get((int)r);
                break;
            case 2: 
                r = Math.random()*(double)listLevel2.size();
                resultat = resultat+listLevel2.get((int)r);
                break;
            case 3: 
                r = Math.random()*(double)listLevel3.size();
                resultat = resultat+listLevel3.get((int)r);
                break;   
            case 4: 
                r = Math.random()*(double)listLevel4.size();
                resultat = resultat+listLevel4.get((int)r);
                break;   
            case 5: 
                r = Math.random()*(double)listLevel5.size();
                resultat = resultat+listLevel5.get((int)r);
                break; 
            default:
                break;
        }
        return resultat;
       
    }
    
    public boolean addWordToDico(int level, String word) {
        boolean ajout=false;
        
        switch (level) {
            case 1:
                listLevel1.add(word);
                ajout = true;
                break;
            case 2: 
                listLevel2.add(word);
                ajout = true;
                break;
            case 3: 
                listLevel3.add(word);
                ajout = true;
                break;   
            case 4: 
                listLevel4.add(word);
                ajout = true;
                break;   
            case 5: 
                listLevel5.add(word);
                ajout = true;
                break; 
            default:
                break;
        }
        return ajout;
       
    }
    
    public void readDictionnary(String filename) throws Exception {
        Document doc = XMLUtil.DocumentFactory.fromFile(filename);
        int l;
        NodeList n=doc.getElementsByTagName("mot");
        Element e;
        String mot;
        for(int i=0;i< n.getLength();i++){
            e = (Element) n.item(i);
            l =  Integer.parseInt(e.getAttribute("niveau"));
            mot = e.getTextContent();
            this.addWordToDico(l, mot);
        }
    
    }
    
    public String getPathToDicoFile() {
        return pathToDicoFile;
    }
       
}
