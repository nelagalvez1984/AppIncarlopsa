package incarlopsa.com.appincarlopsa;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

public class DrawerLateral {

    private Context contexto;
    private DrawerLayout drawerLayout;
    Intent intent;

    public DrawerLateral(Context contexto, DrawerLayout drawerLayout){
        this.contexto = contexto;
        this.drawerLayout = drawerLayout;
    }

    /*
    1.- A nivel de XML hay que agregar esto con los ID que correspondan:
    <android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
                                        xmlns:app="http://schemas.android.com/apk/res-auto"
                                        android:id="@+id/navigation_drawer_layout"
                                        android:layout_width="match_parent"
                                        android:layout_height="match_parent"
                                        android:fitsSystemWindows="@bool/fitsSystemWindows">

    //COSAS
    //MAS COSAS

    <android.support.design.widget.NavigationView
        android:id="@+id/navigation_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:fitsSystemWindows="@bool/fitsSystemWindows"
        app:headerLayout="@layout/navigation_drawer_header"
        app:menu="@menu/navigation_drawer_menu"
        app:theme="@style/NavigationViewTheme" />

    </android.support.v4.widget.DrawerLayout>


    2.- Se asocia el drawerLayout con su id en el XML:
    DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer_layout);

    3.- Se crea el DrawerLateral
    public DrawerLateral(Context contexto, DrawerLayout drawerLayout)

    4.- Se crea el navigationView para pasarselo como parametro al DrawerLateral en el setup
    NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        if (navigationView != null) {
        drawerLateral.setupDrawer(navigationView);
    }
    drawerLateral.setupDrawer(navigationView);

    5.- Si se quiere abrir el drawer ante un determinado click, se saca con esta sentencia:
    drawerLayout.openDrawer(GravityCompat.START);

    */

    public void setupDrawer(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.item_navigation_drawer_publicaciones: //Ver publicaciones
                                menuItem.setChecked(true);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                intent = new Intent(contexto, VCabeceraPublicacion.class);
                                contexto.startActivity(intent);
                                return true;
                            case R.id.item_navigation_drawer_chats: //Ver chats
                                menuItem.setChecked(true);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                intent = new Intent(contexto, VCabeceraChat.class);
                                contexto.startActivity(intent);
                                return true;
                            case R.id.item_navigation_drawer_nuevo_chat: //Crear chat
                                menuItem.setChecked(true);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                intent = new Intent(contexto, VCrearNuevoChat.class);
                                contexto.startActivity(intent);

                                return true;
                            case R.id.item_navigation_drawer_nueva_publicacion: //Crear publicacion
                                menuItem.setChecked(true);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                intent = new Intent(contexto, VCrearNuevaPublicacion.class);
                                contexto.startActivity(intent);
                                return true;
                        }
                        return true;
                    }
                });
    }
}
