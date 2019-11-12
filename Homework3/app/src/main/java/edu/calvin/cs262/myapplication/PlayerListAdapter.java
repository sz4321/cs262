package edu.calvin.cs262.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlayerListAdapter extends RecyclerView.Adapter<PlayerListAdapter.PlayerViewHolder> {

    private final LayoutInflater mInflater;
    private List<Player> mPlayers; //copy of players

    PlayerListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new PlayerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PlayerViewHolder holder, int position) {
        if (mPlayers != null) {

            Player current = mPlayers.get(position);
            holder.playerItemView.setText(current.getPlayerName());


        } else {
            // Covers the case of data not being ready yet.
            holder.playerItemView.setText("No Player");
        }
    }

    void setPlayers(List<Player> players){
        mPlayers = players;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mPlayers != null)
            return mPlayers.size();
        else return 0;
    }

    class PlayerViewHolder extends RecyclerView.ViewHolder {
        private final TextView playerItemView;

        private PlayerViewHolder(View itemView) {
            super(itemView);
            playerItemView = itemView.findViewById(R.id.textView);
        }
    }

    public Player getPlayerAtPosition (int position) {
        return mPlayers.get(position);
    }

}
