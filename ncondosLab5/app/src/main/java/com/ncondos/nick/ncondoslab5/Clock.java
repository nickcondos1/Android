package com.ncondos.nick.ncondoslab5;

import android.animation.TimeAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


import java.util.Calendar;
import java.util.GregorianCalendar;

public class Clock extends View implements TimeAnimator.TimeListener
{
    private static final float[] HOUR = {.25f, .5f, 0, 2.5f, -.25f, .5f};
    private static final float[] MINUTE = {.25f, -.5f, 0, -4, -.25f, -.5f};

    public static final int RADIUS = 5;
    private TimeAnimator mTimer;
    private float height;
    private float width;
    private GetTimeListener timeListener;

    public Clock(Context context)
    {
        super(context);
        commonInit() ;

    }

    public Clock(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        commonInit() ;

    }

    public Clock(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        commonInit() ;

    }

    private void commonInit()
    {
        mTimer = new TimeAnimator();
        mTimer.setTimeListener(this);
        mTimer.start();

    }

    public void setListener(GetTimeListener getTimeListener)
    {
        timeListener = getTimeListener;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int aWidth = MeasureSpec.getSize(widthMeasureSpec);
        int aHeight = MeasureSpec.getSize(heightMeasureSpec);

        double aspectW = 12;
        double aspectH = 12;

        double scaledWidth = (aspectW / aspectH) * aHeight;
        double scaledHeight = (aspectH / aspectW) * aWidth;

        double most = Math.min(scaledWidth * aHeight, scaledHeight * aWidth);
        if (most == scaledWidth * aHeight)
        {
            setMeasuredDimension((int)scaledWidth, aHeight);

        }
        else
        {
            setMeasuredDimension(aWidth, (int)scaledHeight);

        }


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        height = h;
        width = w;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {


        setLayerType(this.LAYER_TYPE_SOFTWARE, null) ;
        canvas.drawRGB(255,255,255);

        float wid = width / 12;
        float hei = height / 12;

        canvas.translate(width/2,  height/2);
        canvas.scale(wid, -hei);


        Path path = new Path();

        path.moveTo(0,0);
        path.lineTo(MINUTE[0], MINUTE[1]);
        path.lineTo(MINUTE[2], MINUTE[3]);
        path.lineTo(MINUTE[4], MINUTE[5]);
        path.close();

        Path path2 = new Path();

        path2.moveTo(0,0);
        path2.lineTo(HOUR[0], HOUR[1]);
        path2.lineTo(HOUR[2], HOUR[3]);
        path2.lineTo(HOUR[4], HOUR[5]);
        path2.close();



        GregorianCalendar calendar = new GregorianCalendar();
        float degreesSECOND = ((float)calendar.get(calendar.SECOND) / 60) * 360;
        float degreesMINUTE = ((float)calendar.get(calendar.MINUTE) / 60) * 360;
        float degreeHOUR = ((float)calendar.get(calendar.HOUR) / 12) * 360;

        float degreeMINUTEMORE = ((float)calendar.get(calendar.SECOND) / 60) * 6;
        float degreeHOURMORE = ((float)calendar.get(calendar.MINUTE) / 60) * 30;

        int hour = calendar.get(calendar.HOUR_OF_DAY);
        int minute = calendar.get(calendar.MINUTE);
        int second = calendar.get(calendar.SECOND);
        timeListener.getTime(hour, minute, second);

        Paint paint = new Paint();

        canvas.save();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(0,0, 5.9f, paint);

        canvas.restore();

        canvas.save();
        paint.reset();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(.03f);
        canvas.rotate(-1 * degreesSECOND);
        canvas.drawLine(0,0,0,.98f * RADIUS, paint);
        canvas.restore();

        canvas.save();
        paint.setColor(Color.BLACK);
        canvas.rotate((-1 * degreesMINUTE - degreeMINUTEMORE) + 180);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        paint.setColor(Color.BLACK);
        canvas.rotate(-1 * degreeHOUR - degreeHOURMORE);
        canvas.drawPath(path2, paint);
        canvas.restore();


        canvas.save();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(.05f);


        for (int i = 0; i < 12; i++)
        {
            canvas.drawLine(0, 5.3f, 0, 5.7f, paint);
            canvas.rotate(-6);
            for (int j = 0; j < 4; j++)
            {
                canvas.drawLine(0, 5.5f, 0, 5.7f, paint);
                canvas.rotate(-6);
            }

        }
        canvas.restore();

    }


    @Override
    public void onTimeUpdate(TimeAnimator animation, long totalTime, long deltaTime) {
        invalidate();
    }


}
