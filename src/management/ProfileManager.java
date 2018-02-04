/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;


import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.Period;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import static management.XMLUtil.DocumentFactory.fromObject;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;
import tux.ObjectFactory;
import tux.Profile;


/**
 *
 * @author Last_Pacifist
 */
public class ProfileManager {
    public Profile profil;
    
    
    public ProfileManager() throws DatatypeConfigurationException{
     //instantiation et initialisation   
        try{
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(Profile.class.getPackage().getName());
        } catch (javax.xml.bind.JAXBException ex) {
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        ObjectFactory profileOF = new ObjectFactory();
        profil = profileOF.createProfile();

    }
    public void toXML(String filename){
        try {
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(profil.getClass().getPackage().getName());

            javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();

            // quelques paramètres pour le support du bon encoding et pour l'affichage simplifié pour les humains
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); 
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

          
            SchemaFactory sf = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);

            Schema schema = sf.newSchema(new File("src/xml/profile.xsd"));
            marshaller.setSchema(schema);
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_SCHEMA_LOCATION, "http://myGame/profile src/xml/profile.xsd");

            // et c'est parti pour l'opération de marshalling (
            marshaller.marshal(profil, new FileOutputStream(filename));

        } catch (javax.xml.bind.JAXBException ex) {
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        } catch (java.io.FileNotFoundException e) {
            System.out.println("Probleme d'ouverture fichier " + filename + " en ecriture !");
        } catch (SAXException ex) {
            Logger.getLogger(ProfileManager.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
        /*public Document toDOM(Profile p) {
        try {
            // Mets en place un buffer (et manipulateur du buffer) de String
            StringWriter writer = new StringWriter();
            // Fabrication d'un contexte pour notre classe.
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(p.getClass());
            // création d'un marshaller pour notre package à partir du contexte JAXB (ainsi le mashaller connait le binding).
            javax.xml.bind.Marshaller marshaller;
            marshaller = jaxbCtx.createMarshaller();
            // quelques paramètres pour le support du bon encoding et pour l'affichage simplifié pour les humains
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            // réalise le marshalling vers le StringWriter (stocke le résultat dans un buffer de String)
            marshaller.marshal(obj, writer);
            // Crée un Document DOM vide à partir d'une fabrique de Document
            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder domBuilder = domFactory.newDocumentBuilder();
            // Parse le StringWriter vers un document DOM
            Document document = domBuilder.parse(new InputSource(new StringReader(writer.toString())));
            // retourne ce document DOM
            return document;
        } catch (JAXBException | ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }*/

    public Profile fromXML(String filename){
        
        try {
            // création du contexte à partir de la classe Profile (ici, encore non instanciée, donc on génère le contexte à partir de la classe)
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(Profile.class.getPackage().getName());

            // création d'un unmarshaller 
            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();

            // unmarshalling... (utilisation d'un transtypage)
            Profile profile = (Profile) unmarshaller.unmarshal(new java.io.File(filename)); //NOI18N

            // et voila, ça marche. Ici on affiche le nom et l'age du joueur
            LocalDate today = LocalDate.now();
            XMLGregorianCalendar birthdayCalendar = profile.getBirthday();
            LocalDate birthday = LocalDate.of(birthdayCalendar.getYear(), birthdayCalendar.getMonth(), birthdayCalendar.getDay());
            Period p = Period.between(birthday, today);
            System.out.println("Joueur: " + profile.getName() + " (" + p.getYears() + " ans)");
            return profile;
            

        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
            return null;
        }
        
    }
    
    public double tempsMoyen(){
        String tmps;
        double x=0.0;
        Document d=fromObject(profil);
        NodeList n=d.getElementsByTagName("time");
        for(int i=0;i<n.getLength();i++){
            tmps=n.item(i).getTextContent();
            x=x+(double) (Double.parseDouble(tmps));            
        }
        
        x=x/n.getLength();
        
        x=x*100;
        x=(int)x;
        x=x/100;
        return x;      
    }
    
}
