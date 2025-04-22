package parqueadero;

import javax.swing.JOptionPane;

public class Parqueadero {
    public static void main(String[] args) {
        // Instanciar clases
        Archivos objarc = new Archivos();
        CRUDVehiculo objcrudv = new CRUDVehiculo();
        Matriz objMa = new Matriz();
        Object mat[][] = null;
        ManejoMatriz objManMat = new ManejoMatriz();
        ListaSimple objLS = new ListaSimple();
        ListaDoble objLD = new ListaDoble();
        ManejoListas objML = new ManejoListas();
        Vehiculos objV = new Vehiculos();
        Object objaux;
        
        // Variables para menús
        int resp, f = 0, c = 0, op1, op2, op3, op4, op5;
        String pl, texto;

        do {
            op1 = Validaciones.LeerInt("MENU PRINCIPAL\n"
                + "1. Archivo Vehiculos\n"
                + "2. Matriz Vehiculos\n"
                + "3. Lista Simple Vehiculos\n"
                + "4. Lista Doble Vehiculos\n"
                + "5. Salir\n");

            switch(op1) {
                case 1: // Menú Archivos Vehículos
                    do {
                        op2 = Validaciones.LeerInt("MENU ARCHIVOS VEHICULOS\n"
                            + "1. Ingresar vehículo\n"
                            + "2. Mostrar todo el archivo\n"
                            + "3. Consultar un vehículo\n"
                            + "4. Volver al menu principal\n");
                            
                        switch(op2) {
                            case 1:
                                pl = Validaciones.LeerString("Ingrese placa a grabar: ");
                                objcrudv.IngresarVehiculo(objarc, pl);
                                break;
                            case 2:
                                JOptionPane.showMessageDialog(null, "El archivo es:\n" + objcrudv.MostrarTodo(objarc));
                                break;
                            case 3:
                                pl = Validaciones.LeerString("Ingrese placa a consultar: ");
                                objV = objcrudv.Consultar(objarc, pl);
                                if(objV != null) {
                                    JOptionPane.showMessageDialog(null, "Vehículo encontrado:\n" + objV.toString());
                                }
                                break;
                        }
                    } while(op2 < 4);
                    break;
                    
                case 2: // Menú Matriz Vehículos
                    resp = JOptionPane.showConfirmDialog(null, "¿Desea usar prueba de escritorio?", "Matriz", JOptionPane.YES_NO_OPTION);
                    if(resp == JOptionPane.YES_OPTION) {
                        f = c = 3;
                        objMa = new Matriz(f, c);
                        mat = objManMat.PruebaEscritorio(objMa.Mat);
                    } else {
                        f = Validaciones.LeerInt("Ingrese número de filas: ");
                        c = Validaciones.LeerInt("Ingrese número de columnas: ");
                        objMa = new Matriz(f, c);
                        mat = objManMat.LlenarMatriz(objMa.Mat, f, c);
                    }
                    
                    do {
                        op3 = Validaciones.LeerInt("MENU MATRIZ VEHICULOS\n"
                            + "1. Mostrar por filas\n"
                            + "2. Mostrar por columnas\n"
                            + "3. Buscar vehículo\n"
                            + "4. Promedio de modelos\n"
                            + "5. Diagonal Principal\n"
                            + "6. Diagonal Secundaria\n"
                            + "7. Vehículo más antiguo\n"
                            + "8. Vehículo más nuevo\n"
                            + "9. Copiar matriz a archivo\n"
                            + "10. Copiar 2 registros a matriz\n"
                            + "11. Contar vehículos mismo color\n"
                            + "12. Porcentaje autos (Matriz vs Archivo)\n" 
                            +  "13. Buscar por color (vs último en matriz)\n" 
                             +  "14. Promedio vehículos en mal estado\n" 
                              +  "15. Grabar vehículos amarillos\n" 
                              +  "16. Promedio de modelos (diagonal/fila)\n" 
                                +  "17. cambiar estado vehiculos 2011\n"
                                +  "18. reemplazar extremos\n"
                                +  "19. lista doble vehiculos rojos\n"
                                +  "20. copiar diagonal principal a la lista\n"
                               +  "22. Volver al menú principal");
                         
                            
                        switch(op3) {
                            case 1:
                                JOptionPane.showMessageDialog(null, "Matriz por filas:\n" + objMa.JuntarMatrizFilas(mat));
                                break;
                            case 2:
                                JOptionPane.showMessageDialog(null, "Matriz por columnas:\n" + objMa.JuntarMatrizColumnas(mat));
                                break;
                            case 3:
                                pl = Validaciones.LeerString("Ingrese placa: ");
                                objaux = objManMat.BuscarVehiculoEspecifico(mat, f, c, pl);
                                if(objaux != null) {
                                    JOptionPane.showMessageDialog(null, "Vehículo:\n" + objaux.toString());
                                }
                                break;
                            case 5:
                                texto = objManMat.JuntarDiagonalPpal(mat, f, c);
                                if(!texto.equals("")) {
                                    JOptionPane.showMessageDialog(null, "Diagonal principal:\n" + texto);
                                }
                                break;
                            case 6:
                                texto = objManMat.JuntarDiagonalSec(mat, f, c);
                                if(!texto.equals("")) {
                                    JOptionPane.showMessageDialog(null, "Diagonal secundaria:\n" + texto);
                                }
                                break;
                            case 7:
                                objV = (Vehiculos)objManMat.BuscarMasAntiguo(mat, f, c);
                                JOptionPane.showMessageDialog(null, "Vehículo más antiguo:\n" + objV.EstructuraReg());
                                break;
                            case 8:
                                objV = (Vehiculos)objManMat.BuscarMasNuevo(mat, f, c);
                                JOptionPane.showMessageDialog(null, "Vehículo más nuevo:\n" + objV.EstructuraReg());
                        case 9:
                                if (mat != null)//hay datos
                                {
                                    if (objManMat.GrabarMatriz(mat, f, c, objarc, objcrudv) == true) {
                                        JOptionPane.showMessageDialog(null, "se grabaron vehiculos de la matriz al archivo");
                                    } else {
                                        JOptionPane.showMessageDialog(null, "NO se grabaron vehiculos de la matriz al archivo");
                                    }
                                }
                                //fin si
                                break;
    case 10:
    if(mat != null) {
        Object[] resultados = objManMat.copiarMatrizAArchivoConVisualizacion(mat, f, c, objarc, objcrudv);
        String reporte = (String)resultados[0];
        Object[][] nuevaMatriz = (Object[][])resultados[1];
        
        // Mostrar reporte
        JOptionPane.showMessageDialog(null, reporte);
        
        // Mostrar matriz actualizada
        JOptionPane.showMessageDialog(null, "Matriz actualizada:\n" + 
            objMa.JuntarMatrizFilas(nuevaMatriz));
        
        // Actualizar referencia a la matriz
        mat = nuevaMatriz;
    } else {
        JOptionPane.showMessageDialog(null, "La matriz está vacía - No hay datos para copiar");
    }
    break;
 
                          case 11:
                                objManMat.contarVehiculosMismoColor(objarc, mat, f, c);
                                break;

 case 12:
    JOptionPane.showMessageDialog(null, 
        objManMat.porcentajeAutomoviles(mat, f, c, objarc, objcrudv));
    break;
    
case 13:
    JOptionPane.showMessageDialog(null, 
        objManMat.vehiculosMismoColor(mat, f, c, objarc, objcrudv));
    break;
case 14:
    JOptionPane.showMessageDialog(null, 
        objManMat.promedioMalEstado(mat, f, c));
    break;
case 15:
    JOptionPane.showMessageDialog(null, 
        objManMat.grabarAmarillos(mat, f, c, objarc, objcrudv));
    break;
case 16:
    JOptionPane.showMessageDialog(null, 
        objManMat.promedioModelos(mat, f, c));
    break;
    case 17:
    String resultado23 = objManMat.cambiarEstadoModelo2011(mat, f, c, objLS);
    JOptionPane.showMessageDialog(null, resultado23);
    break;

// Agregar este case en el menú de matrices (case 2) después del case 8 y antes del case 9
case 18: // Reemplazar extremos de matriz (versión simplificada)
    if (mat == null) {
        JOptionPane.showMessageDialog(null, "Primero debe crear una matriz");
        break;
    }
    
    if (f < 2 || c < 2) {
        JOptionPane.showMessageDialog(null, "La matriz debe ser de al menos 2x2");
        break;
    }
    
    // Mostrar diálogo simple de confirmación
    String confirmacion = JOptionPane.showInputDialog(
        "¿Desea reemplazar los extremos? (Escriba SI para confirmar)");
    
    if (confirmacion != null && confirmacion.equalsIgnoreCase("SI")) {
        // Reemplazar [0][0]
        String placa = JOptionPane.showInputDialog("Ingrese placa para [0][0]:");
        mat[0][0] = new Vehiculos().IngresarDatos(placa);
        
        // Reemplazar [0][última columna]
        placa = JOptionPane.showInputDialog("Ingrese placa para [0]["+(c-1)+"]:");
        mat[0][c-1] = new Vehiculos().IngresarDatos(placa);
        
        // Reemplazar [última fila][0]
        placa = JOptionPane.showInputDialog("Ingrese placa para ["+(f-1)+"][0]:");
        mat[f-1][0] = new Vehiculos().IngresarDatos(placa);
        
        // Reemplazar [última fila][última columna]
        placa = JOptionPane.showInputDialog("Ingrese placa para ["+(f-1)+"]["+(c-1)+"]:");
        mat[f-1][c-1] = new Vehiculos().IngresarDatos(placa);
        
        JOptionPane.showMessageDialog(null, "Extremos reemplazados exitosamente");
    } else {
        JOptionPane.showMessageDialog(null, "Operación cancelada");
    }
    break; 
  case 19: // Filtrar vehículos rojos a lista doble
    ListaDoble listaRojos = objManMat.FiltrarVehiculosRojos(mat, f, c);
    if(listaRojos.IsEmpty()) {
        JOptionPane.showMessageDialog(null, "La matriz no tiene vehículos rojos, ¡no se creó la lista!");
    } else {
        JOptionPane.showMessageDialog(null, "Lista de vehículos rojos:\n" + listaRojos.JuntarDesdeStart());
    }
    break;

case 20: // Copiar diagonal principal
        if(mat != null) {
            ListaDoble listaDiagonal = objManMat.copiarDiagonalPrincipal(mat, f, c);
            if(!listaDiagonal.IsEmpty()) {
                JOptionPane.showMessageDialog(null, "Diagonal principal:\n" + listaDiagonal.JuntarDesdeStart());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Matriz vacía");
        }
        break;
                        
                        }
                    } while(op3 < 22);
                    break;
                    
  
                    
                case 3: // Menú Lista Simple
                    do {
                        op4 = Validaciones.LeerInt("MENU LISTA SIMPLE\n"
                            + "1. Crear lista\n"
                            + "2. Mostrar desde inicio\n"
                            + "3. Insertar al inicio\n"
                            + "4. Insertar al final\n"
                            + "5. Insertar antes de\n"
                            + "6. Insertar después de\n"
                            + "7. Eliminar primero\n"
                            + "8. Eliminar último\n"
                            + "9. Eliminar específico\n"
                                + "10. Pasar Archivo a lista simple\n"
                                + "11. Pasar lista simple a archivo\n"
                                 + "12. lista simple antiguos y lista simple neuvos\n"
                                
                            + "15. Volver\n");
                            
                        switch(op4) {
                            case 1:
                                objLS = new ListaSimple();
                                resp = 0;
                                do {
                                    try {
                                        resp = Integer.parseInt(JOptionPane.showInputDialog("Crear lista:\n1. Por inicio\n2. Por final"));
                                        if(resp < 1 || resp > 2) {
                                            JOptionPane.showMessageDialog(null, "Solo 1 o 2");
                                        }
                                    } catch(Exception e) {
                                        JOptionPane.showMessageDialog(null, "Debe digitar números");
                                    }
                                } while(resp < 1 || resp > 2);
                                objLS = objML.Crear(resp, objLS);
                                break;
                            case 2:
                                if(!objLS.IsEmpty()) {
                                    JOptionPane.showMessageDialog(null, "Lista:\n" + objLS.JuntarDesdeInicio());
                                } else {
                                    JOptionPane.showMessageDialog(null, "Lista vacía");
                                }
                                break;
                            case 3:
                                if(!objLS.IsEmpty()) {
                                    pl = Validaciones.LeerString("Ingrese placa: ");
                                    objV = objV.IngresarDatos(pl);
                                    objLS.InsertarStart(objV);
                                }
                                break;
                                case 10:
    String resultado21 = objML.pasarListaSimpleAArchivo(objLS, objarc, objcrudv);
    JOptionPane.showMessageDialog(null, resultado21);
    break;

case 11:
    objLS = objML.pasarArchivoAListaSimple(objarc);
    JOptionPane.showMessageDialog(null, "Archivo pasado a lista simple");
    break;
case 12:
    ListaSimple listaAntiguos = new ListaSimple();
    ListaSimple listaNuevos = new ListaSimple();
    
    // Llamar al método (ahora muestra el resultado directamente)
    objML.crearListasPorAntiguedad(listaAntiguos, listaNuevos);
    break;
            case 13: // Crear lista con motos del archivo
    ListaSimple listaMotos = objML.FiltrarMotosArchivo(objarc);
    if(listaMotos.IsEmpty()) {
        JOptionPane.showMessageDialog(null, "No hay motos en el archivo");
    } else {
        JOptionPane.showMessageDialog(null, "Lista de motos:\n" + listaMotos.JuntarDesdeInicio());
    }
    break;
case 14: // Mostrar número de elementos
    JOptionPane.showMessageDialog(null, "La lista simple tiene " + objLS.NumeroElementos() + " elementos");
    break;                    
                                
                            // ... otros casos de lista simple ...
                        }
                    } while(op4 < 15);
                    break;
                    
                case 4: // Menú Lista Doble
                    do {
                        op5 = Validaciones.LeerInt("MENU LISTA DOBLE\n"
                            + "1. Crear lista\n"
                            + "2. Mostrar desde inicio\n"
                            + "3. Mostrar desde final\n"
                            + "4. Insertar al inicio\n"
                            + "5. Insertar al final\n"
                            + "6. Insertar antes de\n"
                            + "7. Insertar después de\n"
                            + "8. Eliminar primero\n"
                            + "9. Eliminar último\n"
                            + "10. Eliminar específico\n"
                            + "11. Copiar archivo a lista\n"
                            + "12. Volver\n");
                            
                        switch(op5) {
                            case 1:
                                objLD = new ListaDoble();
                                resp = 0;
                                do {
                                    try {
                                        resp = Integer.parseInt(JOptionPane.showInputDialog("Crear lista:\n1. Por inicio\n2. Por final"));
                                        if(resp < 1 || resp > 2) {
                                            JOptionPane.showMessageDialog(null, "Solo 1 o 2");
                                        }
                                    } catch(Exception e) {
                                        JOptionPane.showMessageDialog(null, "Debe digitar números");
                                    }
                                } while(resp < 1 || resp > 2);
                                objLD = objML.Crear(resp, objLD);
                                break;
                            case 2:
                                if(!objLD.IsEmpty()) {
                                    JOptionPane.showMessageDialog(null, "Lista desde inicio:\n" + objLD.JuntarDesdeStart());
                                } else {
                                    JOptionPane.showMessageDialog(null, "Lista vacía");
                                }
                                break;
                            case 11:
                                objLD = objML.CopiarArchivoLista(objarc, objLD);
                                JOptionPane.showMessageDialog(null, "Registros copiados a lista");
                                break;
                                case 12: // Mostrar número de elementos
    JOptionPane.showMessageDialog(null, "La lista doble tiene " + objLD.NumeroElementos() + " elementos");
    break;
case 13: // Invertir lista doble
    objLD.InvertirLista();
    JOptionPane.showMessageDialog(null, "Lista invertida:\n" + objLD.JuntarDesdeStart());
    break;
case 14: // Ordenar lista doble por placa
    objLD.OrdenarPorPlaca();
    JOptionPane.showMessageDialog(null, "Lista ordenada por placa:\n" + objLD.JuntarDesdeStart());
    break;
                            // ... otros casos de lista doble ...
                        }
                    } while(op5 < 12);
                    break;
            }
        } while(op1 < 5);
    }
}
