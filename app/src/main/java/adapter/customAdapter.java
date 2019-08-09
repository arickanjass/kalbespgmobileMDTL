package adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.kalbenutritionals.app.kalbespgmobile.R;

import library.spgmobile.common.ModelListview;

public class customAdapter extends ArrayAdapter<ModelListview>{
        ModelListview[] modelItems = null;
        Context context;
public customAdapter(Context context, ModelListview[] resource) {
        super(context, R.layout.custom_listitem,resource);
        this.context = context;
        this.modelItems = resource;
        }
@Override
public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.custom_listitem, parent, false);
        TextView name = (TextView) convertView.findViewById(R.id.textView1);
        CheckBox cb = (CheckBox) convertView.findViewById(R.id.checkBox1);
        name.setText(modelItems[position].get_name());
        if(modelItems[position].get_value() == 1)
        cb.setChecked(true);
        else
        cb.setChecked(false);
        return convertView;
        }
        }



