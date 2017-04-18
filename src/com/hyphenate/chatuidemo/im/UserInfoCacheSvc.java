package com.hyphenate.chatuidemo.im;

import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * 缓存用户信息（主要用于聊天显示昵称和头像）
 */
public class UserInfoCacheSvc {
	public static List<UserApiModel> getAllList(){
		Dao<UserApiModel, String> daoScene = SqliteHelper.getInstance().getUserDao();
		try {
			List<UserApiModel> list = daoScene.queryBuilder().query();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static UserApiModel getByChatUserName(String chatUserName){
		Dao<UserApiModel, String> dao = SqliteHelper.getInstance().getUserDao();
		try {
            QueryBuilder<UserApiModel, String> queryBuilder = dao.queryBuilder();
            queryBuilder.where().eq("EaseMobUserName", chatUserName);
            UserApiModel model = queryBuilder.queryForFirst();
			return model;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public static UserApiModel getById(String id){
		Dao<UserApiModel, String> dao = SqliteHelper.getInstance().getUserDao();
		try {
			return dao.queryForId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean createOrUpdate(String chatUserName, String userNickName, String avatarUrl){
		try {
			Dao<UserApiModel, String> dao = SqliteHelper.getInstance().getUserDao();

			UserApiModel user = getById(chatUserName);

			int changedLines = 0;
			if (user == null){
				user = new UserApiModel();
				user.setId(chatUserName);
				user.setHeadImg(avatarUrl);
				user.setEaseMobUserName(userNickName);

				changedLines = dao.create(user);
			}else {
				user.setId(userNickName);
				user.setHeadImg(avatarUrl);
				user.setEaseMobUserName(chatUserName);

				changedLines = dao.update(user);
			}

			if(changedLines > 0){
				Log.i("UserInfoCacheSvc", "操作成功~");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Log.e("UserInfoCacheSvc", "操作异常~");
		}
		
		return false;
	}

	public static boolean createOrUpdate(UserApiModel model){

		if(model == null) return false;

		try {
			Dao<UserApiModel, String> dao = SqliteHelper.getInstance().getUserDao();

			UserApiModel user = getById(model.getId());
			int changedLines = 0;
			if (user == null){
				changedLines = dao.create(model);
			}else {
				model.setId(user.getId());
				changedLines = dao.update(model);
			}

			if(changedLines > 0){
				Log.i("UserInfoCacheSvc", "操作成功~");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Log.e("UserInfoCacheSvc", "操作异常~");
		}

		return false;
	}

}