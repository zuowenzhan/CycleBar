# CycleBar
自定义圆形进度条

# 自定义步骤 讲解
### 1.创建CycleBar继承View，在布局中使用只需要重写两个参数的构造
### 2.绘制圆环需要重写onDraw()方法，在方法中需要三步
##### ①绘制圆环
##### ②绘制圆弧
##### ③绘制文本
### 3.先定义属性设置

    private int progress = 0;//当前进度
    private int max = 100;//最大进度
    private int roundColor = Color.GRAY;//圆环的颜色
    private int roundProgressColor = Color.RED;//圆环进度的颜色
    private float roundWidth = 10;//圆环的宽度
    private int textSize = 20;//文字的大小
    private int textColor = Color.GREEN;//文字的颜色
    并设置get和set方法，方便动态设置
### 4.绘制圆环

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
        //viewWidth为onMeasure（）中获取视图宽和高
### 5.绘制圆弧
        //创建圆环中心线的外切矩形  左上顶点和右下顶点坐标
        RectF rectF = new RectF(roundWidth / 2, roundWidth / 2,viewWidth - roundWidth/2,viewWidth - roundWidth/2);
        paint.setColor(roundProgressColor);//圆弧颜色
        canvas.drawArc(rectF,0,progress * 360 / max,false,paint);
### 6.绘制文本
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
  ### 7.动起来调用
         postInvalidate(); 
        
        
        
        
        
