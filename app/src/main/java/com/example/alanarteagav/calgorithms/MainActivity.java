package com.example.alanarteagav.calgorithms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private TextView cPrime;
    private TextView javaPrime;
    private Button startPrimeBtn;
    private EditText searchPrimeEt;

    private TextView cQuick;
    private TextView javaQuick;
    private Button startQuickBtn;
    private EditText quickEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cPrime = findViewById(R.id.c_prime);
        javaPrime = findViewById(R.id.java_prime);
        startPrimeBtn = findViewById(R.id.start_prime);
        searchPrimeEt = findViewById(R.id.search_prime);

        cQuick = findViewById(R.id.c_quick);
        javaQuick = findViewById(R.id.java_quick);
        startQuickBtn = findViewById(R.id.start_quick);
        quickEt = findViewById(R.id.quick_sort);
    }

    public native void calculaprimo(int number);

    public native void quicksort(int n);

    @Override
    protected void onStart() {
        super.onStart();

        startPrimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = searchPrimeEt.getText().toString().trim();
                if (number.isEmpty())
                    Toast.makeText(getApplicationContext(), "Inserta un número", Toast.LENGTH_LONG).show();
                else {
                    int nth = Integer.parseInt(number);
                    int times = getTimes(nth);

                    long[] timesForAverage = new long[times];
                    for (int i = 0; i < times; i++) {
                        long start = System.nanoTime();
                        Algorithms.calculaPrimo(nth);
                        timesForAverage[i] = System.nanoTime() - start;
                    }

                    long average = getAverage(timesForAverage);
                    String time = average / 1000000 + "." + average / 1000;
                    javaPrime.setText("Con Java: primo encontrado en " + time + " ms ");

                    timesForAverage = new long[times];
                    for (int i = 0; i < times; i++) {
                        long start = System.nanoTime();
                        calculaprimo(nth);
                        timesForAverage[i] = System.nanoTime() - start;
                    }

                    average = getAverage(timesForAverage);
                    time = average / 1000000 + "." + average / 1000;
                    cPrime.setText("Con C: primo encontrado en " + time + " ms ");
                }
            }
        });

        startQuickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = quickEt.getText().toString().trim();
                if (number.isEmpty())
                    Toast.makeText(getApplicationContext(), "Inserta un número", Toast.LENGTH_LONG).show();
                else {
                    int n = Integer.parseInt(number);

                    int times = getTimes(n);

                    long[] timesForAverage = new long[times];
                    for (int i = 0; i < times; i++) {
                        long start = System.nanoTime();
                        Algorithms.quickSort(n);
                        timesForAverage[i] = System.nanoTime() - start;
                    }

                    long average = getAverage(timesForAverage);
                    String time = average / 1000000 + "." + average / 1000;
                    javaQuick.setText("Con Java: " + n + " números ordenados en " + time + " ms (promedio de "
                            + times + ")");

                    timesForAverage = new long[times];
                    for (int i = 0; i < times; i++) {
                        long start = System.nanoTime();
                        quicksort(n);
                        timesForAverage[i] = System.nanoTime() - start;
                    }

                    average = getAverage(timesForAverage);
                    time = average / 1000000 + "." + average / 1000;
                    cQuick.setText("Con C: " + n + " números ordenados en " + time + " ms (promedio de "
                            + times + ")");
                }
            }
        });
    }

    private long getAverage(long[] times) {
        long sum = 0;
        for (int i = 0; i < times.length; i++) {
            sum += times[i];
        }
        return sum / times.length;
    }

    private int getTimes(int n) {
        if (n <= 100)
            return 100;
        else if (n <= 1000)
            return 50;
        else if (n <= 10000)
            return 30;
        else if (n <= 100000)
            return 15;
        else
            return 1;
    }
}
