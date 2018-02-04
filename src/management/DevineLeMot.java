/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import LanceurDeJeu.Room;
import LanceurDeJeu.Tux;
import env3d.Env;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Mamadou Dian Diallo
 */
public class DevineLeMot {

    private final Env env;
    private final Tux tux;
    private final ArrayList<Letter> letters;
    private int nbLettresRestantes;
    private int temps;
    private final Chronometre chrono;
    public int reponse = 2;

    public char personnage;

    String message = "";
    char c = choix_personnage();
    public DevineLeMot(String mot, Env env, Room room) {
             
        tux = new Tux(20, 20);
        if(c=='a' || c=='A'){
            tux.army(tux);
        }else if(c=='k' || c=='K'){
            tux.kungfu(tux);
        }else if(c=='n' || c=='N'){
            tux.ninja(tux);
        }
        
        chrono = new Chronometre(60);
        letters = new ArrayList<Letter>();
        nbLettresRestantes = mot.length();
        double xl, zl;
        Letter l;
        for (int i = 0; i < mot.length(); i++) {

            xl = (Math.random() * (room.getWidth() -15))+3;
            zl = (Math.random() * (room.getDepth() - 15))+2;
                     
            l = new Letter(mot.charAt(i), xl, zl);
            while ((distance(tux, l)) < 4) {
               xl = (Math.random() * (room.getWidth() -15))+3;
               zl = (Math.random() * (room.getDepth() - 15))+2;
            }                     
            
            l = new Letter(mot.charAt(i), xl,zl);

            letters.add(l);
            env.addObject(l);
            message += mot.charAt(i);
        }

        this.env = env;

        // Sets up the camera
        env.setCameraXYZ(22.5, 31, 80);
        env.setCameraPitch(-30);
        // Turn off the default controls
        env.setDefaultControl(false);

    }
    public Tux getTux() {
        return tux;
    }

    public void checkUserKey(Env env){

        tux.move(env,env.getKeyDown());
    }

    public ArrayList<Letter> getLetters() {
        return letters;
    }
    
    

    public void jouer() {
        Letter letter;
        int i = 0;
        env.start();    
        
        // Insert Tux
        env.addObject(tux);
        // Start chrono
        chrono.start();

        // play the sound
        env.soundLoop("sounds/mario1.wav");
        
        // The main game loop
        do {
            
            // Ask for user input, check if it collides and remove letters if necessary
            
            checkUserKey(env);
            letter = tuxMeetsLetter();
            
            if (collision(tux, letters.get(0))) {
                tux.faire_sauter_tux(env);
                tux.setX(letters.get(0).getX());
                tux.setZ(letters.get(0).getZ());
                
                env.soundPlay("sounds/clapping.wav");
                letters.remove(0);
                nbLettresRestantes--;
                letter.ajoute_mot(env, letter, i);
                i = i + 2;
                env.soundStop("sounds/clapping.wav");

            }
            // Update display
            env.advanceOneFrame();

            env.setDisplayStr("Compte à rebours : " + chrono.compteARebours() + "s", 0, 40);
        } while (!(env.getKey() == 1) && !(letters.isEmpty()) && (chrono.remainsTime()));

        env.soundStop("sounds/mario1.wav");
        chrono.stop();

        temps = chrono.getSeconds();

        //Post-Process: game is finished
        //we have to keep the data to save our score (chrono, temps, nbLettresRestantes) 
        if (env.getKey() == 1) {
            message_escape();
        } else if (nbLettresRestantes == 0) {
            message_if_win();
        } else {
            message_if_not_remains_time();
        }

    }

    private Letter tuxMeetsLetter() {
        if (!letters.isEmpty()) {
            Letter letter = letters.get(0);
            if (collision(tux, letter)) {
                return letter;
            }

        }
        return null;
    }
    
    private char choix_personnage(){
        System.out.println("Veuillez choisir un joueur\nT pour sélectionner le tux\nK pour sélectionner le kung fu\n"
                + "A pour sélectionner le militaire\nN pour sélectionner le ninja");
        char c=LectureClavier.lireChar();
        while ( ((c != 'a' && c!='t') && (c!='k' && c!= 'n')) && ((c!='A' && c!='N') && (c!='K' && c!='T')) ){
            System.out.println("Veuillez choisir un joueur\nT pour sélectionner le tux\n"
                +"K pour sélectionner le kung fu\n"
                +"A pour sélectionner le militaire\n"
                +"N pour sélectionner le ninja");
            c=LectureClavier.lireChar();
        }
        return c;
    }
    
    
    private double distance(Tux tux, Letter letter) {
        return tux.distance(letter);
    }

    private boolean collision(Tux tux, Letter letter) {
        return (distance(tux, letter) < 3);
    }

    public int getNbLettresRestantes() {
        return nbLettresRestantes;
    }

    public int getTemps() {
        return temps;
    }

    public void message_escape() {

        if (getNbLettresRestantes() == message.length()) {
            reponse = JOptionPane.showConfirmDialog(null, "Aucune lettre trouvée",
                    "Voulez-vous quitter ?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (reponse == 0) {
                reponse = 1;
            } else {
                reponse = 0;
            }
        } else {
            if(getNbLettresRestantes() == (message.length()-1) ){
                reponse = JOptionPane.showConfirmDialog(null, "Il vous reste " + getNbLettresRestantes() + " lettre",
                    "Voulez-vous quitter ?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            }else{
                reponse = JOptionPane.showConfirmDialog(null, "Il vous reste " + getNbLettresRestantes() + " lettres",
                    "Voulez-vous quitter ?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            }
            
            if (reponse == 0) {
                reponse = 1;
            } else {
                reponse = 0;
            }
        }
    }

    public void message_if_win() {
        reponse = JOptionPane.showConfirmDialog(null, "Partie Gagnée !\nTemps effectué : " + getTemps() + "s",
                "Voulez-vous rejouer ?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

    }

    public void message_if_not_remains_time() {
        reponse = JOptionPane.showConfirmDialog(null, "Perdu !!\nIl ne vous reste plus de temps",
                "Voulez-vous rejouer", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
    }

}
