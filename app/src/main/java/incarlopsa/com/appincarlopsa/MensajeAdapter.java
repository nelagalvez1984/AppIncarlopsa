package incarlopsa.com.appincarlopsa;

/**
 * Created by David on 06/11/2017.
 */

public class MensajeAdapter {
    private Chat<Mensaje> mMensajes;


    public MensajeAdapter(Chat<Mensaje> mensajes) {
        mMensajes = mensajes;
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
                    mListener.onItemClick(mMensajes.get(position), position);
                }
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mMensajes.get(position));
    }

    @Override
    public int getItemCount() {
        return mMensajes.size();
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
}
