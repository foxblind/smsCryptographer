package com.bvblogic.examplearbvb.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import com.bvblogic.examplearbvb.R;
import com.bvblogic.examplearbvb.activity.core.BaseActivity;
import com.bvblogic.examplearbvb.db.domain.Chat;
import com.bvblogic.examplearbvb.db.presenter.ChatPresenter;
import com.bvblogic.examplearbvb.fragment.core.BaseFragment;
import com.bvblogic.examplearbvb.mvp.core.FragmentById;
import com.bvblogic.examplearbvb.mvp.core.FragmentData;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;


@EFragment(R.layout.fragment_create_chat)
public class CreateChatFragment extends BaseFragment {

    EditText phoneTxt;
    EditText keysTxt;
    Button createBtn;


    @Bean
    ChatPresenter chatPresenter;

    @AfterViews
    public void init() {
        phoneTxt = getActivity().findViewById(R.id.phoneTxt);
        keysTxt = getActivity().findViewById(R.id.keysTxt);
        createBtn = getActivity().findViewById(R.id.createBtn);




    }

    @Click
    void createBtn()
    {
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        int i = sharedPref.getInt("ID", 0);

        Chat chat = new Chat();
        chat.setId(i);
        chat.setPhone(phoneTxt.getText().toString());

        chatPresenter.saveChat(chat);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("ID", ++i);
        editor.apply();

        InputMethodManager inputManager = (InputMethodManager) getActivity()
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        View currentFocusedView = getActivity().getCurrentFocus();
        if (currentFocusedView != null) {
            inputManager.hideSoftInputFromWindow(currentFocusedView.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

        ((BaseActivity)getActivity()).changeFragmentTo(new FragmentData(FragmentById.CHAT_LIST_FRAGMENT));
    }

}
