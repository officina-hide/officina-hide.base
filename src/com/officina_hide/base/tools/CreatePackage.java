package com.officina_hide.base.tools;

import java.io.File;
import java.io.IOException;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.I_FD_Log;
import com.officina_hide.base.model.I_FD_Numbering;
import com.officina_hide.base.model.I_FD_Table;
import com.officina_hide.base.model.I_FD_TableColumn;

/**
 * パッケージ構築クラス<br>
 * <p>本パッケージを初期状態から構築するための機能を提供する。<br>
 * 将来的には、ウィザード形式でシステムが構築できるようにしていく。</p>
 * @author officine-hide.com
 * @version 1.00
 * @since 2020/10/08
 */
public class CreatePackage {

	public static void main(String[] args) {
		/*
		 * 1.環境情報の取込
		 * 2.ベース情報の構築
		 * 3.ベース情報の登録
		 * 4.ベース情報のダンプ作成
		 */
		//環境情報のPathを設定する。
		FD_EnvData env = null;
		try {
			String propPath = new File(".").getCanonicalPath()+"\\data\\base.properties";
			//環境情報の取込
			env = new FD_EnvData(propPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//ログ情報構築
		FDLog log = new FDLog();
		log.createTable(env);
		
		//テーブル情報構築
		FDTable table = new FDTable();
		table.createTable(env);
		table.addData(env, I_FD_Table.TABLE_ID, I_FD_Table.Table_Name, I_FD_Table.NAME, I_FD_Table.COMMENT);
		table.addData(env, I_FD_Log.TABLE_ID, I_FD_Log.Table_Name, I_FD_Log.NAME, I_FD_Log.COMMENT);
		//採番情報構築
		FDNumbering num = new FDNumbering();
		num.createTable(env);
		table.addData(env, I_FD_Numbering.TABLE_ID, I_FD_Numbering.Table_Name
				, I_FD_Numbering.NAME, I_FD_Numbering.COMMENT);
		num.addData(env,I_FD_Table.TABLE_ID, I_FD_Table.TABLE_ID, 0, 1000001);
		num.addData(env,I_FD_Numbering.TABLE_ID, I_FD_Numbering.TABLE_ID, 0, 1000001);
		//リファレンス情報構築
		FDReference ref = new FDReference();
		ref.createDBTable(env);
		ref.addColumnTypeReference(env);
		//テーブル項目情報構築
		FDTableColumn column = new FDTableColumn();
		column.createTable(env);
		table.addData(env, I_FD_TableColumn.TABLE_ID, I_FD_TableColumn.Table_Name
				, I_FD_TableColumn.NAME, I_FD_TableColumn.COMMENT);
		num.addData(env,I_FD_TableColumn.TABLE_ID, I_FD_TableColumn.TABLE_ID, 0, 1000001);
		//テーブル項目情報登録
		table.addTableColumn(env);
		column.addTableColumn(env);
	}

}
