package tk.GhostfFilms.myreminders;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CreateReminder extends AppCompatActivity {
    // declare intent
    Intent intent;

    // declare EditText;
    EditText titleEditText;
    EditText noteEditText;

    // declare Calender
    Calendar calendar;

    // declare String
    String date;

    // Declare a DBHandler
    DBHandler dbHandler;

    /**
     * This method initializes the Action Bar and View of the activity.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_reminder);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // initialize EditTexts
        titleEditText = findViewById(R.id.titleEditText);
        noteEditText = findViewById(R.id.noteEditText);

        // initialize calender
        calendar = Calendar.getInstance();

        // initialize DatePickerDialog and register an OnDateSetListener to it.
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            /**
             * This method handles the OnDateDet event
             * @param datePicker DatePickerDialog view
             * @param year selected year
             * @param month selected month
             * @param dayOfMonth selected day
             */
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                // set the year, month, and dayOfMonth selected in the dialog into the calender
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                // call method that takes date in calendar and place it in date
                updateDueDate();
            }
        };

        // initialize DBHandler
        dbHandler = new DBHandler(this, null);
    }

    private void updateDueDate() {
        // create a format for the date in the calendar
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        // apply format to date in calender and set formatted date for current focused reminder
        date = simpleDateFormat.format(calendar.getTime());
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
        getMenuInflater().inflate(R.menu.menu_create_reminder, menu);
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
     * This method gets called when the add button in the Action Bar gets clicked
     * @param menuItem add list menu item
     */
    public void createReminder(MenuItem menuItem) {
        // get data input in EditTexts and store it in Strings
        String title = titleEditText.getText().toString();
        String note = noteEditText.getText().toString();
        updateDueDate();

        // trim Strings and check to see if they're equal to an empty string
        if(title.trim().equals("") || note.trim().equals("")) {
            //display "Please enter a title and text!"
            Toast.makeText(this, "Please enter a title and text!",
                    Toast.LENGTH_LONG).show();
        } else {
            // add CreateReminder list into database
            dbHandler.addReminderList(title, note, date);// display "Reminder Created!"
            Toast.makeText(this, "Reminder Created!",
                    Toast.LENGTH_LONG).show();
        }
    }
}