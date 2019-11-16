package edu.calvin.cs262.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static edu.calvin.cs262.myapplication.NewPlayerActivity.NEW_PLAYER_ACTIVITY_REQUEST_CODE;


public class MainActivity extends AppCompatActivity {
    private PlayerViewModel mPlayerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        GameViewModel mGameViewModel;
        GamePlayerViewModel mGamePlayerViewModel;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewPlayerActivity.class);
                startActivityForResult(intent, NEW_PLAYER_ACTIVITY_REQUEST_CODE);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final PlayerListAdapter adapter = new PlayerListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mPlayerViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
        mPlayerViewModel.getAllPlayers().observe(this, new Observer<List<Player>>() {
            @Override
            public void onChanged(@Nullable final List<Player> words) {
                // Update the cached copy of the words in the adapter.
                adapter.setPlayers(words);
            }
        });

        // Add the functionality to swipe items in the 
        // recycler view to delete that item
        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView,
                                          RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder,
                                         int direction) {
                        int position = viewHolder.getAdapterPosition();
                        Player myPlayer = adapter.getPlayerAtPosition(position);
                        Toast.makeText(MainActivity.this, "Deleting " +
                                myPlayer.getPlayerName(), Toast.LENGTH_LONG).show();

                        // Delete the word
                        mPlayerViewModel.deletePlayer(myPlayer);
                    }
                });

        helper.attachToRecyclerView(recyclerView);


        mGameViewModel = ViewModelProviders.of(this).get(GameViewModel.class);
        mGameViewModel.getAllGames().observe(this, new Observer<List<Game>>() {
            @Override
            public void onChanged(@Nullable final List<Game> games) {
                if (games != null) {
                    for (int i = 0; i < games.size(); i++) {
                        Log.d("Game.ID", Integer.toString(games.get(i).getID()));
                        Log.d("Game.Time", games.get(i).getTimeStamp());
                    }
                }
            }
        });

        mGamePlayerViewModel = ViewModelProviders.of(this).get(GamePlayerViewModel.class);
        mGamePlayerViewModel.getGameFromPlayer(5).observe(this, new Observer<List<Game>>() {
            @Override
            public void onChanged(@Nullable final List<Game> games) {
                if (games != null) {
                    for (int i = 0; i < games.size(); i++) {
                        Log.d("Game for Player 5 ID", Integer.toString(games.get(i).getID()));
                        Log.d("Game for player 5 Time", (games.get(i).getTimeStamp()));

                    }
                }
            }
        });

        mGamePlayerViewModel = ViewModelProviders.of(this).get(GamePlayerViewModel.class);
        mGamePlayerViewModel.getPlayerFromGame(1).observe(this, new Observer<List<Player>>() {
            @Override
            public void onChanged(@Nullable final List<Player> games) {
                if (games != null) {
                    for (int i = 0; i < games.size(); i++) {
                        Log.d("Player for Game 1 ID", Integer.toString(games.get(i).getID()));
                        Log.d("Player for Game 1 Email", (games.get(i).getEmail()));
                        Log.d("Player for Game 1 Name", (games.get(i).getPlayerName()));

                    }
                }
            }
        });

    }

    /**
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_PLAYER_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Player player = new Player(Integer.parseInt(data.getStringExtra(getString(R.string.id))),
                    data.getStringExtra(getString(R.string.name)),
                    data.getStringExtra(getString(R.string.email)));
            mPlayerViewModel.insert(player);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

    /**
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.clear_data) {
            // Add a toast just for confirmation
            Toast.makeText(this, "Clearing the data...",
                    Toast.LENGTH_SHORT).show();

            // Delete the existing data
            mPlayerViewModel.deleteAll();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
