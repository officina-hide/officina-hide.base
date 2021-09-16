package com.officina_hide.base.model;

/**
 * テーブル情報インターフェースクラス[Table Information Interfaces class]<br>
 * @author officine-hide.com
 * @version 1.00
 * @since 2021/04/08
 */
public interface I_FD_Table extends I_FD_DB {

	/** テーブル名 */
	public static final String Table_Name = "FD_Table";
	/** テーブル表示名 */
	public static final String Table_Disp_Name = "テーブル情報";
	/** テーブルID */
	public static final int Table_ID = 100;

	/** 項目 : テーブル情報ID */
	public static final String COLUMNNAME_FD_Table_ID = Table_Name+"_ID";
	public static final String NAME_FD_Table_ID = Table_Disp_Name;
	public static final String COLUMNNAME_FD_Table_Name = Table_Name + "_Name";
	public static final String COMMENT_FD_Table_Name = "テーブル物理名";
	public static final String COLUMNNAME_FD_Name = "FD_Name";
	public static final String COMMENT_FD_Name = "名前";
	public static final String COLUMNNAME_FD_Description = "FD_Description";
	public static final String COMMENT_FD_Description = "説明";
 }
