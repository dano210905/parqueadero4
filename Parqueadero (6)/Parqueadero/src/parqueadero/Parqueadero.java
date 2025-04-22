
package parqueadero;

import javax.swing.JOptionPane;


public class Parqueadero 
{

   
    public static void main(String[] args) 
    {
       //INSTANCIAR LAS CLASES
       //clase de archicos
       Archivos objarc=new Archivos();
       //clase de crud de vehiculos
       CRUDVehiculo objcrudv=new CRUDVehiculo();
       //clase matriz
       Matriz objMa=new Matriz();
       Object mat[][]=null;//para recibir la matriz creada
       //clase manejo de matriz
       ManejoMatriz objManMat=new ManejoMatriz();
       //lista simple de vehiculos
       ListaSimple objLS=new ListaSimple();//condiciones iniciales
       //lista doble de vehiculos
       ListaDoble objLD=new ListaDoble();//condiciones iniciales
       //clase Manejo de listas
       ManejoListas objML=new ManejoListas();
       
       
       //objeto vehiculo para recibir retornos
       Vehiculos objV=new Vehiculos();
       Object objaux;
       //opciones para los menu
       int resp,f,c,op1,op2,op3,op4,op5;
       String pl,texto;
   
       do{//mientras del menu principal op1
           op1=Validaciones.LeerInt("MENU PRINCIPAL\n"
                   + "1. Archivo Vehiculos\n"
                   + "2. Matriz Vehiculos\n"
                   + "3. Lista Simple Vehiculos\n"
                   + "4. Lista Doble Vehiculos\n"
                   + "5. Salir\n");
           //en caso de op1 del menu principal
           switch(op1)
           {
               case 1: do{//ciclo mientras del menu de archivos de vehiculos op2
                            op2=Validaciones.LeerInt("MENU ARCHIVOS VEHICULOS\n"
                                                    + "1. Ingresar vehículo\n"
                                                    + "2. Mostrar todo el archivo de Vehículos\n"
                                                    + "3. Consultar un vehículo específico\n"
                                                    + "4. Volver al menu principal\n");//se puede hacer modificar o borrar
                             //en caso de op2 del menu de archivos de vehiculo
                             switch(op2)
                             {
                                 case 1://ingresar datos en el archivo de vehiculos
                                        pl=Validaciones.LeerString("ingrese placa a grabar: ");
                                        objcrudv.IngresarVehiculo(objarc, pl);
                                        break;
                                 case 2://se llama al metodo mostrar todo el archivo
                                        JOptionPane.showMessageDialog(null,"El archivo es:\n"+objcrudv.MostrarTodo(objarc));
                                        break;
                                 case 3:/*se lee la placa para buscar en el archivo y se llama al metodo consultar 
                                        para mostrar la informacion grabada*/
                                        break;
                             }//fin caso del menu de vehiculo
                         }while(op2<4);
                         break;//caso 1 para el menu de arhivos vehiculos del menu principal
               case 2: //ingresar todo lo de la matriz
                       resp=JOptionPane.showConfirmDialog(null,"Desea usar la prueba de escritorio: ","INGRESAR DATOS",JOptionPane.YES_NO_OPTION);
                       //respuesta del usuario para usar la asignación interna
                       if(resp==JOptionPane.YES_OPTION)
                       {
                           f=c=3;//porque es orden de 3*3 segun los datos
                           objMa=new Matriz(f,c);
                           mat=objManMat.PruebaEscritorio(objMa.Mat);
                       }else{//usar asignacion externa
                       f=Validaciones.LeerInt("Ingrese el número de filas: ");
                       c=Validaciones.LeerInt("Ingrese el número de columnas: ");
                       objMa=new Matriz(f,c);//se crea la matriz con el tamaño leido
                       //ingresar datos en la matriz de vehiculos para entrar al menu de manejo
                       mat=objManMat.LlenarMatriz(objMa.Mat,f,c);
                       }
                       do{//ciclo mientras del menu de matriz de vehiculos op3
                            
                            op3=Validaciones.LeerInt("MENU MATRIZ VEHICULOS\n"
                                                    + "1. Mostrar la matriz por filas de Vehículos\n"
                                                    + "2. Mostrar la matriz por columnas de Vehículos\n"
                                                    + "3. Mostrar un vehículo específico\n"
                                                    + "4. Promedio de modelos por todos los vehículos\n"
                                                    + "5. Diagonal Principal\n"
                                                    + "6. Diagonal Secundaria\n"
                                                    + "7. Vehículo mas antiguo\n"
                                                    + "8. Vehículo mas nuevo\n"
                                                    + "9. Copiar matriz al archivo de Vehículos SIN REPETICIONES\n"
                                                    + "15. Volver al menu principal\n");
                             //en caso de op3 del menu de matrices de vehiculo
                             switch(op3)
                             {
                                 case 1://se llama al metodo por filas
                                        JOptionPane.showMessageDialog(null,"la matriz por filas es:\n"+objMa.JuntarMatrizFilas(mat));
                                        break;
                                 case 2://se llama al metodo por columnas
                                        JOptionPane.showMessageDialog(null,"la matriz por columnas es:\n"+objMa.JuntarMatrizColumnas(mat));
                                        break;
                                 case 3: pl=Validaciones.LeerString("Ingrese placa del vehículo a buscar: ");
                                         objaux=objManMat.BuscarVehiculoEspecifico(mat, f, c, pl);
                                         if(objaux!=null)
                                             JOptionPane.showMessageDialog(null,"El vehiculo es: \n"+objaux.toString());
                                        break;
                                 case 5:texto=objManMat.JuntarDiagonalPpal(mat, f, c);//recibimos el texto
                                        if(!texto.equals(""))//si texto NO esta vacio
                                        {
                                            JOptionPane.showMessageDialog(null,"la diagonal principal es:\n"+texto);
                                        }//fin si
                                        break;
                                 case 6:texto=objManMat.JuntarDiagonalSec(mat, f, c);//recibimos el texto
                                        if(!texto.equals(""))//si texto NO esta vacio
                                        {
                                            JOptionPane.showMessageDialog(null,"la diagonal secundaria es:\n"+texto);
                                        }//fin si
                                        break;  
                                 case 7:objV=(Vehiculos)objManMat.BuscarMasAntiguo(mat, f, c);
                                        JOptionPane.showMessageDialog(null,"el vehiculo mas antiguo es: "+objV.EstructuraReg());
                                        break;
                                 case 8:objV=(Vehiculos)objManMat.BuscarMasNuevo(mat, f, c);
                                        JOptionPane.showMessageDialog(null,"el vehiculo mas nuevo es: "+objV.EstructuraReg());
                                        break;  
                                 case 9:if(mat!=null)//hay datos
                                            if(objManMat.GrabarMatriz(mat, f, c, objarc, objcrudv)==true)
                                                JOptionPane.showMessageDialog(null,"se grabaron vehiculos de la matriz al archivo");
                                            else
                                                JOptionPane.showMessageDialog(null,"NO se grabaron vehiculos de la matriz al archivo");
                                            //fin si
                                        break;
                             }//fin caso del menu de matriz vehiculo
                         }while(op3<15);
                         break;//caso 1 para el menu de matriz vehiculos del menu principal
           case 3: do{//ciclo mientras del menu de listas simples de vehiculos op4
                            op4=Validaciones.LeerInt("MENU LISTA SIMPLE VEHICULOS\n"
                                                    + "1. Crear lista simple de vehículos\n"
                                                    + "2. Mostrar lista simple de  Vehículos desde inicio\n"
                                                    + "3. Consultar un vehículo específico\n"
                                                    + "4. Insertar al inicio\n"
                                                    + "5. Insertar al final\n"
                                                    + "6. Insertar antes de un dato existente\n"
                                                    + "7. Insertar despues de un dato existente\n"
                                                    + "8. Liberar inicio\n"//borrar primero
                                                    + "9. Liberar final\n"//borrar ultimo
                                                    + "10. Liberar dato especifico\n"
                                                    + "15. Volver al menu principal\n");
                             //en caso de op4 del menu de lista simple de vehiculos
                             switch(op4)
                             {
                                 case 1://crear la lista por inicio o final
                                        objLS=new ListaSimple();//porque es crear condiciones iniciales
                                        resp=0;
                                        do{//obliga al usuario a digitar lo que necesito
                                            try{//maneja las excepciones que se salen de las manos
                                        resp=Integer.parseInt(JOptionPane.showInputDialog("CREAR LISTA\n"
                                                + "1. Por inicio\n"
                                                + "2. Por final\n"));//se pide la opcion de crear
                                        if(resp<1 || resp>2)//solo es para mostrar mensaje de error
                                            JOptionPane.showMessageDialog(null,"Debe digitar SOLO 1 o 2");
                                        
                                            }catch(Exception err)
                                            {//no ingresa lo pedido
                                                JOptionPane.showMessageDialog(null,"Debe digitar NUMEROS ");
                                            }
                                        //se sale con la respouesta que QUIERO de lo contrario se queda    
                                        }while(resp<1 || resp>2); //while(resp!=1 || resp!=2);
                                        
                                        objLS=objML.Crear(resp, objLS);
                                        break;
                                 case 2://se llama al metodo juntar la lista
                                        if(objLS.IsEmpty()==false)//si hay datos
                                        {
                                        JOptionPane.showMessageDialog(null,"La lista simple es:\n"+objLS.JuntarDesdeInicio());
                                         }
                                         else
                                             JOptionPane.showMessageDialog(null,"Lista vacia");
                                        break;
                                 case 3:/*se lee la placa para buscar en el archivo y se llama al metodo consultar 
                                        para mostrar la informacion grabada*/
                                        break;
                                 case 4:if(objLS.IsEmpty()==false)//si hay datos
                                        {
                                        pl=Validaciones.LeerString("Ingrese número de placa a insertar de primero: ");
                                        objV=objV.IngresarDatos(pl);
                                        objLS.InsertarStart(objV);
                                        JOptionPane.showMessageDialog(null,"Se insertó de primero en la lista a "+pl);
                                        }
                                         else
                                             JOptionPane.showMessageDialog(null,"Lista vacia");
                                       break;
                                 case 5:if(objLS.IsEmpty()==false)//si hay datos
                                        {
                                        pl=Validaciones.LeerString("Ingrese número de placa a insertar de último: ");
                                        objV=objV.IngresarDatos(pl);
                                        objLS.InsertarEnd(objV);
                                        JOptionPane.showMessageDialog(null,"Se insertó de último en la lista a "+pl);
                                        }
                                         else
                                             JOptionPane.showMessageDialog(null,"Lista vacia");
                                       break;    
                                 case 6:if(objLS.IsEmpty()==false)//si hay datos
                                        {
                                        pl=Validaciones.LeerString("Ingrese número de placa a insertar: ");
                                        texto=Validaciones.LeerString("Ingrese número de placa referencia para insertar antes: ");
                                        objLS.InsertarAntes(pl, texto);
                                        }
                                         else
                                             JOptionPane.showMessageDialog(null,"Lista vacia");
                                        break;
                                 case 7:if(objLS.IsEmpty()==false)//si hay datos
                                        {
                                        pl=Validaciones.LeerString("Ingrese número de placa a insertar: ");
                                        texto=Validaciones.LeerString("Ingrese número de placa referencia para insertar despues: ");
                                        objLS.InsertarDespues(pl, texto);
                                        }
                                         else
                                             JOptionPane.showMessageDialog(null,"Lista vacia");
                                        break;    
                                 case 8:  if(objLS.IsEmpty()==false)//si hay datos
                                        {
                                        JOptionPane.showMessageDialog(null,"El dato eliminado de primero es:\n "+objLS.LiberarStart());
                                        }   
                                          else
                                             JOptionPane.showMessageDialog(null,"Lista vacia");
                                        break;
                                 case 9:  if(objLS.IsEmpty()==false)//si hay datos
                                        {
                                        JOptionPane.showMessageDialog(null,"El dato eliminado de último es:\n "+objLS.LiberarEnd());
                                        }   
                                          else
                                             JOptionPane.showMessageDialog(null,"Lista vacia");
                                        break;
                                 case 10:if(objLS.IsEmpty()==false)//si hay datos
                                        {
                                        pl=Validaciones.LeerString("Ingrese número de placa a eliminar: ");
                                        
                                        objLS.LiberarDato(pl);
                                        }
                                         else
                                             JOptionPane.showMessageDialog(null,"Lista vacia");
                                        break;    
                             }//fin caso del menu de lista simple vehiculo
                         }while(op4<15);
                         break;//caso 4 para el menu de lista simple de vehiculos del menu principal
          
        case 4: do{//ciclo mientras del menu de listas doble de vehiculos op5
                            op5=Validaciones.LeerInt("MENU LISTA DOBLE VEHICULOS\n"
                                                    + "1. Crear lista doble de vehículos\n"
                                                    + "2. Mostrar lista doble de  Vehículos desde inicio\n"
                                                    + "3. Mostrar lista doble de  Vehículos desde final\n"
                                                    + "4. Insertar al inicio\n"
                                                    + "5. Insertar al final\n"
                                                    + "6. Insertar antes de un dato existente\n"
                                                    + "7. Insertar despues de un dato existente\n"
                                                    + "8. Liberar inicio\n"//borrar primero
                                                    + "9. Liberar final\n"//borrar ultimo
                                                    + "10. Liberar dato especifico\n"
                                                    + "11. Copiar archivo a Lista\n"
                                                    + "15. Volver al menu principal\n");
                             //en caso de op5 del menu de lista doble de vehiculos
                             switch(op5)
                             {
                                 case 1://crear la lista por inicio o final
                                        objLD=new ListaDoble();//porque es crear condiciones iniciales
                                        resp=0;
                                        do{//obliga al usuario a digitar lo que necesito
                                            try{//maneja las excepciones que se salen de las manos
                                        resp=Integer.parseInt(JOptionPane.showInputDialog("CREAR LISTA\n"
                                                + "1. Por inicio\n"
                                                + "2. Por final\n"));//se pide la opcion de crear
                                        if(resp<1 || resp>2)//solo es para mostrar mensaje de error
                                            JOptionPane.showMessageDialog(null,"Debe digitar SOLO 1 o 2");
                                        
                                            }catch(Exception err)
                                            {//no ingresa lo pedido
                                                JOptionPane.showMessageDialog(null,"Debe digitar NUMEROS ");
                                            }
                                        //se sale con la respouesta que QUIERO de lo contrario se queda    
                                        }while(resp<1 || resp>2); //while(resp!=1 || resp!=2);
                                        
                                        objLD=objML.Crear(resp, objLD);
                                        break;
                                 case 2://se llama al metodo juntar la lista
                                        if(objLD.IsEmpty()==false)//si hay datos
                                        {
                                        JOptionPane.showMessageDialog(null,"La lista doble es:\n"+objLD.JuntarDesdeStart());
                                         }
                                         else
                                             JOptionPane.showMessageDialog(null,"Lista vacia");
                                        break;
                                 case 3://se llama al metodo juntar la lista desde final
                                        if(objLD.IsEmpty()==false)//si hay datos
                                        {
                                        JOptionPane.showMessageDialog(null,"La lista doble es:\n"+objLD.JuntarDesdeEnd());
                                         }
                                         else
                                             JOptionPane.showMessageDialog(null,"Lista vacia");
                                        break;
                                 case 4:if(objLD.IsEmpty()==false)//si hay datos
                                        {
                                        pl=Validaciones.LeerString("Ingrese número de placa a insertar de primero: ");
                                        objV=objV.IngresarDatos(pl);
                                        objLD.InsertarStart(objV);
                                        JOptionPane.showMessageDialog(null,"Se insertó de primero en la lista a "+pl);
                                        }
                                         else
                                             JOptionPane.showMessageDialog(null,"Lista vacia");
                                       break;
                                 case 5:if(objLD.IsEmpty()==false)//si hay datos
                                        {
                                        pl=Validaciones.LeerString("Ingrese número de placa a insertar de último: ");
                                        objV=objV.IngresarDatos(pl);
                                        objLD.InsertarEnd(objV);
                                        JOptionPane.showMessageDialog(null,"Se insertó de último en la lista a "+pl);
                                        }
                                         else
                                             JOptionPane.showMessageDialog(null,"Lista vacia");
                                       break;    
                                 case 6:if(objLD.IsEmpty()==false)//si hay datos
                                        {
                                        pl=Validaciones.LeerString("Ingrese número de placa a insertar: ");
                                        texto=Validaciones.LeerString("Ingrese número de placa referencia para insertar antes: ");
                                        objLD.InsertarAntes(pl, texto);
                                        }
                                         else
                                             JOptionPane.showMessageDialog(null,"Lista vacia");
                                        break;
                                 case 7:if(objLD.IsEmpty()==false)//si hay datos
                                        {
                                        pl=Validaciones.LeerString("Ingrese número de placa a insertar: ");
                                        texto=Validaciones.LeerString("Ingrese número de placa referencia para insertar despues: ");
                                        objLD.InsertarDespues(pl, texto);
                                        }
                                         else
                                             JOptionPane.showMessageDialog(null,"Lista vacia");
                                        break;    
                                 case 8:  if(objLD.IsEmpty()==false)//si hay datos
                                        {
                                        JOptionPane.showMessageDialog(null,"El dato eliminado de primero es:\n "+objLD.LiberarStart());
                                        }   
                                          else
                                             JOptionPane.showMessageDialog(null,"Lista vacia");
                                        break;
                                 case 9:  if(objLD.IsEmpty()==false)//si hay datos
                                        {
                                        JOptionPane.showMessageDialog(null,"El dato eliminado de último es:\n "+objLD.LiberarEnd());
                                        }   
                                          else
                                             JOptionPane.showMessageDialog(null,"Lista vacia");
                                        break;
                                 case 10:if(objLD.IsEmpty()==false)//si hay datos
                                        {
                                        pl=Validaciones.LeerString("Ingrese número de placa a eliminar: ");
                                        
                                        objLD.LiberarDato(pl);
                                        }
                                         else
                                             JOptionPane.showMessageDialog(null,"Lista vacia");
                                        break;    
                                 case 11://se llama al metodo de copiar archivo a lista
                                        objLD=objML.CopiarArchivoLista(objarc, objLD);
                                        JOptionPane.showMessageDialog(null,"Se copiaron registros a la lista doble");
                                        break;
                             }//fin caso del menu de lista simple vehiculo
                         }while(op5<15);
                         break;//caso 4 para el menu de lista doble de vehiculos del menu principal
                           
                         
           }//fin caso de op1 menu principal
       }while(op1<5);//fin del mientras del menu principal
   
    }//fin main
    
    
}//fin clase parqueadero
