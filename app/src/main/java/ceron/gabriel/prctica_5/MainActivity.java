package ceron.gabriel.prctica_5;

import android.app.ListFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends FragmentActivity implements
        FragList.onTituloSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Comprobamos si estamos usando la version con
        // con el FrameLayout
        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            // Establecemos el ListFragment en el caso de que sea la version
            // de un panel (SmartPhone)
            FragList fragment = new FragList();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, null).commit();
        }
    }

    @Override
    public void onTituloSelected(int position) {
        // TODO Auto-generated method stub

        // Comprobamos si tenemos disponible el Fragment de
        // contenido
        ContenidoFragment contFragment = (ContenidoFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contenidoFragment);

        if (contFragment != null) {
            // Si está disponible, estamos en la versión de 2 paneles
            contFragment.actualizarContenido(position);
        } else {
            // Si no está disponible, estamos en el layout
            // del FrameLayout, y tenemos que cambiar los Fragment
            contFragment = new ContenidoFragment();
            Bundle args = new Bundle();

            // Establecemos la posición que hemos elegido
            args.putInt(ContenidoFragment.POSICION, position);
            contFragment.setArguments(args);

            // Reemplazamos el Fragment que había por el nuevo
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, contFragment)
                    .addToBackStack(null).commit();
        }
    }

}