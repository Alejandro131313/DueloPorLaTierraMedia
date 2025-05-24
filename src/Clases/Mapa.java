package Clases;

import java.util.List;

public class Mapa {

    private List<Territorio> territorios;

    public Mapa(final List<Territorio> territorios) {
        this.territorios = territorios;
    }

    public List<Territorio> getTerritorios() {
        return territorios;
    }

    public void setTerritorios(final List<Territorio> territorios) {
        this.territorios = territorios;
    }
}