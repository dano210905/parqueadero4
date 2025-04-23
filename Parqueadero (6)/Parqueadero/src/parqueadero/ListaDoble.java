
package parqueadero;

import javax.swing.JOptionPane;

public class ListaDoble {

    //atributos propios y privados
    private Nodo Start, End;

    //constructor vacio
    public ListaDoble() {
        Start = End = null;//condiciones iniciales de lista vacia
    }

    /*auxiliares, la q se va a usar para recorridos y la 
    p SOLO PARA EL METODO BUSCAR*/
    Nodo q, p;

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

    public void CrearPorFinal(Object info) {
        if (IsEmpty() == false) {
            getEnd().setSig(new Nodo(End, info, null));
            setEnd(getEnd().getSig());
        } else {
            Start = End = new Nodo(null, info, null);
        }//Fin si
    }//fin de insertar nodo por final

    public void CrearPorInicio(Object info) {
        if (IsEmpty() == false) {
            setStart(new Nodo(null, info, getStart()));
            getStart().getSig().setAnt(getStart());
        } else {
            Start = End = new Nodo(null, info, null);
        }//Fin si

    }//fin de insertar nodo por inicio

    /*

     */
    public String JuntarDesdeStart() {
        String texto = "";//variable del retorno
        if (IsEmpty() == false) {
            q = getStart();
            while (q != null) {
                texto = texto + q.getDato() + "\n";
                q = q.getSig();//adelantar en la lista
            }//fin mientras
        }//fin si
        return texto;
    }//fin juntar desde inicio

    public String JuntarDesdeEnd() {
        String texto = "";//variable del retorno
        if (IsEmpty() == false) {
            q = getEnd();
            while (q != null) {
                texto = texto + q.getDato() + "\n";
                q = q.getAnt();//al anterior en la lista
            }//fin mientras
        }//fin si
        return texto;
    }//fin juntar desde final

    public void InsertarStart(Object d) {

        if (IsEmpty() == false) {
            setStart(new Nodo(null, d, getStart()));
            getStart().getSig().setAnt(getStart());

        }// fin si
    }// fin insertarStart

    public void InsertarEnd(Object d) {

        if (IsEmpty() == false) {
            setEnd(new Nodo(getEnd(), d, null));
            getEnd().getAnt().setSig(getEnd());

        }// fin si
    }// fin insertarStart

    public Object LiberarStart() {

        Object d = null;
        if (IsEmpty() == false) {
            d = getStart().getDato();
            if (getStart().getSig() == null) {
                getStart().finalize();
                Start = End = null;

            } else {
                setStart(getStart().getSig());
                getStart().getAnt().finalize();
                getStart().setAnt(null);
            }//fin si

        }// fin si
        return d;
    }// fin LiberarStart

    public Object LiberarEnd() {

        Object d = null;
        if (IsEmpty() == false) {
            d = getEnd().getDato();
            if (getEnd().getAnt() == null) {
                getEnd().finalize();
                Start = End = null;

            } else {
                setEnd(getEnd().getAnt());
                getEnd().getSig().finalize();
                getEnd().setSig(null);
            }//fin si

        }// fin si
        return d;
    }// fin LiberarStart

    public boolean buscar(String pl) {
        if (!IsEmpty()) {
            Nodo p = getStart();
            Nodo t = null;
            String pla = ((Vehiculos) p.getDato()).getNroPlaca();

            while (p != null && !pla.equalsIgnoreCase(pl)) {
                t = p;                // t se queda atrás de p
                p = p.getSig();       // p avanza en la lista

                if (p != null) {
                    pla = ((Vehiculos) p.getDato()).getNroPlaca();
                }
            }

            return p != null; // Si p no es null, encontramos la placa
        }

        return false;
    }

    public void InsertarAntes(String nuevaPlaca, String placaReferencia) {
        Nodo p = getStart();

        while (p != null && !((Vehiculos) p.getDato()).getNroPlaca().equalsIgnoreCase(placaReferencia)) {
            p = p.getSig();
        }

        if (p != null) {
            Nodo nuevo = new Nodo(new Vehiculos(nuevaPlaca));
            nuevo.setSig(p);
            nuevo.setAnt(p.getAnt());

            if (p.getAnt() != null) {
                p.getAnt().setSig(nuevo);
            } else {
                setStart(nuevo); // es el nuevo primero
            }

            p.setAnt(nuevo);
        } else {
            JOptionPane.showMessageDialog(null, "❌ Placa de referencia no encontrada.");
        }
    }

    public void InsertarDespues(String nuevaPlaca, String placaReferencia) {
        Nodo p = getStart();

        while (p != null && !((Vehiculos) p.getDato()).getNroPlaca().equalsIgnoreCase(placaReferencia)) {
            p = p.getSig();
        }

        if (p != null) {
            Nodo nuevo = new Nodo(new Vehiculos(nuevaPlaca));
            nuevo.setAnt(p);
            nuevo.setSig(p.getSig());

            if (p.getSig() != null) {
                p.getSig().setAnt(nuevo);
            } else {
                setEnd(nuevo); // es el nuevo último
            }

            p.setSig(nuevo);
        } else {
            JOptionPane.showMessageDialog(null, "❌ Placa de referencia no encontrada.");
        }
    }

    public void LiberarDato(String placa) {
        Nodo p = getStart();

        while (p != null && !((Vehiculos) p.getDato()).getNroPlaca().equalsIgnoreCase(placa)) {
            p = p.getSig();
        }

        if (p != null) {
            if (p.getAnt() != null) {
                p.getAnt().setSig(p.getSig());
            } else {
                setStart(p.getSig()); // eliminando el primero
            }

            if (p.getSig() != null) {
                p.getSig().setAnt(p.getAnt());
            } else {
                setEnd(p.getAnt()); // eliminando el último
            }

            p.setAnt(null);
            p.setSig(null);
            p = null;

            JOptionPane.showMessageDialog(null, "✅ Placa eliminada correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "❌ Placa no encontrada.");
        }
    }
// For ListaDoble

    public int NumeroElementos(ListaDoble lista) {
        int count = 0;
        Nodo q = lista.getStart();
        while (q != null) {
            count++;
            q = q.getSig();
        }
        return count;
    }

    public void invertirListaDoble(ListaDoble lista) {
        if (lista.IsEmpty() || lista.getStart().getSig() == null) {
            return; // Empty or single-element list
        }

        Nodo current = lista.getStart();
        Nodo temp = null;

        // Swap next and prev for all nodes
        while (current != null) {
            temp = current.getAnt();
            current.setAnt(current.getSig());
            current.setSig(temp);
            current = current.getAnt(); // Move to next node (original next)
        }

        // Update start and end pointers
        temp = lista.getStart();
        lista.setStart(lista.getEnd());
        lista.setEnd(temp);
    }

    public void ordenarListaPorPlaca(ListaDoble lista) {
        if (lista.IsEmpty() || lista.getStart().getSig() == null) {
            return; // Empty or single-element list
        }

        boolean swapped;
        do {
            swapped = false;
            Nodo current = lista.getStart();

            while (current.getSig() != null) {
                Vehiculos v1 = (Vehiculos) current.getDato();
                Vehiculos v2 = (Vehiculos) current.getSig().getDato();

                if (v1.getNroPlaca().compareToIgnoreCase(v2.getNroPlaca()) > 0) {
                    // Swap data
                    Object temp = current.getDato();
                    current.setDato(current.getSig().getDato());
                    current.getSig().setDato(temp);
                    swapped = true;
                }
                current = current.getSig();
            }
        } while (swapped);
    }

    public int contarElementos() {
        int contador = 0;
        Nodo q = getStart();

        while (q != null) {
            contador++;
            q = q.getSig();
        }

        return contador;
    }

    public void insertarOEliminarPorPlaca(String placa, Vehiculos vehiculo) {
        if (IsEmpty()) {
            // Si está vacía, simplemente insertar
            CrearPorInicio(vehiculo);
            JOptionPane.showMessageDialog(null, "✅ Lista vacía. Vehículo insertado al inicio.");
            return;
        }

        Nodo actual = Start;
        int total = 0;

        // Buscar si existe la placa y contar los nodos
        boolean existe = false;
        while (actual != null) {
            String placaActual = ((Vehiculos) actual.getDato()).getNroPlaca();
            if (placaActual.equalsIgnoreCase(placa)) {
                existe = true;
                break;
            }
            actual = actual.getSig();
            total++;
        }

        if (existe) {
            // Eliminar el nodo con la placa
            LiberarDato(placa);
            JOptionPane.showMessageDialog(null, "Vehículo eliminado. Placa ya existía.");
        } else {
            // Insertar en la mitad
            int mitad = total / 2;
            Nodo temp = Start;
            for (int i = 0; i < mitad; i++) {
                temp = temp.getSig();
            }

            Nodo nuevo = new Nodo(null, vehiculo, null);
            if (temp != null) {
                nuevo.setSig(temp);
                nuevo.setAnt(temp.getAnt());

                if (temp.getAnt() != null) {
                    temp.getAnt().setSig(nuevo);
                } else {
                    Start = nuevo;
                }
                temp.setAnt(nuevo);
            } else {
                // Si no hay nodo (caso raro), se agrega al final
                CrearPorFinal(vehiculo);
            }

            JOptionPane.showMessageDialog(null, "Vehículo insertado en la mitad.");
        }

        // Mostrar contenido final
        JOptionPane.showMessageDialog(null, "Contenido actual de la lista:\n" + JuntarDesdeStart());
    }

    public void llenarDesdeMatrizYArchivoTipoPar(Object[][] mat, int f, int c, CRUDVehiculo objCrud, Archivos objArch) {
        String[] tiposPar = {"bus", "camion", "van"};

        // 1. Desde la matriz (se admiten repetidos)
        for (int i = 0; i < f; i++) {
            for (int j = 0; j < c; j++) {
                if (mat[i][j] != null) {
                    Vehiculos v = (Vehiculos) mat[i][j];
                    if (esTipoPar(v.getTipoVehiculo(), tiposPar)) {
                        CrearPorFinal(v);
                    }
                }
            }
        }

        // 2. Desde el archivo (también se admiten repetidos)
        try {
            objArch.AbrirArchivoModoLectura("Vehiculos.txt");
            String[] Reg = objArch.LeerRegistro(6);

            while (Reg != null) {
                String pl = Reg[0];
                String tipo = Reg[1];
                String mar = Reg[2];
                String col = Reg[3];
                int mod = Integer.parseInt(Reg[4]);
                boolean est = Boolean.parseBoolean(Reg[5]);

                Vehiculos v = new Vehiculos(pl, tipo, mar, col, mod, est);

                if (esTipoPar(tipo, tiposPar)) {
                    CrearPorFinal(v);
                }

                Reg = objArch.LeerRegistro(6);
            }

            objArch.CerrarArchivoModoLectura();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "❌ Error leyendo archivo: " + e.getMessage());
        }

        JOptionPane.showMessageDialog(null, "✅ Lista doble creada desde matriz y archivo (tipos de capacidad par).");
    }

    private boolean esTipoPar(String tipo, String[] tiposPar) {
        for (String par : tiposPar) {
            if (tipo.equalsIgnoreCase(par)) {
                return true;
            }
        }
        return false;
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
    
   public void extraerYReemplazarExtremosEnMatriz(Vehiculos[][] matriz, int fila, int col) {
    // Verifica si la lista está vacía
    if (Start == null || End == null) {
        System.out.println("La lista está vacía. No se puede extraer.");
        return;
    }

    // Verifica si la matriz está inicializada y la fila existe
    if (matriz == null || fila >= matriz.length || col > matriz[fila].length) {
        System.out.println("Matriz no válida o fuera de límites.");
        return;
    }

    // Extraemos los extremos
    Vehiculos primero = (Vehiculos) Start.getDato();
    Vehiculos ultimo = (Vehiculos) End.getDato();

    // Si la lista solo tiene un nodo
    if (Start == End) {
        Start = End = null;
    } else {
        // Eliminar primero
        Start = Start.getSig();
        Start.getAnt().setSig(null);
        Start.setAnt(null);

        // Eliminar último
        End = End.getAnt();
        End.getSig().setAnt(null);
        End.setSig(null);
    }

    // Sobrescribe solo si hay espacio suficiente
    if (col >= 2) {
        matriz[fila][0] = primero;
        matriz[fila][col - 1] = ultimo;
    } else {
        System.out.println("No hay suficientes columnas para sobrescribir extremos.");
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

    
    
