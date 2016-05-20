package xyz.a4dgames.a4dgames.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import xyz.a4dgames.a4dgames.R;
import xyz.a4dgames.a4dgames.model.RankItem;

/**
 * Created by Issac on 5/19/2016.
 */
public class RankAdapter extends ArrayAdapter<RankItem>{

    private List<RankItem> items;
    private Context context;

    public RankAdapter(Context context, int resource,List<RankItem> items){
        super(context, resource, items);
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public RankItem getItem(int position) {
        return items.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = new ViewHolder();
        View v = convertView;
        if (v == null) {
            LayoutInflater vi= LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.ranking_view, null);
        }

        RankItem item = getItem(position);

        vh.textRank = (TextView) v.findViewById(R.id.text_rank);
        vh.textUsername = (TextView) v.findViewById(R.id.text_username);
        vh.textCash = (TextView) v.findViewById(R.id.text_cash);
        vh.imageUser = (ImageView) v.findViewById(R.id.image_user);

        vh.textRank.setText(item.getRank() + "");
        vh.textUsername.setText(item.getUsername());
        vh.textCash.setText(item.getCash());
        Picasso.with(context).load(item.getImageUrl()).into(vh.imageUser);

        return v;
    }

    static class ViewHolder {
        TextView textRank;
        TextView textUsername;
        TextView textCash;
        ImageView imageUser;
    }
}
