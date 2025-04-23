package parqueadero;

import javax.swing.JOptionPane;

public class ListaSimple {
    //atributos propios y privados

    private Nodo Start, End;

    //constructor vacio
    public ListaSimple() {
        Start = null;
        End = null;
    }

    /*auxiliares, la q se va a usar para recorridos y la 
    p SOLO PARA EL METODO BUSCAR*/
    Nodo q, p, t;

    /*metodo que retorna verdadero si la lista esta vacia o 
    falso en caso contrario*/
    public boolean IsEmpty() {
        if (Start == null) {
            return true;
        } else {
            return false;
        }
        //fin si
    }//fin is empty

    public void CrearPorInicio(Object d) {
        if (IsEmpty() == true) {
            setStart(new Nodo(d));
            setEnd(getStart());
        } else {
            setStart(new Nodo(d, getStart()));
        }//fin si
    }//fin de crear por inicio

    public void CrearPorFinal(Object d) {
        if (IsEmpty() == true) {
            setStart(new Nodo(d));
            setEnd(getStart());
        } else {
            getEnd().setSig(new Nodo(d));
            setEnd(getEnd().getSig());
        }//Fin si
    }//fin de Crear por final

    //junta el contenido de la lista desde el inicio y lo retorna
    public String JuntarDesdeInicio() {
        String texto = "";
        if (IsEmpty() == false)//si hay datos
        {
            q = getStart();//se comienza desde el primero
            //mientras existan datos
            while (q != null) {
                texto = texto + q.getDato().toString() + "\n";
                q = q.getSig();//adelantamos en la lista
            }//fin mientras
        }//fin si
        return texto;
    }//fin de juntar

    /*metodo que busca por la placa en la lista,
 retorna verdadero si lo encuentra y ademas deja el apuntador
 p en el dato, y al apuntador t atras de p, y retorna falso en 
 caso de no encontrarlo*/
    public boolean Buscar(String pl) {
        if (IsEmpty() == false)//si hay datos
        {
            String pla;//variable que debemos usar para capturar la placa 
            p = getStart();//colocamos el apuntador al inicio
            pla = ((Vehiculos) p.getDato()).getNroPlaca();/*tomamos la placa del vehiculo
         que esta en el nodo para que no haga la pregunta con p en null!! esto
         NO es necesario en el algoritmo porque es un problema de programacion 
         de java no del analisis*/
            while (p != null && !(pla.equalsIgnoreCase(pl))) {
                t = p;//ubicamos al apuntador t atras de p
                p = p.getSig();//p adelanta en la lista
                if (p != null)/*si hay aun datos para tomar otro id, esto 
                 no lo tenemos que hacer en el algoritmo*/ {
                    pla = ((Vehiculos) p.getDato()).getNroPlaca();
                }
            }//fin mientras
        }
        if (p == null)//recorrio toda la lista y no lo encuentra
        {
            return false;
        } else//p quedo ubicada en la lista en el dato buscado
        {
            return true;
        }
    }//fin buscar

    /*metodo que recibe un dato y lo inserta de primero
    en la lista*/
    public void InsertarStart(Object d) {
        if (IsEmpty() == false)//si hay datos
        {
            setStart(new Nodo(d, getStart()));
        }
        //fin si
    }//fin Insertar Start

    /*metodo que recibe un dato y lo inserta de ultimo
    en la lista*/
    public void InsertarEnd(Object d) {
        if (IsEmpty() == false)//si hay datos
        {
            getEnd().setSig(new Nodo(d));
            setEnd(getEnd().getSig());
        }//fin si
    }//fin Insertar End

    /*metodo que retorna el dato que elimina de primero
    en la lista*/
    public Object LiberarStart() {
        Object dato = null;//variable de retorno
        if (IsEmpty() == false)//hay datos
        {
            dato = getStart().getDato();//se toma el dato de retorno
            if (getStart().getSig() == null)//solo un dato en la lista
            {
                Start.finalize();//llamada al destructor
                Start = End = null;//condiciones iniciales de lista vacia
                JOptionPane.showMessageDialog(null, "La lista quedó vacia");
            } else {//hay mas de un dato en la lista	
                q = getStart();//para poder llamar al destructor
                setStart(getStart().getSig());
                q.finalize();//llamada al destructor para limpieza del primer dato
            }//fin si
        }//fin si
        return dato;
    }//fin Liberar Start

    /*metodo que ubica el apuntador q en el penultimo dato 
    de la lista*/
    public void UbicarPenultimo() {
        if (IsEmpty() == false)//hay datos
        {
            if (getStart().getSig() != null)//no es lista de un solo nodo
            {
                q = getStart();//se coloca q al inicio para el recorrido
                while (q.getSig() != null)//mientras q se ubique en el ultimo
                {
                    t = q;//se ubica t antes de q para que t quede de penultimo
                    q = q.getSig();//se adelanta q hasta el ultimo
                }//fin mientras
            } else {
                JOptionPane.showMessageDialog(null, "Lista de un solo nodo");
            }
            //fin si
        }//fin si
    }//fin Ubicar Penultimo

    /*metodo que retorna el dato que elimina de ultimo
    en la lista*/
    public Object LiberarEnd() {
        Object dato = null;//variable de retorno
        if (IsEmpty() == false) {
            dato = getEnd().getDato();
            if (getStart().getSig() == null)//no se tiene lista de un solo nodo
            {
                End.finalize();//llamada al destructor
                Start = End = null;//condiciones iniciales de lista vacia
                JOptionPane.showMessageDialog(null, "Lista quedó vacia");
            } else {
                UbicarPenultimo();//para tener ubicado a t en el penultimo
                End.finalize();//se limpia la informacion contenida en el ultimo nodo
                t.setSig(null);//se coloca en t  .sig  null, porque ya es el ultimo
                setEnd(t);//t ya es End
            }//fin si
        }//fin si
        return dato;
    }//fin Liberar End

    /*metodo que recibe una placa, la busca en la lista y la elimina de la lista,
este dato puede estar en cualquier lugar de la lista*/
    public void LiberarDato(String pl) {
        if (IsEmpty() == false)//hay datos
        {
            if (Buscar(pl) == true)//placa se encuentra en la lista, p y t quedan ubicados
            {
                if (p == getStart())//si el dato a eliminar esta de primero
                {
                    LiberarStart();//invocamos el metodo de eliminar el primero
                } else {
                    if (p == getEnd())//si el dato a eliminar esta de ultimo
                    {
                        LiberarEnd();//invocamos el metodo de eliminar el ultimo
                    } else {//esta en medio de dos nodos
                        t.setSig(p.getSig());
                        p.finalize();
                    }//fin si
                }//fin si
                JOptionPane.showMessageDialog(null, "Se eliminó " + pl);
            } else {
                JOptionPane.showMessageDialog(null, "Vehículo No existe, no se elimina");
            }
            //fin si
        }//fin si
    }//fin Liberar Dato

    /*metodo que recibe dos numeros de placa, una que se insertara como nuevo y otra para buscar
como referencia e insertar despues de ella*/
    public void InsertarDespues(String pl, String plRef) {
        Vehiculos objv = new Vehiculos();
        if (IsEmpty() == false)//hay datos
        {
            if (Buscar(plRef) == true)//la placa referencia para insertar despues existe en la lista
            {
                objv = objv.IngresarDatos(pl);//se piden los datos de la placa a insertar despues
                if (p == getEnd())//si la placa referencia esta de ultima
                {
                    InsertarEnd(objv);//se inserta de ultimo despues de placa referencia
                } else//esta entre dos
                {
                    p.setSig(new Nodo(objv, p.getSig()));
                }//fin si
                JOptionPane.showMessageDialog(null, "Se insertó a " + pl + " despues de " + plRef);
            }//no esta en lista la placa referencia
            else {
                JOptionPane.showMessageDialog(null, "Placa referencia NO existe en la lista, NO insertamos");
            }
            //fin si
        }//fin si

    }//fin insertar despues

    /*metodo que recibe dos numeros de placa, una que se insertara como nuevo y otra para buscar
como referencia e insertar antes de ella*/
    public void InsertarAntes(String pl, String plRef) {
        Vehiculos objv = new Vehiculos();
        if (IsEmpty() == false)//hay datos
        {
            if (Buscar(plRef) == true)//la placa referencia para insertar antes existe en la lista
            {
                objv = objv.IngresarDatos(pl);//se piden los datos de la placa a insertar antes
                if (p == getStart())//si la placa referencia esta de primera
                {
                    InsertarStart(objv);//se inserta de primero antes de placa referencia
                } else//esta entre dos
                {
                    t.setSig(new Nodo(objv, p));
                }//fin si
                JOptionPane.showMessageDialog(null, "Se insertó a " + pl + " antes de " + plRef);
            }//no esta en lista la placa referencia
            else {
                JOptionPane.showMessageDialog(null, "Placa referencia NO existe en la lista, NO insertamos");
            }
            //fin si
        }//fin si

    }//fin insertar antes

    public int contarElementos() {
        int contador = 0;
        Nodo q = getStart();

        while (q != null) {
            contador++;
            q = q.getSig();
        }

        return contador;
    }

    public Vehiculos BuscarMenorModelo() {
        if (IsEmpty()) {
            return null;
        }

        Nodo q = getStart();
        Vehiculos objv = (Vehiculos) q.getDato();
        int menorModelo = objv.getModelo();
        q = q.getSig();

        while (q != null) {
            Vehiculos temp = (Vehiculos) q.getDato();
            if (temp.getModelo() < menorModelo) {
                menorModelo = temp.getModelo();
                objv = temp;
            }
            q = q.getSig();
        }
        return objv;
    }

    public String BuscarMenorRepetidos() {
        String texto = "";

        if (!IsEmpty()) {
            Vehiculos objv = BuscarMenorModelo();
            int modeloMenor = objv.getModelo();
            Nodo q = getStart();

            while (q != null) {
                Vehiculos actual = (Vehiculos) q.getDato();
                if (actual.getModelo() == modeloMenor) {
                    texto += actual.toString() + "\n";
                }
                q = q.getSig();
            }
        }

        return texto;
    }

    public ListaSimple Generar17() {
        ListaSimple nueva = new ListaSimple();
        Vehiculos objv = BuscarMenorModelo();

        if (objv != null) {
            int modeloMenor = objv.getModelo();
            Nodo q = getStart();

            while (q != null) {
                Vehiculos actual = (Vehiculos) q.getDato();
                if (actual.getModelo() == modeloMenor) {
                    nueva.CrearPorFinal(actual); // o CrearPorFinal según tengas definido
                }
                q = q.getSig();
            }
        }

        return nueva;
    }
    
    public void editarVehiculoPorPlacaEnLista(Nodo inicio) {
    if (inicio == null) {
        JOptionPane.showMessageDialog(null, "⚠️ La lista está vacía.");
        return;
    }

    String placa = Validaciones.LeerString("🔍 Ingrese la placa del vehículo a editar:");
    Nodo p = inicio;
    boolean encontrado = false;

    while (p != null) {
        Vehiculos v = (Vehiculos) p.getDato();
        if (v.getNroPlaca().equalsIgnoreCase(placa)) {
            encontrado = true;

            String nuevaMarca = Validaciones.LeerString("Marca actual: " + v.getMarca() + "\nIngrese nueva marca:");
            String nuevoTipo = Validaciones.LeerString("Tipo actual: " + v.getTipoVehiculo() + "\nIngrese nuevo tipo:");
            String nuevoColor = Validaciones.LeerString("Color actual: " + v.getColor() + "\nIngrese nuevo color:");
            int nuevoModelo = Validaciones.LeerInt("Modelo actual: " + v.getModelo() + "\nIngrese nuevo modelo:");
            boolean nuevoEstado = JOptionPane.showConfirmDialog(null, "¿Está activo el vehículo?", "Estado", JOptionPane.YES_NO_OPTION) == 0;

            v.setMarca(nuevaMarca);
            v.setTipoVehiculo(nuevoTipo);
            v.setColor(nuevoColor);
            v.setModelo(nuevoModelo);
            v.setEstado(nuevoEstado);

            JOptionPane.showMessageDialog(null, "✅ Datos actualizados para la placa: " + v.getNroPlaca());
            break;
        }

        p = p.getSig();
    }

    if (!encontrado) {
        JOptionPane.showMessageDialog(null, "❌ Vehículo no encontrado con esa placa.");
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

