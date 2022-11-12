package com.example.weatherlens;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class WAHazadapter extends FirebaseRecyclerAdapter<modelhazard, WAHazadapter.wahazviewholder> {

    public WAHazadapter(@NonNull FirebaseRecyclerOptions<modelhazard> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull wahazviewholder holder, @SuppressLint("RecyclerView") int position, @NonNull modelhazard model) {
        holder.haztype.setText(model.getType());
        holder.hazdate.setText(model.getDate());
        holder.hazdesc.setText(model.getDesc());
        holder.haztitle.setText(model.getTitle());

        holder.dltbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.hazdesc.getContext());
                builder.setTitle("Delete Hazard");
                builder.setMessage("Are you sure you want to delete?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("Hazard").child(getRef(position).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

        holder.editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.hazdesc.getContext())
                        .setContentHolder(new ViewHolder(R.layout.activity_upd_hazard))
                        .setExpanded(true, 1200)
                        .create();

                View myview = dialogPlus.getHolderView();
                EditText updtitle = myview.findViewById(R.id.etupdtitle);
                EditText upddesc = myview.findViewById(R.id.etupddesc);
                EditText updtype = myview.findViewById(R.id.etupdtype);
                EditText upddate = myview.findViewById(R.id.etupddate);
                Button update = myview.findViewById(R.id.updhazsubmit);

                updtitle.setText(model.getTitle());
                upddesc.setText(model.getDesc());
                updtype.setText(model.getType());
                upddate.setText(model.getDate());

                dialogPlus.show();

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("title", updtitle.getText().toString());
                        map.put("desc", upddesc.getText().toString());
                        map.put("type", updtype.getText().toString());
                        map.put("date", upddate.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Hazard")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
            }
        });
    }

    @NonNull
    @Override
    public wahazviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hazsinglerowwa,parent,false);
        return new wahazviewholder(view);
    }

    public static class wahazviewholder extends RecyclerView.ViewHolder{

        TextView haztype, hazdate, hazdesc, haztitle;
        ImageButton editbtn,dltbtn;

        public wahazviewholder(@NonNull View itemView) {
            super(itemView);
            haztype = (TextView) itemView.findViewById(R.id.tv_typewa);
            hazdate = (TextView) itemView.findViewById(R.id.tv_datewa);
            hazdesc = (TextView) itemView.findViewById(R.id.tv_descwa);
            haztitle = (TextView) itemView.findViewById(R.id.tv_titlewa);

            editbtn=(ImageButton)itemView.findViewById(R.id.editwa);
            dltbtn=(ImageButton)itemView.findViewById(R.id.deletewa);
        }
    }
}

