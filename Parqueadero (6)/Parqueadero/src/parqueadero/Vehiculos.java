
package parqueadero;


public class Vehiculos 
{
//atributos propios y privados
    private String NroPlaca, TipoVehiculo, Marca, Color;
    private int Modelo;
    private boolean Estado;

    public Vehiculos() {
    }

    public Vehiculos(String NroPlaca, String TipoVehiculo, String Marca, String Color, int Modelo, boolean Estado) {
        this.NroPlaca = NroPlaca;
        this.TipoVehiculo = TipoVehiculo;
        this.Marca = Marca;
        this.Color = Color;
        this.Modelo = Modelo;
        this.Estado = Estado;
    }

    Vehiculos(String nuevaPlaca) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /*este metodo recibe la placa y retorna un objeto 
    de la clase vehiculos*/
    public Vehiculos IngresarDatos(String pl)
    {
        //variables locales aucxiliares
        
        String TiVe, Mar, Co;
        int Mod;
        boolean Est;
        TiVe=Validaciones.LeerString("Ingrese Tipo de vehiculo: ");
        Mar=Validaciones.LeerString("Ingrese marca de vehiculo: ");
        Co=Validaciones.LeerString("Ingrese color de vehiculo: ");
        Mod=Validaciones.LeerInt("Ingrese modelo de vehiculo: ");
        Est=true;//se le asigna por defecto y se cambia por metodo o proceso
        //objeto de retorno
        Vehiculos objv=new Vehiculos(pl,TiVe, Mar, Co,Mod,Est);
        return objv;
    }//fin ingresar
    
    
    @Override
    public String toString() {
        return "NroPlaca=" + NroPlaca + ", TipoVehiculo=" + TipoVehiculo + ", Marca=" + Marca + ", Color=" + Color + ", Modelo=" + Modelo + ", Estado=" + Estado;
    }

    public String EstructuraReg() {
        return NroPlaca + "," + TipoVehiculo + "," + Marca + "," + Color + "," + Modelo + "," + Estado;
    }
    
    public String getNroPlaca() {
        return NroPlaca;
    }

    public void setNroPlaca(String NroPlaca) {
        this.NroPlaca = NroPlaca;
    }

    public String getTipoVehiculo() {
        return TipoVehiculo;
    }

    public void setTipoVehiculo(String TipoVehiculo) {
        this.TipoVehiculo = TipoVehiculo;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public int getModelo() {
        return Modelo;
    }

    public void setModelo(int Modelo) {
        this.Modelo = Modelo;
    }

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean Estado) {
        this.Estado = Estado;
    }

    
    
}//fin clase vehiculos
