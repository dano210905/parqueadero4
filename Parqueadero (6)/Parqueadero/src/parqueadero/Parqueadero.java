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
                            + "15. Volver\n");
                            
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
                                break;
                                     case 9: // Copiar matriz a archivo
                                if(mat != null) {
                               String resultado = objManMat.GrabarMatriz(mat, f, c, objarc, objcrudv);
                                JOptionPane.showMessageDialog(null, resultado);
                         } else {
                                         JOptionPane.showMessageDialog(null, "No hay matriz creada");
                               }
                              break;
                            case 10:
                                objManMat.copiarDosRegistrosAMatriz(objarc, mat, f, c);
                                JOptionPane.showMessageDialog(null, "Registros copiados a matriz");
                                break;
                            case 11:
                                objManMat.contarVehiculosMismoColor(objarc, mat, f, c);
                                break;
                        }
                    } while(op3 < 15);
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
                            + "10. Volver\n");
                            
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
                            // ... otros casos de lista simple ...
                        }
                    } while(op4 < 10);
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
                            // ... otros casos de lista doble ...
                        }
                    } while(op5 < 12);
                    break;
            }
        } while(op1 < 5);
    }
}
