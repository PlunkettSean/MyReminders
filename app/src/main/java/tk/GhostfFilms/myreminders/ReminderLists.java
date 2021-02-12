package tk.GhostfFilms.myreminders;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * The ReminderList class will map the data selected from the reminder list table, in the cursor,
 * to the li_reminder_list layout resource.
 */
public class ReminderLists extends CursorAdapter {

    /**
     * Initialize a ReminderList CursorAdapter
     *
     * @param context Reference to the Activity that initializes the ReminderLists
     *                CursorAdaptor
     * @param c       Reference to the cursor from which to get the data.
     * @param flags   Flags used to determine the behavior of the adapter; may
     *                be any combination of {@link #FLAG_AUTO_REQUERY} and
     *                {@link #FLAG_REGISTER_CONTENT_OBSERVER}.
     */
    public ReminderLists(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    /**
     * Makes a new view to hold the data pointed to by cursor.
     *
     * @param context Reference to the Activity that initializes the ReminderLists
     *                CursorAdaptor
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param parent  Reference to the ReminderListView that will contain the new
     *                View created by this method.
     * @return the newly created view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.li_reminder_list, parent, false);
    }

    /**
     * Bind an existing view to the data pointed to by cursor
     *
     * @param view    Existing view, returned earlier by newView
     * @param context Reference to the Activity that initializes the ReminderLists
     *                CursorAdaptor
     * @param cursor  The cursor from which to get the data. The cursor is already
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        /**
         * Set the value of titleTextView of li_reminder_list layout to the name of the
         * ReminderList from the cursor
         */
        ((TextView) view.findViewById(R.id.titleTextView)).
                setText(cursor.getString(cursor.getColumnIndex("title")));
        /**
         /**
         * Set the value of noteTextView of li_reminder_list layout to the date of the
         * ReminderList from the cursor
         */
        ((TextView) view.findViewById(R.id.dateTextView)).
                setText(cursor.getString(cursor.getColumnIndex("date")));
    }
}
