package com.example.temperatura.grado;

public class Farenheit extends Grado{
    public Farenheit(Double valor) {
        this.setUnidad("F");
        this.setValor(valor);
    }

    public Farenheit parse(Celcius celcius) {
        return new Farenheit((celcius.getValor() * 9/5) + 32);
    }

    public Farenheit parse(Kelvin kelvin) {
        return new Farenheit((kelvin.getValor() - 273.15) * 9/5 + 32);
    }
}
