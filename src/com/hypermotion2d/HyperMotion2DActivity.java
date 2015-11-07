package com.hypermotion2d;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;

public class HyperMotion2DActivity extends Activity
{
    private GLSurfaceView _glSurfaceView;
    private HyperMotion2D _renderer;

    @Override
    //作成
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        _glSurfaceView = new GLSurfaceView(this);
        _renderer = new HyperMotion2D(this);
        _glSurfaceView.setRenderer(_renderer);
        setContentView(_glSurfaceView);
        Display display = getWindowManager().getDefaultDisplay(); 
        _renderer._width = display.getWidth();
        _renderer._height = display.getHeight();
    }

    @Override
    //フォーカスが再開したとき
    protected void onResume()
    {
    	//再開
        super.onResume();
        //再開
        _glSurfaceView.onResume();
    }

    @Override
    //フォーカスを失ったとき
    protected void onPause()
    {
    	//一時停止
        super.onPause();
        //一時停止
        _glSurfaceView.onPause();
    }

    @Override
    //画面がタッチされたときの処理
    public boolean onTouchEvent(MotionEvent event)
    {
    	float x = event.getX();
    	float y = event.getY();
    	switch (event.getAction())
    	{
    	case MotionEvent.ACTION_DOWN:
   			_renderer.actionDown(x,y);
    		break;
    	case MotionEvent.ACTION_MOVE:
   			_renderer.actionMove(x,y);
   		break;
    	case MotionEvent.ACTION_UP:
    		_renderer.actionUp(x,y);
            break;
    	}
    	return true;
    }
}
