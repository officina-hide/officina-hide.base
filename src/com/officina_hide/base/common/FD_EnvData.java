package com.officina_hide.base.common;

/**
 * 環境情報クラス[Environmental information class]<br>
 * @author officina-hide.com
 * @version 1.00
 * @since 2021/04/05
 */
public class FD_EnvData {

	/** 処理者情報ID 2021/09/18 Addition */
	private long actionUserID;
	
	
	
	/** 処理レベル */
	private int runLevel;
	/** 
	 * モデル生成用情報[Information for model generation] 
	 */
	/** ソース保管用Path[Source storage path] */
	private String generatePath;

	public int getRunLevel() {
		return runLevel;
	}

	public void setRunLevel(int runLevel) {
		this.runLevel = runLevel;
	}

	public String getGeneratePath() {
		return generatePath;
	}
	
	public void setGeneratePath(String generatePath) {
		this.generatePath = generatePath;
	}

	/**
	 * 環境情報初期化[Environmental information initialization]<br>
	 * @author officine-hide.com
	 * @since 1.00 2021/05/24
	 */
	public void initialize() {
		
	}

	public long getActionUserID() {
		return actionUserID;
	}
	public void setActionUserID(long actionUserID) {
		this.actionUserID = actionUserID;
	}
}
