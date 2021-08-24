package com.example.d_bee_final.ui.login_page;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.d_bee_final.MyApp;
import com.example.d_bee_final.R;
import com.example.d_bee_final.logic.bean.LoginBean;
import com.example.d_bee_final.logic.dao.UserDao;
import com.example.d_bee_final.ui.MainActivity;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;


public class LoginFragment extends Fragment {

    private boolean isUserNameReady = false;
    private boolean isPassWordReady = false;
    private TextInputEditText textInputEditTextUserName;
    private TextInputEditText textInputEditTextPassWord;
    private Button buttonLogin;
    private CheckBox checkBoxRememberPassWord;
    private UserDao userDao = new UserDao();
    private TextView textViewGoToRegister;
    private LoginViewModel loginViewModel;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        initView(view);
        return view;
    }


    private void initView(View view) {
        textInputEditTextUserName = view.findViewById(R.id.tiet_username);
        textInputEditTextPassWord = view.findViewById(R.id.tiet_password);
        buttonLogin = view.findViewById(R.id.btn_login);
        checkBoxRememberPassWord = view.findViewById(R.id.cb_remember_password);
        textViewGoToRegister = view.findViewById(R.id.tv_hint_for_register);

//      启用button
        enableLoginButton();
//        填充账号密码 初始化UI
        initInputText();
//        记住账号密码
        checkBoxRememberInfo();
//        跳转到注册页面
        goToRegisterPage();
//        给登录按钮注册点击事件
        setOnLoginClick();

    }

    //        给登录按钮注册点击事件
    private void setOnLoginClick() {
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = Objects.requireNonNull(textInputEditTextUserName.getText()).toString();
                String passWord = Objects.requireNonNull(textInputEditTextPassWord.getText()).toString();
                loginViewModel.getLoginMutableLiveData().observe(getViewLifecycleOwner(), new Observer<LoginBean>() {
                    @Override
                    public void onChanged(LoginBean loginBean) {
                        if (loginBean.getErrorCode() == 0) {
                            Toast.makeText(MyApp.context, "登录成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(requireActivity(), MainActivity.class);
                            startActivity(intent);
                            requireActivity().finish();
                        } else
                            Toast.makeText(MyApp.context, loginBean.getErrorMsg(), Toast.LENGTH_SHORT).show();

                    }
                });
                loginViewModel.login(userName, passWord);

            }
        });
    }

    //    跳转到注册页面
    private void goToRegisterPage() {
        textViewGoToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                切换到注册Fragment
                FragmentManager childFragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = childFragmentManager.beginTransaction();
//                fragmentTransaction.setCustomAnimations(R.anim.slide_right_in,R.anim.slide_left_out);
                fragmentTransaction.replace(R.id.fragment_container_view_login, new RegisterFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

    //        填充账号密码 初始化UI
    private void initInputText() {
        if (userDao.isUserInfoSaved()) {
            textInputEditTextUserName.setText(userDao.getUserName());
            textInputEditTextPassWord.setText(userDao.getPassWord());
            checkBoxRememberPassWord.setChecked(true);
        }

    }

    //        记住账号密码
    private void checkBoxRememberInfo() {

        checkBoxRememberPassWord.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    userDao.saveUserInfo(Objects.requireNonNull(textInputEditTextUserName.getText()).toString(), Objects.requireNonNull(textInputEditTextPassWord.getText()).toString());
                else userDao.clearUserInfo();
            }
        });
    }

    //      启用button
    private void enableLoginButton() {
        textInputEditTextUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isUserNameReady = s.length() > 0;
                //                如果账号和密码都不为空启用登录按钮
                buttonLogin.setEnabled(isPassWordReady && isUserNameReady);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });
        textInputEditTextPassWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isPassWordReady = s.length() > 0;
//                如果账号和密码都不为空启用登录按钮
                buttonLogin.setEnabled(isPassWordReady && isUserNameReady);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}