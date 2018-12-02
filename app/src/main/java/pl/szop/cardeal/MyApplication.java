package pl.szop.cardeal;

import android.app.Application;
import android.content.SharedPreferences;

import pl.szop.cardeal.data.IDataProvider;
import pl.szop.cardeal.data.DatabaseInitializer;
import pl.szop.cardeal.data.database.DatabaseProvider;
import pl.szop.cardeal.models.DaoMaster;
import pl.szop.cardeal.models.DaoSession;

public class MyApplication extends Application {

    public static final int OFFLINE_MODE = 0;
    public static final int ONLINE_MODE = 1;

    private IDataProvider mDataProvider;

    private SharedPreferences mPreferences;

    private static MyApplication mInstance;

    @Override
    public void onCreate(){
        super.onCreate();
        mPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        setDataProvider(OFFLINE_MODE);

        if(isFirstRun()){
            initApplication();
        }

        mInstance = this;
    }

    private void initApplication(){
        DatabaseInitializer.init(((DatabaseProvider)mDataProvider).getDaoSession(),getApplicationContext());
        mPreferences.edit().putBoolean("firstrun", false).apply();
    }

    public static MyApplication instance(){
        assert mInstance != null;
        return mInstance;
    }

    public void setDataProvider(int mode){
        mDataProvider = createDataProvider(mode);
    }

    public IDataProvider createDataProvider(int mode){
        switch (mode){
            case OFFLINE_MODE:
                return createOfflineDataProvider();
                default:
                    throw new IllegalArgumentException();
        }
    }

    private IDataProvider createOfflineDataProvider(){
        DaoSession daoSession = createDaoSession();
        return new DatabaseProvider(daoSession);
    }

    private DaoSession createDaoSession() {
        return new DaoMaster(new DaoMaster.DevOpenHelper(this, "products_db").getWritableDb()).newSession();
    }

    public IDataProvider getDataProvider() {
        return mDataProvider;
    }

    private boolean isFirstRun(){
        return mPreferences.getBoolean("firstrun", true);
    }
}
