/*
----------------
      Nodo 
----------------
-Dato: objeto
- sig: Nodo
- ant: Nodo
----------------
Nodo()
Nodo(d)
Nodo(d,next)
Nodo(li,d,ld)
----------------

*/

package parqueadero;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Nodo 
{
    
    //atributos propios de la clase
    private Object dato;
    private Nodo sig, ant;
    
    //constructor vacio
    public Nodo()
    {} 
    //fin método Nodo

    /*constructor que recibe el dato 
    y copia al nodo en su parte dato la informacion
     y coloca la liga o enlace en null*/
    public Nodo(Object d)
    {
        dato=d;
        sig=null;//siempre los nodos terminales
        
    }//fin método Nodo

     /*constructor que recibe el dato y en enlace 
    siguiente y copia el nodo en su parte dato la informacion
     y coloca la liga o enlace en la dirección de 
    memoria que recibe*/
    public Nodo(Object d, Nodo s) {
        this.dato = d;
        this.sig = s;
    }

    /*este constructor lo usaremos para la ingresar los datos en
    las tres partes del nodo doble*/
    
    public Nodo(Nodo li, Object info, Nodo ld )
    {
        ant=li;
        dato=info;
        sig=ld;
    }
        
    
    @Override
    public String toString() {
        return dato.toString();
    }
    
    
     //destructor ojo, este metodo finalize se  genera, es el inicio de un destructor
    public void finalize()
    {   try {
        super.finalize();
        } catch (Throwable ex) {
            Logger.getLogger(Nodo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//fin finalize

    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public Nodo getSig() {
        return sig;
    }

    public void setSig(Nodo sig) {
        this.sig = sig;
    }

    public Nodo getAnt() {
        return ant;
    }

    public void setAnt(Nodo ant) {
        this.ant = ant;
    }
    
    
}//fin clase nodo
