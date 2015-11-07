package com.roxiga.hypermotion2d;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;
import javax.microedition.khronos.opengles.GL11Ext;

public class SpriteText extends Sprite2D
{
    //2Dスプライト描画
    public void draw(GL10 gl,int num,float ratio)
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

   		String str =  String.valueOf(num);
   		for ( int i = 0; i < str.length(); i++ )
   		{
   			int j = (int)(num/Math.pow(10, i)) % 10;
   			//テクスチャの座標と幅高さを指定
   			int rect[] = {j*_texWidth, _texY, _texWidth, _texHeight};
   
   			//バインドされているテクスチャ画像のどの部分を使うかを指定
   			((GL11) gl).glTexParameteriv(GL10.GL_TEXTURE_2D,
   				GL11Ext.GL_TEXTURE_CROP_RECT_OES, rect, 0);
   			//2D描画
   			((GL11Ext) gl).glDrawTexfOES(
   				_pos._x-(i-str.length())*_width*ratio,
   				_pos._y, _pos._z,
				_width*ratio, _height*ratio);
   		}
   	   	gl.glEnable(GL10.GL_DEPTH_TEST);
	}
}
