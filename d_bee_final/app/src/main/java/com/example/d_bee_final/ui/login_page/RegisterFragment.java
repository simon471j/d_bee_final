package com.example.d_bee_final.ui.login_page;

import android.os.Bundle;

import androidx.annotation.Nullable;
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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.d_bee_final.MyApp;
import com.example.d_bee_final.R;
import com.example.d_bee_final.logic.bean.LoginBean;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;


public class RegisterFragment extends Fragment {


    private ImageView imageViewBackArrow;
    private Button buttonRegister;
    private TextInputEditText textInputEditTextUserName;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;
    private boolean isRegisterUserNameReady;
    private boolean isRegisterPasswordReady;
    private boolean isRegisterConfirmPasswordReady;
    private boolean isPasswordAndConfirmPasswordTheSame;
    private RegisterViewModel registerViewModel;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //    添加动画
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        int id = enter ? R.anim.slide_left_in : R.anim.slide_left_out;
        return AnimationUtils.loadAnimation(getActivity(), id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
//        初始化
        initView(view);
        return view;
    }


    private void initView(View view) {
        imageViewBackArrow = view.findViewById(R.id.iv_back_arrow);
        buttonRegister = view.findViewById(R.id.btn_register);
        textInputEditTextUserName = view.findViewById(R.id.tiet_register_username);
        textInputEditTextPassword = view.findViewById(R.id.tiet_register_password);
        textInputEditTextConfirmPassword = view.findViewById(R.id.tiet_confirm_password);
//        点击返回的相应事件
        clickBack();
//        启用注册按键
        enableRegisterButton();
//        点击注册
        onClickRegister();

    }

    private void onClickRegister() {
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = Objects.requireNonNull(textInputEditTextUserName.getText()).toString();
                String password = Objects.requireNonNull(textInputEditTextPassword.getText()).toString();
                String repassword = Objects.requireNonNull(textInputEditTextConfirmPassword.getText()).toString();
                registerViewModel.getRegisterMutableLiveData().observe(getViewLifecycleOwner(), new Observer<LoginBean>() {
                    @Override
                    public void onChanged(LoginBean loginBean) {
                        if (loginBean.getErrorCode() == 0) {
                            Toast.makeText(MyApp.context, "注册成功", Toast.LENGTH_SHORT).show();
                            FragmentManager parentFragmentManager = getParentFragmentManager();
                            FragmentTransaction fragmentTransaction = parentFragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.fragment_container_view_login, new LoginFragment());
                            fragmentTransaction.commit();
                        } else
                            Toast.makeText(MyApp.context, loginBean.getErrorMsg(), Toast.LENGTH_SHORT).show();
                    }
                });
                registerViewModel.register(userName, password, repassword);

            }
        });
    }

    //    启用注册按钮
    private void enableRegisterButton() {
        textInputEditTextUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isRegisterUserNameReady = s.length() > 0;
                buttonRegister.setEnabled(isPasswordAndConfirmPasswordTheSame && isRegisterConfirmPasswordReady && isRegisterUserNameReady && isRegisterPasswordReady);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        textInputEditTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isRegisterPasswordReady = s.length() > 0;
                isPasswordAndConfirmPasswordTheSame = s.toString().equals(Objects.requireNonNull(textInputEditTextConfirmPassword.getText()).toString());
                buttonRegister.setEnabled(isPasswordAndConfirmPasswordTheSame && isRegisterConfirmPasswordReady && isRegisterUserNameReady && isRegisterPasswordReady);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        textInputEditTextConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isRegisterConfirmPasswordReady = s.length() > 0;
                isPasswordAndConfirmPasswordTheSame = s.toString().equals(Objects.requireNonNull(textInputEditTextPassword.getText()).toString());
                if (!isPasswordAndConfirmPasswordTheSame)
                    textInputEditTextConfirmPassword.setError("确保两次输入的密码一致");
                buttonRegister.setEnabled(isPasswordAndConfirmPasswordTheSame && isRegisterConfirmPasswordReady && isRegisterUserNameReady && isRegisterPasswordReady);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    //    点击返回
    private void clickBack() {
        imageViewBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager parentFragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = parentFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container_view_login, new LoginFragment());
                fragmentTransaction.commit();
            }
        });
    }
}