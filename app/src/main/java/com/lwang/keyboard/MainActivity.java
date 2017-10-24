package com.lwang.keyboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.ziyeyouhu.library.KeyboardTouchListener;
import com.ziyeyouhu.library.KeyboardUtil;


public class MainActivity extends AppCompatActivity {

    private KeyboardUtil keyboardUtil;
    private LinearLayout parent;
    private ScrollView scroll;
    private EditText etPwd;
    private EditText etAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parent = (LinearLayout) findViewById(R.id.parent);
        scroll = (ScrollView) findViewById(R.id.scroll);
        etAccess = (EditText) findViewById(R.id.et_access);
        etPwd = (EditText) findViewById(R.id.et_pwd);

        initKeyboard();
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

                keyboardUtil.hideSystemKeyBoard();
                keyboardUtil.hideAllKeyBoard();
                keyboardUtil.hideKeyboardLayout();
            } else {

                return super.onKeyDown(keyCode, event);
            }
            return false;

        } else {
            return super.onKeyDown(keyCode, event);
        }
    }


}
