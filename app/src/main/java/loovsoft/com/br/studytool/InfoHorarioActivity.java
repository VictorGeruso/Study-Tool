package loovsoft.com.br.studytool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class InfoHorarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_horario);

        TableLayout tableLayout = findViewById(R.id.infoHorario_tabela);
        final TableRow row = new TableRow(this);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.FILL_PARENT
        );
        row.setLayoutParams(lp);

        TableRow.LayoutParams nomeParams = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT);
        nomeParams.setMargins(0, 5, 0, 5);
        nomeParams.width = 460;
        TextView nome = new TextView(this);
        nome.setText(" Teste Nome " );
        nome.setLayoutParams(nomeParams);

        TableRow.LayoutParams quantParams = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT);
        quantParams.setMargins(0, 5, 0, 5);
        quantParams.width = 90;
        TextView quant = new TextView(this);
        quant.setText(" Teste Quantidade" );
        quant.setLayoutParams(quantParams);

        row.addView(nome);
        row.addView(quant);

        tableLayout.addView(row);
    }
}
