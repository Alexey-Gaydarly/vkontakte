package ru.vilka.vkontakte.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiUserFull;
import com.vk.sdk.api.model.VKUsersArray;
import com.vk.sdk.dialogs.VKCaptchaDialog;

import java.util.ArrayList;

import ru.vilka.vkontakte.R;
import ru.vilka.vkontakte.adapter.FriendsAdapter;

public class FriendFragment extends ListFragment {

    private ArrayList<VKApiUserFull> mSparseArrayFriend = new ArrayList<VKApiUserFull>();
    private FriendsAdapter friendsAdapter = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        friendsAdapter = new FriendsAdapter(mSparseArrayFriend, R.layout.friends_list_adapter, getActivity());

        VKRequest request = VKApi.friends().get(VKParameters.from("order", "hints", VKApiConst.FIELDS, "id,first_name,last_name,photo_100,online,universities"));
        request.executeWithListener(new VKFriendListener());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_friend, container, false);
    }

    public class VKFriendListener extends VKRequest.VKRequestListener{

        public VKFriendListener() {
            super();
        }

        @Override
        public void onComplete(VKResponse response) {
            super.onComplete(response);
            for (VKApiUserFull currentUser : (VKUsersArray) response.parsedModel) {
                friendsAdapter.addItem(currentUser);
            }

            setListAdapter(friendsAdapter);
        }

        @Override
        public void onError(VKError error) {
            super.onError(error);
            if(error.errorCode == 14){
                new VKCaptchaDialog(error);
            }
        }
    }

}
