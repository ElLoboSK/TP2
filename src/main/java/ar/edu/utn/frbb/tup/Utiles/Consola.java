package ar.edu.utn.frbb.tup.Utiles;

public class Consola {
    
    public static void limpiarPantalla() {
        //se limpia la pantalla
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
