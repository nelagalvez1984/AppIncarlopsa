package incarlopsa.com.appincarlopsa;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

/**
 * Created by Nela on 04/11/2017.
 */

class DrawerLateral {

    //Propiedades
    private static final DrawerLateral ourInstance = new DrawerLateral();
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;


    static DrawerLateral getInstance() {
        return ourInstance;
    }

    private DrawerLateral() {
        navigationView = null;
    }

    public NavigationView getNavigationView() {
        return navigationView;
    }

    public void setNavigationView(NavigationView navigationView) {
        this.navigationView = navigationView;
    }

    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

    public void setDrawerLayout(DrawerLayout drawerLayout) {
        this.drawerLayout = drawerLayout;
    }

    private void setup() {
        setup(navigationView);
    }

    private void setup(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.item_navigation_drawer_publicaciones: //Ver publicaciones
                                menuItem.setChecked(true);
                                //Hacer cosas
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_chats: //Ver chats
                                menuItem.setChecked(true);
                                //Hacer cosas
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_nuevo_chat: //Crear mensaje
                                menuItem.setChecked(true);
                                //Hacer cosas
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_nueva_publicacion: //Crear comentario
                                menuItem.setChecked(true);
                                //Hacer cosas
                                drawerLayout.closeDrawer(GravityCompat.START);

                                return true;
                        }
                        return true;
                    }
                });
    }

}
