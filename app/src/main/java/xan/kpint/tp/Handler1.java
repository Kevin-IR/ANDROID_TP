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

public class Handler1 extends SQLiteOpenHelper {

    private SQLiteDatabase mBb;
    private static Context MCtx;

    private static final String DATABASE_NAME = "utilisateur.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Utilisateur";
    private static final String C_PSEUDO = "pseudo";
    private static final String C_MDP = "mdp";
    //private static final int id_asso = 0;

    private static final String CREATE_BDD = "CREATE TABLE IF NOT EXISTS utilisateur ( "
            + "id_asso INTEGER NOT NULL,"
            + "pseudo TEXT NOT NULL,"
            + "mdp TEXT NOT NULL)";

    private static final String TAG = "Adapter1";

    private static Handler1 mInstance = null;

    public Handler1(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static Handler1 getmInstance(Context context) {
        if (mInstance == null) {
            mInstance = new Handler1(context.getApplicationContext());
        }
        return mInstance;
    }

    Handler1(Context context) {
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
    public Handler1 open() throws SQLException {
        this.getWritableDatabase();
        return this;
    }
    public void close() {
        if (this !=null) {
            this.close();
        }
    }
    public long insertUtilisateur(SQLiteDatabase db, String pseudo, String mdp) {

        ContentValues initialValues = new ContentValues();
        initialValues.put(C_PSEUDO, pseudo);
        initialValues.put(C_MDP, mdp);
        return db.insert(TABLE_NAME, null, initialValues);
    }

    public Cursor fetchAllUtilisateur(SQLiteDatabase db) {

        Cursor mCursor = db.rawQuery("SELECT * FROM utilisateur", null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
}