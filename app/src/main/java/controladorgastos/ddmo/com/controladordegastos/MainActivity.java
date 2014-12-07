package controladorgastos.ddmo.com.controladordegastos;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    EditText valorGasto, dataGasto;
    private Spinner categorias;
    List<ListaGastos> listaGastos = new ArrayList<ListaGastos>();

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
                addGasto(valorGasto.getText().toString(), dataGasto.getText().toString(), categorias.getSelectedItem().toString());
                Toast.makeText(getApplicationContext(), "Gasto de R$" + valorGasto.getText().toString()+" foi adicionado!", Toast.LENGTH_SHORT).show();
            }
        });

        valorGasto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @TargetApi(Build.VERSION_CODES.GINGERBREAD)
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                salvarGasto.setEnabled(!valorGasto.getText().toString().trim().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void addGasto(String valor, String data, String categoria){
        listaGastos.add(new ListaGastos(valor, data, categoria));
    }

    private class ListaGastosAdapter extends ArrayAdapter<ListaGastos>{
        public ListaGastosAdapter(){
            super(MainActivity.this, R.layout.listview_gastos, listaGastos);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent){
            if (view == null)
                view = getLayoutInflater().inflate(R.layout.listview_gastos, parent, false);

            ListaGastos gastoAtual = listaGastos.get(position);
            TextView valor = (TextView) view.findViewById(R.id.despesa);
            valor.setText(gastoAtual.get_valor());
            TextView data = (TextView) view.findViewById(R.id.despesaData);
            data.setText(gastoAtual.get_data());
            TextView categoria = (TextView) view.findViewById(R.id.categoria);
            categoria.setText(gastoAtual.get_categoria());

            return view;
        }
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
