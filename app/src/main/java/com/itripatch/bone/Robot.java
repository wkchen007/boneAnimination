package com.itripatch.bone;

public class Robot {
    //机器人的各个部件
    BodyPart bRoot, bBody, bHead, bLeftTop, bLeftBottom, bRightTop, bRightBottom,
            bRightLegTop,bRightLegBottom,bLeftLegTop,bLeftLegBottom,bLeftFoot,bRightFoot,bNeck,
            bTV1,bTV2,bTV3,bTV4,bTV5,bTV6,bTV7,bTV8,bTV9,bTV10,bTV11,bTV12,
            bLV1,bLV2,bLV3,bLV4,bLV5;
    BodyPart[] bpArray;
    //用于绘制的最终矩阵数组
    float[][] fianlMatrixForDrawArray;
    //用于绘制的临时矩阵数组
    float[][] fianlMatrixForDrawArrayTemp;
    Object lock = new Object();//绘制数据锁

    public Robot(LoadedObjectVertexNormalTexture[] lovntArray, MySurfaceView msv) {
        bRoot = new BodyPart(0, 0, 0, null, 0, msv, this);//
        bBody = new BodyPart(0.0f, 0.938f, 0.0f, lovntArray[0], 1, msv, this);
        bHead = new BodyPart(0.0f, 1.00f, 0.0f, lovntArray[1], 2, msv, this);
        bLeftTop = new BodyPart(0.107f, 0.938f, 0.0f, lovntArray[2], 3, msv, this);
        bLeftBottom = new BodyPart(0.105f, 0.707f, -0.033f, lovntArray[3], 4, msv, this);
        bRightTop = new BodyPart(-0.107f, 0.938f, 0.0f, lovntArray[4], 5, msv, this);
        bRightBottom = new BodyPart(-0.105f, 0.707f, -0.033f, lovntArray[5], 6, msv, this);
        bRightLegTop = new BodyPart(-0.068f, 0.6f, 0.02f, lovntArray[6], 7, msv, this);
        bRightLegBottom = new BodyPart(-0.056f, 0.312f, 0f, lovntArray[7], 8, msv, this);
        bLeftLegTop = new BodyPart(0.068f, 0.6f, 0.02f, lovntArray[8], 9, msv, this);
        bLeftLegBottom = new BodyPart(0.056f, 0.312f, 0f, lovntArray[9], 10, msv, this);
        bLeftFoot = new BodyPart(0.068f, 0.038f, 0.033f, lovntArray[10], 11, msv, this);
        bRightFoot = new BodyPart(-0.068f, 0.038f, 0.033f, lovntArray[11], 12, msv, this);
        bNeck = new BodyPart(0.00097f,0.6222f,-0.03896f, lovntArray[12], 13, msv, this);
        bTV1 = new BodyPart(0.00097f,0.6222f,-0.03896f, lovntArray[13], 14, msv, this);
        bTV2 = new BodyPart(0.00097f,0.6222f,-0.03896f, lovntArray[14], 15, msv, this);
        bTV3 = new BodyPart(0.00097f,0.6222f,-0.03896f, lovntArray[15], 16, msv, this);
        bTV4 = new BodyPart(0.00097f,0.6222f,-0.03896f, lovntArray[16], 17, msv, this);
        bTV5 = new BodyPart(0.00097f,0.6222f,-0.03896f, lovntArray[17], 18, msv, this);
        bTV6 = new BodyPart(0.00097f,0.6222f,-0.03896f, lovntArray[18], 19, msv, this);
        bTV7 = new BodyPart(0.00097f,0.6222f,-0.03896f, lovntArray[19], 20, msv, this);
        bTV8 = new BodyPart(0.00097f,0.6222f,-0.03896f, lovntArray[20], 21, msv, this);
        bTV9 = new BodyPart(0.00097f,0.6222f,-0.03896f, lovntArray[21], 22, msv, this);
        bTV10= new BodyPart(0.00097f,0.6222f,-0.03896f, lovntArray[22], 23, msv, this);
        bTV11= new BodyPart(0.00097f,0.6222f,-0.03896f, lovntArray[23], 24, msv, this);
        bTV12= new BodyPart(0.00097f,0.6222f,-0.03896f, lovntArray[24], 25, msv, this);
        bLV1= new BodyPart(0.00097f,0.6222f,-0.03896f, lovntArray[25], 26, msv, this);
        bLV2= new BodyPart(0.00097f,0.6222f,-0.03896f, lovntArray[26], 27, msv, this);
        bLV3= new BodyPart(0.00097f,0.6222f,-0.03896f, lovntArray[27], 28, msv, this);
        bLV4= new BodyPart(0.00097f,0.6222f,-0.03896f, lovntArray[28], 29, msv, this);
        bLV5= new BodyPart(0.00097f,0.6222f,-0.03896f, lovntArray[29], 30, msv, this);
        bpArray = new BodyPart[]{//所有的骨骼列表
                bRoot, bBody, bHead, bLeftTop, bLeftBottom, bRightTop, bRightBottom,
                bRightLegTop,bRightLegBottom,bLeftLegTop,bLeftLegBottom,bLeftFoot,bRightFoot,bNeck,
                bTV1,bTV2,bTV3,bTV4,bTV5,bTV6,bTV7,bTV8,bTV9,bTV10,bTV11,bTV12,
                bLV1,bLV2,bLV3,bLV4,bLV5
        };
        //每个骨骼一个矩阵
        fianlMatrixForDrawArray = new float[bpArray.length][16];
        fianlMatrixForDrawArrayTemp = new float[bpArray.length][16];
        bRoot.addChild(bTV1);
        bRoot.addChild(bTV2);
        bRoot.addChild(bTV3);
        bRoot.addChild(bTV4);
        bRoot.addChild(bTV5);
        bRoot.addChild(bTV6);
        bRoot.addChild(bTV7);
        bRoot.addChild(bTV8);
        bRoot.addChild(bTV9);
        bRoot.addChild(bTV10);
        bRoot.addChild(bTV11);
        bRoot.addChild(bTV12);
        bRoot.addChild(bLV1);
        bRoot.addChild(bLV2);
        bRoot.addChild(bLV3);
        bRoot.addChild(bLV4);
        bRoot.addChild(bLV5);
        bTV1.addChild(bNeck);
        bNeck.addChild(bHead);
        bRoot.addChild(bRightLegTop);
        bRoot.addChild(bLeftLegTop);
//        bRoot.addChild(bBody);
//        bBody.addChild(bHead);
//        bBody.addChild(bLeftTop);
//        bBody.addChild(bRightTop);
//        bLeftTop.addChild(bLeftBottom);
//        bRightTop.addChild(bRightBottom);
//        bBody.addChild(bRightLegTop);
        bRightLegTop.addChild(bRightLegBottom);
//        bBody.addChild(bLeftLegTop);
        bLeftLegTop.addChild(bLeftLegBottom);
        bLeftLegBottom.addChild(bLeftFoot);
        bRightLegBottom.addChild(bRightFoot);
        //级联计算每个子骨骼在父骨骼坐标系中的原始坐标，并且将平移信息记录进矩阵
        bRoot.initFatherMatrix();
        //层次级联更新骨骼矩阵的方法真实的平移信息，相对于世界坐标系
        bRoot.updateBone();
        //层次级联计算子骨骼初始情况下在世界坐标系中的变换矩阵的逆矩阵
        bRoot.calMWorldInitInver();
    }

    public void updateState() {//在线程中调用此方法
        bRoot.updateBone();
    }

    public void backToInit() {    //在线程中调用此方法
        bRoot.backToIInit();
    }

    public void flushDrawData() {//在线程中调用此方法
        synchronized (lock) {//加锁将主数据拷贝进绘制数据
            for (BodyPart bp : bpArray) {
                bp.copyMatrixForDraw();
            }
        }
    }

    public void drawSelf() {
        synchronized (lock) {//绘制前将绘制数据拷贝进临时数据
            for (int i = 0; i < bpArray.length; i++) {
                for (int j = 0; j < 16; j++) {
                    fianlMatrixForDrawArrayTemp[i][j] = fianlMatrixForDrawArray[i][j];
                }
            }
        }
        MatrixState.pushMatrix();
        //从根部件开始绘制
        bRoot.drawSelf(fianlMatrixForDrawArrayTemp);
        MatrixState.popMatrix();
    }
}
