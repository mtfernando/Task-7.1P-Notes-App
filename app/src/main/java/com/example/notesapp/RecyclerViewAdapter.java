package com.example.notesapp;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.notesapp.model.Note;

import org.w3c.dom.Text;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Note> noteList;
    private Context context;

    public RecyclerViewAdapter(List<Note> noteList, Context context) {
        this.noteList = noteList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.note_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {


        if(this.getItemCount()>0){

            //Setting up each Food Item's viewholder
            holder.noteHeaderTextView.setText(noteList.get(position).getHeader());
        }
        else{
            Toast.makeText(context, "No notes to show!", Toast.LENGTH_SHORT).show();
        }


        //When an Item in the recyclerview is clicked
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note note;
                note = noteList.get(holder.getAdapterPosition());

                int noteID = note.getId();
                String noteHeader = note.getHeader();
                String noteDesc = note.getDesc();

                Intent intent = new Intent(context, changeNoteActivity.class);
                intent.putExtra("noteID", noteID);
                intent.putExtra("noteHeader", noteHeader);
                intent.putExtra("noteDesc", noteDesc);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView noteHeaderTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            noteHeaderTextView = itemView.findViewById(R.id.headerTextView);

        }
    }
}
