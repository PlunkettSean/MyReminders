package tk.GhostfFilms.myreminders;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // declare intent
    Intent intent;

    // Declare a DBHandeler
    DBHandler dbHandler;

    // Declare a ReminderList CursorAdaptor
    CursorAdapter reminderListCursorAdaptor;

    // Declare a ListView
    ListView reminderListView;


    /**
     * This method initializes the Action Bar and View of the activity.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // initialize the View and Action bar of the MainActivity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Initialize DBHandler
        dbHandler = new DBHandler(this, null);

        // set subtitle to number of reminders
        getSupportActionBar().setSubtitle(dbHandler.getTableRows() + " Reminders");

        // Initialize ListView
        reminderListView = (ListView) findViewById(R.id.reminderListView);

        // initialize ReminderLists CursorAdaptor
        reminderListCursorAdaptor = new ReminderLists(this,
                dbHandler.getReminderList(),0);

        // Set ReminderLists CursorAdaptor on the ListView
        reminderListView.setAdapter(reminderListCursorAdaptor);
    }

    /**
     * This method further initializes the Action Bar of the activity.
     * It gets the code (XML) in the menu resource file and incorporates it into the Action Bar.
     * @param menu menu resource file for the activity.
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * This method gets called when a menu item in the overflow menu is selected and it
     * controls what happens when the menu item is selected.
     * @param item selected item in the overflow menu.
     * @return true if the menu item is selected, else false.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // get the id of the menu item selected
        switch(item.getItemId()) {
            case R.id.action_home :
                // initialize an Intent for the MainActivity and start it.
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_add_reminder:
                // initialize an Intent for the CreateReminder Activity and start it.
                intent = new Intent(this, CreateReminder.class);
                startActivity(intent);
                return true;
            default :
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * This method gets called when the add Floating Action Button is selected.
     * It starts the CreateReminder Activity
     * @param view
     */
    public void openCreateList(View view) {
        // initialize an Intent for the CreateReminder Activity and start it.
        intent = new Intent(this, CreateReminder.class);
        startActivity(intent);
    }
}