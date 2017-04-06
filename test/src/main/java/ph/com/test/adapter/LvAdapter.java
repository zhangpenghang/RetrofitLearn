package ph.com.test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ph.com.test.R;
import ph.com.test.bean.CateItemBean;


/**
 * Created by qf on 16/8/7.
 */
public class LvAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private CateItemBean cateItemBean;

    public LvAdapter(Context context, CateItemBean cateItemBean) {
        this.context = context;
        this.cateItemBean = cateItemBean;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return cateItemBean.getTngou().size();
    }

    @Override
    public Object getItem(int i) {
        return cateItemBean.getTngou().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        ViewHolder holder;
        if (converView == null) {
            converView = inflater.inflate(R.layout.lisview_item, parent, false);
            holder = new ViewHolder(converView);
            converView.setTag(holder);
        } else {
            holder = (ViewHolder) converView.getTag();
        }
        CateItemBean.TngouBean bean = cateItemBean.getTngou().get(position);
        Picasso.with(context).load("http://tnfs.tngou.net/image"+bean.getImg()).into(holder.iv);
        holder.title.setText(bean.getTitle());
        holder.keywords.setText("[关键词] "+bean.getKeywords());
        return converView;
    }

    class ViewHolder {
        ImageView iv;
        TextView title, keywords;

        public ViewHolder(View itemView) {
            iv = (ImageView) itemView.findViewById(R.id.iv);
            title = (TextView) itemView.findViewById(R.id.title);
            keywords = (TextView) itemView.findViewById(R.id.keywords);
        }
    }
}
