package com.mkt.calculatricetp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tvResultat, tvSolution;
    MaterialButton buttonC, buttonParentheseOuvrante, buttonParentheseFermante;
    MaterialButton buttonDiviser, buttonMultiplier, buttonSoustraire, buttonAdditionner, buttonEgal;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton buttonAc, buttonVirgule;
    double chiffre1,resultat;
    boolean clicOperateur = false;
    boolean update = false;
    String operateur = "";
    public static  final String BUNDLE_STATE_CHIFFRE1= "BUNDLE_STATE_CHIFFRE1";
    public static final String BUNDLE_STATE_CLICOPERATEUR = "BUNDLE_STATE_CLICOPERATEUR";
    public static  final String BUNDLE_STATE_OPERATEUR = "BUNDLE_STATE_OPERATEUR";
    public static  final String  BUNDLE_STATE_UPDATE = "BUNDLE_STATE_UPDATE";
    public static  final String  BUNDLE_STATE_TVRESULTAT = "BUNDLE_STATE_TVRESULTAT";
    public static  final String  BUNDLE_STATE_TVSOLUTION = "BUNDLE_STATE_TVSOLUTION";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResultat = findViewById(R.id.tv_resultat);
        tvSolution = findViewById(R.id.tv_soltution);

        if (savedInstanceState != null) {
            chiffre1 = savedInstanceState.getDouble(BUNDLE_STATE_CHIFFRE1);
            clicOperateur = savedInstanceState.getBoolean(BUNDLE_STATE_CLICOPERATEUR);
            update = savedInstanceState.getBoolean(BUNDLE_STATE_UPDATE);
            operateur = savedInstanceState.getString(BUNDLE_STATE_OPERATEUR);
            tvResultat.setText(savedInstanceState.getString(BUNDLE_STATE_TVRESULTAT));
            tvSolution.setText(savedInstanceState.getString(BUNDLE_STATE_TVSOLUTION));

        } else {

            clicOperateur = false;
            update = true;
            chiffre1 = 0;
            operateur = "";
        }


        affecterId(buttonC,R.id.button_c);

        affecterId(buttonDiviser,R.id.button_diviser);
        affecterId(buttonMultiplier,R.id.button_multiplier);
        affecterId(buttonSoustraire,R.id.button_soustraire);
        affecterId(buttonAdditionner,R.id.button_additionner);
        affecterId(buttonEgal,R.id.button_egal);
        affecterId(button0,R.id.button_0);
        affecterId(button1,R.id.button_1);
        affecterId(button2,R.id.button_2);
        affecterId(button3,R.id.button_3);
        affecterId(button4,R.id.button_4);
        affecterId(button5,R.id.button_5);
        affecterId(button6,R.id.button_6);
        affecterId(button7,R.id.button_7);
        affecterId(button8,R.id.button_8);
        affecterId(button9,R.id.button_9);
        affecterId(buttonAc,R.id.button_ac);
        affecterId(buttonVirgule,R.id.button_virgule);


    }

    void affecterId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
       // tvSolution.setText(buttonText);
        String operation = tvResultat.getText().toString();
        if(buttonText.equals("+")){

            if(clicOperateur){
                chiffre1 = chiffre1 + Double.valueOf(tvResultat.getText().toString()).doubleValue();
                tvResultat.setText(String.valueOf(chiffre1));

            }else{
                chiffre1 = Double.valueOf(tvResultat.getText().toString()).doubleValue();
                clicOperateur = true;
            }
            operateur = "+";
            update = true;
            tvSolution.setText(chiffre1+" + ");
            return;
        }
        if(buttonText.equals("-")){
            if(clicOperateur){
                chiffre1 = chiffre1 - Double.valueOf(tvResultat.getText().toString()).doubleValue();
                tvResultat.setText(String.valueOf(chiffre1));

            }else{
                chiffre1 = Double.valueOf(tvResultat.getText().toString()).doubleValue();
                clicOperateur = true;
            }
            operateur = "-";
            update = true;
            tvSolution.setText(chiffre1+" - ");
            return;
        }
        if(buttonText.equals("x")){
            if(clicOperateur){
                chiffre1 = chiffre1 * Double.valueOf(tvResultat.getText().toString()).doubleValue();
                tvResultat.setText(String.valueOf(chiffre1));

            }else{
                chiffre1 = Double.valueOf(tvResultat.getText().toString()).doubleValue();
                clicOperateur = true;
            }
            operateur = "*";
            update = true;
            tvSolution.setText(chiffre1+" * ");
            return;
        }
        if(buttonText.equals("/")){
            if(clicOperateur){
                try{
                    chiffre1 = chiffre1 / Double.valueOf(tvResultat.getText().toString()).doubleValue();

                    tvResultat.setText(String.valueOf(chiffre1));

                    if (Double.isInfinite(chiffre1)||Double.isNaN(chiffre1)) {
                        tvSolution.setText("Division par zero est interdite");
                    }

                    }catch(ArithmeticException e){
                    tvResultat.setText("0");
                }
            }else{
                chiffre1 = Double.valueOf(tvResultat.getText().toString()).doubleValue();
                clicOperateur = true;
            }
            operateur = "/";
            update = true;
            tvSolution.setText(chiffre1+" / ");
            return;
        }
        if(buttonText.equals("AC")){
            tvSolution.setText("");
            tvResultat.setText("0");
            clicOperateur = false;
            update = true;
            chiffre1 = 0;
            operateur = "";
            return;
        }
        if(buttonText.equals("=")){
            calcul();
            update = true;
            clicOperateur = false;
            return;
        }
        if(buttonText.equals("C")){
            if(operation.length()>=1)
            operation = operation.substring(0,operation.length()-1);
            tvResultat.setText(operation);
        }
        else {

            chiffreClick(buttonText);
        }
    }

    public void chiffreClick(String str) {
        if(update){
            update = false;
        }else{
            if(!tvResultat.getText().equals("0"))
                str = tvResultat.getText() + str;
        }
        tvResultat.setText(str);



    }

    //Voici la méthode qui fait le calcul qui a été demandé par l'utilisateur
    private void calcul(){
        if(operateur.equals("+")){
            chiffre1 = chiffre1 + Double.valueOf(tvResultat.getText().toString()).doubleValue();
            tvResultat.setText(String.valueOf(chiffre1));
        }

        if(operateur.equals("-")){
            chiffre1 = chiffre1 - Double.valueOf(tvResultat.getText().toString()).doubleValue();
            tvResultat.setText(String.valueOf(chiffre1));
        }

        if(operateur.equals("*")){
            chiffre1 = chiffre1 * Double.valueOf(tvResultat.getText().toString()).doubleValue();
            tvResultat.setText(String.valueOf(chiffre1));
        }

        if(operateur.equals("/")){
            try{
                chiffre1 = chiffre1 / Double.valueOf(tvResultat.getText().toString()).doubleValue();
                if (Double.isInfinite(chiffre1)||Double.isNaN(chiffre1)) {
                    tvSolution.setText("Division par zero est interdite");
                    chiffre1=0;
                }
                tvResultat.setText(String.valueOf(chiffre1));
            }catch(ArithmeticException e){

                tvResultat.setText("0");
            }
        }
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putDouble(BUNDLE_STATE_CHIFFRE1, chiffre1);
        outState.putBoolean(BUNDLE_STATE_CLICOPERATEUR, clicOperateur);
        outState.putBoolean(BUNDLE_STATE_UPDATE, update);
        outState.putString(BUNDLE_STATE_OPERATEUR, operateur);
        outState.putString(BUNDLE_STATE_TVRESULTAT,tvResultat.getText().toString());
        outState.putString(BUNDLE_STATE_TVSOLUTION,tvSolution.getText().toString());

    }

}