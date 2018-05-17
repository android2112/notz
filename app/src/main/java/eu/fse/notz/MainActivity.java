package eu.fse.notz;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int EDIT_REQUEST = 1001;
    public static final int RESUL_REMOVE_NOTE = RESULT_FIRST_USER + 1;

    private RecyclerView mRecyclerView;
    private NotesAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FloatingActionButton addNoteButton;
    private DatabaseHandler databaseHandler=new DatabaseHandler(this);



    // private String[] myDataset = {"nota 1"," nota 2", "fai la spesa", "paga bolletta luca", "dadsadasa", "dsasdasd", "dassad"};
    private ArrayList<Note> myDataset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.notes_rv);


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        addNoteButton = findViewById(R.id.add_note_fab);

        mLayoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        myDataset = new ArrayList<>();
        //myDataset=databaseHandler.getAllNotes();
        myDataset.addAll(databaseHandler.getAllNotes());


        // specify an adapter (see also next example)
        mAdapter = new NotesAdapter(myDataset, this);
        mRecyclerView.setAdapter(mAdapter);


        getNotesFromURL();


        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialog();

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EDIT_REQUEST) {

            if (resultCode == RESULT_OK) {

                //getPosition from returnIntent
                int editedNotePosition = data.getIntExtra("position", -1);
                mAdapter.updateNote(editedNotePosition,
                        data.getStringExtra("title"),
                        data.getStringExtra("description"));
                databaseHandler.updateNote(mAdapter.getNote(editedNotePosition));

            }


            if (resultCode == RESUL_REMOVE_NOTE) {
                final int editedNotePosition = data.getIntExtra("position", -1);
                databaseHandler.deletNote(mAdapter.getNote(editedNotePosition));
                mAdapter.removeNote(editedNotePosition);


                Snackbar.make(mRecyclerView, getString(R.string.note_removed), Snackbar.LENGTH_LONG)
                        .setAction(R.string.cancel, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                Note note = new Note(data.getStringExtra("title"),
                                        data.getStringExtra("description"));

                                mAdapter.addNote(editedNotePosition, note);
                            }
                        })
                        .show();
            }


        }

    }

    private void showDialog() {

        final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);

        final View dialogView = LayoutInflater.from(this)
                .inflate(R.layout.dialog_add_note, null);


        final EditText titleEt = dialogView.findViewById(R.id.dialog_title_et);
        final EditText descriptionEt = dialogView.findViewById(R.id.dialog_description_et);


        alertBuilder.setView(dialogView)
                .setTitle(R.string.dialog_add_note_title)
                .setPositiveButton(R.string.dialog_positive_button,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //

                                String insertedTitle = titleEt.getText().toString();
                                String insertedDescription = descriptionEt.getText().toString();

                                Note.NoteBuilder builder = new Note.NoteBuilder();
                                builder
                                        .setTitle(insertedTitle)
                                        .setDescription(insertedDescription)
                                        .setId(12)
                                        .setShownOnTop(true);

                                mAdapter.addNote(builder.build());

                                databaseHandler.addNote(builder.build());

                            }
                        })
                .setNegativeButton(R.string.dialog_negative_button,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                databaseHandler.deletNote(myDataset.remove(i));
                                mAdapter.removeNote(i);

                            }
                        })
                .create()
                .show();


    }


    private void getNotesFromURL() {

        //Make HTTP call

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://5af1bf8530f9490014ead894.mockapi.io/api/v1/notes";

        // Request a string response from the provided URL.
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, // METHOD
                url, // URL
                null, // Body parameters
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // TODO manage success
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                            Log.d("jsonRequest",response.toString());

                            ArrayList<Note> noteListFromResponse = Note.getNotesList(jsonArray);
                            mAdapter.addNotesList(noteListFromResponse);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Log.e("jsonRequest",error.getMessage());
                        Toast.makeText(MainActivity.this,
                                "Il server E' momentaneamente off-line codice" + error.networkResponse.statusCode,
                                Toast.LENGTH_LONG).show();

                        findViewById(R.id.loadingPanel).setVisibility(View.GONE);



                    }
                });

        // Add the request to the RequestQueue.
        queue.add(request);


    }


}
