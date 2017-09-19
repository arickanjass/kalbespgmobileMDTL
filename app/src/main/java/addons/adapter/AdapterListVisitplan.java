package addons.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.kalbenutritionals.app.kalbespgmobile.R;

import java.util.ArrayList;

import library.spgmobile.common.clsSwipeList;

/**
 * Created by ASUS ZE on 13/03/2017.
 */

public class AdapterListVisitplan extends BaseAdapter implements ListAdapter{
    private Context context;
    private ArrayList<clsSwipeList> mAppList;

    public AdapterListVisitplan(Context context, ArrayList<clsSwipeList> mAppList) {
        this.context = context;
        this.mAppList = mAppList;
    }

    @Override
    public int getCount() {
        return mAppList.size();
    }

    @Override
    public String getItem(int position) {
        return String.valueOf("Outlet : " + mAppList.get(position).get_txtTitle()+"\n"+"Date Plan : " + mAppList.get(position).get_txtDescription()+"\n" + "Status : " + mAppList.get(position).get_intPIC());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.child_item_visitplan, null);
            new ViewHolder(convertView);
        }
        final String item = getItem(position);
        final ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.tv_name.setText(item);
        ImageView icon = (ImageView) convertView.findViewById(R.id.iconRealisasi);
        if (mAppList.get(position).get_intPIC().equals("Done")){
            icon.setImageResource(R.drawable.ic_check_circle_green);
        }else{
            icon.setImageResource(R.drawable.ic_notice_red);
        }
        /*holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, String.valueOf(position),Toast.LENGTH_SHORT).show();
//                new tCustomerBasedMobileDetailProductBL().deleteDataByProductId(mAppList.get(position).get_txtId());
//                mAppList.remove(position);
                notifyDataSetChanged();
            }
        });*/
        return convertView;
    }

    class ViewHolder {
        Button btn_delete;
        TextView tv_name;

        public ViewHolder(View view) {
//            btn_delete = (Button) view.findViewById(R.id.icon);
            tv_name = (TextView) view.findViewById(R.id.TvVisitplan);

            view.setTag(this);
        }
    }
}
