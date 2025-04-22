
package parqueadero;

import javax.swing.JOptionPane;


public class ManejoListas 
{
    /*este metodo crea la lista de vehiculos por inicio o por final
    de acuerdo a la opcion del usuario, recuerden que 
    es excluyente*/
    public ListaSimple Crear(int op, ListaSimple LS)
    {
        String pl;//para la lectura del objeto
        int resp;//ingresar datos si o no
        Vehiculos objv;//puede ser object
        resp=JOptionPane.showConfirmDialog(null,
                "Ingresar vehículo en la lista?",
                "CREANDO LISTA",JOptionPane.YES_NO_OPTION);
        while(resp==JOptionPane.YES_NO_OPTION)//mientras se quiera ingresar datos en la lista
        {
           objv=new Vehiculos();//para sobreescribir 
           pl=Validaciones.LeerString("Ingrese placa:");
           objv=objv.IngresarDatos(pl);//obtenemos el objeto vehiculo
           if(op==1)//por inicio
           {
               LS.CrearPorInicio(objv);//se crea el nodo en la lista y se copia el vehiculo
           }
           else//si es por final
           {
               LS.CrearPorFinal(objv);//se crea el nodo en la lista y se copia el vehiculo
           }//fin si
            resp=JOptionPane.showConfirmDialog(null,
                "Ingresar otro vehículo en la lista?",
                "CREANDO LISTA",JOptionPane.YES_NO_OPTION);
        }//fin mientras
        
        return LS;//variable de retorno
    }//fin de crear
    
    
    
    /*este metodo crea la lista doble de vehiculos por inicio o por final
    de acuerdo a la opcion del usuario, recuerden que 
    es excluyente*/
    public ListaDoble Crear(int op, ListaDoble LD)
    {
        String pl;//para la lectura del objeto
        int resp;//ingresar datos si o no
        Vehiculos objv;//puede ser object
        resp=JOptionPane.showConfirmDialog(null,
                "Ingresar vehículo en la lista?",
                "CREANDO LISTA",JOptionPane.YES_NO_OPTION);
        while(resp==JOptionPane.YES_NO_OPTION)//mientras se quiera ingresar datos en la lista
        {
           objv=new Vehiculos();//para sobreescribir 
           pl=Validaciones.LeerString("Ingrese placa:");
           objv=objv.IngresarDatos(pl);//obtenemos el objeto vehiculo
           if(op==1)//por inicio
           {
               LD.CrearPorInicio(objv);//se crea el nodo en la lista y se copia el vehiculo
           }
           else//si es por final
           {
               LD.CrearPorFinal(objv);//se crea el nodo en la lista y se copia el vehiculo
           }//fin si
            resp=JOptionPane.showConfirmDialog(null,
                "Ingresar otro vehículo en la lista?",
                "CREANDO LISTA",JOptionPane.YES_NO_OPTION);
        }//fin mientras
        
        return LD;//variable de retorno
    }//fin de crear
    
    //metodo que copia el archivo a la lista doble, se admiten repeticiones en caso de tener datos la lista
    public ListaDoble CopiarArchivoLista(Archivos objArch, ListaDoble ld) 
    {      
        String cadena = "";
        try {
            //locales auxiliares para extraer la informacion del archivo
            String pl,TiVe, Mar, Co;
            int Mod;
            boolean Est;
           
            String Reg[];//para tomar la linea String como vector de datos y facilitar el trabajo con el registro
            //se abre el archivo modo lectura y se imprime el mensaje de apertura que retorna
            JOptionPane.showMessageDialog(null, "" + objArch.AbrirArchivoModoLectura("Vehiculos.txt"));
            //se invoca al metodo de leer registro con 6 atributos para el vector de la linea o registro del archivo plano 
            //se recibe el texto en Reg
            Reg = objArch.LeerRegistro(6);
            //mientras existan datos en el archivo
            while (Reg!=null) //mientras not EOF()
            {
               /*los datos del Reg que se obtiene del archivo plano de texto se 
                asignan a las variables auxiliares locales para su facil manejo 
                como posiciones del vector String*/
                pl = Reg[0];
                TiVe= Reg[1];
                Mar = Reg[2];
                Co = Reg[3];
                Mod= Integer.parseInt(Reg[4]);
                Est = Boolean.parseBoolean(Reg[5]);
                                
                Vehiculos objv;
                objv=new Vehiculos(pl,TiVe,Mar,Co,Mod,Est);
                
                ld.CrearPorFinal(objv);//se copia el registro a la lista doble
                                
                Reg = objArch.LeerRegistro(6);//se lee otro registro para terminar y salir del ciclo
            }//fin mientras  
            objArch.CerrarArchivoModoLectura();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "***Archivo leído y cerrado correctamente*****");
        }
        return ld;//se cambia, se retorna
     }//fin de copiar archivo a lista
    
}//fin clase manejo de listas

