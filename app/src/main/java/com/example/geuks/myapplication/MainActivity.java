package com.example.geuks.myapplication;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.DatePickerDialog;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.solver.widgets.WidgetContainer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Affiche le nom de l'application
        Log.i("PRINT_Information",getResources().getString(R.string.app_name));

        //Déclaration des variables
        TextView dateView = (TextView) findViewById(R.id.tDateView);
        final EditText eNom= (EditText) findViewById(R.id.edit_Nom);
        final EditText ePrenom= (EditText) findViewById(R.id.edit_Prenom);
        final EditText eEmail = (EditText) findViewById(R.id.edit_Email);
        final RadioButton rFemale = (RadioButton) findViewById(R.id.rFemale);
        final RadioButton rMale = (RadioButton) findViewById(R.id.rMale);
        final EditText eHours = (EditText) findViewById(R.id.edit_Hours);
        final Button bInit = (Button) findViewById(R.id.bReset);
        final CheckBox cLanguage1 = (CheckBox) findViewById(R.id.cLanguage1);
        final CheckBox cLanguage2 = (CheckBox) findViewById(R.id.cLanguage2);
        final CheckBox cLanguage3 = (CheckBox) findViewById(R.id.cLanguage3);
        final CheckBox cLanguage4 = (CheckBox) findViewById(R.id.cLanguage4);
        final Button bValidate = (Button) findViewById(R.id.bOk);


        //1.1 Affiche la date
        Date currentTime = Calendar.getInstance().getTime();

        dateView.setText(currentTime.toString());

        //Affichage Email
        eEmail.setKeyListener(null);
        eNom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                eEmail.setText(eNom.getText().toString().toLowerCase()+".");
            }
        });

        ePrenom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                eEmail.setText(eNom.getText().toString().toLowerCase()+"."+ePrenom.getText().toString().toLowerCase()+"@ludus-academie.com");
            }
        });

        //Choix sexe
        rMale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    Toast.makeText(MainActivity.this,"Bienvenue monsieur "+eNom.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });

        rFemale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    Toast.makeText(MainActivity.this,"Bienvenue madame "+eNom.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });

        eHours.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //Heures joués
                if(eHours.getText().toString().length()>0){
                    if(Integer.parseInt(eHours.getText().toString())<2){
                        Toast toast = Toast.makeText(getApplicationContext(),"Correct", Toast.LENGTH_SHORT);
                        TextView toastMessage = (TextView) toast.getView().findViewById(android.R.id.message);
                        toastMessage.setTextColor(Color.GREEN);
                        toast.show();
                    }
                    else if(Integer.parseInt(eHours.getText().toString())>=2 && Integer.parseInt(eHours.getText().toString())<6){
                        Toast toast = Toast.makeText(getApplicationContext(),"Attention", Toast.LENGTH_SHORT);
                        TextView toastMessage = (TextView) toast.getView().findViewById(android.R.id.message);
                        toastMessage.setTextColor(Color.YELLOW);
                        toast.show();
                    }
                    else{
                        Toast toast = Toast.makeText(getApplicationContext(),"Addict", Toast.LENGTH_LONG);
                        TextView toastMessage = (TextView) toast.getView().findViewById(android.R.id.message);
                        toastMessage.setTextColor(Color.RED);
                        toast.show();
                    }
                }
            }
        });

        //Init button
        bInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eNom.setText(null);
                ePrenom.setText(null);
                eEmail.setText(null);
                eHours.setText(null);
                cLanguage1.setChecked(false);
                cLanguage2.setChecked(false);
                cLanguage3.setChecked(false);
                cLanguage4.setChecked(false);
            }
        });

        //Valider
        bValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("PRINT_Nom",eNom.getText().toString());
                Log.i("PRINT_Prenom",ePrenom.getText().toString());
                Log.i("PRINT_Email",eEmail.getText().toString());
                if(rMale.isChecked()){
                    Log.i("PRINT_Sexe", getResources().getString(R.string.string_male));
                }
                else if(rFemale.isChecked()) {
                    Log.i("PRINT_Sexe", getResources().getString(R.string.string_female));
                }
                Log.i("PRINT_Hours","Heures joués :"+ eHours.getText().toString());
                if(cLanguage1.isChecked())
                    Log.i("PRINT_Language",getResources().getString(R.string.string_language1).toString());
                if(cLanguage2.isChecked())
                    Log.i("PRINT_Language",getResources().getString(R.string.string_language2).toString());
                if(cLanguage3.isChecked())
                    Log.i("PRINT_Language",getResources().getString(R.string.string_language3).toString());
                if(cLanguage4.isChecked())
                    Log.i("PRINT_Language",getResources().getString(R.string.string_language4).toString());

            }
        });





    }

}
