/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import javax.xml.datatype.DatatypeConfigurationException;
import management.ProfileManager;
import static management.XMLUtil.DocumentFactory.fromObject;
import static management.XMLUtil.DocumentTransform.fromXSLTransformation;
import org.w3c.dom.Document;
import xml.BrowserUtil;
import xml.FileUtil;

/**
 *
 * @author Last_Pacifist
 */
public class testProfileManager {

    
    public static void main(String[] args) throws DatatypeConfigurationException, Exception {
        ProfileManager manager = new ProfileManager();       
        manager.profil = manager.fromXML("src/xml/profile.xml");
        manager.toXML("src/xml/profile2.xml");
        Document doc=fromObject(manager.profil);
        String nom=doc.getChildNodes().item(0).getChildNodes().item(1).getTextContent();
        String annee=doc.getChildNodes().item(0).getChildNodes().item(5).getTextContent().substring(0, 4);
        System.out.println("Le joueur, nommé "+nom+" est né en "+annee);
        double i =manager.tempsMoyen();
        System.out.println("Son temps moyen de résolution est de "+i+" secondes");
        String ProfilHtml= fromXSLTransformation("src/xsl/profil.xsl", doc);
        System.out.println(ProfilHtml);
        FileUtil.stringToFile(ProfilHtml,"src/xml/profile.html");
        BrowserUtil.launch("src/xml/profile.html");
        
    }
    
}
