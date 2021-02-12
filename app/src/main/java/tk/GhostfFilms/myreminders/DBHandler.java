package tk.GhostfFilms.myreminders;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    // initialize constants for the db name and version
    public static final String DATABASE_NAME = "reminder.db";
    public static final int DATABASE_VERSION = 1;

    // Initialize constants for the reminderlist table
    public static final String TABLE_REMINDER_LIST ="reminderlist";
    public static final String COLUMN_LIST_ID = "_id";
    public static final String COLUMN_LIST_TITLE = "title";
    public static final String COLUMN_LIST_NOTE = "note";
    public static final String COLUMN_LIST_DATE = "date";

    /**
     * Create a Version of our database
     * @param context reference to the activity that initializes a DBHandler
     * @param factory null
     */
    public DBHandler(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    /**
     * Creates Reminder database tables
     * @param sqLiteDatabase reference to the reminder database
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Define create statement for reminderList table and store it in a string
        String query = "CREATE TABLE " + TABLE_REMINDER_LIST + "(" +
                COLUMN_LIST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_LIST_TITLE + " TEXT, " +
                COLUMN_LIST_NOTE + " TEXT, " +
                COLUMN_LIST_DATE + " TEXT);";

        // Execute the SQL statement
        sqLiteDatabase.execSQL(query);
    }

    /**
     * Creates a new Version of the Reminder database
     * @param sqLiteDatabase reference to the reminder database
     * @param oldVersion old version of the reminder database
     * @param newVersion new version of the reminder database
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // Define drop statement and store it in a string
        String query = "DROP TABLE IF EXISTS " + TABLE_REMINDER_LIST;

        // Execute the drop SQL Statement
        sqLiteDatabase.execSQL(query);

        // Create Tables
        onCreate(sqLiteDatabase);
    }

    /**
     * This method gets called when the add button in the Action Bar of the CreateReminder
     * Activity gets clicked. It inserts a new row into the reminder list table.
     * @param name reminder list name
     * @param note reminder list store
     * @param date reminder list date
     */
    public void addReminderList(String name, String note, String date) {
        // get reference to the reminder database
        SQLiteDatabase db = getWritableDatabase();

        // Initialize a ContentValues object
        ContentValues values = new ContentValues();

        // put data in to ContentValues Object
        values.put(COLUMN_LIST_TITLE, name);
        values.put(COLUMN_LIST_NOTE, note);
        values.put(COLUMN_LIST_DATE, date);

        // Insert data in ContentValues Object into the reminderList table
        db.insert(TABLE_REMINDER_LIST, null, values);

        // Close database reference
        db.close();
    }

    /**
     * This method gets called when the main activity is created. It will select
     * and return all of the data in the reminderList table.
     * @return Cursor that contains all the data in the reminderList table
     */
    public Cursor getReminderList() {
        // get reference to the reminder database
        SQLiteDatabase db = getWritableDatabase();

        // define select statement and store it in a string
        String query = "SELECT * FROM " + TABLE_REMINDER_LIST;

        // execute statement and return it as a Cursor
        return db.rawQuery(query, null);
    }

    /**
     * Get number of rows (reminders) in the data base and return thats number as a string
     * @return string representation of the number of rows in TABLE_REMINDER_LIST
     */
    public String getTableRows() {
        SQLiteDatabase db = getWritableDatabase();
        long taskCount = DatabaseUtils.queryNumEntries(db, TABLE_REMINDER_LIST);
        String count = String.valueOf(taskCount);
        return count;
    }
}
