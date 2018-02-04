/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;
import env3d.Env;
import env3d.EnvObject;


/**
 *
 * @author diallo1
 */
public class Letter extends EnvObject{
    public char letter;

    public char getLetter() {
        return letter;
    }
    
    
    
    public Letter(char l, double x, double z){
        this.letter=l;
        this.setX(x);
        this.setY(1);
        this.setZ(z);
        setScale(1);
        if(l==' '){
            setTexture("models/cube/cube.png");
            setModel("models/cube/cube.obj");
        }else{
            setTexture("models/letter/"+l+".png");
            setModel("models/cube/cube.obj");
        }   
    }  
    
    public void ajoute_mot(Env env,Letter l,int i){
       
        env.removeObject(l);
        l.setX(2+i);
        l.setZ(50);
        l.setScale(1);
        env.addObject(l); 
    }
}
