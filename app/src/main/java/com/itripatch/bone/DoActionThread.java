package com.itripatch.bone;

public class DoActionThread extends Thread {
    String TAG = "MyThread";
    static int currActionIndex2 = 0;//当前动作编号
    static int currStep2 = 0;//当前的动作编号细节编号
    Action[] currAction2;//当前动作数据
    Robot robot;
    MySurfaceView msv;
    static int acCase = 2;
    static boolean stand = false;

    public static void acStart2(int index) {
        currActionIndex2 = index;
        currStep2 = 0;
        stand = false;
        acCase = 2;
    }

    public DoActionThread(Robot robot, MySurfaceView msv) {
        this.robot = robot;
        this.msv = msv;
    }

    public void run() {
        while (true) {
            switch (acCase) {
                case 2: {
                    if (!stand) {
                        currAction2 = ActionGenerator2.acArray[currActionIndex2];    //拿到当前的动作编号
                        for (int i = 0; i < currAction2.length; i++) {
                            //如果此次动画播放完了，则进行下一组动画的播放
                            while (currStep2 <= currAction2[i].totalStep) {
                                robot.backToInit();//变成最原始的初始变化矩阵，

                                //将ActionGenerator中给出的动作数据进行分解
                                for (float[] ad : currAction2[i].data) {//修改数据
                                    int partIndex = (int) ad[0];//部件索引
                                    int aType = (int) ad[1]; //动作类型
                                    if (aType == 0) {//若aType==0此部件动作为平移
                                        float xStart = ad[2];//起始位置的x坐标
                                        float yStart = ad[3];//起始位置的y坐标
                                        float zStart = ad[4];//起始位置的z坐标
                                        float xEnd = ad[5];//部件最终要到达位置的x坐标
                                        float yEnd = ad[6];//部件最终要到达位置的y坐标
                                        float zEnd = ad[7];//部件最终要到达位置的z坐标
                                        //根据当前的动作编号细节编号，计算出当前的平移距离
                                        float currX = xStart + (xEnd - xStart) * currStep2 / currAction2[i].totalStep;
                                        float currY = yStart + (yEnd - yStart) * currStep2 / currAction2[i].totalStep;
                                        float currZ = zStart + (zEnd - zStart) * currStep2 / currAction2[i].totalStep;
                                        //将当前部件的平移信息记录进变换矩阵
                                        robot.bpArray[partIndex].transtate(currX, currY, currZ);
                                    } else if (aType == 1) {//若aType==0此部件动作为旋转
                                        float startAngle = ad[2];//旋转的起始角度
                                        float endAngle = ad[3];//旋转的介绍角度
                                        //根据当前的动作编号细节编号出当前的旋转角度
                                        float currAngle = startAngle + (endAngle - startAngle) * currStep2 / currAction2[i].totalStep;
                                        //此部件的旋转轴
                                        float x = ad[4];
                                        float y = ad[5];
                                        float z = ad[6];
                                        //将当前部件的旋转信息记录进变换矩阵
                                        robot.bpArray[partIndex].rotate(currAngle, x, y, z);
                                    }
                                }
                                robot.updateState();//层次级联更新骨骼矩阵的方法
                                //将最终矩阵内容拷贝进绘制用的最终矩阵
                                robot.flushDrawData();
                                currStep2++;  //当前的动作编号细节编号+1
                                try {
                                    Thread.sleep(25);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            currStep2 = 0;//当前的动作编号细节编号，变为0;
                        }
                        stand = true;
                    }
                    break;
                }
            }
        }
    }
}