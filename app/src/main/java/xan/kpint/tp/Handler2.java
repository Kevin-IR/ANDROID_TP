package xan.kpint.tp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.List;

public class Handler2 extends SQLiteOpenHelper {

    private SQLiteDatabase mBb;
    private static Context MCtx;

    private static final String DATABASE_NAME = "association.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Association";
    private static final String C_ASSO = "asso";
    private static final String C_MODELE = "asso";
    private static final int NUM_C_ID = 0;
    private static final int NUM_C_MARQUE = 1;

    private static final String CREATE_BDD = "CREATE TABLE IF NOT EXISTS association ( "
            + "_id_asso INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "asso TEXT NOT NULL)";

    private static final String TAG = "Adapter1";

    private static Handler2 mInstance = null;

    public Handler2(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static Handler2 getmInstance(Context context) {
        if (mInstance == null) {
            mInstance = new Handler2(context.getApplicationContext());
        }
        return mInstance;
    }

    Handler2 (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.w(TAG, CREATE_BDD);

        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Log.w(TAG, "Mise à jour de la base " + oldVersion + "to"
                + newVersion + " destruction de la base ...");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public Handler2 open() throws SQLException {
        this.getWritableDatabase();
        return this;
    }
    public void close() {
        if (this !=null) {
            this.close();
        }
    }
    public long insertAsso(SQLiteDatabase db, String asso) {

        ContentValues initialValues = new ContentValues();
        initialValues.put(C_ASSO, asso);
        return db.insert(TABLE_NAME, null, initialValues);
    }

    public Cursor fetchAllAsso(SQLiteDatabase asso) {

        Cursor mCursor = asso.rawQuery("SELECT * FROM asssociation", null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
}