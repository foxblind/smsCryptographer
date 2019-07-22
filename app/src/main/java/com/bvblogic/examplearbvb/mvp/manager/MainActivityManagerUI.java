package com.bvblogic.examplearbvb.mvp.manager;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import com.bvblogic.examplearbvb.R;
import com.bvblogic.examplearbvb.fragment.ChatsFragment_;
import com.bvblogic.examplearbvb.fragment.CreateChatFragment_;
import com.bvblogic.examplearbvb.fragment.MessagesFragment_;
import com.bvblogic.examplearbvb.mvp.core.FragmentById;
import com.bvblogic.examplearbvb.mvp.core.FragmentData;
import com.bvblogic.examplearbvb.mvp.manager.core.BaseMainActivityManagerUI;
import java.util.ArrayList;


/**
 * Created by hanz on 7.05.18.
 */
public class MainActivityManagerUI extends BaseMainActivityManagerUI {

    public MainActivityManagerUI(AppCompatActivity activity) {
        super(activity);
    }

    @Override
    protected int getIdFragmentsContainer() {
        return R.id.fragment_container;
    }

    @Override
    protected void initUI() {
    }

    @SuppressLint("CommitTransaction")
    @Override
    public void changeFragmentTo(FragmentData fragment) {
        FragmentById gragmentId = fragment.getFragmentById();
        switch (gragmentId) {
            
            case CHAT_LIST_FRAGMENT:
                {
                    removeFragment();
                    addFragmentToContainer(ChatsFragment_.builder().build(), false,
                        this.getActivity().
                                getSupportFragmentManager().
                                beginTransaction().
                                setCustomAnimations(R.anim.anim_enter,  R.anim.anim_exit));
                break;
            }

            case MESSAGES_FRAGMENT:
                {
                    addFragmentToContainer(MessagesFragment_.builder().build(),true,
                        this.getActivity().
                                getSupportFragmentManager().
                                beginTransaction().
                                setCustomAnimations(R.anim.anim_enter,  R.anim.anim_exit));
                    fragments.add(MessagesFragment_.class.getSimpleName());
                 break;
            }
            case CREATE_CHAT_FRAGMENT:
            {
                    addFragmentToContainer(CreateChatFragment_.builder().build(),true,
                        this.getActivity().
                                getSupportFragmentManager().
                                beginTransaction().
                                setCustomAnimations(R.anim.anim_enter,  R.anim.anim_exit));
                fragments.add(CreateChatFragment_.class.getSimpleName());

                break;
            }

        }
    }

    private void removeAnimFragment(Fragment fragment, int anim) {
        this.getActivity().getSupportFragmentManager().
                beginTransaction().
                setCustomAnimations(R.anim.anim_enter, anim).
                remove(fragment).
                commitAllowingStateLoss();
        getActivity().getSupportFragmentManager().popBackStack();
    }

    private static ArrayList<String> fragments = new ArrayList<>();

    @Override
    public boolean removeFragment() {
        for (String s : fragments) {
            Fragment fragment = this.getActivity().getSupportFragmentManager().findFragmentByTag(s);
            if (fragment != null) {
//                if (fragment.getClass().getSimpleName().equals(SplashFragment_.class.getSimpleName())) {
//                    BaseFragment.changeColorBar(getActivity(), BaseFragment.ColorBar.WHITE_DARK);
//                }
                removeAnimFragment(fragment, R.anim.anim_exit);
                return true;
            }

        }
        return false;
    }
}
