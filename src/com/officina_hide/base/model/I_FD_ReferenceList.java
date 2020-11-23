package com.officina_hide.base.model;

/**
 * リファレンスリスト情報インタフェース<br>
 * @author officina-hide.com
 * @version 1.21
 * @since 2020/11/23
 */
public interface I_FD_ReferenceList {

	/** リファレンスリスト情報 : テーブル名 */
	public final String Table_Name = "FD_ReferenceList";
	/** リファレンスリスト情報 : テーブル表示名 */
	public final String NAME = "リファレンスリスト情報";
	/** リファレンスリスト情報 : テーブル説明 */
	public final String COMMENT = "リファレンスリストを管理する為の情報";
	/** リファレンスリスト情報 : テーブル情報ID */
	public final int TABLE_ID = 107;
	
	/** リファレンスリスト情報ID */
	public final String COLUMNNAME_FD_ReferenceList_ID = Table_Name + "_ID";
	public final String NAME_FD_ReferenceList_ID = NAME+"ID";
	public final String COMMENT_FD_ReferenceList_ID = "リファレンスリストを識別するための情報ID";
	/** リファレンス情報 */
	public final String COLUMNNAME_FD_Reference_ID = I_FD_Reference.Table_Name + "_ID";
	public final String NAME_FD_Reference_ID = I_FD_Reference.NAME+"ID";
	public final String COMMENT_FD_Reference_ID = "リファレンスリスト情報の親となるリファレンスの情報ID";
}
