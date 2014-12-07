package controladorgastos.ddmo.com.controladordegastos;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    EditText valorGasto, dataGasto;
    private Spinner categorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        valorGasto = (EditText) findViewById(R.id.despesa);
        dataGasto = (EditText) findViewById(R.id.despesaData);
        categorias = (Spinner) findViewById(R.id.categoria);

        TabHost tabHost = (TabHost) findViewById(R.id.tabHost2);

        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("gerarDespesa");
        tabSpec.setContent(R.id.gastoAdicionar);
        tabSpec.setIndicator("Cadastrar gastos");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("consultarDespesa");
        tabSpec.setContent(R.id.gastoMostrar);
        tabSpec.setIndicator("Consultar Gastos");
        tabHost.addTab(tabSpec);


        final Button salvarGasto = (Button) findViewById(R.id.salvarGasto);
        salvarGasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Despesa salva com sucesso!", Toast.LENGTH_SHORT);
            }
        });

        valorGasto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                salvarGasto.setEnabled(!valorGasto.getText().toString().trim().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
