package util.bd;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import util.bd.GestionarRutesBD;
import util.bd.Ruta;
import util.bd.PuntGeo;

public class UtilitzarRutesBD2{

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
        // Creació del gestionador
        GestionarRutesBD gRutes = new GestionarRutesBD();
        
        Ruta r = gRutes.buscar(1);
        r.mostraRuta();
        r.setDesnivellAcumulat(606);
        gRutes.guardar(r);
        
       /* r = gRutes.buscar(2);
        r.mostraRuta();
        r.getLlistaDePunts().add(0, new PuntGeo ("Plaça M.Agustina", 39.988507,-0.034533));
        gRutes.guardar(r); */
        
        System.out.println("Després de modificar");
        r=gRutes.buscar(1);
        r.mostraRuta();
        r=gRutes.buscar(2);
        r.mostraRuta();
        
        gRutes.close();
    }
}