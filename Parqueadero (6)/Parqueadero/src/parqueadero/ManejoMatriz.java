
package parqueadero;

import javax.swing.JOptionPane;


public class ManejoMatriz 
{
    
    /*metodo que ingresa los datos en la matriz vehiculos, lo elaboramos
    por aca en esta clase porque no es propio de la clase matriz, debido a
    que se crean matrices de diferentes tipos*/
    public Object [][] LlenarMatriz(Object mat[][], int MaxF, int MaxC)
    {
    int F,C;//para el manejo de filas y columnas
    String Idp;
    //objeto vehiculo para el ingreso de datos
    Vehiculos objv=new Vehiculos();
        
    // Si la Matriz No está llena, se puede agregar datos
    //se esta llenando por filas   
    for(F=0;F<=(MaxF-1);F++)//ciclo filas
        {
           for(C=0;C<=(MaxC-1);C++)//ciclo columnas
           {
              //se pide la placa para cada objeto 
              Idp=Validaciones.LeerString("Digite placa del vehiculo: ");  
              //se recibe el objeto vehiculo del metodo ingresar y se asigna en las posiciones de la matriz
              mat[F][C]=objv.IngresarDatos(Idp);
           }//Fin para C
        }// Fin para F
  
    return mat;
    }//Fin del Metodo LLenarMatriz

    /*Metodo que retorna los vehiculos de la diagonal principal en caso 
    de ser cuadrada*/
    public String JuntarDiagonalPpal(Object mat[][],int maxf, int maxc)
    {
        //variable local de retorno
        String texto="";
        int i;
        if(maxf==maxc)//si es cuadrada
        {
            for(i=0;i<=maxc-1;i++)//ciclo de recorrido para diagonal
            //for(i=0;i<maxc;i++)    
            {
               texto=texto+mat[i][i].toString()+"\n"; 
            }//fin para i
        }
        else
        {
            JOptionPane.showMessageDialog(null,"La matriz NO es cuadrada no puede mostrar diagonal");
        }//fin si
        return texto;      
        
    }//fin de diagonal ppal
    
     /*Metodo que retorna los vehiculos de la diagonal secundaria en caso 
    de ser cuadrada*/
    public String JuntarDiagonalSec(Object mat[][],int maxf, int maxc)
    {
        //variable local de retorno
        String texto="";
        int i;
        if(maxf==maxc)//si es cuadrada
        {
            for(i=0;i<=maxc-1;i++)//ciclo de recorrido para diagonal
            //for(i=0;i<maxc;i++)    
            {
               texto=texto+mat[i][maxc-i-1].toString()+"\n"; 
            }//fin para i
        }
        else
        {
            JOptionPane.showMessageDialog(null,"La matriz NO es cuadrada no puede mostrar diagonal");
        }//fin si
        return texto;      
        
    }//fin de diagonal secundaria
    
    /*metodo que retorna el vehiculo mas antiguo*/
    public Object BuscarMasAntiguo(Object mat[][], int maxf, int maxc)
    {
        //objeto para manejar el vehiculo
        Vehiculos objVehiculo=new Vehiculos();
        int MasViejo=0,i,j;
        //recorrido por columnas de la matriz para buscar el mas viejo
        for(i=0;i<=maxf-1;i++)//ciclo filas
        {
            for(j=0;j<=maxc-1;j++)//ciclo columnas
            {
                if(i==0 && j==0)//solo ingresa una vez
                {//se inicializa con el primer dato ingresado fila 0 y columna 0
                    MasViejo=((Vehiculos)mat[i][j]).getModelo();
                    objVehiculo=(Vehiculos)mat[i][j];
                }
                else//si ya ingreso una vez
                {
                    //condicion para el mayor
                    if(MasViejo>((Vehiculos)mat[i][j]).getModelo())
                    {//se actualiza con la posicion de la matriz en curso
                        MasViejo=((Vehiculos)mat[i][j]).getModelo();
                        objVehiculo=(Vehiculos)mat[i][j];
                    }//fin si
                }//fin si
            }//fin para i
        }//fin para j
        return objVehiculo;
    }//fin mas antiguo
    
    /*metodo que retorna el vehiculo mas nuevo*/
    public Object BuscarMasNuevo(Object mat[][], int maxf, int maxc)
    {
        //objeto para manejar el vehiculo
        Vehiculos objVehiculo=new Vehiculos();
        int MasNuevo=0,i,j;
        //recorrido por columnas de la matriz para buscar el mas nuevo
        for(i=0;i<=maxf-1;i++)//ciclo filas
        {
            for(j=0;j<=maxc-1;j++)//ciclo columnas
            {
                if(i==0 && j==0)//solo ingresa una vez
                {//se inicializa con el primer dato ingresado fila 0 y columna 0
                    MasNuevo=((Vehiculos)mat[i][j]).getModelo();
                    objVehiculo=(Vehiculos)mat[i][j];
                }
                else//si ya ingreso una vez
                {
                    //condicion para el mas nuevo
                    if(MasNuevo<((Vehiculos)mat[i][j]).getModelo())
                    {//se actualiza con la posicion de la matriz en curso
                        MasNuevo=((Vehiculos)mat[i][j]).getModelo();
                        objVehiculo=(Vehiculos)mat[i][j];
                    }//fin si
                }//fin si
            }//fin para i
        }//fin para j
        return objVehiculo;
    }//fin mas nuevo
    
/*este metodo recibe la matriz, su tamaño y la placa a buscar y retorna 
    verdadero en caso de encontrar el vehiculo o falso si no lo encuentra*/    
public boolean Buscar(Object mat[][], int f, int c, String pl)
{
boolean sw=false;
int i,j;
for(i=0;i<=f-1;i++)//ciclo filas
        {
            for(j=0;j<=c-1;j++)//ciclo columnas
            {
             if(pl.equalsIgnoreCase(((Vehiculos)mat[i][j]).getNroPlaca()))
		sw=true;
	     //fin si
            }//fin para j
        }//fin para i 	
return sw;
}//fin buscar

//Mostrar un vehículo específico
public Object BuscarVehiculoEspecifico(Object mat[][],int f,int c, String pl)
{
Object objv=null;//variable local de retorno
if(Buscar(mat,f,c,pl)==true)//si vehiculo esta en la matriz
{
int i,j;
for(i=0;i<=f-1;i++)//ciclo filas
        {
            for(j=0;j<=c-1;j++)//ciclo columnas
            {
             if(pl.equalsIgnoreCase(((Vehiculos)mat[i][j]).getNroPlaca()))
		objv=mat[i][j];
	     //fin si
            }//fin para j
        }//fin para i 
}
else
         JOptionPane.showMessageDialog(null,"El vehículo NO esta almacenado en la matriz");	
//Fin si
return objv;
}//fin buscar especifico               

/*este metodo graba la matriz al archivo, OJO se debe tener en cuenta que en el archivo 
NO se admiten repeticiones pero en la matriz sí, retorna verdadero si se grabo algo de la 
matriz al archivo y retorna falso si no se grabó nada, puede ser porque ya todo estaba
repetido o porque la matriz o el archivo esten vacios*/
public String GrabarMatriz(Object mat[][], int f, int c, Archivos objArch, CRUDVehiculo objCrud) {
    if(mat == null || f <= 0 || c <= 0 || objArch == null || objCrud == null) {
        return "Error: Datos inválidos para copiar matriz";
    }
    
    boolean grabadoAlgo = false;
    try {
        for(int i = 0; i < f; i++) {
            for(int j = 0; j < c; j++) {
                Vehiculos v = (Vehiculos)mat[i][j];
                if(v != null && !objCrud.Buscar(objArch, v.getNroPlaca())) {
                    objCrud.GrabarVehiculo(objArch, v);
                    grabadoAlgo = true;
                }
            }
        }
        return grabadoAlgo ? "Matriz copiada al archivo exitosamente" 
                         : "No se grabaron vehículos nuevos (posiblemente repetidos)";
    } catch(Exception e) {
        return "Error al copiar matriz: " + e.getMessage();
    }
}

// Eliminar el método copiarMatrizAArchivo ya que ahora usamos GrabarMatriz directamente
public void copiarMatrizAArchivo(Object mat[][], int f, int c, Archivos objArch, CRUDVehiculo objCrud) {
    if(mat == null || objArch == null || objCrud == null) return;
    
    for(int i = 0; i < f; i++) {
        for(int j = 0; j < c; j++) {
            Vehiculos v = (Vehiculos)mat[i][j];
            if(v != null && !objCrud.Buscar(objArch, v.getNroPlaca())) {
                objCrud.GrabarVehiculo(objArch, v);
            }
        }
    }
}
public void copiarDosRegistrosAMatriz(Archivos objArch, Object mat[][], int f, int c) {
    try {
        objArch.AbrirArchivoModoLectura("Vehiculos.txt");
        String[] primerReg = objArch.LeerRegistro(6);
        String[] ultimoReg = primerReg;
        String[] temp;
        
        while((temp = objArch.LeerRegistro(6)) != null) {
            ultimoReg = temp;
        }
        
        if(primerReg != null) {
            mat[0][0] = new Vehiculos(primerReg[0], primerReg[1], primerReg[2], 
                                     primerReg[3], Integer.parseInt(primerReg[4]), 
                                     Boolean.parseBoolean(primerReg[5]));
            
            mat[f-1][c-1] = new Vehiculos(ultimoReg[0], ultimoReg[1], ultimoReg[2], 
                                         ultimoReg[3], Integer.parseInt(ultimoReg[4]), 
                                         Boolean.parseBoolean(ultimoReg[5]));
        }
    } catch(Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    } finally {
        objArch.CerrarArchivoModoLectura();
    }
}
public void contarVehiculosMismoColor(Archivos objArch, Object mat[][], int f, int c) {
    try {
        objArch.AbrirArchivoModoLectura("Vehiculos.txt");
        String[] primerReg = objArch.LeerRegistro(6);
        if(primerReg == null) return;
        
        String colorPrimero = primerReg[3];
        int contador = 0;
        StringBuilder lista = new StringBuilder();
        
        for(int i = 0; i < f; i++) {
            for(int j = 0; j < c; j++) {
                Vehiculos v = (Vehiculos)mat[i][j];
                if(v != null && v.getColor().equalsIgnoreCase(colorPrimero)) {
                    contador++;
                    lista.append(v.getNroPlaca()).append("\n");
                }
            }
        }
        
        JOptionPane.showMessageDialog(null, "Total: " + contador + "\n\n" + lista.toString());
    } catch(Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    } finally {
        objArch.CerrarArchivoModoLectura();
    }
}
 
    
    
    
    //prueba de escritorio, para no tener que estar ingresando siempre
    
    public Object[][] PruebaEscritorio(Object mat[][])//para matriz de vehiculos de 3*3
    {
        mat[0][0]=new Vehiculos("WYT500","Automóvil","Audi","Rojo",2023,true);
    	mat[0][1]=new Vehiculos("KBP300","Motocicleta","Bentley","Azul",2010,false);
        mat[0][2]=new Vehiculos("RZK508","Camioneta","RENAULT","Rojo",2019,true);
  
        mat[1][0]=new Vehiculos("FBS100","Automóvil","Porsche","Negro",2000,true);
        mat[1][1]=new Vehiculos("RZX280","Camioneta","TOYOTA","Blanco",1994,true);
       	mat[1][2]=new Vehiculos("RZE900","Microbus","CHEVROLET","Gris",2024,true);
    
        mat[2][0]=new Vehiculos("JYT281","Automóvil","Volkswagen","Rojo",2025,true);
        mat[2][1]=new Vehiculos("KBV300","Campero","Skoda","Gris",2010,false);
        mat[2][2]=new Vehiculos("KAK000","Automóvil","KIA","Negro",2010,true);
   
        return mat;
      
    }//fin de prueba de escritorio

}//fin de manejo de matriz
