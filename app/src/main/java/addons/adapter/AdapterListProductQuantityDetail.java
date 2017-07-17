package addons.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.kalbenutritionals.app.kalbespgmobile.R;

import java.util.ArrayList;

import bl.tCustomerBasedMobileDetailProductBL;
import bl.tSalesProductQuantityDetailBL;
import library.salesforce.common.clsSwipeList;

/**
 * Created by Rian Andrivani ZE on 13/03/2017.
 */

public class AdapterListProductQuantityDetail extends BaseAdapter implements ListAdapter{
    private Context context;
    private ArrayList<clsSwipeList> mAppList;

    public AdapterListProductQuantityDetail(Context context, ArrayList<clsSwipeList> mAppList) {
        this.context = context;
        this.mAppList = mAppList;
    }

    @Override
    public int getCount() {
        return mAppList.size();
    }

    @Override
    public String getItem(int position) {
        return String.valueOf("Product : " + mAppList.get(position).get_txtTitle()+"\n"+"TglExpire : " + mAppList.get(position).get_txtDescription()+"\n" + "Qty : " + mAppList.get(position).get_intPIC());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_list_product_cus_based, null);
            new ViewHolder(convertView);
        }
        final String item = getItem(position);
        final ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.tv_name.setText(item);
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, String.valueOf(position),Toast.LENGTH_SHORT).show();
//                new tCustomerBasedMobileDetailProductBL().deleteDataByProductId(mAppList.get(position).get_txtId());
                new tSalesProductQuantityDetailBL().deleteDataByProductId(mAppList.get(position).get_txtId());
                mAppList.remove(position);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    class ViewHolder {
        Button btn_delete;
        TextView tv_name;

        public ViewHolder(View view) {
            btn_delete = (Button) view.findViewById(R.id.buttonDelete);
            tv_name = (TextView) view.findViewById(R.id.tv_name);

            view.setTag(this);
        }
    }
}
