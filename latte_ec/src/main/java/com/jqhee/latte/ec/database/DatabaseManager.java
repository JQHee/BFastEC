package com.jqhee.latte.ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

public class DatabaseManager {

    // 以下两个对象是greendao插件生成
    private DaoSession mDaoSession;
    private UserProfileDao mUserProfileDao;

    private DatabaseManager() {
    }

    public DatabaseManager init(Context context) {
        initDao(context);
        return this;
    }

    private static final class Holder {
        private static final DatabaseManager INSTANCE = new DatabaseManager();
    }

    public static DatabaseManager getInstance() {
        return Holder.INSTANCE;
    }

    private void initDao(Context context) {
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context, "fast_ec.db");
        final Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
        mUserProfileDao = mDaoSession.getUserProfileDao();
    }

    public final UserProfileDao getDao() {
        return mUserProfileDao;
    }

}
