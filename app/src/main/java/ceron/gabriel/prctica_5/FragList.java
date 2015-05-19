package ceron.gabriel.prctica_5;


        import android.app.Activity;
        import android.app.FragmentManager;
        import android.app.FragmentTransaction;
        import android.support.v4.app.ListFragment;
        import android.support.v4.app.Fragment;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.Toast;
        import android.util.Log;

public class FragList extends ListFragment {

    onTituloSelectedListener mCallback;

    // Interface que la Activity contenedora debe implementar
    // para poder tener comunicaci�n
    public interface onTituloSelectedListener {
        public void onTituloSelected(int position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        // Establecemos el Adapter cuando se crea el Fragment
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, Contenido.titulos));
    }

    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
        // Inicializamos nuestra variable de referencia del tipo
        // onTituloSelectedListener junto con el valor del objeto
        // activity que debe ser una Activity que implemente esta interface
        try {
            mCallback = (onTituloSelectedListener) activity;
        } catch (ClassCastException e) {
            Log.d("ClassCastException",
                    "La Activity debe implementar esta Interface");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);

        // Llamamos al m�todo que implementa la Activity pasandole
        // la posicion del elemento que hemos pulsado
        mCallback.onTituloSelected(position);
    }

}