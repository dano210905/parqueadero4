
package parqueadero;

/*
Matriz
-Matriz[][]: arreglo Objeto
-MaxF: entero
-MaxC: entero

+MatrizVerificar()
+ImprimirMatrizFilas()
+ImprimirMatrizColumnas
*/

public class Matriz 
{
    
     // Atributos propios y privados de la Matriz 
	 Object Mat[][];
         int F,C, MaxF , MaxC;
        
        
    //constructores    
    public Matriz() {
    }

    public Matriz(int F, int C) 
    {
        this.MaxF = F;
        this.MaxC = C;
        Mat=new Object[MaxF][MaxC];
     
    }

    public boolean MatrizVerificar(Object mat[][])
    {
     if(mat==null)
     {
         //if (mat.length == 0) 
             // Matriz vacía!! 
          return true;
     }
     else 
         // Matriz llena!!
	  return false;
     //fin si
    }//fin del método Matriz verificar
    
      
    public String JuntarMatrizFilas(Object matriz[][])
    {
       String texto="";//retorno   
      
       for(F=0;F<=(MaxF-1);F++)
        {
           for(C=0;C<=(MaxC-1);C++)
           {
	      texto=texto + matriz[F][C].toString()+"\n";
           }//Fin para C
        }//Fin para F
  
       return texto;
    }//Fin del Metodo Imprimir

    public String JuntarMatrizColumnas(Object matriz[][])
    {
       String texto="";//retorno   
  
       for(C=0;C<=(MaxC-1);C++)
        {
           for(F=0;F<=(MaxF-1);F++)
           {
	      texto=texto + matriz[F][C].toString()+"\n";
           }//Fin para F
        }//Fin para C
    
       return texto;
    }//Fin del Metodo Imprimir
    
    
}//fin clase matriz
