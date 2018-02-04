package management;

public class Chronometre {
    private long begin;
    private long end;
    private long current;
    private final int limite;
    
    
    public Chronometre(int limite) {
        //intialisation
        this.limite=limite;
        
    }
    
    public void start(){
        this.begin = System.currentTimeMillis();
    }
 
    public void stop(){
        this.end = System.currentTimeMillis();
    }
 
    public long getTime() {
        return this.end-this.begin;
    }
    
    public long getMilliseconds() {
        return this.end-this.begin;
    }
 
    public int getSeconds() {
        return (int) ((this.end - this.begin) / 1000.0);
    }
 
    public double getMinutes() {
        return (this.end - this.begin) / 60000.0;
    }
 
    public double getHours() {
        return (this.end - this.begin) / 3600000.0;
    }
    
    /**
    * Method to know if it remains time.
     * @return 
    */
    
    public boolean remainsTime() {
        current = System.currentTimeMillis();
        int timeSpent;
        timeSpent = (int) ((current-this.begin)/1000.0);
        return (timeSpent<=this.limite);
    }
    
     public int compteARebours() {
        current = System.currentTimeMillis();
        int timeSpent;
        timeSpent = (int) ((current-this.begin)/1000.0);
        return limite-timeSpent;
    }
     
}
