package com.reduc.alpha;

import com.reduc.alpha.util.OpenSimplexNoise;


/**
 * Created by rbell on 8/3/2017.
 */
public class Test {
	public static void main(String[] args) {
		OpenSimplexNoise noise = new OpenSimplexNoise();
		Double[][] map = new Double[56][56];
		
		for(int i = 0; i < map.length; i++) {
			System.out.print("\n*");
			for(int k = 0; k < map[i].length; k++) {
				map[i][k] = noise.eval(i, k);
				double current = map[i][k];
				//System.out.println(current);
				if(current <= .2 && current >= -.2) {
					System.out.print("X");
				} else {
					System.out.print("O");
				}
			}
		}
	}
}
