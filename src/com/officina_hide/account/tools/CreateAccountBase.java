package com.officina_hide.account.tools;

import com.officina_hide.account.model.AC_AccountTitle;
import com.officina_hide.account.model.AC_JournalSlip;
import com.officina_hide.account.model.I_AC_AccountTitle;
import com.officina_hide.account.model.I_AC_JournalSlip;
import com.officina_hide.account.model.I_FV_AccountTitle;
import com.officina_hide.account.model.I_FV_JournalSlip;
import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_Column;
import com.officina_hide.base.model.FD_Reference;
import com.officina_hide.base.model.FD_Table;
import com.officina_hide.base.model.FD_TableReference;
import com.officina_hide.base.model.I_FD_DB;
import com.officina_hide.fx.base.MainFrameSetting;
import com.officina_hide.fx.model.FX_Field;
import com.officina_hide.fx.model.FX_Menu;
import com.officina_hide.fx.model.FX_Tab;
import com.officina_hide.fx.model.FX_View;

/**
 * 会計機能基盤生成[Accounting function infrastructure generation]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/11/13 Ver. 1.00
 */
public class CreateAccountBase implements I_FD_DB {
	
	/** 環境情報[Environment information] */
	private FD_EnvData env;

	/**
	 * コンストラクタ[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2021/11/13 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public CreateAccountBase(FD_EnvData env) {
		this.env = env;
	}

	/**
	 * 生成実行[Run generation]<br>
	 * @author officina-hide.net
	 * @since 2021/11/13 Ver. 1.00
	 */
	public void execute() {
		FD_Table table = new FD_Table();
		/*
		 * 1. 勘定科目情報生成[Generate account information]
		 * 1-2. 勘定科目情報画面関連登録[Account information screen related registration]
		 * 2. 仕訳伝票情報生成[Generate Journal Slip information]
		 * 3. メイン画面関連情報設定
		 */
		//1.
		AC_AccountTitle aat = new AC_AccountTitle();
		aat.createTable(env);
		aat.addTitle(env);
		//1-2.
		//画面情報登録
		FX_View view = new FX_View();
		long viewId = view.add(env, 0, I_FV_AccountTitle.VIEWNAME, "", "");
		FX_Tab tab = new FX_Tab();
		long tabId =  tab.add(env, 0, "FV_AccountTitle", viewId, table.getTableId(I_AC_AccountTitle.Table_Name), "勘定科目情報", "", 0);
		FX_Field field = new FX_Field();
		FD_Column column = new FD_Column();
		field.add(env, 0, "勘定科目コード", "勘定科目コード", tabId,
				column.getColumnID(env, I_AC_AccountTitle.Table_Name, I_AC_AccountTitle.COLUMNNAME_AC_AccountTitle_Code),
				FD_Field_SimpleText, 0);
		field.add(env, 0, "勘定科目名", "勘定科目名", tabId,
				column.getColumnID(env, I_AC_AccountTitle.Table_Name, COLUMNNAME_FD_Name),
				FD_Field_SimpleText, 0);
		
		//2.
		AC_JournalSlip ajs = new AC_JournalSlip();
		ajs.createTable(env);
		
		
		//メニュー情報登録
		FX_Menu menu = new FX_Menu();
		menu.add(env, 0, I_FV_AccountTitle.VIEWNAME, viewId, FD_Menu_View, "勘定科目");
		
		viewId = view.add(env, 0, I_FV_JournalSlip.VIEWNAME, "", "");
		menu.add(env, 0, I_FV_JournalSlip.VIEWNAME, viewId, FD_Menu_View, "仕分伝票");
		tabId = tab.add(env, 0, I_FV_JournalSlip.VIEWNAME, viewId, table.getTableId(I_AC_JournalSlip.Table_Name),
				I_AC_JournalSlip.Table_Disp_Name, I_AC_JournalSlip.Table_Comment, 0);
//		field.add(env, 0, I_AC_JournalSlip.Table_Name, I_AC_JournalSlip.COLUMNNAME_AC_IssueDate, tabId, FD_Field_Date, 0);

		FD_Reference ref = new FD_Reference();
		long refId = ref.add(env, 0, "AC_JournalSlip_List", FD_Reference_Table);
		FD_TableReference tref = new FD_TableReference();
		tref.add(env, 0, refId, table.getTableId(I_AC_AccountTitle.Table_Name));
		
//		field.add(env, 0, I_AC_JournalSlip.Table_Name,
//				I_AC_JournalSlip.COLUMNNAME_AC_Credit_AccountTitle_ID, tabId, FD_Field_List, refId);
		
		
		//3.
		MainFrameSetting mfs = new MainFrameSetting();
		mfs.addInformation(env);
	}

}
