package com.lwang.keyboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.ziyeyouhu.library.KeyboardTouchListener;
import com.ziyeyouhu.library.KeyboardUtil;


public class MainActivity extends AppCompatActivity {

    private LinearLayout parent;
    private ScrollView scroll;
    private EditText etPwd;
    private EditText etAccess;
    private TextView tvLogin;
    private KeyboardUtil keyboardUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parent = (LinearLayout) findViewById(R.id.parent);
        scroll = (ScrollView) findViewById(R.id.scroll);
        etAccess = (EditText) findViewById(R.id.et_access);
        etPwd = (EditText) findViewById(R.id.et_pwd);
        tvLogin = (TextView) findViewById(R.id.tv_login);

        initKeyboard();


        tvLogin.setOnClickListener(v -> {

            hideKeyBoard();
            String access = etAccess.getText().toString().trim();
            String pwd = etPwd.getText().toString().trim();
            Toast.makeText(this, "账号：" + access + "密码：" + pwd, Toast.LENGTH_SHORT).show();
        });
    }


    /**
     * 初始化键盘
     */
    private void initKeyboard() {

        keyboardUtil = new KeyboardUtil(this, parent, scroll);
        keyboardUtil.setKeyBoardStateChangeListener((i, editText) -> {
        });
        keyboardUtil.setInputOverListener((i, editText) -> {
        });
        keyboardUtil.setOtherEdittext(etAccess);
        keyboardUtil.setKeyBoardCursorNew(etPwd);
        etPwd.setOnTouchListener(new KeyboardTouchListener(keyboardUtil, KeyboardUtil.INPUTTYPE_ABC, -1));
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            if (keyboardUtil != null && keyboardUtil.isShow) {
                hideKeyBoard();
            } else {
                return super.onKeyDown(keyCode, event);
            }
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }


    /**
     * 隐藏安全键盘
     */
    private void hideKeyBoard() {

        keyboardUtil.hideSystemKeyBoard();
        keyboardUtil.hideAllKeyBoard();
        keyboardUtil.hideKeyboardLayout();
    }


}
