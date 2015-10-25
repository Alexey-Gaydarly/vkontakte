package ru.vilka.vkontakte.adapter.view_holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ru.vilka.vkontakte.R;

public class FriendsViewHolder {

    private ImageView mAvatar = null;
    private TextView mfullName = null;
    private TextView mUniversities = null;
    private TextView mOnline = null;

    public FriendsViewHolder(View view){
        mAvatar = (ImageView) view.findViewById(R.id.avatar_friends);
        mfullName = (TextView) view.findViewById(R.id.fullName_friends);
        mUniversities = (TextView) view.findViewById(R.id.universities_friends);
        mOnline = (TextView) view.findViewById(R.id.status_online_friends);
    }

    public ImageView getAvatar() {
        return mAvatar;
    }

    public TextView getUniversities() {
        return mUniversities;
    }

    public TextView getFirstLastNames() {
        return mfullName;
    }

    public TextView getOnline() {
        return mOnline;
    }
}
