package com.example.admin1.enactusmnnit;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by admin1 on 23-05-2017.
 */
public class picgridAdapter extends BaseAdapter {
    private Context iContext;
    private List<picgrid> grid;

    public picgridAdapter(Context iContext, List<picgrid>grid) {
        super();
        this.iContext = iContext;
        this.grid = grid;
    }

    @Override
    public int getCount() {
        return grid.size();
    }

    @Override
    public Object getItem(int i) {
        return grid.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class Holder
    {
        TextView os_text;
        ImageView os_img;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(iContext, R.layout.grid_layout, null);

        String str=grid.get(i).getName();
        //Log.d("harry123","value of url-->"+str);

        ImageView imageView=(ImageView)v.findViewById(R.id.image);
        Picasso.with(iContext)
                .load(str)
                .fit().centerCrop()
                 .into(imageView);

        v.setTag(grid.get(i).getName());
        return v;

    }
}
