package com.example.hasee.dialogdemo;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,MyDialog1.OnCenterItemClickListener{
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;

    private MyDialog1 myDialog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button)findViewById(R.id.btn_1);
        btn1.setOnClickListener(this);
        btn2 = (Button)findViewById(R.id.btn_2);
        btn2.setOnClickListener(this);
        btn3 = (Button)findViewById(R.id.btn_3);
        btn3.setOnClickListener(this);
        btn4 = (Button)findViewById(R.id.btn_4);
        btn4.setOnClickListener(this);
        btn5 = (Button)findViewById(R.id.btn_5);
        btn5.setOnClickListener(this);
        btn6 = (Button)findViewById(R.id.btn_6);
        btn6.setOnClickListener(this);
        btn7 = (Button)findViewById(R.id.btn_7);
        btn7.setOnClickListener(this);
        btn8 = (Button)findViewById(R.id.btn_8);
        btn8.setOnClickListener(this);
        btn9 = (Button)findViewById(R.id.btn_9);
        btn9.setOnClickListener(this);
        myDialog1 = new MyDialog1(this,R.layout.dialog_2,new int[]{R.id.dialog_btn});
        myDialog1.setOnCenterItemClickListener((MyDialog1.OnCenterItemClickListener) this);
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_1:
                showAlterDialog();
                break;
            case R.id.btn_2:
                showListDialog();
                break;
            case R.id.btn_3:
                showSingDialog();
                break;
            case R.id.btn_4:
                showMultiChoiceDialog();
                break;
            case R.id.btn_5:
                showProgressDialog();
                break;
            case R.id.btn_6:
                showWhiteDialog();
                break;
            case R.id.btn_7:
                DiyDialog1();
                break;
            case R.id.btn_8:
                DiyDialog2();
                break;
            case R.id.btn_9:
                myDialog1.show();
                break;



        }

    }
    /**
     * 普通dialog
     */
    private void showAlterDialog(){
        final AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(MainActivity.this);
        alterDiaglog.setIcon(R.drawable.icon);//图标
        alterDiaglog.setTitle("简单的dialog");//文字
        alterDiaglog.setMessage("生存还是死亡");//提示消息
        //积极的选择
        alterDiaglog.setPositiveButton("生存", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"点击了生存",Toast.LENGTH_SHORT).show();
            }
        });
        //消极的选择
        alterDiaglog.setNegativeButton("死亡", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"点击了死亡",Toast.LENGTH_SHORT).show();
            }
        });

        alterDiaglog.setNeutralButton("不生不死", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"点击了不生不死",Toast.LENGTH_SHORT).show();
            }
        });

        //显示
        alterDiaglog.show();
    }
    /**
     * 列表Dialog
     */
    private void showListDialog(){
        final String[] items = {"我是1","我是2","我是3"};
        AlertDialog.Builder listDialog = new AlertDialog.Builder(MainActivity.this);
        listDialog.setIcon(R.drawable.icon);//图标
        listDialog.setTitle("我就是个列表Dialog");
        listDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"点击了"+items[which],Toast.LENGTH_SHORT).show();
            }
        });
        listDialog.show();
    }
    /**
     * 单选Dialog
     */
    int choice;
    private void showSingDialog(){
        final String[] items = {"我是1","我是2","我是3"};
        AlertDialog.Builder singleChoiceDialog = new AlertDialog.Builder(MainActivity.this);
        singleChoiceDialog.setIcon(R.drawable.icon);
        singleChoiceDialog.setTitle("我是单选Dialo");
        //第二个参数是默认的选项
        singleChoiceDialog.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                choice= which;
            }
        });
        singleChoiceDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (choice!=-1){
                    Toast.makeText(MainActivity.this,
                            "你选择了" + items[choice],
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        singleChoiceDialog.show();
    }
    /**
     * 多选对话框
     */
    ArrayList<Integer> choices= new ArrayList<>();
    private void showMultiChoiceDialog(){
        final String[] items = {"我是1","我是2","我是3"};
        //设置默认选择都是false
        final boolean initchoices[] = {false,false,false};
        choices.clear();
        AlertDialog.Builder multChoiceDialog = new AlertDialog.Builder(MainActivity.this);
        multChoiceDialog.setIcon(R.drawable.icon);
        multChoiceDialog.setTitle("我是个多选Dialog");
        multChoiceDialog.setMultiChoiceItems(items, initchoices, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked){
                    choices.add(which);
                }else {
                    choices.remove(which);
                }
            }
        });
        multChoiceDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int size = choices.size();
                String str = "";
                for(int i = 0;i<size;i++){
                    str+=items[choices.get(i)]+"";
                }
                Toast.makeText(MainActivity.this,
                        "你选中了" + str,
                        Toast.LENGTH_SHORT).show();
            }
        });
        multChoiceDialog.show();
    }
    /**等待Dialog具有屏蔽其他控件的交互能力
     * @setCancelable 为使屏幕不可点击，设置为不可取消(false)
     * 下载等事件完成后，主动调用函数关闭该Dialog
     */
    private void showProgressDialog(){
        final int MAX = 100;
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("我是个等待的Dialog");
        progressDialog.setMessage("等待中");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
    /**
     * 进度条Dialog
     */
    private void showWhiteDialog(){
        /* @setProgress 设置初始进度
         * @setProgressStyle 设置样式（水平进度条）
         * @setMax 设置进度最大值
         */
        final int Max = 100;
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setProgress(0);
        progressDialog.setIcon(R.drawable.icon);
        progressDialog.setTitle("我是一个进度条Dialog");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(Max);
        progressDialog.show();
        /**
         * 开个线程
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                int p = 0;
                while (p<Max){
                    try {
                        Thread.sleep(100);
                        p++;
                        progressDialog.setProgress(p);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                progressDialog.cancel();//达到最大就消失
                }

        }).start();
    }
    /**
     * 自定义1 控制普通的dialog的位置，大小，透明度
     * 在普通的dialog.show下面添加东西
     */
    private void DiyDialog1(){
        AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(MainActivity.this);
        alterDiaglog.setIcon(R.drawable.icon);//图标
        alterDiaglog.setTitle("简单的dialog");//文字
        alterDiaglog.setMessage("生存还是死亡");//提示消息
        //积极的选择
        alterDiaglog.setPositiveButton("生存", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"点击了生存",Toast.LENGTH_SHORT).show();
            }
        });
        //消极的选择
        alterDiaglog.setNegativeButton("死亡", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"点击了死亡",Toast.LENGTH_SHORT).show();
            }
        });

        alterDiaglog.setNeutralButton("不生不死", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"点击了不生不死",Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = alterDiaglog.create();

        //显示
        dialog.show();
        //自定义的东西
       //放在show()之后，不然有些属性是没有效果的，比如height和width
        Window dialogWindow = dialog.getWindow();
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        // 设置高度和宽度
        p.height = (int) (d.getHeight() * 0.4); // 高度设置为屏幕的0.6
        p.width = (int) (d.getWidth() * 0.6); // 宽度设置为屏幕的0.65

        p.gravity = Gravity.TOP;//设置位置

        p.alpha = 0.8f;//设置透明度
        dialogWindow.setAttributes(p);
    }
    /**
     * 自定义dialog2 简单自定义布局
     */
    private void DiyDialog2() {
        AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(MainActivity.this,R.style.MyDialog);
        alterDiaglog.setView(R.layout.dialog_1);//加载进去
        AlertDialog dialog = alterDiaglog.create();
        //显示
        dialog.show();
        //自定义的东西
    }
    /**
     * 完全自定义dialog2
     */
//    private void DiyDialog3() {
//        MyDialog1 myDialog1 = new MyDialog1(MainActivity.this);
//        myDialog1.setContentView(R.layout.dialog_2);
//        Button s = (Button)myDialog1.findViewById(R.id.dialog_btn);
//        s.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"点击了",Toast.LENGTH_SHORT).show();
//            }
//        });
//        myDialog1.show();
//    }


    @Override
    public void OnCenterItemClick(MyDialog1 dialog, View view) {
        switch (view.getId()){
            case R.id.dialog_btn:
                Toast.makeText(getApplicationContext(),"点击了",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

    }
}
