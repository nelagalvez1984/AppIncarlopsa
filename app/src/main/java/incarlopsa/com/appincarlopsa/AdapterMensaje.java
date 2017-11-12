package incarlopsa.com.appincarlopsa;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterMensaje extends RecyclerView.Adapter<AdapterMensaje.ViewHolder> {

    private ArrayList<DataBaseItem> listaMensajes;
    private Usuario usuarioAutor;
    private Usuario usuarioDestino;
    private SingleCredenciales credenciales = SingleCredenciales.getInstance();
    private Mensaje mensajeAnterior;

    public AdapterMensaje(ArrayList<DataBaseItem> listaCabecera, Usuario usuarioAutor, Usuario usuarioDestino) {
        this.listaMensajes = listaCabecera;
        this.usuarioAutor = usuarioAutor;
        this.usuarioDestino = usuarioDestino;
        mensajeAnterior = new Mensaje();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mensaje, parent, false);
        final ViewHolder viewHolder = new ViewHolder(v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                if(mListener!=null) {
                    mListener.onItemClick(listaMensajes.get(position), position);
                }
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Mensaje mensaje = (Mensaje)listaMensajes.get(position);

        //Recuperar el usuario que ha creado el chat
        ArrayList<DataBaseItem> resultados = new ArrayList<>();

        Usuario usuarioAux;
        if (mensaje.getIdUsuario() == usuarioAutor.getIdUsuario()){
            usuarioAux = usuarioAutor;
        }else{ //Por si acaso
            usuarioAux = usuarioDestino;
        }

        String fecha = mensaje.getFecha();
        String hora = mensaje.getHora();
        String contenidoMensaje = mensaje.getMensaje();
        String autor = usuarioAux.getNombre() + " "
                + usuarioAux.getApellidos();

        holder.hora.setText(hora);
        holder.contenidoMensaje.setText(contenidoMensaje);
        holder.leido.setChecked(mensaje.getLeidoPorDestino());
        holder.leido.setEnabled(false);

        //Accesos directos...
        View cardView = holder.v;
        Resources recursos = holder.v.getResources();

        //Personalizacion: Cambiar el color y alineacion si me escribe alguien
        if (mensaje.getIdUsuario() != credenciales.getIdUsuario()){ // Mensaje entrante, alineado a la izquierda
            cardView.setBackground(recursos.getDrawable(R.drawable.mensaje_degradado_entrante));
            holder.contenidoMensaje.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            holder.leido.setVisibility(View.VISIBLE);
            personalizar(cardView, holder.autor, holder.fecha, autor, fecha);

        }else{ // Mensaje saliente, alineado a la derecha
            cardView.setBackground(recursos.getDrawable(R.drawable.mensaje_degradado_saliente));
            holder.leido.setVisibility(View.INVISIBLE);
            holder.contenidoMensaje.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            //Permutar el orden de fecha y autor
            personalizar(cardView, holder.fecha, holder.autor, autor, fecha);
        }

        //Visibilidad de items y personalizacion de fuente
        holder.fecha.setVisibility(View.VISIBLE);
        holder.fecha.setTypeface(null, Typeface.BOLD);

        //Ojo que los mensajes estan almacenados del mas viejo al mas nuevo
        //Y la lista la recorre del final hasta el inicio
        if (position != 0){ //Comprobar que no soy el ultimo mensaje de la lista
            mensajeAnterior = (Mensaje)listaMensajes.get(position-1);
            if (mensaje.getIdUsuario() != credenciales.getIdUsuario()) {
                cambiarVisibilidad(holder.autor, holder.fecha, mensaje);
            }else{
                //Permutar el orden de hora y autor
                cambiarVisibilidad(holder.fecha, holder.autor, mensaje);
            }

        }
        mensajeAnterior = mensaje;

    }

    private void personalizar(View v, TextView izquierda, TextView derecha, String autor, String hora){
        izquierda.setText(autor);
        derecha.setText(hora);
        izquierda.setVisibility(View.VISIBLE);
        izquierda.setTypeface(null, Typeface.BOLD);
        derecha.setTypeface(null, Typeface.BOLD);
        izquierda.setTextColor(v.getResources().getColor(R.color.colorRosaApp) );
        derecha.setTextColor(v.getResources().getColor(R.color.colorNegro) );

    }

    private void cambiarVisibilidad(TextView izquierda, TextView central, Mensaje mensaje){
        if (mensaje.getIdUsuario() == mensajeAnterior.getIdUsuario()){ //Si son del mismo autor...
            central.setVisibility(View.INVISIBLE);
            izquierda.setVisibility(View.INVISIBLE);
        }
        if ( !mensajeAnterior.getFecha().equals(mensaje.getFecha())
                && mensajeAnterior.getIdUsuario() == mensaje.getIdUsuario() ) {
            central.setVisibility(View.VISIBLE);
        }
    }

    public void actualizar(ArrayList<DataBaseItem> nuevaLista){
        listaMensajes.clear();
        listaMensajes.addAll(nuevaLista);
        mensajeAnterior = new Mensaje();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaMensajes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView autor;
        private TextView fecha;
        private TextView hora;
        private TextView contenidoMensaje;
        private CheckBox leido;
        private View v;


        public ViewHolder(View v) {
            super(v);
            autor = (TextView) v.findViewById(R.id.textMensajeAutor);
            fecha = (TextView) v.findViewById(R.id.mensajeFecha);
            hora = (TextView) v.findViewById(R.id.mensajeHora);
            contenidoMensaje = (TextView) v.findViewById(R.id.textMensajeMensaje);
            leido = (CheckBox) v.findViewById(R.id.chkMensajeLeido);
            this.v = v;
        }
    }

    public Integer ultimaPosicion(){
        Integer retorno = 0;
        if (listaMensajes.size()>0){
            retorno = listaMensajes.size()-1;
        }
        return retorno;
    }

    public interface OnItemClickListener {
        void onItemClick(DataBaseItem item, int position);
    }
    private AdapterMensaje.OnItemClickListener mListener;

    public void setOnItemListener(AdapterMensaje.OnItemClickListener listener) {
        mListener = listener;
    }

}