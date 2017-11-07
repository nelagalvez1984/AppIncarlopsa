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
    private AdapterChat adapterChatEntrantes;
    private AdapterChat adapterChatSalientes;
    private HiloParaRead hiloParaRead;
    private Intent intent;

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

        layoutManagerEntrantes = new LinearLayoutManager(this);
        recyclerEntrantes.setLayoutManager(layoutManagerEntrantes);
        recyclerEntrantes.setItemAnimator(new DefaultItemAnimator());

        //Chats entrantes
        hiloParaRead = new HiloParaRead(new DAOChat());
        try {
            resultadosEntrantes = hiloParaRead.execute(DAME_LOS_TOPIC_HACIA_MI).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        adapterChatEntrantes = new AdapterChat(resultadosEntrantes);
        recyclerEntrantes.setAdapter(adapterChatEntrantes);

        adapterChatEntrantes.setOnItemListener(new AdapterChat.OnItemClickListener() {
            @Override
            public void onItemClick(DataBaseItem item, int position) {
                Chat c = (Chat)item;

                Intent intent = new Intent(VCabeceraChat.this, VChat.class);
                intent.putExtra("idChat", c.getId());
                intent.putExtra("tituloChat", c.getTitulo());
                startActivity(intent);

            }
        });

        //Chats salientes
        layoutManagerSalientes = new LinearLayoutManager(this);
        recyclerSalientes.setLayoutManager(layoutManagerSalientes);
        recyclerSalientes.setItemAnimator(new DefaultItemAnimator());

        hiloParaRead = new HiloParaRead(new DAOChat());
        try {
            resultadosSalientes = hiloParaRead.execute(DAME_LOS_TOPIC_DESDE_MI).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        adapterChatSalientes = new AdapterChat(resultadosSalientes);
        recyclerSalientes.setAdapter(adapterChatSalientes);

        adapterChatSalientes.setOnItemListener(new AdapterChat.OnItemClickListener() {
            @Override
            public void onItemClick(DataBaseItem item, int position) {
                Chat c = (Chat)item;

                Intent intent = new Intent(VCabeceraChat.this, VChat.class);
                intent.putExtra("idChat", c.getId());
                intent.putExtra("tituloChat", c.getTitulo());
                startActivity(intent);

            }
        });

    }

    @Override
    public void onClick(View view) {

    }
}
