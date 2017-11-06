package incarlopsa.com.appincarlopsa;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by malozano on 25/12/16.
 */

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.ViewHolder> {

    private List<Film> mFilms;

    public FilmAdapter(List<Film> films) {
        mFilms = films;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_film, parent, false);
        final ViewHolder holder = new ViewHolder(v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if(mListener!=null) {
                    mListener.onItemClick(mFilms.get(position), position);
                }
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mFilms.get(position));
    }

    @Override
    public int getItemCount() {
        return mFilms.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitulo;
        public TextView mDirector;
        public ImageView mPoster;

        public ViewHolder(View v) {
            super(v);
            mTitulo = (TextView)v.findViewById(R.id.txtFilmItemTitle);
            mDirector = (TextView)v.findViewById(R.id.txtFilmItemDirector);
            mPoster = (ImageView)v.findViewById(R.id.imgPoster);
        }

        public void bind(Film f) {
            mTitulo.setText(f.title);
            mDirector.setText(f.director);
            mPoster.setImageResource(f.imageResId);
        }
    }

    // Listener para la lista

    public interface OnItemClickListener {
        public void onItemClick(Film f, int position);
    }
    private OnItemClickListener mListener;

    public void setOnItemListener(OnItemClickListener listener) {
        mListener = listener;
    }
}