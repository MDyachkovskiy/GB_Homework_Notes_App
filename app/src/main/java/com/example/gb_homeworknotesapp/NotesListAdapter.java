package com.example.gb_homeworknotesapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NotesListAdapter extends RecyclerView.Adapter<NotesListAdapter.ViewHolder>{

    private NoteSource dataSource;
    private RecyclerView.ViewHolder holder;
    private int position;

    public NotesListAdapter(NoteSource dataSource) {
        this.dataSource = dataSource;
    }

    @NonNull
    @Override
    public NotesListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_blank, parent, false);

        return new NotesListAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesListAdapter.ViewHolder holder, int position) {

        holder.setData(dataSource.getNoteData(position));

    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView description;
        private TextView creationDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.note_title);
            description = itemView.findViewById(R.id.note_description);
            creationDate = itemView.findViewById(R.id.note_creationDate);
        }

        public void setData (NoteData noteData) {
            title.setText(noteData.getTitle());
            description.setText(noteData.getDescription());
            creationDate.setText(noteData.getCreationDate());
        }

    }

}