package com.roxiga.hypermotion2d;

import java.io.IOException;
import java.io.InputStream;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;
import javax.microedition.khronos.opengles.GL11Ext;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

public class Sprite2D
{
    //
    public boolean _visible = true;
	//テクスチャID番号
    public int _textureNo;
    //配置する位置
    public Vector3D _pos = new Vector3D(0,0,0);
    //配置するストレッチ幅
    public float _width;
    //配置するストレッチ高さ
    public float _height;
    //テクスチャ上のX位置
    public int _texX;
    //テクスチャ上のY位置(0が最小でなく画像の高さから計る)
    public int _texY;
    //テクスチャ上の幅
    public int _texWidth;
    //テクスチャ上の高さ
    public int _texHeight;

    //テクスチャを読み込んでセット
    public void setTexture(GL10 gl,Resources res,int id)
    {
    	//　テクスチャをresから読み出す
    	InputStream is = res.openRawResource(id);

    	Bitmap bitmap;
    	try
    	{
    		bitmap = BitmapFactory.decodeStream(is);
    	}
    	finally
    	{
    		try
    		{
    			is.close();
    		}
    		catch(IOException e)
    		{
    		}
    	}
    	gl.glEnable(GL10.GL_ALPHA_TEST);
    	gl.glEnable(GL10.GL_BLEND);
    	gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
    	gl.glTexEnvf(GL10.GL_TEXTURE_ENV, GL10.GL_TEXTURE_ENV_MODE, GL10.GL_MODULATE);
    	//テクスチャIDを割り当てる
    	int[] textureNo = new int[1];
    	gl.glGenTextures(1, textureNo, 0);
    	_textureNo = textureNo[0];
    	//テクスチャIDのバインド
    	gl.glBindTexture(GL10.GL_TEXTURE_2D, _textureNo);	
     	//OpenGL ES用のメモリ領域に画像データを渡す。上でバインドされたテクスチャIDと結び付けられる。
    	GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
    	//テクスチャ座標が1.0fを超えたときの、テクスチャをS軸方向に繰り返す設定
    	gl.glTexParameterx(GL10.GL_TEXTURE_2D,
    			GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT );
    	//テクスチャ座標が1.0fを超えたときの、テクスチャをT軸方向に繰り返す設定
    	gl.glTexParameterx(GL10.GL_TEXTURE_2D,
    			GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT );
    	//テクスチャを元のサイズから拡大、縮小して使用したときの色の使い方を設定
    	gl.glTexParameterx(GL10.GL_TEXTURE_2D,
    			GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR );
    	//テクスチャを元のサイズから拡大、縮小して使用したときの色の使い方を設定
    	gl.glTexParameterx(GL10.GL_TEXTURE_2D,
    			GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR );
    	
    	setPos(
    		0,bitmap.getHeight(),
    		bitmap.getWidth(),-bitmap.getHeight(),
    		0,0,0,
    		bitmap.getWidth(),bitmap.getHeight());
    }

    //テクスチャ位置セット
    public void setPos(int texX,int texY,int texW,int texH,
    		float x,float y,float z,float w,float h)
    {
    	_texX = texX;
    	_texY = texY;
    	_texWidth = texW;
       	_texHeight = texH;
     	_pos = new Vector3D(x,y,z);
     	_width = w;
     	_height = h;
    }

    public void draw(GL10 gl)
    {
    	if ( !_visible )
    	{
    		return;
    	}
    	gl.glDisable(GL10.GL_DEPTH_TEST);
    	//白色セット
   		gl.glColor4x(0x10000, 0x10000, 0x10000, 0x10000);
   		//テクスチャ0番をアクティブにする
   		gl.glActiveTexture(GL10.GL_TEXTURE0);
   		//テクスチャID(_textureNo)に対応するテクスチャをバインドする
   		gl.glBindTexture(GL10.GL_TEXTURE_2D, _textureNo);

		//テクスチャの座標と幅高さを指定
   		int rect[] = {_texX, _texY, _texWidth, _texHeight};
   
   		//バインドされているテクスチャ画像のどの部分を使うかを指定
   		((GL11) gl).glTexParameteriv(GL10.GL_TEXTURE_2D,
   			GL11Ext.GL_TEXTURE_CROP_RECT_OES, rect, 0);
   		//高速2D描画
   		((GL11Ext) gl).glDrawTexfOES(
   				_pos._x, _pos._y, _pos._z, _width, _height);
   		//
   	   	gl.glEnable(GL10.GL_DEPTH_TEST);
	}

    //2Dスプライト描画
    public void draw(GL10 gl,float ratio)
    {
    	if ( !_visible )
    	{
    		return;
    	}
    	gl.glDisable(GL10.GL_DEPTH_TEST);

   		//白色セット
   		gl.glColor4x(0x10000, 0x10000, 0x10000, 0x10000);
   		//テクスチャ0番をアクティブにする
   		gl.glActiveTexture(GL10.GL_TEXTURE0);
   		//テクスチャID(_textureNo)に対応するテクスチャをバインドする
   		gl.glBindTexture(GL10.GL_TEXTURE_2D, _textureNo);

		//テクスチャの座標と幅高さを指定
   		int rect[] = {_texX, _texY, _texWidth, _texHeight};
   
   		//バインドされているテクスチャ画像のどの部分を使うかを指定
   		((GL11) gl).glTexParameteriv(GL10.GL_TEXTURE_2D,
   			GL11Ext.GL_TEXTURE_CROP_RECT_OES, rect, 0);
   		//高速2D描画
   		((GL11Ext) gl).glDrawTexfOES(
   				_pos._x, _pos._y, _pos._z,
   				_width*ratio, _height*ratio);
   		//
   	   	gl.glEnable(GL10.GL_DEPTH_TEST);
	}
    
    public boolean hit(float x,float y)
    {
    	if ( x >= _pos._x && x <= _pos._x + _width )
    	{
        	if ( y >= _pos._y && y <= _pos._y + _height )
        	{
        		return true;
        	}
        }
    	return false;
    }
}
