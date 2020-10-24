package com.officina_hide.base.tools;

import java.util.Date;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_Log;

/**
 * ログ情報クラス<br>
 * @author officine-hide.com
 * @version 1.00
 * @since 2020/10/20
 */
public class FDLog extends FD_DB implements I_FD_Log {

	/**
	 * ログ情報構築
	 * @author officine-hide.com
	 * @since 1.00 2020/10/20
	 * @param env 環境情報
	 */
	public void createTable(FD_EnvData env) {
		//テーブル構築
		createDBTable(env);
	}

	/**
	 * ログ情報テーブル構築<br>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/20
	 * @param env 環境情報
	 */
	private void createDBTable(FD_EnvData env) {
		StringBuffer sqlDrop = new StringBuffer();
		//既に登録されているログ情報を削除する。
		sqlDrop.append("DROP TABLE IF EXISTS ").append(Table_Name);
		DBexecute(env, sqlDrop.toString());
		//ログ情報テーブル構築
		StringBuffer sql = new StringBuffer();
		sql.append("CREATE TABLE IF NOT EXISTS ").append(Table_Name).append(" (");
		sql.append(COLUMNNAME_FD_Log_ID).append(" INT UNSIGNED NOT NULL PRIMARY KEY COMMENT ")
			.append(FD_SQ).append(NAME_FD_Log_ID).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_Log_Type_ID).append(" INT UNSIGNED COMMENT ")
			.append(FD_SQ).append(NAME_Log_Type_ID).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_Log_Data).append(" TEXT COMMENT ")
			.append(FD_SQ).append(NAME_Log_Data).append(FD_SQ).append(",");
		
		sql.append(COLUMNNAME_FD_CREATE).append(" DATETIME COMMENT ")
			.append(FD_SQ).append(NAME_FD_CREATE).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_CREATED).append(" INT UNSIGNED COMMENT ")
			.append(FD_SQ).append(NAME_FD_CREATED).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_UPDATE).append(" DATETIME COMMENT ")
			.append(FD_SQ).append(NAME_FD_UPDATE).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_UPDATED).append(" INT UNSIGNED COMMENT ")
			.append(FD_SQ).append(NAME_FD_UPDATED).append(FD_SQ).append(" ");
		sql.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=").append(FD_SQ).append(NAME).append(FD_SQ);
		DBexecute(env, sql.toString());
		
		System.out.println(new Date() + " : " + NAME + "テーブル生成完了");

		//ログ情報の構築をログ情報に登録する。
		addLog(env, LOGTYPE_Table_Drop_ID, changeEscape(sqlDrop.toString()));
		addLog(env, LOGTYPE_Table_Create_ID, changeEscape(sql.toString()));
		
	}
}
