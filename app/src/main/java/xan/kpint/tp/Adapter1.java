package xan.kpint.tp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.constraintlayout.helper.widget.Layer;

import org.w3c.dom.Text;

import java.util.List;

public class Adapter1 extends CursorAdapter {

    public Adapter1(Context context, Cursor cursor, int flag) {
        super(context, cursor, 0);}

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        Spinner spinner = (Spinner) view.findViewById(R.id.Combobox_Asso);
        Handler2 asso = new Handler2(context.getApplicationContext());
        List<String> listasso = asso.fetchAllAsso();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context.getApplicationContext(), android.R.layout.simple_spinner_item, listasso);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }
}
