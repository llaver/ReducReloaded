package com.game.alpha.reduc.util;


import android.graphics.Color;

/**
 * Created by rbell on 6/29/2017.
 */
public class GraphicUtil {
	
	//LetS geT soME FuCKING RaInB0wz up iN tHiS BItcH
	public static int getColor(int size, int index) {
		int divisionSize = 1400 / size;
		int value = divisionSize * index;
		int red = 0;
		int green = 0;
		int blue = 0;
		switch(value / 255) {
			case 0:
				red = 255;
				blue = value;
				break;
			case 1:
				red = 255 - value % 255;
				blue = 255;
				break;
			case 2:
				green = value % 255;
				blue = 255;
				break;
			case 3:
				green = 255;
				blue = 255 - value % 255;
				break;
			case 4:
				red = value % 255;
				green = 255;
				break;
			default:
				red = 255;
				green = 255;
		}
		return Color.rgb(red, green, blue);
	}
}
