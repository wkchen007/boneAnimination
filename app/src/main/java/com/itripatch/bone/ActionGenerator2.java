package com.itripatch.bone;

public class ActionGenerator2 {
    static Action[][] acArray = new Action[10][];
    static int count = 20;
    //駝背
    static float[][] humpback =
            {
                    {13, 0, 0, 0, 0.063f, 0, 0, 0.063f},  //spine1
                    {13, 1, -20, -20, 1, 0, 0},  //spine1
                    {14, 0, 0, 0, 0.063f, 0, 0, 0.063f},  //spine2
                    {14, 1, -20, -20, 1, 0, 0},  //spine2
                    {15, 0, 0, 0, 0.063f, 0, 0, 0.063f},  //spine3
                    {15, 1, -20, -20, 1, 0, 0},  //spine3
                    {16, 0, 0, 0, 0.063f, 0, 0, 0.063f},  //spine4
                    {16, 1, -10, -10, 1, 0, 0},  //spine4
                    {17, 0, 0, 0, 0.063f, 0, 0, 0.063f},  //spine5
                    {17, 1, 20, 20, 1, 0, 0},  //spine5
                    {18, 0, 0, 0, 0.056f, 0, 0, 0.056f},  //spine6
                    {18, 1, 30, 30, 1, 0, 0},  //spine6
                    {19, 0, 0, 0, 0.049f, 0, 0, 0.049f},  //spine7
                    {19, 1, 30, 30, 1, 0, 0},  //spine7
                    {20, 0, 0, 0, 0.042f, 0, 0, 0.042f},  //spine8
                    {20, 1, 30, 30, 1, 0, 0},  //spine8
                    {21, 0, 0, 0, 0.035f, 0, 0, 0.035f},  //spine9
                    {21, 1, 30, 30, 1, 0, 0},  //spine9
                    {22, 0, 0, 0, 0.021f, 0, 0, 0.021f},  //spine10
                    {22, 1, 30, 30, 1, 0, 0},  //spine10
                    {23, 0, 0, 0, 0.014f, 0, 0, 0.014f},  //spine11
                    {23, 1, 30, 30, 1, 0, 0},  //spine11
                    {24, 0, 0, 0, 0.007f, 0, 0, 0.007f},  //spine12
                    {24, 1, 20, 20, 1, 0, 0},  //spine12
            };
    //外八彎曲
    static float[][] curve =
            {
                    {7, 1, -30, -30, 0, 1, 0},  //rightLegTop
                    {8, 1, -30, -30, 0, 1, 0},  //rightLegBottom
                    {9, 1, 30, 30, 0, 1, 0},    //leftLegTop
                    {10, 1, 30, 30, 0, 1, 0},   //leftLegBottom
            };

    //走路
    static float[][][] walk = {
            {
                    {3, 1, -70, 0, 0.948f, 0, 0.316f},  //leftTop
                    {5, 1, -70, 0, -0.948f, 0, 0.316f}, //rightTop
                    {4, 1, -80, -80, 0.948f, 0, 0.316f},//leftBottom
                    {6, 1, 80, 80, -0.948f, 0, 0.316f}, //rightBottom
                    {7, 1, -50, 0, 1, 0, 0},//rightLegTop
                    {9, 1, 20, 0, 1, 0, 0}, //leftLegTop
                    {10, 1, 0, 90, 1, 0, 0},//leftLegBottom
                    {11, 1, 30, 0, 1, 0, 0},//leftFoot
            },
            {
                    {3, 1, 0, 70, 0.948f, 0, 0.316f},   //leftTop
                    {5, 1, 0, 70, -0.948f, 0, 0.316f},  //rightTop
                    {4, 1, -80, -80, 0.948f, 0, 0.316f},//leftBottom
                    {6, 1, 80, 80, -0.948f, 0, 0.316f},//rightBottom
                    {7, 1, 0, 20, 1, 0, 0}, //rightLegTop
                    {9, 1, 0, -50, 1, 0, 0},//leftLegTop
                    {10, 1, 90, 0, 1, 0, 0},//leftLegBottom
                    {12, 1, 0, 30, 1, 0, 0},//rightFoot
            },
            {
                    {3, 1, 70, 0, 0.948f, 0, 0.316f},   //leftTop
                    {5, 1, 70, 0, -0.948f, 0, 0.316f},  //rightTop
                    {4, 1, -80, -80, 0.948f, 0, 0.316f},//leftBottom
                    {6, 1, 80, 80, -0.948f, 0, 0.316f}, //rightBottom
                    {7, 1, 20, 0, 1, 0, 0}, //rightLegTop
                    {9, 1, -50, 0, 1, 0, 0},//leftLegTop
                    {8, 1, 0, 90, 1, 0, 0}, //rightLegBottom
                    {12, 1, 30, 0, 1, 0, 0},//rightFoot
            },
            {
                    {3, 1, 0, -70, 0.948f, 0, 0.316f},  //leftTop
                    {5, 1, 0, -70, -0.948f, 0, 0.316f}, //rightTop
                    {4, 1, -80, -80, 0.948f, 0, 0.316f},//leftBottom
                    {6, 1, 80, 80, -0.948f, 0, 0.316f}, //rightBottom
                    {7, 1, 0, -50, 1, 0, 0},//rightLegTop
                    {9, 1, 0, 20, 1, 0, 0}, //leftLegTop
                    {8, 1, 90, 0, 1, 0, 0}, //rightLegBottom
                    {11, 1, 0, 30, 1, 0, 0},//leftFoot
            }
    };

    //坐姿
    static float[][] sit =
            {
                    {7, 1, -80, -80, 1, 0, 0},  //rightLegTop
                    {8, 1, 90, 90, 1, 0, 0},    //rightLegBottom
                    {9, 1, -80, -80, 1, 0, 0},  //leftLegTop
                    {10, 1, 90, 90, 1, 0, 0},   //leftLegBottom
            };

    public static float[][] spineActionX(float angleX1, float angleX2) {
        float[][] action = new float[17][];
        for (int i = 0; i < 17; i++)
            action[i] = new float[]{14 + i, 1, angleX1, angleX2, 1, 0, 0};
        return action;
    }
    public static float[][] spineActionY(float angleY1, float angleY2) {
        float[][] action = new float[17][];
        for (int i = 0; i < 17; i++)
            action[i] = new float[]{14 + i, 1, angleY1, angleY2, 0, 1, 0};
        return action;
    }
    public static float[][] spineActionZ(float angleZ1, float angleZ2) {
        float[][] action = new float[17][];
        for (int i = 0; i < 17; i++)
            action[i] = new float[]{14 + i, 1, angleZ1, angleZ2, 0, 0, 1};
        return action;
    }
    public static float[][] spineActionXY(float angleXY1, float angleXY2) {
        float[][] action = new float[17][];
        for (int i = 0; i < 17; i++)
            action[i] = new float[]{14 + i, 1, angleXY1, angleXY2, 1, 1, 0};
        return action;
    }
    public static float[][] spineActionXZ(float angleXZ1, float angleXZ2) {
        float[][] action = new float[17][];
        for (int i = 0; i < 17; i++)
            action[i] = new float[]{14 + i, 1, angleXZ1, angleXZ2, 1, 0, 1};
        return action;
    }
    public static float[][] spineActionYZ(float angleYZ1, float angleYZ2) {
        float[][] action = new float[17][];
        for (int i = 0; i < 17; i++)
            action[i] = new float[]{14 + i, 1, angleYZ1, angleYZ2, 0, 1, 1};
        return action;
    }
    public static float[][] spineActionXYZ(float angleXYZ1, float angleXYZ2) {
        float[][] action = new float[17][];
        for (int i = 0; i < 17; i++)
            action[i] = new float[]{14 + i, 1, angleXYZ1, angleXYZ2, 1, 1, 1};
        return action;
    }
    static {
        acArray[0] = new Action[1];
        acArray[1] = new Action[2]; //acArray[1] = new Action[4];
        acArray[2] = new Action[2]; //acArray[2] = new Action[4];
        acArray[3] = new Action[2]; //acArray[3] = new Action[4];
        acArray[4] = new Action[2];
        acArray[5] = new Action[2];
        acArray[6] = new Action[2];
        acArray[7] = new Action[2];
        acArray[8] = new Action[2];
        acArray[9] = new Action[2];

        for (int i = 0; i < acArray.length; i++)
            for (int j = 0; j < acArray[i].length; j++)
                acArray[i][j] = new Action();
        //0靜止
        acArray[0][0].totalStep = count;
        acArray[0][0].data = new float[][]{};
        /*
        //1 正常走路
        for (int i = 0; i < acArray[1].length; i++) {
            acArray[1][i].totalStep = count;
            acArray[1][i].data = walk[i]; //走路
        }

        //2 駝背走路
        for (int i = 0; i < acArray[2].length; i++) {
            acArray[2][i].totalStep = count;
            acArray[2][i].data = Arrays.copyOf(humpback, humpback.length + walk[i].length); //駝背
            System.arraycopy(walk[i], 0, acArray[2][i].data, humpback.length, walk[i].length); //走路
        }

        //3 外八走路
        for (int i = 0; i < acArray[3].length; i++) {
            acArray[3][i].totalStep = count;
            acArray[3][i].data = Arrays.copyOf(curve, curve.length + walk[i].length); //外八彎曲
            System.arraycopy(walk[i], 0, acArray[3][i].data, curve.length, walk[i].length); //走路
        }

        //4 正常坐姿
        acArray[4][0].totalStep = count;
        acArray[4][0].data = sit;

        //5 駝背坐姿
        acArray[5][0].totalStep = count;
        acArray[5][0].data = Arrays.copyOf(humpback, humpback.length + sit.length); //駝背
        System.arraycopy(sit, 0, acArray[5][0].data, humpback.length, sit.length); //坐姿

        //6 脊椎轉動30
        acArray[6][0].totalStep = count;
        acArray[6][0].data = setUpSpineAction(30);

        //7 脊椎轉動40
        acArray[7][0].totalStep = count;
        acArray[7][0].data = setUpSpineAction(40);
        */

        acArray[1][0].totalStep = count;
        acArray[1][0].data = spineActionX(0, 90);
        acArray[1][1].totalStep = count;
        acArray[1][1].data = spineActionX(90, 90);

        acArray[2][0].totalStep = count;
        acArray[2][0].data = spineActionY(0, 90);
        acArray[2][1].totalStep = count;
        acArray[2][1].data = spineActionY(90, 90);

        acArray[3][0].totalStep = count;
        acArray[3][0].data = spineActionZ(0, 90);
        acArray[3][1].totalStep = count;
        acArray[3][1].data = spineActionZ(90, 90);

        acArray[4][0].totalStep = count;
        acArray[4][0].data = spineActionXY(0, 90);
        acArray[4][1].totalStep = count;
        acArray[4][1].data = spineActionXY(90, 90);

        acArray[5][0].totalStep = count;
        acArray[5][0].data = spineActionXZ(0, 90);
        acArray[5][1].totalStep = count;
        acArray[5][1].data = spineActionXZ(90, 90);

        acArray[6][0].totalStep = count;
        acArray[6][0].data = spineActionYZ(0, 90);
        acArray[6][1].totalStep = count;
        acArray[6][1].data = spineActionYZ(90, 90);

        acArray[7][0].totalStep = count;
        acArray[7][0].data = spineActionXYZ(0, 90);
        acArray[7][1].totalStep = count;
        acArray[7][1].data = spineActionXYZ(90, 90);
    }
}
