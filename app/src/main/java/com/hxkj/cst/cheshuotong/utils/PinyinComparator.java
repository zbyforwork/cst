package com.hxkj.cst.cheshuotong.utils;

import com.hxkj.cst.cheshuotong.bean.CLPP;
import com.hxkj.cst.cheshuotong.bean.CLXX;

import java.util.Comparator;

/**
 * 
 * @author 
 *
 */
public class PinyinComparator implements Comparator<CLPP> {

	public int compare(CLPP o1, CLPP o2) {
		if (o1.getPPMC().equals("@")
				|| o2.getPPMC().equals("#")) {
			return -1;
		} else if (o1.getPPMC().equals("#")
				|| o2.getPPMC().equals("@")) {
			return 1;
		} else {
			return CharacterParser.getInstance().getSelling(o1.getPPMC()).toUpperCase().compareTo(CharacterParser.getInstance().getSelling(o2.getPPMC()).toUpperCase());
		}
	}

}
