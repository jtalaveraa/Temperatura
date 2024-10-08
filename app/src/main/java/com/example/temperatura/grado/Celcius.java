package com.example.temperatura.grado;

public class Celcius extends Grado{

    /*
    public Celcius(){
        this.setUnidad("C");
    }
    */
    public Celcius(Double valor){
        this.setUnidad("C");
        this.setValor(valor);
    }

    public Celcius parse(Kelvin kelvin){
       // Double valor = kelvin.getValor()-273.15; // se implementa el método de la clase Grado

       //  Celcius celcius = new Celcius();
        // celcius.setValor(valor);
       // return new Celcius(valor);

        return new Celcius(kelvin.getValor()-273.15);
    }
    public Celcius parse(Farenheit farenheit){
        return new Celcius((farenheit.getValor()-32)*5/9);
    }
}
