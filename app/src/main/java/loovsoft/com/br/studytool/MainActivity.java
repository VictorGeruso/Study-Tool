package loovsoft.com.br.studytool;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    private EditText nomeEstudante;
    private Button botaoIniciar;
    private SecurityPreferences mSecurityPreferences;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSecurityPreferences = new SecurityPreferences(this);
        if(!mSecurityPreferences.getStoreString("ESTUDANTE").isEmpty()){
            intent = new Intent(MainActivity.this, StudyToolActivity.class);
            startActivity(intent);
            finish();
            return;
        } else {
            logar();
        }

    }

    public void logar(){
        setContentView(R.layout.activity_main);
        this.mSecurityPreferences = new SecurityPreferences(this);

        nomeEstudante = findViewById(R.id.MainActivity_NomeEstudanteID);
        botaoIniciar = findViewById(R.id.MainActivity_BotaoIniciarAppID);

        botaoIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nomeEstudante.getText().length() == 0) {
                    Toast.makeText(MainActivity.this, "Por favor, informe seu nome", Toast.LENGTH_SHORT).show();
                }else {
                    intent = new Intent(MainActivity.this, StudyToolActivity.class);
                    mSecurityPreferences.storeString("ESTUDANTE",nomeEstudante.getText().toString());
                    startActivity(intent);
                    finish();
                }
            }
        });
    }



}
