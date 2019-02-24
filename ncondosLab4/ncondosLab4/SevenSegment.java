package com.ncondos.nick.ncondoslab4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.text.AttributedCharacterIterator;
import java.util.LinkedList;

public class SevenSegment extends View
{
    public static final float[] VERTICES = {42.5f, 42.5f, 51, 34, 119, 34, 127.5f, 42.5f, 119, 51, 51, 51};

    private int currentValue;
    private boolean[] onOff = new boolean[7];
    private int onState = Color.argb(255,255,0,0);
    private int offState = Color.argb(50,255,0,0);
    private float height;
    private float width;
    private LinkedList<int[]> map;


    public SevenSegment(Context context)
    {
        super(context);
        commonInit() ;

    }

    public SevenSegment(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        commonInit() ;

    }

    public SevenSegment(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        commonInit() ;

    }

    public void commonInit()
    {
        currentValue = 10;

        int[] zero = {1, 1, 1, 0, 1, 1, 1};
        int[] one = {0, 0, 1, 0, 0, 1, 0};
        int[] two = {1, 0, 1, 1, 1, 0, 1};
        int[] three = {1, 0, 1, 1, 0, 1, 1};
        int[] four = {0, 1, 1, 1, 0, 1, 0};
        int[] five = {1, 1, 0, 1, 0, 1, 1};
        int[] six = {1, 1, 0, 1, 1, 1, 1};
        int[] seven = {1, 0, 1, 0, 0, 1, 0};
        int[] eight = {1, 1, 1, 1, 1, 1, 1};
        int[] nine = {1, 1, 1, 1, 0, 1, 1};
        int[] ten = {0, 0, 0, 0, 0, 0, 0,};

        map = new LinkedList<>();
        map.add(zero);
        map.add(one);
        map.add(two);
        map.add(three);
        map.add(four);
        map.add(five);
        map.add(six);
        map.add(seven);
        map.add(eight);
        map.add(nine);
        map.add(ten);

        for (int i = 0; i < onOff.length; i++)
        {
            onOff[i] = false;
        }

    }

    public int get()
    {
        return currentValue;
    }

    public void set(int cur)
    {
        currentValue = cur;
    }

    public void increment()
    {
        if (currentValue == 10)
            currentValue = 0;
        else
            currentValue++;

        for (int i = 0; i < onOff.length; i++)
        {
            if (map.get(currentValue)[i] == 0)
                onOff[i] = false;
            else
                onOff[i] = true;
        }


    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        Bundle bundle = (Bundle) state;

        onOff = bundle.getBooleanArray("array");
        state = bundle.getParcelable("instanceState");
        super.onRestoreInstanceState(state);
    }

    @Override
    protected Parcelable onSaveInstanceState()
    {
        Bundle bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());

        bundle.putBooleanArray("array", onOff);
        return bundle;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int aWidth = MeasureSpec.getSize(widthMeasureSpec);
        int aHeight = MeasureSpec.getSize(heightMeasureSpec);

        double aspectW = 10;
        double aspectH = 15;

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
        canvas.drawRGB(0,0,0);

        float wid = width / 170;
        float hei = height / 255;
        canvas.scale(wid, hei);

        Path path = new Path();

        path.moveTo(VERTICES[0], VERTICES[1]);
        path.lineTo(VERTICES[2], VERTICES[3]);
        path.lineTo(VERTICES[4], VERTICES[5]);
        path.lineTo(VERTICES[6], VERTICES[7]);
        path.lineTo(VERTICES[8], VERTICES[9]);
        path.lineTo(VERTICES[10], VERTICES[11]);
        //path.lineTo(VERTICES[0], VERTICES[1]);
        path.close();

        Paint paint = new Paint();
        paint.setColor(offState);


        //SEGMENT 0, TOP
        if (onOff[0]) {
           paint.setColor(onState);
            canvas.drawPath(path, paint);
        }
        else {
            paint.setColor(offState);
            canvas.drawPath(path, paint);
        }

        //SEGMENT 1, LEFT TOP
        if (onOff[1]) {
            paint.setColor(onState);
            canvas.save();
            canvas.rotate(90, 42.5f, 42.5f);
            canvas.drawPath(path, paint);
            canvas.restore();
        }
        else {
            paint.setColor(offState);
            canvas.save();
            canvas.rotate(90, 42.5f, 42.5f);
            canvas.drawPath(path, paint);
            canvas.restore();
        }

        //SEGMENT 2, RIGHT TOP
        if (onOff[2]) {
            paint.setColor(onState);
            canvas.save();
            canvas.translate(127.5f - 42.5f, 0);
            canvas.rotate(90, 42.5f, 42.5f);
            canvas.drawPath(path, paint);
            canvas.restore();
        }
        else {
            paint.setColor(offState);
            canvas.save();
            canvas.translate(127.5f - 42.5f, 0);
            canvas.rotate(90, 42.5f, 42.5f);
            canvas.drawPath(path, paint);
            canvas.restore();
        }

        //SEGMENT 3, MIDDLE
        if (onOff[3]) {
            paint.setColor(onState);
            canvas.save();
            canvas.translate(0, 127.5f - 42.5f);
            canvas.drawPath(path, paint);
            canvas.restore();
        }
        else {
            paint.setColor(offState);
            canvas.save();
            canvas.translate(0, 127.5f - 42.5f);
            canvas.drawPath(path, paint);
            canvas.restore();
        }

        //SEGMENT 4, BOTTOM LEFT
        if (onOff[4]) {
            paint.setColor(onState);
            canvas.save();
            canvas.translate(0, 127.5f - 42.5f);
            canvas.rotate(90, 42.5f, 42.5f);
            canvas.drawPath(path, paint);
            canvas.restore();
        }
        else {
            paint.setColor(offState);
            canvas.save();
            canvas.translate(0, 127.5f - 42.5f);
            canvas.rotate(90, 42.5f, 42.5f);
            canvas.drawPath(path, paint);
            canvas.restore();
        }

        //SEGMENT 5, BOTTOM RIGHT
        if (onOff[5]) {
            paint.setColor(onState);
            canvas.save();
            canvas.translate(127.5f - 42.5f, 127.5f - 42.5f);
            canvas.rotate(90, 42.5f, 42.5f);
            canvas.drawPath(path, paint);
            canvas.restore();
        }
        else {
            paint.setColor(offState);
            canvas.save();
            canvas.translate(127.5f - 42.5f, 127.5f - 42.5f);
            canvas.rotate(90, 42.5f, 42.5f);
            canvas.drawPath(path, paint);
            canvas.restore();
        }

        //SEGMENT 6, BOTTOM BOTTOM
        if (onOff[6]) {
            paint.setColor(onState);
            canvas.save();
            canvas.translate(0, 2 * (127.5f - 42.5f));
            canvas.drawPath(path, paint);
            canvas.restore();
        }
        else {
            paint.setColor(offState);
            canvas.save();
            canvas.translate(0, 2 * (127.5f - 42.5f));
            canvas.drawPath(path, paint);
            canvas.restore();
        }
    }

}
