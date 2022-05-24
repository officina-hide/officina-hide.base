package com.officina_hide.medical.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.FD_Table;
import com.officina_hide.base.model.I_FD_Column;
import com.officina_hide.base.model.I_FD_Table;
import com.officina_hide.base.model.X_FD_Column;

/**
 * 検査項目情報I/Oクラス[Inspection item information I/O class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/05/23 Ver. 1.00
 */
public class X_FM_InspectionItem extends FD_DB implements I_FM_InspectionItem {

	/**
	 * コンストラクター[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2022/05/23 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param entryData 登録情報[Entry data]
	 */
	public X_FM_InspectionItem(FD_EnvData env, String entryData) {
		createColumnList(env, Table_Name);
	}

	/**
	 * テーブル項目リスト初期化[Table item list initialization]<br>
	 * @author officina-hide.net
	 * @since 2022/05/23 Ver. 1.00
	 * @param tableName テーブル名[Table name]
	 * @param env 環境情報[Environment information]
	 */
	private void createColumnList(FD_EnvData env, String tableName) {
		columnCollection.clear();
		//テーブル項目リスト取得
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection(env);
			pstmt = getConn().prepareStatement(SQL_Get_ColumnData);
			pstmt.setString(1, tableName);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				long id = rs.getLong(I_FD_Column.COLUMNNAME_FD_Column_ID);
				X_FD_Column column = new X_FD_Column(env, id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, rs);
		}
	}

	private final String SQL_Get_ColumnData =
			"SELECT "+I_FD_Column.COLUMNNAME_FD_Column_ID+" FROM "+I_FD_Column.Table_Name+" c "
			+ "LEFT JOIN " + FD_Table.Table_Name + " t ON t." + I_FD_Table.COLUMNNAME_FD_Table_ID + " = "
				+ "c." + I_FD_Column.COLUMNNAME_FD_Table_ID + " "
			+"WHERE " + I_FD_Table.COLUMNNAME_FD_Table_Code + " = ? ";
}
