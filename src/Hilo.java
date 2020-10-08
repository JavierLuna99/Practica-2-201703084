//Javier Enrique Luna Díaz
package condicionescompetencias;
import javax.swing.JTextArea;

public class Hilo extends Thread {
    private JTextArea area;
    private RCompartido rc;
    private boolean pausa;
    private boolean detener;
    
    Hilo(JTextArea area, RCompartido rc){
        this.area = area;
        this.rc = rc;
        this.pausa = false;
        this.detener = false;
    }
    
    public synchronized void pausar(){
        pausa = true;
    }
    
    public synchronized void reanudar(){
        pausa = false;
        this.notify();
    }
    
    public synchronized void parar(){
        detener = true;
    }
    
    public void run(){
        while(!this.detener){
            try{
                synchronized(this){
                    if (pausa){
                        wait();
                    }
                }
                rc.setDatoCompartido(this.getName());
                area.append(rc.getDatoCompartido()+ "\n");
                sleep(2000);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
