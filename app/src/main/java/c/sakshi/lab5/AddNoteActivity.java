package c.sakshi.lab5;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNoteActivity extends AppCompatActivity {

    int noteid = -1;
    String username;
    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnote);

        EditText editTextBox = (EditText)findViewById(R.id.editTextBox);
        Intent intent = getIntent();
        int id = intent.getIntExtra("noteid", -1);
        noteid = id;

        if(noteid != -1) {
            Note note = NotesActivity.notes.get(noteid);
            String noteContent = note.getContent();

            editTextBox.setText(noteContent);
        }

        Context context = getApplicationContext();
        sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE, null);
        dbHelper = new DBHelper(sqLiteDatabase);
    }

    public void saveMethod(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username", "");
        EditText editTextBox = (EditText)findViewById(R.id.editTextBox);
        String content = editTextBox.getText().toString();

        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if(noteid == -1) {
            title = "NOTE_" + (NotesActivity.notes.size() + 1);
            dbHelper.saveNotes(username, title, content, date);
        } else {
            title = "NOTE_" + (noteid + 1);
            dbHelper.updateNote(title, date, content, username);
        }

        Intent intent = new Intent(this, NotesActivity.class);
        startActivity(intent);
    }

}
