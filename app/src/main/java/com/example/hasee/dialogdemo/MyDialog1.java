package com.example.hasee.dialogdemo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

public class MyDialog1 extends Dialog {
    //在构造方法里提前加载了样式
    public MyDialog1(Context context){
        this(context,R.style.MyDialog);
    }

    public MyDialog1(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //提前设置Dialog的一些样式
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams p = dialogWindow.getAttributes();
//        p.x = 100;
//        p.y = 100;
        p.gravity = Gravity.CENTER_HORIZONTAL;
        dialogWindow.setAttributes(p);
    }
}
