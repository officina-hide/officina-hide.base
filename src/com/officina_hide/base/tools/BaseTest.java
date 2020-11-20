package com.officina_hide.base.tools;

import java.io.File;
import java.io.IOException;

import com.officina_hide.base.common.FD_DB_Utility;
import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.I_FD_Numbering;
import com.officina_hide.base.model.I_FD_Process;
import com.officina_hide.project.model.I_FD_Project;
import com.officina_hide.project.model.I_FD_Task;
import com.officina_hide.project.tools.FDProject;
import com.officina_hide.project.tools.FDTask;

public class BaseTest {

	/**
	 * ベースパッケージテストクラス
	 * @param args
	 */
	public static void main(String[] args) {
		FD_EnvData env = null;
		try {
			String propPath = new File(".").getCanonicalPath()+"\\data\\base.properties";
			//環境情報の取込
			env = new FD_EnvData(propPath);
		} catch (IOException e) {
			e.printStackTrace();
		}

		//関連情報リセット処理
		resetBaseData(env, 999);

		FDProcess process = new FDProcess();
		int processId = process.createProcess(env, 999, "Test");
		env.setActiveProcessID(processId);

		/*
		 * 
		 */
		
		/*
		 * タスク登録テスト
		 * ・プロジェクト情報を登録する。
		 * ・タスク伝票の採番情報を登録する。
		 * ・タスク情報を登録する。
		 */
//		FDProject project = new FDProject();
//		int projectId = project.addData(env, "TEST Project", "タスク登録テスト用プロジェクト", "TEST-0000");
//		FDNumbering num = new FDNumbering();
//		FDTableColumn column = new FDTableColumn();
//		num.addData(env, 0, I_FD_Task.TABLE_ID, 0, 1
//				, column.getColumnId(env, I_FD_Task.TABLE_ID, I_FD_Task.COLUMNNAME_Task_Number), "TEST-0000");
//		FDTask task = new FDTask();
//		task.addData(env, projectId);
		
//		/*
//		 * 特定の採番を行うための改良テスト
//		 */
//		FDTableColumn column = new FDTableColumn();
//		int columnId = column.getColumnId(env, I_FD_Table.TABLE_ID, I_FD_Table.COLUMNNAME_Table_Name);
//		FDNumbering num = new FDNumbering();
//		num.addData(env, 0, I_FD_Table.TABLE_ID, 0, 1, columnId, "SSDS");
//		FD_DB DB = new FD_DB();
//		int id = DB.getNewID(env, I_FD_Table.Table_Name, columnId, "SSDS");
//		int tid = DB.getNewID(env, I_FD_Table.Table_Name);
//		System.out.println(id+":"+tid);
	}
	
	/**
	 * Fxベースクラス登録用情報リセット<br>
	 * <p>実行プロセス情報IDを持つベース情報を削除して、ベース構築処理をリセットする。</p>
	 * @author officine-hide.com
	 * @since 1.10 2020/11/03
	 * @param env 環境情報
	 * @param processId 
	 */
	private static void resetBaseData(FD_EnvData env, int processId) {
		//プロセス情報削除
		FD_DB_Utility dbutil = new FD_DB_Utility();
		dbutil.deleteDataByProcessId(env, I_FD_Process.Table_Name, processId);
		dbutil.deleteDataByProcessId(env, I_FD_Project.Table_Name, processId);
		dbutil.deleteDataByProcessId(env, I_FD_Numbering.Table_Name, processId);
	}

}
