package com.game.alpha.reduc.views;

import android.graphics.*;
import com.game.alpha.reduc.util.GraphicUtil;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by rbell on 7/11/2017.
 */


public class GameCanvasView extends View {
	
	public int width;
	public int height;
	private Bitmap mBitmap;
	private Canvas mCanvas;
	private List<Path> Paths = Collections.synchronizedList(new ArrayList());
	private Context context;
	private Paint mPaint;
	private float oldX, oldY;
	private float newX, newY;
	private static final float TOLERANCE = 5;
	
	public GameCanvasView(Context c, AttributeSet attrs) {
		super(c, attrs);
		context = c;
		
		// and we set a new Paint with the desired attributes
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setColor(Color.BLACK);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeJoin(Paint.Join.ROUND);
		mPaint.setStrokeWidth(8f);
	}
	
	// override onSizeChanged
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		
		// your Canvas will draw onto the defined Bitmap
		mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		mCanvas = new Canvas(mBitmap);
		
	}
	
	// override onDraw
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// draw the mPath with the mPaint on the canvas when onDraw
		for(int i = 1; i < Paths.size(); i++) {
			mPaint.setColor(GraphicUtil.getColor(Paths.size(), i));
			Path first = Paths.get(i);
			canvas.drawPath(first, mPaint);
		}
	}
	
	// when ACTION_DOWN start touch according to the x,y values
	private void startTouch(float x, float y) {
		if(oldX != 0 && oldY != 0) {
			Path p = new Path();
			p.moveTo(x, y);
			Paths.add(p);
		}
		oldX = newX;
		oldY = newY;
		newX = x;
		newY = y;
	}
	
	// when ACTION_MOVE move touch according to the x,y values
	private void moveTouch(float x, float y) {
		Path p = new Path();
		p.moveTo(oldX, oldY);
		p.quadTo(oldX, oldY, newX, newY);
		oldX = newX;
		oldY = newY;
		newX = x;
		newY = y;
		Paths.add(p);
	}
	
	public void clearCanvas() {
		Paths.clear();
		invalidate();
	}
	
	// when ACTION_UP stop touch
	private void upTouch() {
		Path p = new Path();
		p.moveTo(oldX, oldY);
		Paths.add(p);
		p.lineTo(newX, newY);
		Paths.add(p);
	}
	
	//override the onTouchEvent
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				startTouch(x, y);
				invalidate();
				break;
			case MotionEvent.ACTION_MOVE:
				moveTouch(x, y);
				invalidate();
				break;
			case MotionEvent.ACTION_UP:
				upTouch();
				invalidate();
				break;
		}
		return true;
	}
}
