package incarlopsa.com.appincarlopsa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class VCabeceraPublicacion extends AppCompatActivity implements IVista {

    private RecyclerView recycler;
    private ArrayList<DataBaseItem> resultados;
    private RecyclerView.LayoutManager layoutManager;
    private AdapterTopic adapterPublicacion;
    private HiloParaRead hiloParaRead;
    private Intent intent;
    private SingleTostada tostada = SingleTostada.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vcabecera_publicacion);
        inicializarVista();
    }

    @Override
    public void inicializarVista() {

        tostada.setContexto(this);
        resultados = new ArrayList<>();

        recycler = (RecyclerView) findViewById(R.id.recyPublicacion);

        layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
        recycler.setItemAnimator(new DefaultItemAnimator());

        //Publicaciones
        try {
            hiloParaRead = new HiloParaRead(new DAOPublicacion());
            resultados = hiloParaRead.execute(DAME_LOS_TOPIC).get();
        } catch (Exception e) {
            tostada.errorConexionBBDD();
        }

        adapterPublicacion = new AdapterTopic(resultados, new DAOPublicacion(), TOPIC_PUBLICACION);
        recycler.setAdapter(adapterPublicacion);

        adapterPublicacion.setOnItemListener(new AdapterTopic.OnItemClickListener() {
            @Override
            public void onItemClick(DataBaseItem item, int position) {
                Publicacion p = (Publicacion) item;

                intent = new Intent(VCabeceraPublicacion.this, VPublicacion.class);
                intent.putExtra("idPublicacion", p.getId());
                startActivity(intent);

            }
        });

    }

    @Override
    public void onClick(View view) {

    }
}