/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LanceurDeJeu;

import env3d.Env;
import env3d.EnvObject;

import static org.lwjgl.input.Keyboard.*;

/**
 *
 * @author diallo1
 */
public class Tux extends EnvObject {

    private double volume;

    public Tux(double x, double z) {
        setX(x);
        setY(3);
        setZ(z);
        volume = 0;
        setScale(2);
        setTexture("models/tux/tux.png");
        setModel("models/tux/tux.obj");
    }
    
    public void army(Tux t) {      
        setTexture("models/boxDude/army.png");
        setModel("models/boxDude/box-dude.obj");
    }
    
    public void kungfu(Tux t) {      
        setTexture("models/boxDude/kungfu.png");
        setModel("models/boxDude/box-dude.obj");
    }
    
    public void ninja(Tux t) {      
        setTexture("models/boxDude/ninja.png");
        setModel("models/boxDude/box-dude.obj");
    }
    
    

    public void move(Env env, int currentkey) {
     
        switch (currentkey) {
            case KEY_UP:
                this.setRotateY(180);
                if (this.getZ() > 3) {
                    this.setZ(this.getZ() - 0.3);
                }

                break;
            case KEY_DOWN:
                this.setRotateY(0);
                if (this.getZ() <  50 - 3) {
                    this.setZ(this.getZ() + 0.3);

                }

                break;
            case KEY_LEFT:
                this.setRotateY(-90);
                if (this.getX() > 3) {
                    this.setX(this.getX() - 0.3);
                }

                break;
            case KEY_RIGHT:
                this.setRotateY(90);
                if (this.getX() < 45 - 3) {
                    this.setX(this.getX() + 0.3);
                }

                break;
            case KEY_ADD:
                if (volume <= 49.5) {
                    volume = volume + 0.5;
                    env.setVolume(volume);
                    volume = volume * 100;
                    volume = (int) volume;
                    volume = volume / 100;
                    env.setDisplayStr("Volume : " + volume, 300, 40);                           
                }

                break;
            case KEY_SUBTRACT:
                if (volume >= 0.5) {
                    volume = volume - 0.5;
                    env.setVolume(volume);
                    volume = volume * 100;
                    volume = (int) volume;
                    volume = volume / 100;
                    env.setDisplayStr("Volume : " + volume, 300, 40);
                    
                }
                break;
            case KEY_SPACE:
                faire_sauter_tux(env);
                break;
            case KEY_M:
                env.setDisplayStr("Mute", 300, 40);
                env.setVolume(0);
                break;
            case KEY_HOME:
                this.setRotateY(0);
                this.setZ(3);
                this.setX(3);
                break;
            case KEY_END:
                this.setRotateY(0);
                this.setZ(47);
                this.setX(42);
                break;
                

        }
    }

    public void faire_sauter_tux(Env e) {
        int i;
        for (i = 0; i < 5; i++) {
            setY(getY() + 1);
            e.advanceOneFrame();
        }
        for (i = 0; i < 5; i++) {
            setY(getY() - 1);
            e.advanceOneFrame();
        }
        
    }

}
