package com.hyphenate.chatuidemo.im;

import android.database.sqlite.SQLiteDatabase;

import com.hyphenate.chatuidemo.DemoApplication;
import com.ican.dbutil.db.BaseDb;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * @author wbs
 * @time 17/2/20 15:16
 */
public class SqliteHelper extends BaseDb {
    private SqliteHelper() {
        super(DemoApplication.getInstance(), "USER_INFO", 5);
    }

    private static SqliteHelper sqliteHelper;

    public static SqliteHelper getInstance() {
        if (sqliteHelper == null) {
            synchronized (SqliteHelper.class) {
                if (sqliteHelper == null) {
                    sqliteHelper = new SqliteHelper();
                }
            }
        }
        return sqliteHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, UserApiModel.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource,UserApiModel.class,true);
            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<UserApiModel,String> getUserDao() {
        try {
            return getDao(UserApiModel.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
