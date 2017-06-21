package com.yaolaizai.ylzx.cyclebar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ylzx on 2017/6/21.
 */
public class CycleBar extends View {


    private int progress = 0;//当前进度
    private int max = 100;//最大进度
    private int roundColor = Color.GRAY;//圆环的颜色
    private int roundProgressColor = Color.RED;//圆环进度的颜色
    private float roundWidth = 10;//圆环的宽度
    private int textSize = 20;//文字的大小
    private int textColor = Color.GREEN;//文字的颜色


    private int viewWidth;//当前视图的宽或高
    private Paint paint;//画笔

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;

        postInvalidate();//强制重绘，可以在分线程中执行
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getRoundColor() {
        return roundColor;
    }

    public void setRoundColor(int roundColor) {
        this.roundColor = roundColor;
    }

    public int getRoundProgressColor() {
        return roundProgressColor;
    }

    public void setRoundProgressColor(int roundProgressColor) {
        this.roundProgressColor = roundProgressColor;
    }

    public float getRoundWidth() {
        return roundWidth;
    }

    public void setRoundWidth(float roundWidth) {
        this.roundWidth = roundWidth;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    //在布局中，重写
    public CycleBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        //创建画笔
        paint = new Paint();
        paint.setAntiAlias(true);//去除锯齿

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        viewWidth = getMeasuredWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //1.绘制圆环
        //获取圆心坐标
        int circleX = viewWidth / 2;
        int circleY = viewWidth / 2;
        //获取圆的半径
        int radius = (int) (viewWidth / 2 - roundWidth / 2);
        //设置画笔
        paint.setColor(roundColor);
        paint.setStrokeWidth(roundWidth);//指定画笔的宽度
        paint.setStyle(Paint.Style.STROKE);//设置为一个空心圆
        canvas.drawCircle(circleX,circleY,radius,paint); //参数为圆心坐标，圆半径，画笔

        //2.绘制圆弧
        //创建圆环中心线的外切矩形  左上顶点和右下顶点坐标
        RectF rectF = new RectF(roundWidth / 2, roundWidth / 2,viewWidth - roundWidth/2,viewWidth - roundWidth/2);
        paint.setColor(roundProgressColor);//圆弧颜色
        canvas.drawArc(rectF, 0, progress * 360 / max, false, paint);


        //3.绘制文本
        //得到文本内容
        String text = progress * 100 / max + "%";
        //得到包含文本内容的边框
        Rect bounds = new Rect();
        //执行完此方法，即可得到有宽高的边框
        paint.getTextBounds(text,0,text.length(),bounds);
        //得到文本的左下顶点的坐标
        int textX = viewWidth/2 - bounds.width()/2;
        int textY = viewWidth/2 + bounds.height()/2;

        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setStrokeWidth(0);
        canvas.drawText(text,textX,textY,paint);

    }

}
