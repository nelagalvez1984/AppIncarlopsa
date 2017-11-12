package incarlopsa.com.appincarlopsa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class VLogin extends AppCompatActivity implements IVista{

    //Propiedades
    private EditText etUsuario;
    private EditText etPassword;
    private Button btnConectar;
    private SingleCredenciales credenciales;
    private HiloParaLogin hiloParaLogin;
    private Intent intent;
    private SingleTostada tostada = SingleTostada.getInstance();
    private SingleConexion conexion = SingleConexion.getInstance();
    private ArrayList<DataBaseItem> resultados;

    @Override
    public void inicializarVista() {
        etUsuario = (EditText)findViewById(R.id.etUsuario);
        etPassword = (EditText)findViewById(R.id.etPassword);
        btnConectar = (Button) findViewById(R.id.btnConectar);
        btnConectar.setOnClickListener(this);
        credenciales = SingleCredenciales.getInstance();
        tostada.setContexto(this);

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.btnConectar:
                if (etUsuario.length()> 0 && etPassword.length() >0) {

                    Boolean conexionOK = false;
                    tostada.conectando();

                    //Rellenar la parte de login de las credenciales
                    credenciales.setLogin(etUsuario.getText().toString());
                    credenciales.setPassword(etPassword.getText().toString());
                    credenciales.setUsername(etUsuario.getText().toString());

                    //1.- Comprobar si hay que deslogarse antes
                    if (conexion.conectar() != null){ //Si ya habia una conexion anterior, se cierra primeramente
                        conexion.desconexionDelSistema();
                    }

                    //Intentar conexion
                    hiloParaLogin = new HiloParaLogin();
                    try {
                        conexionOK = hiloParaLogin.execute().get();
                        if (conexionOK) { //El usuario/pass es correcto!

                            //Rellenar el perfil
                            HiloParaRead hilo = new HiloParaRead(new DAOUsuario());
                            resultados = new ArrayList<>();
                            resultados = hilo.execute(credenciales).get();
                            if (resultados.size()>0){
                                Usuario u = (Usuario)resultados.get(0);
                                credenciales.setIdUsuario(u.getIdUsuario());
                                credenciales.setNombre(u.getNombre());
                                credenciales.setApellidos(u.getApellidos());
                                credenciales.setDni(u.getDni());
                                credenciales.setTipoEmpleado(u.getTipoEmpleado());
                                credenciales.setFotoBytes(u.getFoto());
                            }else{
                                throw new EXCErrorBBDD();
                            }

                            intent = new Intent(this, VGeneral.class);
                            startActivity(intent);
                        } else {
                            throw new EXCErrorLogin();
                        }
                    } catch (EXCErrorLogin e) {
                        tostada.errorLogin();
                    } catch (EXCErrorBBDD e) {
                        tostada.errorConexionBBDD();
                    } catch (Exception e) {
                        tostada.errorConexionBBDD();
                    }

                }else{
                    tostada.errorIntroducirDatos();
                }
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicializarVista();
    }
}
