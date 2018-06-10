package top.cyixlq.cy.ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

public class DatabaseManager {

    private DaoSession mDaoSession;
    private UserProfileDao mUserProfileDao;

    private DatabaseManager(){

    }

    public DatabaseManager init(Context context){
        initDao(context);
        return this;
    }

    private static final class Holder{
        private static final DatabaseManager INSTANCE = new DatabaseManager();
    }

    public static DatabaseManager getInstance(){
        return Holder.INSTANCE;
    }

    private void initDao(Context context){
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context, "aimi_ec.db");
        final Database database = helper.getWritableDb();
        mDaoSession = new DaoMaster(database).newSession();
        mUserProfileDao = mDaoSession.getUserProfileDao();
    }

    public final UserProfileDao getUserProfileDao(){
        return mUserProfileDao;
    }
}
