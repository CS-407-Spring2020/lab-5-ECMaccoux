package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        if(!sharedPreferences.getString("username", "").equals("")) {
            Intent intent = new Intent(this, NotesActivity.class);
            //intent.putExtra("username", sharedPreferences.getString("username", ""));
            startActivity(intent);
        } else {
            setContentView(R.layout.activity_login);
        }
    }

    public void clickButton(View view) {
        EditText textUsername = (EditText)findViewById(R.id.textUsername);
        String username = textUsername.getText().toString();

        if(!username.equals("")) {
            SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
            sharedPreferences.edit().putString("username", username).apply();

            login(username);
        } else {
            Toast.makeText(LoginActivity.this, "Please enter a username", Toast.LENGTH_LONG).show();
        }
    }

    public void login(String username) {
        Intent intent = new Intent(this, NotesActivity.class);
        //intent.putExtra("username", username);
        startActivity(intent);
    }
}
