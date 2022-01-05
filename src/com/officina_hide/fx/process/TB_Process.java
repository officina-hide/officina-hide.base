package com.officina_hide.fx.process;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.fx.base.FX_FieldItem;
import com.officina_hide.fx.base.FX_Fields;
import com.officina_hide.fx.model.I_FX_ToolBar;
import com.officina_hide.fx.model.X_FX_Field;
import com.officina_hide.fx.model.X_FX_Toolbar;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

/**
 * ツールバー処理[Toolbar processing]<br>
 * @author officina-hide.net
 * @version 新規作成
 * @since 2021/12/07 Ver. 1.00
 */
public class TB_Process implements I_FX_ToolBar {

	/**
	 * 処理実行[Processing execute]<br>
	 * @author officina-hide.net
	 * @since 2021/12/07 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param toolBar ツールバー情報[ToolBar information]
	 * @param fields 画面項目情報[Screen item information]
	 */
	public void execute(FD_EnvData env, X_FX_Toolbar toolBar, FX_Fields fields) {
		/*
		 * 保存ボタンが押されたときの処理<br>
		 * TODO 別クラス化予定
		 */
		if(toolBar.getFD_Name().equals(NAME_TB_Save)) {
			for(int ix = 0; ix < fields.getFields().size(); ix++) {
				FX_FieldItem fitem = fields.getFields().get(ix);
				switch(fitem.getFieldTypeName()) {
				case FD_Field_Date:
					DatePicker dt = (DatePicker) fitem.getFieldItem();
					Calendar cal = new GregorianCalendar(new Locale(Locale.JAPAN.getLanguage(), Locale.JAPAN.getCountry()));
					cal.set(dt.getValue().getYear(), dt.getValue().getMonth().getValue(), dt.getValue().getDayOfMonth());
					break;
				case FD_Field_List:
					ComboBox<String> combo = (ComboBox<String>) fitem.getFieldItem();
					break;
				}
			}
		}
		
		/*
		 * 「新規」ボタンクリック時処理
		 */
		if(toolBar.getFD_Name().equals(NAME_TB_New)) {
			
		}
	}

}
