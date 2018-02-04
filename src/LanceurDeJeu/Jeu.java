/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LanceurDeJeu;

import env3d.Env;
import java.io.IOException;
import java.util.Date;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import management.DevineLeMot;
import management.Dico;
import management.LectureClavier;
import management.Partie;
import management.Profil;
import org.xml.sax.SAXException;

/**
 *
 * @author diallo
 */
public class Jeu {

    public int level;
    public Dico dico;
    private Env env;
    private Room room;
    private DevineLeMot d;

    public Profil profil;
    public char choix;

    public Jeu(String pathToProfile) throws ParserConfigurationException, SAXException, IOException, Exception {

        dico = new Dico("src/xml/dico.xml");

        this.profil = new Profil(pathToProfile);
    }

    public void jouer() throws ParserConfigurationException, SAXException, IOException, TransformerException {

        this.level = profil.getDernierLevel();

        //Create the new environment.  Must be done in the same method as the game loop
        env = new Env();
        // Instanciate a room 
        room = new Room();
        Partie partie;
        boolean initialise = false;
        String mot;
        String date_partie;
        //  char choix ='z';
        boolean save;
        boolean play = true;
        int m, a;
        String s;
        int salle;
        while (play) {

            System.out.println("Veuillez choisir une salle\n1 pour la premiere salle\n2 pour la deuxieme salle\n3 pour la troisieme salle\n4 pour la quatrieme salle");
            salle = LectureClavier.lireEntier();
            while (salle != 1 && salle != 2 && salle != 3 && salle != 4) {

                System.out.println("Veuillez choisir une salle\n1 pour la premiere salle\n2 pour la deuxieme salle\n3 pour la troisieme salle\n4 pour la quatrieme salle");
                salle = LectureClavier.lireEntier();

            }
            // on choisit la room en fonction du choix
            if(salle==2){
                room.Room1(room);
                env.setRoom(room);
            }else if(salle==3){
                room.Room2(room);
                env.setRoom(room);
            }else if(salle == 4){
                room.Room3(room);
                env.setRoom(room);
            }else{
                // salle=1, donc on conserve la room instanciée
                env.setRoom(room);
            }
            
            
            if (initialise == true) {

                env.setRoom(room);
            }

            mot = dico.getWordFromListLevel(this.level);
            d = new DevineLeMot(mot, env, this.room);

            env.setDisplayStr("Niveau " + this.level, 480, 40);
            d.jouer();

            if (d.getNbLettresRestantes() == 0 && level <= 5) {
                this.level = this.level + 1;
            } else if (d.getNbLettresRestantes() == 0 && this.level == 6) {
                this.level = 1;
            }

            env.setDisplayStr("", 300, 40);
            env.setDisplayStr("", 480, 40);

            // on recupere la date du jour
            Date date = new Date();
            m = date.getMonth() + 1;
            a = date.getYear() + 1900;
            date_partie = a + "-" + m + "-" + date.getDate();
            
            //on instancie une nouvelle partie
            partie = new Partie(date_partie, mot, level);
            partie.setTrouve(d.getNbLettresRestantes());
            partie.setTemps(d.getTemps());
            
            //on ajoute cette partie dans le profil
            profil.ajouterPartie(partie);

            //d.reponse c'est la valeur obtenue en cliquant sur une option dans la fenetre
            //qui s'affiche à la fin du jeu
            // Les options varient en fonction de si le niveau est réussi
            //si le joueur veut abandonner ou s'il ne lui reste plus du temps
            if (d.reponse == 0) {
                env.setRoom(room);
                play = true;
                initialise = true;
            } else {
                play = false;
            }

        }
        System.out.println("Voulez-vous enregistrez vos parties réalisées");
        save = LectureClavier.lireOuiNon();
        if (save == true) {
            String file;
            s = profil.toString();
            System.out.println(s);
            System.out.println("Entrez le nom du fichier dans lequel vous voulez enregistrer vos données");
            file = LectureClavier.lireChaine();
            profil.sauvegarder("src/xml/" + file);
            
        }

        env.exit();

    }

}
