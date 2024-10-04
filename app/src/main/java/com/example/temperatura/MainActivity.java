package com.example.temperatura;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;

public class MainActivity extends AppCompatActivity {
    private EditText inputValor;
    private Spinner spinnerOrigen, spinnerDestino;
    private TextView textResultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputValor = findViewById(R.id.input_valor);
        spinnerOrigen = findViewById(R.id.spinner_origen);
        spinnerDestino = findViewById(R.id.spinner_destino);
        textResultado = findViewById(R.id.text_resultado);

        Button btnConvertir = findViewById(R.id.btn_convertir);
        btnConvertir.setOnClickListener(v -> convertirTemperatura());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void convertirTemperatura() {
        double valor = Double.parseDouble(inputValor.getText().toString());
        String origen = spinnerOrigen.getSelectedItem().toString();
        String destino = spinnerDestino.getSelectedItem().toString();

        Temperatura temp;
        // Instanciando la temperatura según la unidad de origen
        switch (origen) {
            case "Celsius":
                temp = new Celsius(valor);
                break;
            case "Kelvin":
                temp = new Kelvin(valor);
                break;
            case "Fahrenheit":
                temp = new Fahrenheit(valor);
                break;
            default:
                throw new IllegalStateException("Unidad no válida: " + origen);
        }

        // Realizando la conversión según la unidad de destino
        double resultado = 0;
        String  unidad = null;
        switch (destino) {
            case "Celsius":
                resultado = temp.parseCelsius();
                unidad = "Celsius";
                break;
            case "Kelvin":
                resultado = temp.parseKelvin();
                unidad = "Kelvin";
                break;
            case "Fahrenheit":
                resultado = temp.parseFahrenheit();
                unidad = "Fahrenheit";
                break;
        }

        textResultado.setText("Resultado en "  + unidad + ": " + resultado);
    }

    // Clase Padre: Temperatura
    static abstract class Temperatura {
        protected double valor;

        public Temperatura(double valor) {
            this.valor = valor;
        }

        public abstract double parseCelsius();
        public abstract double parseKelvin();
        public abstract double parseFahrenheit();
    }

    // Clase Celsius
    static class Celsius extends Temperatura {

        public Celsius(double valor) {
            super(valor);
        }

        @Override
        public double parseCelsius() {
            return valor;
        }

        @Override
        public double parseKelvin() {
            return valor + 273.15;
        }

        @Override
        public double parseFahrenheit() {
            return (valor * 9/5) + 32;
        }
    }

    // Clase Kelvin
    static class Kelvin extends Temperatura {

        public Kelvin(double valor) {
            super(valor);
        }

        @Override
        public double parseCelsius() {
            return valor - 273.15;
        }

        @Override
        public double parseKelvin() {
            return valor;
        }

        @Override
        public double parseFahrenheit() {
            return (valor - 273.15) * 9/5 + 32;
        }
    }

    // Clase Fahrenheit
    static class Fahrenheit extends Temperatura {

        public Fahrenheit(double valor) {
            super(valor);
        }

        @Override
        public double parseCelsius() {
            return (valor - 32) * 5/9;
        }

        @Override
        public double parseKelvin() {
            return (valor - 32) * 5/9 + 273.15;
        }

        @Override
        public double parseFahrenheit() {
            return valor;
        }
    }
}