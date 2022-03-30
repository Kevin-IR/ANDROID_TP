package xan.kpint.tp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Handler1 handler1 = null;
    private Handler2 handler2 = null;
    private SQLiteDatabase utilisateur;
    private SQLiteDatabase association;
    private Button valide = null;
    private EditText login = null;
    private EditText mdp = null;
    private EditText confMDp = null;
    private CheckBox lundi = null;
    private CheckBox mardi = null;
    private CheckBox mercredi = null;
    private CheckBox jeudi = null;
    private CheckBox vendredi = null;
    private RadioGroup niveau = null;
    private RadioButton debutant = null;
    private RadioButton intermediaire = null;
    private RadioButton haut = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.valide = (Button) findViewById(R.id.valide);
        this.login = (EditText) findViewById(R.id.pseudo_saisie);
        this.mdp = (EditText) findViewById(R.id.mdp_saisie);
        this.confMDp = (EditText) findViewById(R.id.confirm_mdp_saisie);

        this.niveau = (RadioGroup) findViewById(R.id.radioGroup);
        this.debutant = (RadioButton) findViewById(R.id.debutant);
        this.intermediaire = (RadioButton) findViewById(R.id.intermediaire);
        this.haut = (RadioButton) findViewById(R.id.haut);


        this.lundi = (CheckBox) findViewById(R.id.lundi);
        this.mardi = (CheckBox) findViewById(R.id.mardi);
        this.mercredi = (CheckBox) findViewById(R.id.mercredi);
        this.jeudi = (CheckBox) findViewById(R.id.jeudi);
        this.vendredi = (CheckBox) findViewById(R.id.vendredi);

        this.valide.setOnClickListener(ValideListener);
    }

    private View.OnClickListener ValideListener = new View.OnClickListener() {

        public void onClick(View v) {
            utilisateur = handler1.getWritableDatabase();
            String t0 = login.getText().toString();
            String t1 = mdp.getText().toString();
            String t2 = confMDp.getText().toString();
            String disponibilité = "";
            String niv_sport ="";
            if (lundi.isChecked())
                disponibilité += disponibilité+"lundi ";
            if (mardi.isChecked())
                disponibilité += disponibilité+"mardi ";
            if (mercredi.isChecked())
                disponibilité += disponibilité+"mercredi ";
            if (jeudi.isChecked())
                disponibilité += disponibilité+"jeudi ";
            if (vendredi.isChecked())
                disponibilité += disponibilité+"vendredi";
            if (debutant.isChecked()){
                debutant.getText().toString();
            }
            if (intermediaire.isChecked()){
                intermediaire.getText().toString();
            }
            if (haut.isChecked()){
                haut.getText().toString();
            }

            Cursor curs1 = utilisateur.rawQuery("SELECT pseudo FROM utilisateur WHERE pseudo="+t0+";", null);
            String pseudo = curs1.toString();
            if (pseudo.equals("")){
                if (t1.equals(t2)){
                    Cursor curs = utilisateur.rawQuery("INSERT INTO user ", null);
                    if (curs.getCount() >0){
                        Toast toast = Toast.makeText(MainActivity.this,"Donner Ajouté",Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
                else {
                    Toast toast2 = Toast.makeText(MainActivity.this,"Mot de passe non identique",Toast.LENGTH_SHORT);
                    toast2.show();
                }
            }
            else{
                Toast toast3 = Toast.makeText(MainActivity.this,"Pseudo identique",Toast.LENGTH_SHORT);
                toast3.show();
            }
        }
    };
}