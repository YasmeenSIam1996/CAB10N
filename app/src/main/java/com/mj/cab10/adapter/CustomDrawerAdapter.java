package com.mj.cab10.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mj.cab10.R;
import com.mj.cab10.model.DrawerItem;

import java.util.List;

public class CustomDrawerAdapter extends ArrayAdapter {

    Context context;
    List drawerItemList;
    int layoutResID;

    public CustomDrawerAdapter(Context context, int layoutResourceID, List listItems) {
        super(context, layoutResourceID, listItems);
        this.context = context;
        this.drawerItemList = listItems;
        this.layoutResID = layoutResourceID;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        DrawerItemHolder drawerHolder;
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            drawerHolder = new DrawerItemHolder();

            view = inflater.from(context).inflate(layoutResID, parent, false);
//            drawerHolder.ItemName = (TextView) view.findViewById(R.id.drawer_itemName);
//            drawerHolder.icon = (ImageView) view.findViewById(R.id.drawer_icon);

            view.setTag(drawerHolder);

        } else {
            drawerHolder = (DrawerItemHolder) view.getTag();

        }

        DrawerItem dItem = (DrawerItem) this.drawerItemList.get(position);

        drawerHolder.icon.setImageDrawable(view.getResources().getDrawable(dItem.getImgResID()));
        drawerHolder.ItemName.setText(dItem.getItemName());

        return view;
    }

    private static class DrawerItemHolder {
        TextView ItemName;
        ImageView icon;
    }

}