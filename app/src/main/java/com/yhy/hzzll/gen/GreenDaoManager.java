package com.yhy.hzzll.gen;

import com.yhy.hzzll.framework.DemoCache;

/**
 * Created by chengying on 2017/9/13.
 */

public class GreenDaoManager {
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    private GreenDaoManager()
    {
        init();
    }

    /**
     * 静态内部类，实例化对象使用
     */
    private static class SingleInstanceHolder
    {
        private static final GreenDaoManager INSTANCE = new GreenDaoManager();
    }

    /**
     * 对外唯一实例的接口
     *
     * @return
     */
    public static GreenDaoManager getInstance()
    {
        return SingleInstanceHolder.INSTANCE;
    }

    /**
     * 初始化数据
     */
    private void init() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(DemoCache.getContext(), "footprint");
        mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }
    public DaoMaster getmDaoMaster()
    {
        return mDaoMaster;
    }
    public DaoSession getmDaoSession()
    {
        return mDaoSession;
    }
    public DaoSession getNewSession()
    {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }


}
