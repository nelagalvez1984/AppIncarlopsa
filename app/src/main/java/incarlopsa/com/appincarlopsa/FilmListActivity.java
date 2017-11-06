package incarlopsa.com.appincarlopsa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class FilmListActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    FilmAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_list);

        mRecyclerView = (RecyclerView)findViewById(R.id.lista_films);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new FilmAdapter(FilmDataSource.films);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemListener(new FilmAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Film f, int position) {
                Intent intent = new Intent(FilmListActivity.this, FilmDataActivity.class);
                intent.putExtra(FilmDataActivity.EXTRA_FILM_INDEX, position);
                startActivity(intent);
            }
        });
    }

}