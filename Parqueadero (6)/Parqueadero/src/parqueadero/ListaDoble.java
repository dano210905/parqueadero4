
package parqueadero;

import javax.swing.JOptionPane;


public class ListaDoble 
{
    //atributos propios y privados
    private Nodo Start, End;

    //constructor vacio
    public ListaDoble() 
    {
        Start=End=null;//condiciones iniciales de lista vacia
    }

     /*auxiliares, la q se va a usar para recorridos y la 
    p SOLO PARA EL METODO BUSCAR*/
    Nodo q,p;
    
    /*metodo que retorna verdadero si la lista esta vacia o 
    falso en caso contrario*/
    public boolean IsEmpty()
    {
        if(Start==null)
            return true;
        else
            return false;
        //fin si
    }//fin is empty
    
public void CrearPorFinal(Object info)
{
if (IsEmpty()==false)
{
   getEnd().setSig(new Nodo(End,info, null));  
   setEnd(getEnd().getSig()); 
}else
{
   Start = End = new Nodo(null,info, null); 
}//Fin si
}//fin de insertar nodo por final

    
public void CrearPorInicio(Object info)
{
if (IsEmpty()==false)
{
    setStart(new Nodo(null,info,getStart())); 
    getStart().getSig().setAnt(getStart()); 
}else
{
      Start = End = new Nodo(null,info, null); 
}//Fin si
    
}//fin de insertar nodo por inicio
   
/*

*/
public String JuntarDesdeStart()
{
String texto="";//variable del retorno
if(IsEmpty()==false)
{
q=getStart();
while(q!=null)
{
	texto=texto+q.getDato()+"\n";
     	q=q.getSig();//adelantar en la lista
}//fin mientras
}//fin si
return texto;
}//fin juntar desde inicio
  
    
public String JuntarDesdeEnd()
{
String texto="";//variable del retorno
if(IsEmpty()==false)
{
q=getEnd();
while(q!=null)
{
	texto=texto+q.getDato()+"\n";
     	q=q.getAnt();//al anterior en la lista
}//fin mientras
}//fin si
return texto;
}//fin juntar desde final



public void InsertarStart(Object d){
    
    if (IsEmpty()==false) {
        setStart(new Nodo(null,d,getStart()));
        getStart().getSig().setAnt(getStart());
        
    }// fin si
}// fin insertarStart

public void InsertarEnd(Object d){
    
    if (IsEmpty()==false) {
        setEnd(new Nodo(getEnd(),d,null));
        getEnd().getAnt().setSig(getEnd());
        
    }// fin si
}// fin insertarStart

public Object LiberarStart(){
    
    Object d=null;
    if (IsEmpty()==false) {
        d=getStart().getDato();
        if (getStart().getSig()==null) {
            getStart().finalize();
            Start=End=null;
            
        }else{
            setStart(getStart().getSig());
            getStart().getAnt().finalize();
            getStart().setAnt(null);
        }//fin si
        
        
    }// fin si
    return d;
}// fin LiberarStart

public Object LiberarEnd(){
    
    Object d=null;
    if (IsEmpty()==false) {
        d=getEnd().getDato();
        if (getEnd().getAnt()==null) {
            getEnd().finalize();
            Start=End=null;
            
        }else{
            setEnd(getEnd().getAnt());
            getEnd().getSig().finalize();
            getEnd().setSig(null);
        }//fin si
        
        
    }// fin si
    return d;
}// fin LiberarStart

public boolean buscar(String pl) {
    if (!IsEmpty()) {
        Nodo p = getStart();
        Nodo t = null;
        String pla = ((Vehiculos) p.getDato()).getNroPlaca();

        while (p != null && !pla.equalsIgnoreCase(pl)) {
            t = p;                // t se queda atrás de p
            p = p.getSig();       // p avanza en la lista

            if (p != null) {
                pla = ((Vehiculos) p.getDato()).getNroPlaca();
            }
        }

        return p != null; // Si p no es null, encontramos la placa
    }

    return false;
}

public void InsertarAntes(String nuevaPlaca, String placaReferencia) {
    Nodo p = getStart();

    while (p != null && !((Vehiculos) p.getDato()).getNroPlaca().equalsIgnoreCase(placaReferencia)) {
        p = p.getSig();
    }

    if (p != null) {
        Nodo nuevo = new Nodo(new Vehiculos(nuevaPlaca));
        nuevo.setSig(p);
        nuevo.setAnt(p.getAnt());

        if (p.getAnt() != null) {
            p.getAnt().setSig(nuevo);
        } else {
            setStart(nuevo); // es el nuevo primero
        }

        p.setAnt(nuevo);
    } else {
        JOptionPane.showMessageDialog(null, "❌ Placa de referencia no encontrada.");
    }
}

public void InsertarDespues(String nuevaPlaca, String placaReferencia) {
    Nodo p = getStart();

    while (p != null && !((Vehiculos) p.getDato()).getNroPlaca().equalsIgnoreCase(placaReferencia)) {
        p = p.getSig();
    }

    if (p != null) {
        Nodo nuevo = new Nodo(new Vehiculos(nuevaPlaca));
        nuevo.setAnt(p);
        nuevo.setSig(p.getSig());

        if (p.getSig() != null) {
            p.getSig().setAnt(nuevo);
        } else {
            setEnd(nuevo); // es el nuevo último
        }

        p.setSig(nuevo);
    } else {
        JOptionPane.showMessageDialog(null, "❌ Placa de referencia no encontrada.");
    }
}

public void LiberarDato(String placa) {
    Nodo p = getStart();

    while (p != null && !((Vehiculos) p.getDato()).getNroPlaca().equalsIgnoreCase(placa)) {
        p = p.getSig();
    }

    if (p != null) {
        if (p.getAnt() != null) {
            p.getAnt().setSig(p.getSig());
        } else {
            setStart(p.getSig()); // eliminando el primero
        }

        if (p.getSig() != null) {
            p.getSig().setAnt(p.getAnt());
        } else {
            setEnd(p.getAnt()); // eliminando el último
        }

        p.setAnt(null);
        p.setSig(null);
        p = null;

        JOptionPane.showMessageDialog(null, "✅ Placa eliminada correctamente.");
    } else {
        JOptionPane.showMessageDialog(null, "❌ Placa no encontrada.");
    }
}






    public Nodo getStart() {
        return Start;
    }
    

    public void setStart(Nodo Start) {
        this.Start = Start;
    }

    public Nodo getEnd() {
        return End;
    }

    public void setEnd(Nodo End) {
        this.End = End;
    }
    
    
    
    
    
    
    
}
