/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LanceurDeJeu;

import env3d.Env;

/**
 *
 * @author diallo1
 */
public class Room {

    public int depth;
    public int height;
    public int width;
    public String textureBottom;
    public String textureNorth;
    public String textureEast;
    public String textureWest;
    public String textureTop;
    public String textureSouth;

    public Room() {
        depth = 50;
        height = 50;
        width = 45;
        textureBottom = "textures/img6.jpg";
        textureNorth = "textures/marble.png";
        textureEast = "textures/marble.png";
        textureWest = "textures/marble.png";
    }
    
    public Room Room1(Room r) {
        r.depth = 50;
        r.height = 50;
        r.width = 45;
        r.textureBottom = "textures/img4.jpg";
        r.textureNorth = "textures/skybox/holodeck/north.png";
        r.textureEast = "textures//skybox/holodeck/east.png";
        r.textureWest = "textures//skybox/holodeck/west.png";
        return r;
    }
    
    public Room Room2(Room r) {
        r.depth = 50;
        r.height = 50;
        r.width = 45;
        r.textureBottom = "textures/img2.jpg";
        r.textureNorth = "textures/wall/mur1.jpg";
        r.textureEast = "textures/wall/mur1.jpg";
        r.textureWest = "textures/wall/mur1.jpg";
        return r;
    }  

    public Room Room3(Room r) {
        r.depth = 50;
        r.height = 50;
        r.width = 45;
        r.textureBottom = "textures/img3.jpg";
        r.textureNorth = "textures/wall/granite.png";
        r.textureEast = "textures/wall/granite.png";
        r.textureWest = "textures/wall/granite.png";
        return r;
    }  
    public int getWidth() {
        return width;
    }

    public int getDepth() {
        return depth;
    }

    public int getHeight() {
        return height;
    }
    

}
