package com.lium.evisa.ui;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.lium.evisa.R;

public class CircleView extends ImageView{
	private int radius;
	private Paint paint;
	private BitmapShader mBitmapShader;
	private Matrix matrix;
	private RectF mRoundRect;
	
	public CircleView(Context context) {
		this(context, null);
	}
	
	public CircleView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CircleView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		TypedArray array=context.obtainStyledAttributes(attrs, R.styleable.CircleView, defStyle, 0);
		radius=array.getDimensionPixelSize(R.styleable.CircleView_Radius, 0);
		array.recycle();
		paint=new Paint();
		paint.setAntiAlias(true); 
		paint.setColor(Color.BLUE);
		matrix=new Matrix();
	}


	@Override
	protected void onDraw(Canvas canvas){
		//Bitmap bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		Drawable drawable = getDrawable();
		if (drawable == null)
		{
			Log.e("View", "null");
			return;
		}
		setUpShader();
		radius=getHeight()/2;
		canvas.drawCircle(getWidth()/2, getHeight()/2, radius, paint);
		//mRoundRect = new RectF(0, 0, getWidth(), getHeight()/2);
		//canvas.drawRoundRect(mRoundRect, 50, 50, paint);
	}

	private Bitmap drawableToBitamp(Drawable drawable)
	{
		if (drawable instanceof BitmapDrawable)
		{
			BitmapDrawable bd = (BitmapDrawable) drawable;
			return bd.getBitmap();
		}
		int w = drawable.getIntrinsicWidth();
		int h = drawable.getIntrinsicHeight();
		Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, w, h);
		drawable.draw(canvas);
		return bitmap;
	}
	
	/**
	 * 初始化BitmapShader
	 */
	private void setUpShader()
	{
		Drawable drawable = getDrawable();
		if (drawable == null)
		{
			Log.e("view", "ok");
			return;
		}

		Bitmap bmp = drawableToBitamp(drawable);
		// 将bmp作为着色器，就是在指定区域内绘制bmp
		mBitmapShader = new BitmapShader(bmp, TileMode.CLAMP, TileMode.CLAMP);
		float scale = 1.0f;
		int bSize = Math.min(bmp.getWidth(), bmp.getHeight());
		scale = getWidth() * 1.0f / bSize;
		// shader的变换矩阵，我们这里主要用于放大或者缩小
		matrix.setScale(scale, scale);
		// 设置变换矩阵
		mBitmapShader.setLocalMatrix(matrix);
		// 设置shader
		paint.setShader(mBitmapShader);
	}
 
}
