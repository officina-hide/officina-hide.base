package com.officina_hide.fx.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_Column;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.FD_DataDictionary;
import com.officina_hide.base.model.FD_Numbering;
import com.officina_hide.base.model.FD_Table;

/**
 * タブ処理情報クラス[Tab process information class]
 * @author officine-hide.net
 * @version 1.00 新規作成
 * @since 2021/10/08 Ver. 1.00
 */
public class FX_TabProcess extends FD_DB implements I_FX_TabProcess {

	/**
	 * タブ処理情報テーブル構築[Tab processing information table construction]<br>
	 * @author officine-hide.net
	 * @since 2021/10/09 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void createTable(FD_EnvData env) {
		//テーブル情報登録
		FD_Table table = new FD_Table();
		table.add(env, Table_ID, Table_Name, Table_Disp_Name, Table_Comment);
		//採番情報
		FD_Numbering num = new FD_Numbering();
		num.add(env, Table_ID, Table_ID, 101, 0);
		//辞書情報
		FD_DataDictionary dd = new FD_DataDictionary();
		dd.add(env, 0, COLUMNNAME_FX_TabProcess_ID, NAME_FX_TabProcess_ID, COMMENT_FX_TabProcess_ID);
		//テーブル項目情報登録
		FD_Column column = new FD_Column();
		column.add(env, 0, Table_ID, COLUMNNAME_FX_TabProcess_ID, FD_Item_ID, 0, false, true, null);
		column.add(env, 0, Table_ID, COLUMNNAME_FX_Tab_ID, FD_Item_ID, 0, true, false, null);
		column.add(env, 0, Table_ID, COLUMNNAME_FD_Process_ID, FD_Item_ID, 0, true, false, null);
		column.add(env, 0, Table_ID, COLUMNNAME_FD_Name, FD_Item_String, 100, true, false, null);
		addCommonColumn(env, Table_ID);
	}

}
