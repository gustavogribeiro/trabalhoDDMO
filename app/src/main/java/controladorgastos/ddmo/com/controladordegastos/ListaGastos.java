package controladorgastos.ddmo.com.controladordegastos;

/**
 * Created by Gustavo on 12/7/2014.
 */
public class ListaGastos {

    private String _valor, _data, _categoria;

    public ListaGastos(String valor, String data, String categoria){
        _valor = valor;
        _data = data;
        _categoria = categoria;
    }

    public String get_valor(){
        return _valor;
    }
    public String get_data(){
        return _data;
    }
    public String get_categoria(){
        return _categoria;
    }

}
