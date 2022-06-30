package com.yhy.hzzll.utils;

import java.util.List;

import com.yhy.hzzll.entity.HomeLawyerEntity;

/**
 * 计算viewpager 的个数
 * 
 * @author wangyang
 * 
 */
public class ViewPagerNum {

	/** 返回律师要加载的页数 */
	public int LawyerPager(List<HomeLawyerEntity> lawyerlist) {
		if (lawyerlist.size() != 0) {
			if (lawyerlist.size() < 8 || lawyerlist.size() == 8) {
				return 1;
			} else if (lawyerlist.size() > 8) {
				return lawyerlist.size() / 8;
			}
		}
		return 1;
	}

	private List<HomeLawyerEntity> LawyerData(List<HomeLawyerEntity> lawyerlist) {
		
		if (lawyerlist.size() != 0) {
			if (lawyerlist.size() -8>8) {
				
			} else if (lawyerlist.size() < 8 || lawyerlist.size() == 8) {
				
			} else if (lawyerlist.size() > 8) {
				
			}
		}
		return lawyerlist;
	}

}
