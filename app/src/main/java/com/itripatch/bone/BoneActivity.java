package com.itripatch.bone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class BoneActivity extends AppCompatActivity {
    private MySurfaceView mGLSurfaceView;
    private FrameLayout boneformLayout;
    private Button ac0, ac1, ac2, ac3, ac4, ac5, ac6, ac7;
    private String TAG = "MyBone";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bone);

        boneformLayout = (FrameLayout) findViewById(R.id.bone_frame);

        mGLSurfaceView = new MySurfaceView(this);
        mGLSurfaceView.requestFocus();//获取焦点
        mGLSurfaceView.setFocusableInTouchMode(true);//设置为可触控
        boneformLayout.addView(mGLSurfaceView);

        ac0 = (Button) findViewById(R.id.ac0);
        ac0.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoActionThread.acStart2(0);
            }
        });

        ac1 = (Button) findViewById(R.id.ac1);
        ac1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoActionThread.acStart2(1);
            }
        });

        ac2 = (Button) findViewById(R.id.ac2);
        ac2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoActionThread.acStart2(2);
            }
        });

        ac3 = (Button) findViewById(R.id.ac3);
        ac3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoActionThread.acStart2(3);
            }
        });

        ac4 = (Button) findViewById(R.id.ac4);
        ac4.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoActionThread.acStart2(4);
            }
        });

        ac5 = (Button) findViewById(R.id.ac5);
        ac5.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoActionThread.acStart2(5);
            }
        });

        ac6 = (Button) findViewById(R.id.ac6);
        ac6.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoActionThread.acStart2(6);
            }
        });

        ac7=(Button) findViewById(R.id.ac7);
        ac7.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoActionThread.acStart2(7);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGLSurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGLSurfaceView.onPause();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            System.exit(0);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
