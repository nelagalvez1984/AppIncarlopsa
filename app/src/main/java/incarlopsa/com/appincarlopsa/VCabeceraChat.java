package incarlopsa.com.appincarlopsa;

import android.content.Intent;
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
    private ArrayList<DataBaseItem> resultadosSalientes;
    private RecyclerView recyclerSalientes;
    private RecyclerView.LayoutManager layoutManagerEntrantes;
    private RecyclerView.LayoutManager layoutManagerSalientes;
    private AdapterTopic adapterChatEntrantes;
    private AdapterTopic adapterChatSalientes;
    private HiloParaRead hiloParaRead;
    private Intent intent;
    private SingleTostada tostada = SingleTostada.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vcabecera_chat);
        inicializarVista();
    }

    @Override
    public void inicializarVista() {

        tostada.setContexto(this);
        resultadosEntrantes = new ArrayList<>();
        resultadosSalientes = new ArrayList<>();

        recyclerEntrantes = (RecyclerView)findViewById(R.id.recyEntrantesChat);
        recyclerSalientes = (RecyclerView)findViewById(R.id.recySalientesChat);

        //Chats entrantes
        layoutManagerEntrantes = new LinearLayoutManager(this);
        recyclerEntrantes.setLayoutManager(layoutManagerEntrantes);
        recyclerEntrantes.setItemAnimator(new DefaultItemAnimator());

        try {
            hiloParaRead = new HiloParaRead(new DAOChat());
            resultadosEntrantes = hiloParaRead.execute(DAME_LOS_TOPIC_HACIA_MI).get();
            adapterChatEntrantes = new AdapterTopic(resultadosEntrantes, new DAOChat(), TOPIC_CHAT_ENTRANTE);
            recyclerEntrantes.setAdapter(adapterChatEntrantes);
            recyclerEntrantes.scrollToPosition(adapterChatEntrantes.ultimaPosicion());

            adapterChatEntrantes.setOnItemListener(new AdapterTopic.OnItemClickListener() {
                @Override
                public void onItemClick(DataBaseItem item, int position) {
                    Chat c = (Chat)item;

                    intent = new Intent(VCabeceraChat.this, VChat.class);
                    intent.putExtra("idChat", c.getId());
                    intent.putExtra("tituloChat", c.getTitulo());
                    intent.putExtra("idAutor", c.getIdUsuario());
                    intent.putExtra("idDestino", c.getIdUsuarioDestino());

                    startActivity(intent);
                }
            });
        } catch (Exception e) {
            tostada.errorConexionBBDD();
        }

        //Chats salientes
        layoutManagerSalientes = new LinearLayoutManager(this);
        recyclerSalientes.setLayoutManager(layoutManagerSalientes);
        recyclerSalientes.setItemAnimator(new DefaultItemAnimator());

        try {
            hiloParaRead = new HiloParaRead(new DAOChat());
            resultadosSalientes = hiloParaRead.execute(DAME_LOS_TOPIC_DESDE_MI).get();
            adapterChatSalientes = new AdapterTopic(resultadosSalientes, new DAOChat(), TOPIC_CHAT_SALIENTE);
            recyclerSalientes.setAdapter(adapterChatSalientes);
            recyclerSalientes.scrollToPosition(adapterChatSalientes.ultimaPosicion());

            adapterChatSalientes.setOnItemListener(new AdapterTopic.OnItemClickListener() {
                @Override
                public void onItemClick(DataBaseItem item, int position) {
                    Chat c = (Chat)item;

                    Intent intent = new Intent(VCabeceraChat.this, VChat.class);
                    intent.putExtra("idChat", c.getId());
                    intent.putExtra("tituloChat", c.getTitulo());
                    intent.putExtra("idAutor", c.getIdUsuario());
                    intent.putExtra("idDestino", c.getIdUsuarioDestino());
                    startActivity(intent);

                }
            });
        } catch (Exception e) {
            tostada.errorConexionBBDD();
        }
    }

    @Override
    public void onClick(View view) {

    }
}
