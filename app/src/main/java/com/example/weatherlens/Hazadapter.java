package com.example.weatherlens;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class Hazadapter extends FirebaseRecyclerAdapter<modelhazard, Hazadapter.hazviewholder> {

    public Hazadapter(@NonNull FirebaseRecyclerOptions<modelhazard> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull hazviewholder holder, int position, @NonNull modelhazard model) {
        holder.haztype.setText(model.getType());
        holder.hazdate.setText(model.getDate());
        holder.hazdesc.setText(model.getDesc());
        holder.haztitle.setText(model.getTitle());
    }

    @NonNull
    @Override
    public hazviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hazsinglerow,parent,false);
        return new hazviewholder(view);
    }

    public static class hazviewholder extends RecyclerView.ViewHolder{

        TextView haztype, hazdate, hazdesc, haztitle;
//        ImageButton editbtn,dltbtn;

        public hazviewholder(@NonNull View itemView) {
            super(itemView);
            haztype = (TextView) itemView.findViewById(R.id.tv_typewa);
            hazdate = (TextView) itemView.findViewById(R.id.tv_datewa);
            hazdesc = (TextView) itemView.findViewById(R.id.tv_descwa);
            haztitle = (TextView) itemView.findViewById(R.id.tv_titlewa);

//            editbtn=(ImageButton)itemView.findViewById(R.id.editbtn);
//            dltbtn=(ImageButton)itemView.findViewById(R.id.dltbtn);
        }
    }
}
