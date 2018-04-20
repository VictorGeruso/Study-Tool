package loovsoft.com.br.studytool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    private EditText nomeEstudante;
    private Button botaoIniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nomeEstudante = findViewById(R.id.MainActivity_NomeEstudanteID);
        botaoIniciar = findViewById(R.id.MainActivity_BotaoIniciarAppID);

        botaoIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StudyToolActivity.class);

                intent.putExtra("nomeEstudante", nomeEstudante.getText().toString());

                startActivity(intent);

                finish();
            }
        });

    }
}
