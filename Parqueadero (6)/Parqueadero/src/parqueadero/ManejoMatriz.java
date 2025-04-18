
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
public String copiarDosRegistrosAMatriz(Archivos objArch, Object[][] mat, int filas, int columnas) {
    // Validación de matriz
    if (mat == null || filas <= 0 || columnas <= 0) {
        return "Matriz no inicializada correctamente";
    }

    try {
        // Abrir archivo
        String mensaje = objArch.AbrirArchivoModoLectura("Vehiculos.txt");
        if (mensaje.contains("Error")) {
            return "No se pudo abrir el archivo";
        }

        // Leer registros
        String[] primerRegistro = objArch.LeerRegistro(6);
        String[] ultimoRegistro = primerRegistro;
        String[] registroActual;

        // Buscar el último registro
        while ((registroActual = objArch.LeerRegistro(6)) != null) {
            ultimoRegistro = registroActual;
        }

        // Validar que existen registros
        if (primerRegistro == null || ultimoRegistro == null) {
            return "El archivo está vacío o no tiene registros válidos";
        }

        // Copiar a matriz (esquina superior izquierda e inferior derecha)
        mat[0][0] = crearVehiculoDesdeRegistro(primerRegistro);
        mat[filas-1][columnas-1] = crearVehiculoDesdeRegistro(ultimoRegistro);

        return "Se copiaron exitosamente:\n" +
               "1. " + primerRegistro[0] + " en [0][0]\n" +
               "2. " + ultimoRegistro[0] + " en [" + (filas-1) + "][" + (columnas-1) + "]";

    } catch (Exception e) {
        return "Error durante la copia: " + e.getMessage();
    } finally {
        try {
            objArch.CerrarArchivoModoLectura();
        } catch (Exception e) {
            System.err.println("Error al cerrar archivo: " + e.getMessage());
        }
    }
}
// Método auxiliar para crear vehículos

// En ManejoListas.java
public String buscarVehiculosMismoColor(Object mat[][], int maxf, int maxc, Archivos objArch) {
    StringBuilder resultado = new StringBuilder();
    int contador = 0;
    
    if(maxf == 0 || maxc == 0) return "Matriz vacía";
    
    // Obtener color del último vehículo
    Vehiculos ultimo = (Vehiculos)mat[maxf-1][maxc-1];
    String colorBuscado = ultimo.getColor();
    resultado.append("Vehículos con color ").append(colorBuscado).append(":\n");
    
    try {
        objArch.AbrirArchivoModoLectura("Vehiculos.txt");
        String[] Reg;
        while((Reg = objArch.LeerRegistro(6)) != null) {
            if(Reg[3].equalsIgnoreCase(colorBuscado)) {
                resultado.append("- ").append(Reg[0]).append(" (").append(Reg[2]).append(")\n");
                contador++;
            }
        }
        objArch.CerrarArchivoModoLectura();
    } catch(Exception e) {
        return "Error buscando: " + e.getMessage();
    }
    
    if(contador == 0) {
        return "No hay vehículos con color " + colorBuscado + " en el archivo";
    }
    return resultado.append("Total encontrados: ").append(contador).toString();
}
// En ManejoMatriz.java
public String promedioModelosDiagonal(Object mat[][], int maxf, int maxc) {
    if(mat == null || maxf == 0 || maxc == 0) return "Matriz vacía";
    
    double suma = 0;
    int contador = 0;
    String tipoCalculo;
    
    if(maxf == maxc) { // Diagonal principal
        tipoCalculo = "diagonal principal";
        for(int i = 0; i < maxf; i++) {
            if(mat[i][i] != null) {
                Vehiculos v = (Vehiculos)mat[i][i];
                suma += v.getModelo();
                contador++;
            }
        }
    } else { // Primera fila
        tipoCalculo = "primera fila";
        for(int j = 0; j < maxc; j++) {
            if(mat[0][j] != null) {
                Vehiculos v = (Vehiculos)mat[0][j];
                suma += v.getModelo();
                contador++;
            }
        }
    }
    
    if(contador == 0) return "No hay vehículos para calcular";
    
    return String.format("Promedio de modelos (%s): %.2f", tipoCalculo, (suma/contador));
}
public String porcentajeAutomoviles(Object[][] mat, int maxf, int maxc, Archivos objArch, CRUDVehiculo objCrud) {
    // Validación de matriz
    if(mat == null || maxf == 0 || maxc == 0) return "Matriz vacía o no inicializada";
    
    // Contar en matriz
    int autosMatriz = 0;
    for(int i = 0; i < maxf; i++) {
        for(int j = 0; j < maxc; j++) {
            if(mat[i][j] != null && ((Vehiculos)mat[i][j]).getTipoVehiculo().equalsIgnoreCase("Automóvil")) {
                autosMatriz++;
            }
        }
    }
    
    // Contar en archivo usando el CRUD existente
    int autosArchivo = objCrud.contarVehiculosPorTipo("Automóvil");
    
    if(autosArchivo == 0) return "No hay automóviles en el archivo para comparar";
    
    double porcentaje = (autosMatriz * 100.0) / autosArchivo;
    return String.format("Automóviles - Matriz: %d, Archivo: %d (%.2f%%)", 
                       autosMatriz, autosArchivo, porcentaje);
}
// Nuevo método en CRUDVehiculo.java
public int contarPorTipoEnArchivo(Archivos objArch, String tipo) {
    int contador = 0;
    try {
        objArch.AbrirArchivoModoLectura("Vehiculos.txt");
        String[] Reg;
        while((Reg = objArch.LeerRegistro(6)) != null) {
            if(Reg[1].equalsIgnoreCase(tipo)) contador++;
        }
        objArch.CerrarArchivoModoLectura();
    } catch(Exception e) {
        JOptionPane.showMessageDialog(null, "Error contando tipos: " + e.getMessage());
    }
    return contador;
}
public String promedioMalEstado(Object[][] mat, int maxf, int maxc) {
    if(mat == null || maxf == 0 || maxc == 0) return "Matriz vacía";
    
    int malEstado = 0;
    int total = 0;
    String tipoCalculo;
    
    if(maxf == maxc) { // Diagonal secundaria
        tipoCalculo = "diagonal secundaria";
        for(int i = 0; i < maxf; i++) {
            Vehiculos v = (Vehiculos)mat[i][maxc-1-i];
            if(!v.isEstado()) malEstado++;
            total++;
        }
    } else { // Primera columna
        tipoCalculo = "primera columna";
        for(int i = 0; i < maxf; i++) {
            Vehiculos v = (Vehiculos)mat[i][0];
            if(!v.isEstado()) malEstado++;
            total++;
        }
    }
    
    return String.format("%s: %d/%d (%.2f%%) en mal estado", 
                       tipoCalculo, malEstado, total, (malEstado*100.0/total));
}
public String grabarAmarillos(Object[][] mat, int maxf, int maxc, Archivos objArch, CRUDVehiculo objCrud) {
    if(mat == null || maxf == 0 || maxc == 0) return "Matriz vacía";
    
    int grabados = 0;
    try {
        objArch.AbrirArchivoModoEscritura("Vehiculos.txt");
        
        for(int i = 0; i < maxf; i++) {
            for(int j = 0; j < maxc; j++) {
                Vehiculos v = (Vehiculos)mat[i][j];
                if(v.getColor().equalsIgnoreCase("Amarillo") && 
                   !objCrud.Buscar(objArch, v.getNroPlaca())) {
                    objArch.EscribirRegistro(v.EstructuraReg());
                    grabados++;
                }
            }
        }
        objArch.CerrarArchivoModoEscritura();
    } catch(Exception e) {
        return "Error grabando archivo";
    }
    
    return grabados > 0 ? "Se grabaron " + grabados + " vehículos amarillos" 
                       : "No se grabaron (ya existían o no hay amarillos)";
}
 public String vehiculosMismoColor(Object[][] mat, int maxf, int maxc, Archivos objArch, CRUDVehiculo objCrud) {
    // Validación de matriz
    if(mat == null || maxf == 0 || maxc == 0) return "Matriz vacía o no inicializada";
    
    // Obtener color del último vehículo en matriz
    Vehiculos ultimo = (Vehiculos)mat[maxf-1][maxc-1];
    String colorBuscado = ultimo.getColor();
    
    // Usar CRUD para buscar en archivo
    String resultado = objCrud.buscarPorColor(colorBuscado);
    
    return resultado.isEmpty() ? 
           "No hay vehículos con color " + colorBuscado + " en el archivo" : 
           "Vehículos color " + colorBuscado + ":\n" + resultado;
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
 public String cambiarEstadoModelo2011(Object[][] mat, int maxf, int maxc, ListaSimple lista) {
    int cambiosMatriz = 0;
    int cambiosLista = 0;
    
    // Procesar matriz
    for (int i = 0; i < maxf; i++) {
        for (int j = 0; j < maxc; j++) {
            if (mat[i][j] != null && ((Vehiculos)mat[i][j]).getModelo() == 2011) {
                ((Vehiculos)mat[i][j]).setEstado(false);
                cambiosMatriz++;
            }
        }
    }
    
    // Procesar lista
    Nodo actual = lista.getStart();
    while (actual != null) {
        Vehiculos v = (Vehiculos) actual.getDato();
        if (v.getModelo() == 2011) {
            v.setEstado(false);
            cambiosLista++;
        }
        actual = actual.getSig();
    }
    
    return "Cambios realizados - Matriz: " + cambiosMatriz + ", Lista: " + cambiosLista;
}
public String reemplazarExtremosMatriz(Object[][] mat, int maxf, int maxc, 
                                     ListaSimple lista, Archivos objArch) {
    // Validación de parámetros
    if (mat == null || lista == null || objArch == null) {
        return "Error: Parámetros no pueden ser nulos";
    }
    if (maxf < 2 || maxc < 2) {
        return "La matriz debe ser de al menos 2x2";
    }

    StringBuilder resultado = new StringBuilder();
    resultado.append("=== Reemplazo Manual de Extremos de Matriz ===\n\n");

    try {
        // 1. Esquina superior izquierda [0][0]
        String placa = Validaciones.LeerString("Ingrese placa para [0][0]:");
        Vehiculos vehiculo = new Vehiculos().IngresarDatos(placa);
        mat[0][0] = vehiculo;
        resultado.append("[0][0] reemplazado con: ").append(vehiculo.getNroPlaca()).append("\n");

        // 2. Esquina superior derecha [0][maxc-1]
        placa = Validaciones.LeerString("Ingrese placa para [0][" + (maxc-1) + "]:");
        vehiculo = new Vehiculos().IngresarDatos(placa);
        mat[0][maxc-1] = vehiculo;
        resultado.append("[0][").append(maxc-1).append("] reemplazado con: ")
                .append(vehiculo.getNroPlaca()).append("\n");

        // 3. Esquina inferior izquierda [maxf-1][0]
        placa = Validaciones.LeerString("Ingrese placa para [" + (maxf-1) + "][0]:");
        vehiculo = new Vehiculos().IngresarDatos(placa);
        mat[maxf-1][0] = vehiculo;
        resultado.append("[").append(maxf-1).append("][0] reemplazado con: ")
                .append(vehiculo.getNroPlaca()).append("\n");

        // 4. Esquina inferior derecha [maxf-1][maxc-1]
        placa = Validaciones.LeerString("Ingrese placa para [" + (maxf-1) + "][" + (maxc-1) + "]:");
        vehiculo = new Vehiculos().IngresarDatos(placa);
        mat[maxf-1][maxc-1] = vehiculo;
        resultado.append("[").append(maxf-1).append("][").append(maxc-1)
                .append("] reemplazado con: ").append(vehiculo.getNroPlaca()).append("\n");

        resultado.append("\nTodos los extremos fueron reemplazados exitosamente");
        return resultado.toString();

    } catch (Exception e) {
        return "Error durante el proceso: " + e.getMessage();
    }
}

private Vehiculos crearVehiculoDesdeRegistro(String[] registro) throws Exception {
    if (registro == null || registro.length < 6) {
        throw new Exception("Registro inválido");
    }
    return new Vehiculos(
        registro[0], registro[1], registro[2],
        registro[3], Integer.parseInt(registro[4]),
        Boolean.parseBoolean(registro[5])
    );
}
 public String crearListasPorAntiguedad(Archivos objArch, ListaSimple listaAntiguos, ListaSimple listaNuevos) {
    try {
        objArch.AbrirArchivoModoLectura("Vehiculos.txt");
        String[] Reg;
        int antiguos = 0, nuevos = 0;
        
        while ((Reg = objArch.LeerRegistro(6)) != null) {
            int modelo = Integer.parseInt(Reg[4]);
            Vehiculos v = new Vehiculos(Reg[0], Reg[1], Reg[2], Reg[3], modelo, Boolean.parseBoolean(Reg[5]));
            
            if (modelo > 1980 && modelo < 2014) {
                listaAntiguos.CrearPorFinal(v);
                antiguos++;
            } else if (modelo >= 2014 && modelo <= 2024) {
                listaNuevos.CrearPorFinal(v);
                nuevos++;
            }
        }
        
        return String.format("Listas creadas - Antiguos: %d, Nuevos: %d", antiguos, nuevos);
    } catch (Exception e) {
        return "Error: " + e.getMessage();
    } finally {
        objArch.CerrarArchivoModoLectura();
    }
}
 public String promedioModelos(Object[][] mat, int maxf, int maxc) {
    if(mat == null || maxf == 0 || maxc == 0) return "Matriz vacía";
    
    double suma = 0;
    int contador = 0;
    String tipoCalculo;
    
    if(maxf == maxc) { // Diagonal principal
        tipoCalculo = "diagonal principal";
        for(int i = 0; i < maxf; i++) {
            Vehiculos v = (Vehiculos)mat[i][i];
            suma += v.getModelo();
            contador++;
        }
    } else { // Primera fila
        tipoCalculo = "primera fila";
        for(int j = 0; j < maxc; j++) {
            Vehiculos v = (Vehiculos)mat[0][j];
            suma += v.getModelo();
            contador++;
        }
    }
    
    return String.format("Promedio modelos (%s): %.2f", tipoCalculo, (suma/contador));
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
