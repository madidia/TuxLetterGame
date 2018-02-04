/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LanceurDeJeu;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

/**
 *
 * @author diallo1
 */
public class Game {

    /**
     * @param args the command line arguments
     * @throws javax.xml.parsers.ParserConfigurationException
     * @throws org.xml.sax.SAXException
     * @throws java.io.IOException
     * @throws javax.xml.transform.TransformerException
     */
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException, Exception {
        // TODO code application logic here
        //Instanciate a new Jeu
        
        Jeu j= new Jeu("/home/diallo/Desktop/2017_DIALLO_DIALLO/TUXGAME_2017_DIALLO_DIALLO/src/xml/profile.xml");
        //Play the game
        j.jouer();       
    }    
}
