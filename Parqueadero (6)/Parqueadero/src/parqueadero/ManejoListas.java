
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
    public ListaDoble vehiculosMismoColorUltimoMatriz(Object mat[][], int maxf, int maxc, 
                                                 Archivos objArch) {
    ListaDoble listaResultado = new ListaDoble();
    
    if(maxf == 0 || maxc == 0) return listaResultado;
    
    // Obtener color del último vehículo en matriz
    Vehiculos ultimo = (Vehiculos)mat[maxf-1][maxc-1];
    String colorBuscado = ultimo.getColor();
    
    // Buscar en archivo y agregar a lista
    try {
        objArch.AbrirArchivoModoLectura("Vehiculos.txt");
        String[] Reg;
        while((Reg = objArch.LeerRegistro(6)) != null) {
            if(Reg[3].equalsIgnoreCase(colorBuscado)) {
                Vehiculos v = new Vehiculos(Reg[0], Reg[1], Reg[2], Reg[3], 
                                          Integer.parseInt(Reg[4]), 
                                          Boolean.parseBoolean(Reg[5]));
                listaResultado.CrearPorFinal(v);
            }
        }
        objArch.CerrarArchivoModoLectura();
    } catch(Exception e) {
        JOptionPane.showMessageDialog(null, "Error buscando por color: " + e.getMessage());
    }
    
    return listaResultado;
}
    public String pasarListaSimpleAArchivo(ListaSimple lista, Archivos objArch, CRUDVehiculo objCrud) {
    if (lista.IsEmpty()) {
        return "La lista simple está vacía";
    }

    int grabados = 0;
    try {
        objArch.AbrirArchivoModoEscritura("Vehiculos.txt");
        Nodo actual = lista.getStart();
        
        while (actual != null) {
            Vehiculos v = (Vehiculos) actual.getDato();
            if (!objCrud.Buscar(objArch, v.getNroPlaca())) {
                objArch.EscribirRegistro(v.EstructuraReg());
                grabados++;
            }
            actual = actual.getSig();
        }
        
        return "Se grabaron " + grabados + " vehículos de la lista al archivo";
    } catch (Exception e) {
        return "Error al grabar: " + e.getMessage();
    } finally {
        objArch.CerrarArchivoModoEscritura();
    }
}
    public ListaSimple pasarArchivoAListaSimple(Archivos objArch) {
    ListaSimple lista = new ListaSimple();
    try {
        objArch.AbrirArchivoModoLectura("Vehiculos.txt");
        String[] Reg;
        
        while ((Reg = objArch.LeerRegistro(6)) != null) {
            Vehiculos v = new Vehiculos(
                Reg[0], Reg[1], Reg[2], Reg[3], 
                Integer.parseInt(Reg[4]), 
                Boolean.parseBoolean(Reg[5])
            );
            lista.CrearPorFinal(v);
        }
        
        objArch.CerrarArchivoModoLectura();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
    return lista;
}
public void crearListasPorAntiguedad(ListaSimple listaAntiguos, ListaSimple listaNuevos) {
    // 1. Crear matriz de prueba usando el método existente
    Object[][] mat = new Object[3][3];
    ManejoMatriz objManejoMat = new ManejoMatriz();
    mat = objManejoMat.PruebaEscritorio(mat);
    
    // 2. Procesar la matriz
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            Vehiculos v = (Vehiculos)mat[i][j];
            int modelo = v.getModelo();
            
            // 3. Clasificar los vehículos
            if (modelo > 1980 && modelo < 2014) {
                listaAntiguos.CrearPorFinal(v);
            } else if (modelo >= 2014 && modelo <= 2024) {
                listaNuevos.CrearPorFinal(v);
            }
        }
    }
    
    // 4. Preparar mensaje de resultado
    String mensaje = "=== Vehículos Clasificados ===\n\n";
    
    mensaje += "ANTIGUOS (1981-2013):\n";
    mensaje += listaAntiguos.IsEmpty() ? "No hay vehículos antiguos\n" : listaAntiguos.JuntarDesdeInicio();
    
    mensaje += "\nNUEVOS (2014-2024):\n";
    mensaje += listaNuevos.IsEmpty() ? "No hay vehículos nuevos\n" : listaNuevos.JuntarDesdeInicio();
    
    // 5. Mostrar el resultado directamente
    JOptionPane.showMessageDialog(null, mensaje);
}
  
public ListaSimple filtrarMotosDesdeArchivo(Archivos objArch) {
        ListaSimple lista = new ListaSimple();
        try {
            objArch.AbrirArchivoModoLectura("Vehiculos.txt");
            String[] registro;
            while((registro = objArch.LeerRegistro(6)) != null) {
                if(registro[1].equalsIgnoreCase("motocicleta")) {
                    Vehiculos moto = new Vehiculos(
                        registro[0], registro[1], registro[2], 
                        registro[3], Integer.parseInt(registro[4]), 
                        Boolean.parseBoolean(registro[5])
                    );
                    lista.CrearPorFinal(moto);
                }
            }
            objArch.CerrarArchivoModoLectura();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Error leyendo archivo");
        }
        
        if(lista.IsEmpty()) {
            JOptionPane.showMessageDialog(null, "No se encontraron motos");
        }
        
        return lista;
    }
}//fin clase manejo de listas
