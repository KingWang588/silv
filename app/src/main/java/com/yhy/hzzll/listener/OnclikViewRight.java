package com.yhy.hzzll.listener;

/**
 * 自定接口监听字母索引View
 * 
 * @author Hasee
 * 
 */
public interface OnclikViewRight {

	/** 松开触屏 */
    void seteventUP();

	/** 按下或滑动触屏传回当前索引的字母 */
    void seteventDownAndMove(String str);

}
