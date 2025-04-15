
package parqueadero;

import javax.swing.JOptionPane;


public class Validaciones 
{
    /*este metodo retorna un entero mayor que cero con las
    validaciones respectivas*/
    public static int LeerInt(String mensaje)
    {
        
    int num=0;//variable de retorno
    do{//obliga al usuario a digitar lo que necesito
       try{//maneja las excepciones que se salen de las manos
           num=Integer.parseInt(JOptionPane.showInputDialog(mensaje));//se pide el entero a validar
           if(num<=0)//solo es para mostrar mensaje de error
             JOptionPane.showMessageDialog(null,"Debe digitar número mayor que cero");
          }catch(Exception err)
          {//no ingresa lo pedido
             JOptionPane.showMessageDialog(null,"Debe digitar NUMEROS ");
          }
         //se sale con el numero mayor que cero de lo contrario se queda    
        }while(num<=0); 
    return num;
    }//fin de leer enteros
    
    /*este metodo retorna un real mayor o igual a cero con las
    validaciones respectivas*/
    public static double LeerDouble(String mensaje)
    {
        
    double num=0;//variable de retorno
    do{//obliga al usuario a digitar lo que necesito
       try{//maneja las excepciones que se salen de las manos
           num=Double.parseDouble(JOptionPane.showInputDialog(mensaje));//se pide el entero a validar
           if(num<0)//solo es para mostrar mensaje de error
             JOptionPane.showMessageDialog(null,"Debe digitar número mayor o igual que cero");
          }catch(Exception err)
          {//no ingresa lo pedido
             JOptionPane.showMessageDialog(null,"Debe digitar NUMEROS ");
          }
         //se sale con el numero mayor o igual que cero de lo contrario se queda    
        }while(num<0); 
    return num;
    }//fin de leer reales
    
     /*este metodo retorna un string o cadena con las
    validaciones respectivas, no lo deja en blanco*/
    public static String LeerString(String mensaje)
    {
    String txt="";//variable de retorno
    do{//obliga al usuario a digitar lo que necesito
       try{//maneja las excepciones que se salen de las manos
           txt=JOptionPane.showInputDialog(mensaje);//se pide el string a validar
           if(txt.equals(""))//solo es para mostrar mensaje de error
             JOptionPane.showMessageDialog(null,"Debe digitar información");
          }catch(Exception err)
          {//no ingresa lo pedido
             JOptionPane.showMessageDialog(null,"Debe digitar información");
          }
         //se sale con algun tipo de entrada de lo contrario se queda    
        }while(txt.equals("")); 
    return txt;
    }//fin de leer cadena o texto
    
}
