package com.example.gb_homeworknotesapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotesListAdapter extends RecyclerView.Adapter<NotesListAdapter.ViewHolder> {

    private final NoteSource dataSource;
    private OnItemClickListener itemClickListener;

    public NotesListAdapter(NoteSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setNewData(List<NoteData> dataSource){
        this.dataSource.setNewData(dataSource);
        notifyDataSetChanged();
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

        private final TextView title;
        private final TextView description;
        private final TextView creationDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.note_title);
            description = itemView.findViewById(R.id.note_description);
            creationDate = itemView.findViewById(R.id.note_creationDate);

            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (itemClickListener != null)
                        itemClickListener.onItemClick(view, position);
                }
            });

        }

        public void setData(NoteData noteData) {
            title.setText(noteData.getTitle());
            description.setText(noteData.getDescription());
            creationDate.setText(noteData.getCreationDate());
        }

    }

}