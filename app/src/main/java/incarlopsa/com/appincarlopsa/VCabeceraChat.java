package incarlopsa.com.appincarlopsa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.util.ArrayList;

public class VCabeceraChat extends AppCompatActivity implements IVista{

    private RecyclerView recyclerEntrantes;
    private ArrayList<DataBaseItem> resultadosEntrantes;
    private RecyclerView recyclerSalientes;
    private RecyclerView.LayoutManager layoutManager;
    private ChatAdapter chatAdapterEntrantes;
    private HiloParaRead hiloParaRead;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vcabecera_chat);
        inicializarVista();
    }

    @Override
    public void inicializarVista() {
        recyclerEntrantes = (RecyclerView)findViewById(R.id.recyEntrantesChat);
        recyclerSalientes = (RecyclerView)findViewById(R.id.recySalientesChat);

        layoutManager = new LinearLayoutManager(this);
        recyclerEntrantes.setLayoutManager(layoutManager);
        recyclerEntrantes.setItemAnimator(new DefaultItemAnimator());

        hiloParaRead = new HiloParaRead(new DAOChat());
        try {
            resultadosEntrantes = hiloParaRead.execute(DAME_LOS_TOPIC_HACIA_MI).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        chatAdapterEntrantes = new ChatAdapter(resultadosEntrantes);
        recyclerEntrantes.setAdapter(chatAdapterEntrantes);

        chatAdapterEntrantes.setOnItemListener(new ChatAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DataBaseItem item, int position) {
                /*
                Intent intent = new Intent(FilmListActivity.this, FilmDataActivity.class);
                intent.putExtra(FilmDataActivity.EXTRA_FILM_INDEX, position);
                startActivity(intent);
                */
            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}
