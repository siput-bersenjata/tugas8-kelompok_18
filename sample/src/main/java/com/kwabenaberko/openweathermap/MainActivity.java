package com.kwabenaberko.openweathermap;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.kwabenaberko.openweathermaplib.constant.Languages;
import com.kwabenaberko.openweathermaplib.constant.Units;
import com.kwabenaberko.openweathermaplib.implementation.OpenWeatherMapHelper;
import com.kwabenaberko.openweathermaplib.implementation.callback.CurrentWeatherCallback;
import com.kwabenaberko.openweathermaplib.model.currentweather.CurrentWeather;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        
        OpenWeatherMapHelper helper = new OpenWeatherMapHelper("2c54fac6c353434391d452a68e95442b");


        helper.setUnits(Units.IMPERIAL);

        helper.setLanguage(Languages.INDONESIAN);


        TextView temperatur, deskripsi, angin,kota;
        kota =findViewById(R.id.kota);
        temperatur=findViewById(R.id.temperatur);
        deskripsi=findViewById(R.id.deskripsi);
        angin=findViewById(R.id.angin);

        helper.getCurrentWeatherByCityName("malang", new CurrentWeatherCallback() {
            @Override
            public void onSuccess(CurrentWeather currentWeather) {

                kota.setText("monitoring di "+currentWeather.getName());
                deskripsi.setText(currentWeather.getWeather().get(0).getDescription());
                double sc = currentWeather.getMain().getTempMax();
                temperatur.setText(String.valueOf(sc) +"atm");
                angin.setText(String.valueOf(currentWeather.getWind().getSpeed()));

            }


            @Override
            public void onFailure(Throwable throwable) {
                Log.v(TAG, throwable.getMessage());
            }
        });

    }
}
