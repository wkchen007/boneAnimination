package com.itripatch.bone;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.view.MotionEvent;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

class MySurfaceView extends GLSurfaceView {
    private SceneRenderer mRenderer;//场景渲染器    
    int armTexId;//系统分配的纹理id--胳膊、身体
    int headTexId;//系统分配的纹理id--头
    int floorTexId;//系统分配的纹理 地面
    //各个身体部件的绘制者数组
    LoadedObjectVertexNormalTexture[] lovntArray = new LoadedObjectVertexNormalTexture[12 + 1 + 17];
    //机器人
    Robot robot;
    //执行动作的线程
    DoActionThread dat;
    private final float TOUCH_SCALE_FACTOR = 80.0f / 800;//角度缩放比例
    private float mPreviousX;//上次的触控位置X坐标
    private Context mContext = null;

    public MySurfaceView(Context context) {
        super(context);
        mContext = context;
        this.setEGLContextClientVersion(2); //设置使用OPENGL ES2.0
        mRenderer = new SceneRenderer();    //创建场景渲染器
        setRenderer(mRenderer);                //设置渲染器
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);//设置渲染模式为主动渲染   
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float x = e.getX();
        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float dx = x - mPreviousX;//计算触控笔X位移
                mRenderer.yAngle += dx * TOUCH_SCALE_FACTOR;//设置沿x轴旋转角度
        }
        mPreviousX = x;//记录触控笔位置
        return true;
    }

    private class SceneRenderer implements Renderer {
        float yAngle = 0;

        public void onDrawFrame(GL10 gl) {
            //清除深度缓冲与颜色缓冲
            GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);
            MatrixState.setCamera(
                    (float) (2.5 * Math.sin(yAngle)),   //人眼位置的X
                    0.05f,    //人眼位置的Y
                    (float) (2.5 * Math.cos(yAngle)),   //人眼位置的Z
                    0,    //人眼球看的点X
                    0,   //人眼球看的点Y
                    0,   //人眼球看的点Z
                    0,
                    1,
                    0);

            //绘制机器人
            robot.drawSelf();
        }

        public void onSurfaceChanged(GL10 gl, int width, int height) {
            //设置视窗大小及位置 
            GLES20.glViewport(0, 0, width, height);
            //计算GLSurfaceView的宽高比
            float ratio = (float) width / height;
            //调用此方法计算产生透视投影矩阵
            MatrixState.setProjectFrustum(-ratio, ratio, -1, 1, 2, 100);
            //调用此方法产生摄像机9参数位置矩阵
            MatrixState.setCamera(2f,   //人眼位置的X
                    0.05f,    //人眼位置的Y
                    2f,   //人眼位置的Z
                    0,    //人眼球看的点X
                    0f,   //人眼球看的点Y
                    0f,   //人眼球看的点Z
                    0,
                    1,
                    0);
            MatrixState.setInitStack();
            MatrixState.setLightLocation(0, 10, 10);
        }

        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            //设置屏幕背景色RGBA
            GLES20.glClearColor(1f, 1f, 1f, 1.0f);
            //关闭背面剪裁   
            GLES20.glDisable(GLES20.GL_CULL_FACE);
            //打开深度检测
            GLES20.glEnable(GLES20.GL_DEPTH_TEST);
            //初始化纹理
            armTexId = initTexture(R.drawable.arm);
            headTexId = initTexture(R.drawable.head);//系统分配的纹理id
            floorTexId = initTexture(R.drawable.wenli);
            //加载身体
            lovntArray[0] = LoadUtil.loadFromFile
                    (
                            "action/body.obj",
                            MySurfaceView.this.getResources(),
                            MySurfaceView.this,
                            armTexId
                    );
            //加载头
            lovntArray[1] = LoadUtil.loadFromFile
                    (
                            "action/head.obj",
                            MySurfaceView.this.getResources(),
                            MySurfaceView.this,
                            headTexId
                    );
            //加载左胳膊
            lovntArray[2] = LoadUtil.loadFromFile
                    (
                            "action/left_top.obj",
                            MySurfaceView.this.getResources(),
                            MySurfaceView.this,
                            armTexId
                    );
            lovntArray[3] = LoadUtil.loadFromFile
                    (
                            "action/left_bottom.obj",
                            MySurfaceView.this.getResources(),
                            MySurfaceView.this,
                            armTexId
                    );


            //加载右胳膊
            lovntArray[4] = LoadUtil.loadFromFile
                    (
                            "action/right_top.obj",
                            MySurfaceView.this.getResources(),
                            MySurfaceView.this,
                            armTexId
                    );
            lovntArray[5] = LoadUtil.loadFromFile
                    (
                            "action/right_bottom.obj",
                            MySurfaceView.this.getResources(),
                            MySurfaceView.this,
                            armTexId
                    );

            //加载右腿
            lovntArray[6] = LoadUtil.loadFromFile
                    (
                            "action/right_leg_top.obj",
                            MySurfaceView.this.getResources(),
                            MySurfaceView.this,
                            armTexId
                    );
            lovntArray[7] = LoadUtil.loadFromFile
                    (
                            "action/right_leg_bottom.obj",
                            MySurfaceView.this.getResources(),
                            MySurfaceView.this,
                            armTexId
                    );

            //加载左腿
            lovntArray[8] = LoadUtil.loadFromFile
                    (
                            "action/left_leg_top.obj",
                            MySurfaceView.this.getResources(),
                            MySurfaceView.this,
                            armTexId
                    );
            lovntArray[9] = LoadUtil.loadFromFile
                    (
                            "action/left_leg_bottom.obj",
                            MySurfaceView.this.getResources(),
                            MySurfaceView.this,
                            armTexId
                    );

            //加载左脚
            lovntArray[10] = LoadUtil.loadFromFile
                    (
                            "action/left_foot.obj",
                            MySurfaceView.this.getResources(),
                            MySurfaceView.this,
                            armTexId
                    );
            lovntArray[11] = LoadUtil.loadFromFile
                    (
                            "action/right_foot.obj",
                            MySurfaceView.this.getResources(),
                            MySurfaceView.this,
                            armTexId
                    );
            //加载脖子
            lovntArray[12] = LoadUtil.loadFromFile
                    (
                            "action/neck.obj",
                            MySurfaceView.this.getResources(),
                            MySurfaceView.this,
                            armTexId
                    );
            //加载胸椎(thoracic vertebrae):12
            for (int i = 1; i <= 12; i++) {
                lovntArray[12 + i] = LoadUtil.loadFromFile
                        (
                                "action/spine/tv" + i + ".obj",
                                MySurfaceView.this.getResources(),
                                MySurfaceView.this,
                                armTexId
                        );
            }
            //加载腰椎(lumbar vertebrae):5
            for (int i = 1; i <= 5; i++) {
                lovntArray[24 + i] = LoadUtil.loadFromFile
                        (
                                "action/spine/lv" + i + ".obj",
                                MySurfaceView.this.getResources(),
                                MySurfaceView.this,
                                armTexId
                        );
            }
            robot = new Robot(lovntArray, MySurfaceView.this);
            dat = new DoActionThread(robot, MySurfaceView.this);
            dat.start();
        }
    }

    public int initTexture(int resId)//textureId
    {
        //生成纹理ID
        int[] textures = new int[1];
        GLES20.glGenTextures
                (
                        1,          //产生的纹理id的数量
                        textures,   //纹理id的数组
                        0           //偏移量
                );
        int textureId = textures[0];
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureId);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);


        //通过输入流加载图片===============begin===================
        InputStream is = this.getResources().openRawResource(resId);
        Bitmap bitmapTmp;
        try {
            bitmapTmp = BitmapFactory.decodeStream(is);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //通过输入流加载图片===============end=====================  

        //实际加载纹理
        GLUtils.texImage2D
                (
                        GLES20.GL_TEXTURE_2D,   //纹理类型，在OpenGL ES中必须为GL10.GL_TEXTURE_2D
                        0,                      //纹理的层次，0表示基本图像层，可以理解为直接贴图
                        bitmapTmp,              //纹理图像
                        0                      //纹理边框尺寸
                );
        bitmapTmp.recycle();          //纹理加载成功后释放图片

        return textureId;
    }
}
