package adapter;

        import android.content.Context;
        import android.graphics.Color;
        import android.view.Gravity;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.GridView;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.kalbenutritionals.app.kalbespgmobile.R;

        import java.util.List;

        import library.spgmobile.common.clsSwipeList;

/**
 * Created by aan.junianto on 2/10/2017.
 */

//https://www.androidhive.info/2012/02/android-gridview-layout-tutorial/ (source)

public class GridAdapter extends BaseAdapter{
    private Context mContext;
    private List<clsSwipeList> mAppList;

    // Constructor
    public GridAdapter(Context context, List<clsSwipeList> mAppList) {
        this.mContext = context;
        this.mAppList = mAppList;
    }

    @Override
    public int getCount() {
        return mAppList.size();
    }

    @Override
    public clsSwipeList getItem(int position) {
        return mAppList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_grid , null);
            new ViewHolder(convertView);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        clsSwipeList item = getItem(position);


        holder.tv_id.setText(item.get_txtTitle());
        holder.tv_desc.setText(item.get_txtDescription());

        return convertView;
    }

    class ViewHolder {
        TextView tv_id, tv_desc;

        public ViewHolder(View view) {
            tv_id = (TextView) view.findViewById(R.id.title);
            tv_desc = (TextView) view.findViewById(R.id.desc);
            tv_id.setGravity(Gravity.CENTER);
            tv_desc.setGravity(Gravity.CENTER);

            view.setTag(this);
        }
    }
}
