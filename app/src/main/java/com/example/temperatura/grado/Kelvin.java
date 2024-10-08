package com.example.temperatura.grado;

public class Kelvin extends Grado  {
    public Kelvin(Double valor) {
        this.setUnidad("K");
        this.setValor(valor);
    }

    public Kelvin parse(Celcius celcius) {
        return new Kelvin(celcius.getValor() + 273.15);
    }

    public Kelvin parse(Farenheit fahrenheit) {
        return new Kelvin((fahrenheit.getValor() - 32) * 5/9 + 273.15);
    }
}
