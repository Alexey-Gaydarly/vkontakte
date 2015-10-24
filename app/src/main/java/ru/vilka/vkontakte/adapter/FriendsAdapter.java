package ru.vilka.vkontakte.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vk.sdk.api.model.VKApiUserFull;

import ru.vilka.vkontakte.R;

public class FriendsAdapter extends BaseAdapter {

    private SparseArray<VKApiUserFull> object;

    private LayoutInflater mInflater;
    private int mLayout;
    private Context ctx;

    public FriendsAdapter(SparseArray<VKApiUserFull> object, int layout, Context ctx) {
        this.object = object;
        this.mInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mLayout = layout;
        this.ctx = ctx;
    }

    public void addParam(VKApiUserFull vkApiUserFull){
        object.put(vkApiUserFull.id, vkApiUserFull);
    }

    @Override
    public int getCount() {
        return object.size();
    }

    @Override
    public Object getItem(int position) {
        return object.get(object.keyAt(position));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = mInflater.inflate(mLayout, parent, false);

        VKApiUserFull currentFriend = (VKApiUserFull) getItem(position);

        Glide.with(ctx)
                .load(currentFriend.photo_50)
                .into(((ImageView) convertView.findViewById(R.id.avatar_friends)));
        ((TextView) convertView.findViewById(R.id.fullName_friends)).setText(currentFriend.first_name + " " + currentFriend.last_name);
        ((TextView) convertView.findViewById(R.id.universities_friends)).setText(currentFriend.universities.getById(0).chair_name);
        ((TextView) convertView.findViewById(R.id.status_online_friends)).setVisibility(currentFriend.online ? View.VISIBLE : View.GONE);

        return convertView;
    }

}
