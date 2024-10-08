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
import com.example.temperatura.grado.Celcius;
import com.example.temperatura.grado.Farenheit;
import com.example.temperatura.grado.Kelvin;

public class MainActivity extends AppCompatActivity {
    private EditText inputValor;
    private Spinner spinnerOrigen, spinnerDestino;
    private TextView textResultado;
    private Button btnConvertir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValor = findViewById(R.id.input_valor);
        spinnerOrigen = findViewById(R.id.spinner_origen);
        spinnerDestino = findViewById(R.id.spinner_destino);
        btnConvertir = findViewById(R.id.btn_convertir);
        textResultado = findViewById(R.id.text_resultado);

        btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertirTemperatura();
            }
        });
    }

    private void convertirTemperatura() {
        double valor = Double.parseDouble(inputValor.getText().toString());
        String origen = spinnerOrigen.getSelectedItem().toString();
        String destino = spinnerDestino.getSelectedItem().toString();

        double resultado = 0;
        String unidadDestino = "";

        if (origen.equals("Celsius")) {
            Celcius celsius = new Celcius(valor);
            if (destino.equals("Fahrenheit")) {
                resultado = new Farenheit(valor).parse(celsius).getValor();  // Convertir Celsius a Fahrenheit
                unidadDestino = "°F";
            } else if (destino.equals("Kelvin")) {
                resultado = new Kelvin(valor).parse(celsius).getValor();  // Convertir Celsius a Kelvin
                unidadDestino = "K";
            } else {
                resultado = valor;
                unidadDestino = "°C";
            }
        } else if (origen.equals("Fahrenheit")) {
            Farenheit fahrenheit = new Farenheit(valor);
            if (destino.equals("Celsius")) {
                resultado = new Celcius(valor).parse(fahrenheit).getValor();  // Convertir Fahrenheit a Celsius
                unidadDestino = "°C";
            } else if (destino.equals("Kelvin")) {
                resultado = new Kelvin(valor).parse(fahrenheit).getValor();  // Convertir Fahrenheit a Kelvin
                unidadDestino = "K";
            } else {
                resultado = valor;
                unidadDestino = "°F";
            }
        } else if (origen.equals("Kelvin")) {
            Kelvin kelvin = new Kelvin(valor);
            if (destino.equals("Celsius")) {
                resultado = new Celcius(valor).parse(kelvin).getValor();  // Convertir Kelvin a Celsius
                unidadDestino = "°C";
            } else if (destino.equals("Fahrenheit")) {
                resultado = new Farenheit(valor).parse(kelvin).getValor();  // Convertir Kelvin a Fahrenheit
                unidadDestino = "°F";
            } else {
                resultado = valor;unidadDestino = "°F";
                unidadDestino = "K";
            }
        }
        textResultado.setText(String.format("%.2f %s", resultado, unidadDestino));
    }
}