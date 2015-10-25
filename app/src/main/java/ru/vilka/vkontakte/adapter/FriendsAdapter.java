package ru.vilka.vkontakte.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bumptech.glide.Glide;
import com.vk.sdk.api.model.VKApiUserFull;

import java.util.ArrayList;

import ru.vilka.vkontakte.adapter.view_holder.FriendsViewHolder;

public class FriendsAdapter extends BaseAdapter {

    private ArrayList<VKApiUserFull> object;

    private LayoutInflater mInflater;
    private int mLayout;
    private Context ctx;

    public FriendsAdapter(ArrayList<VKApiUserFull> object, int layout, Context ctx) {
        this.object = object;
        mLayout = layout;
        this.ctx = ctx;
        this.mInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addItem(VKApiUserFull vkApiUserFull){
        object.add(vkApiUserFull);
    }

    @Override
    public int getCount() {
        return object.size();
    }

    @Override
    public Object getItem(int position) {
        return object.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = mInflater.inflate(mLayout, parent, false);
        }

        VKApiUserFull currentFriend = (VKApiUserFull) getItem(position);

        FriendsViewHolder friendsViewHolder = (FriendsViewHolder) convertView.getTag();

        if(friendsViewHolder == null){
            friendsViewHolder = new FriendsViewHolder(convertView);
            convertView.setTag(friendsViewHolder);
        }

        Glide.with(ctx).load(currentFriend.photo_100).into(friendsViewHolder.getAvatar());

        // Имя Фамилия
        Typeface typeface = Typeface.createFromAsset(ctx.getAssets(), "fonts/Roboto-Medium.ttf");
        friendsViewHolder.getFirstLastNames().setTypeface(typeface);
        friendsViewHolder.getFirstLastNames().setText(currentFriend.first_name + " " + currentFriend.last_name);

        // Вуз
        if (!currentFriend.universities.isEmpty()) {
            typeface = Typeface.createFromAsset(ctx.getAssets(), "fonts/Roboto-Regular.ttf");
            friendsViewHolder.getUniversities().setTypeface(typeface);
            friendsViewHolder.getUniversities().setText(currentFriend.universities.get(0).name);
        }

        // Онлайн статус
        friendsViewHolder.getOnline().setVisibility(currentFriend.online ? View.VISIBLE : View.INVISIBLE);

        return convertView;
    }

}
