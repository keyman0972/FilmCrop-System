package com.ddsc.km.lab.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.ddsc.common.comm.entity.CommOptCde;
import com.ddsc.common.comm.service.ICommOptCdeService;
import com.ddsc.core.action.AbstractAction;
import com.ddsc.core.action.IBaseAction;
import com.ddsc.core.exception.DdscApplicationException;
import com.ddsc.core.exception.DdscAuthException;
import com.ddsc.core.util.Pager;
import com.ddsc.km.lab.entity.LabFilmCorpMst;
import com.ddsc.km.lab.service.ILabFilmCorpMstService;
import com.ddsc.km.lab.service.ILabFilmMstService;

/**
 * <table>
 * <tr>
 * <th>版本</th>
 * <th>日期</th>
 * <th>詳細說明</th>
 * <th>modifier</th>
 * </tr>
 * <tr>
 * <td>1.0</td>
 * <td>2017/8/4</td>
 * <td>新建檔案</td>
 * <td>"keyman"</td>
 * </tr>
 * </table>
 * @author "keyman"
 *
 * 類別說明 :
 *
 *
 * 版權所有 Copyright 2008 © 中菲電腦股份有限公司 本網站內容享有著作權，禁止侵害，違者必究。 <br>
 * (C) Copyright Dimerco Data System Corporation Inc., Ltd. 2009 All Rights
 */

public class Lab0A001KAction extends AbstractAction implements IBaseAction {
	
	private static final long serialVersionUID = 6774263069926663218L;
	
	private ILabFilmCorpMstService labFilmCorpMstService;
	private List<Map<String,String>> labFilmCorpMstListMap;
	private LabFilmCorpMst labFilmCorpMst;
	
	private ICommOptCdeService commOptCdeService;
	private List<CommOptCde> corpAreaList;			//動態產生
	private String corpArea;
	
	private ILabFilmMstService LabFilmMstService;
	
	@Override
	public String init() throws Exception {
		try {
		}catch (Exception ex) {
			DdscApplicationException e = new DdscApplicationException(ex, this.getUserInfo());
			this.addActionError(this.getText("eP.0022", new String[] {e.getMsgCode(), this.getText(e.getMsgCode()),e.getMsgFullMessage() }));
		}
		setNextAction(ACTION_SEARCH);
		return SUCCESS;
	}
	
	@Deprecated
	@Override
	public String approve() throws Exception {
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String search() throws Exception {
		try{
			Map<String, Object> conditions = new HashMap<String, Object>();
			conditions.put("fmCorpId", labFilmCorpMst.getFmCorpId());
			conditions.put("fmCorpName", labFilmCorpMst.getFmCorpName());
			
			Pager resultPager = getLabFilmCorpMstService().searchByConditions(conditions, getPager(), this.getUserInfo());
			
			labFilmCorpMstListMap = (List<Map<String, String>>) resultPager.getData();
			
			setPager(resultPager);
			
			if (labFilmCorpMstListMap == null || labFilmCorpMstListMap.size() <= 0) {
				this.addActionError(this.getText("w.0001"));
			}
		}catch (DdscApplicationException e) {
			this.addActionError(this.getText("eP.0022", new String[] {e.getMsgCode(), this.getText(e.getMsgCode()),e.getMsgFullMessage() }));
		}catch (Exception ex) {
			DdscApplicationException e = new DdscApplicationException(ex, this.getUserInfo());
			this.addActionError(this.getText("eP.0022", new String[] {e.getMsgCode(), this.getText(e.getMsgCode()),e.getMsgFullMessage() }));
		}
		setNextAction(ACTION_SEARCH);
		return SUCCESS;
	}
	
	@Override
	public String query() throws Exception {
		try{
			labFilmCorpMst = labFilmCorpMstService.get(labFilmCorpMst.getFmCorpId(), this.getUserInfo());
		}catch (Exception ex) {
			DdscApplicationException e = new DdscApplicationException(ex, this.getUserInfo());
			this.addActionError(this.getText("eP.0022", new String[] {e.getMsgCode(), this.getText(e.getMsgCode()), e.getMsgFullMessage()}));
		}
		setNextAction(ACTION_QUERY);
		return SUCCESS;
	}
	
	@Override
	public String create() throws Exception {
		try {
		} catch (DdscApplicationException e) {
			this.addActionError(this.getText("eP.0022", new String[] {e.getMsgCode(), this.getText(e.getMsgCode()),e.getMsgFullMessage() }));
		} catch (Exception ex) {
			DdscApplicationException e = new DdscApplicationException(ex, this.getUserInfo());
			this.addActionError(this.getText("eP.0022", new String[] {e.getMsgCode(), this.getText(e.getMsgCode()),e.getMsgFullMessage() }));
		}
		setNextAction(ACTION_CREATE_SUBMIT);
		return SUCCESS;
	}

	@Override
	public String createSubmit() throws Exception {
		try {
			if (this.hasConfirm() == true) {
				setNextAction(ACTION_CREATE_CONFIRM);
				return RESULT_CONFIRM;
			} else {
				return this.createConfirm();
			}
		} catch (DdscApplicationException e) {
			this.addActionError(this.getText("eP.0022", new String[] {e.getMsgCode(), this.getText(e.getMsgCode()),e.getMsgFullMessage() }));
			return RESULT_EDIT;
		} catch (Exception ex) {
			DdscApplicationException e = new DdscApplicationException(ex, this.getUserInfo());
			this.addActionError(this.getText("eP.0022", new String[] {e.getMsgCode(), this.getText(e.getMsgCode()),e.getMsgFullMessage() }));
			return RESULT_EDIT;
		}
	}

	@Override
	public String createConfirm() throws Exception {
		try{
			this.getLabFilmCorpMstService().create(labFilmCorpMst, this.getUserInfo());
			
			setNextAction(ACTION_CREATE);
			return RESULT_SHOW;
		}catch (DdscApplicationException e) {
			this.addActionError(this.getText("eP.0022", new String[] {e.getMsgCode(), this.getText(e.getMsgCode()), e.getMsgFullMessage()}));
			setNextAction(ACTION_CREATE_SUBMIT);
			return RESULT_EDIT;
		}catch (Exception ex) {
			DdscApplicationException e = new DdscApplicationException(ex, this.getUserInfo());
			this.addActionError(this.getText("eP.0022", new String[] {e.getMsgCode(), this.getText(e.getMsgCode()), e.getMsgFullMessage()}));
			setNextAction(ACTION_CREATE_SUBMIT);
			return RESULT_EDIT;
		}
	}

	@Override
	public String update() throws Exception {
		try{
			labFilmCorpMst = labFilmCorpMstService.get(labFilmCorpMst.getFmCorpId(), this.getUserInfo());
		}catch (DdscApplicationException e) {
			this.addActionError(this.getText("eP.0022", new String[] {e.getMsgCode(), this.getText(e.getMsgCode()), e.getMsgFullMessage()}));

		}catch (Exception ex) {
			DdscApplicationException e = new DdscApplicationException(ex, this.getUserInfo());
			this.addActionError(this.getText("eP.0022", new String[] {e.getMsgCode(), this.getText(e.getMsgCode()), e.getMsgFullMessage()}));
		}
		setNextAction(ACTION_UPDATE_SUBMIT);
		return SUCCESS;
	}

	@Override
	public String updateSubmit() throws Exception {
		try {
			if (hasConfirm()) {
				setNextAction(ACTION_UPDATE_CONFIRM);
				return RESULT_CONFIRM;
			}
			else {
				return this.updateConfirm();
			}
		}catch (DdscApplicationException e) {
			this.addActionError(this.getText("eP.0022", new String[] {e.getMsgCode(), this.getText(e.getMsgCode()), e.getMsgFullMessage()}));
			setNextAction(ACTION_UPDATE_SUBMIT);
			return RESULT_EDIT;
		}catch (Exception ex) {
			DdscApplicationException e = new DdscApplicationException(ex, this.getUserInfo());
			this.addActionError(this.getText("eP.0022", new String[] {e.getMsgCode(), this.getText(e.getMsgCode()), e.getMsgFullMessage()}));
			setNextAction(ACTION_UPDATE_SUBMIT);
			return RESULT_EDIT;
		}
	}

	@Override
	public String updateConfirm() throws Exception {
		try{
			labFilmCorpMstService.update(labFilmCorpMst, getUserInfo());
			
			setNextAction(ACTION_UPDATE);
			return RESULT_SHOW;
		}catch (DdscApplicationException e) {
			this.addActionError(this.getText("eP.0022", new String[] {e.getMsgCode(), this.getText(e.getMsgCode()), e.getMsgFullMessage()}));
			setNextAction(ACTION_UPDATE_SUBMIT);
			return RESULT_EDIT;
		}catch (Exception ex) {
			DdscApplicationException e = new DdscApplicationException(ex, this.getUserInfo());
			this.addActionError(this.getText("eP.0022", new String[] {e.getMsgCode(), this.getText(e.getMsgCode()), e.getMsgFullMessage()}));
			setNextAction(ACTION_UPDATE_SUBMIT);
			return RESULT_EDIT;
		}
		
	}

	@Override
	public String delete() throws Exception {
		try{
			labFilmCorpMst = labFilmCorpMstService.get(labFilmCorpMst.getFmCorpId(), this.getUserInfo());
			
		}catch (Exception ex) {
			DdscApplicationException e = new DdscApplicationException(ex, this.getUserInfo());
			this.addActionError(this.getText("eP.0022", new String[] {e.getMsgCode(), this.getText(e.getMsgCode()), e.getMsgFullMessage()}));
		}
		setNextAction(ACTION_DELETE_CONFIRM);
		return SUCCESS;
	}

	@Override
	public String deleteConfirm() throws Exception {
		try{
			labFilmCorpMst = getLabFilmCorpMstService().delete(labFilmCorpMst, getUserInfo());
			setNextAction(ACTION_DELETE);
			return RESULT_SHOW;
		}catch (DdscApplicationException e) {
			this.addActionError(this.getText("eP.0022", new String[] {e.getMsgCode(), this.getText(e.getMsgCode()), e.getMsgFullMessage()}));
			setNextAction(ACTION_COPY_SUBMIT);
			return RESULT_EDIT;
		}catch (Exception ex) {
			DdscApplicationException e = new DdscApplicationException(ex, this.getUserInfo());
			this.addActionError(this.getText("eP.0022", new String[] {e.getMsgCode(), this.getText(e.getMsgCode()), e.getMsgFullMessage()}));
			setNextAction(ACTION_COPY_SUBMIT);
			return SUCCESS;
		}
	}

	@Override
	public String copy() throws Exception {
		try{
			labFilmCorpMst = labFilmCorpMstService.get(labFilmCorpMst.getFmCorpId(), this.getUserInfo());
		}catch (Exception ex) {
			DdscApplicationException e = new DdscApplicationException(ex, this.getUserInfo());
			this.addActionError(this.getText("eP.0022", new String[] {e.getMsgCode(), this.getText(e.getMsgCode()), e.getMsgFullMessage()}));
		}
		setNextAction(ACTION_COPY_SUBMIT);
		return SUCCESS;
	}

	@Override
	public String copySubmit() throws Exception {
		try {
			if (this.hasConfirm() == true) {
				setNextAction(ACTION_COPY_CONFIRM);
				return RESULT_CONFIRM;
			}
			else {
				return this.copyConfirm();
			}
		}catch (DdscApplicationException e) {
			this.addActionError(this.getText("eP.0022", new String[] {e.getMsgCode(), this.getText(e.getMsgCode()), e.getMsgFullMessage()}));
			setNextAction(ACTION_COPY_SUBMIT);
			return RESULT_EDIT;
		}catch (Exception ex) {
			DdscApplicationException e = new DdscApplicationException(ex, this.getUserInfo());
			this.addActionError(this.getText("eP.0022", new String[] {e.getMsgCode(), this.getText(e.getMsgCode()), e.getMsgFullMessage()}));
			setNextAction(ACTION_COPY_SUBMIT);
			return SUCCESS;
		}
	}

	@Override
	public String copyConfirm() throws Exception {
		try{
			labFilmCorpMst = getLabFilmCorpMstService().create(labFilmCorpMst, this.getUserInfo());
			setNextAction(ACTION_COPY);
			return RESULT_SHOW;
		}catch (DdscApplicationException e) {
			// 取得 SQL 錯誤碼，並依多國語系設定顯示於Message box
			this.addActionError(this.getText("eP.0022", new String[] {e.getMsgCode(), this.getText(e.getMsgCode()), e.getMsgFullMessage()}));
			setNextAction(ACTION_COPY_SUBMIT);
			return RESULT_EDIT;
		}catch (Exception ex) {
			DdscApplicationException e = new DdscApplicationException(ex, this.getUserInfo());
			this.addActionError(this.getText("eP.0022", new String[] {e.getMsgCode(), this.getText(e.getMsgCode()), e.getMsgFullMessage()}));
			setNextAction(ACTION_COPY_SUBMIT);
			return RESULT_EDIT;
		}
	}
	
	@Override
	public void validate() {
		try {
			setUpInfo();
		}catch (DdscApplicationException e) {
			// 取得 SQL 錯誤碼，並依多國語系設定顯示於Message box
			this.addActionError(this.getText("eP.0022", new String[] { e.getMsgCode(), this.getText(e.getMsgCode()), e.getMsgFullMessage() }));
		}catch (Exception ex) {
			DdscApplicationException e = new DdscApplicationException(ex, this.getUserInfo());
			this.addActionError(this.getText("eP.0022", new String[] { e.getMsgCode(), this.getText(e.getMsgCode()), e.getMsgFullMessage() }));
		}
	}

	/**
	 * 檢核 - 按送出鈕(新增頁)
	 */
	public void validateCreateSubmit() {
		try {
			this.checkValidateRule();
			this.checkPrimaryKey();
		}catch (DdscApplicationException e) {
			// 取得 SQL 錯誤碼，並依多國語系設定顯示於Message box
			this.addActionError(this.getText("eP.0022", new String[] { e.getMsgCode(), this.getText(e.getMsgCode()), e.getMsgFullMessage() }));
		}catch (Exception ex) {
			DdscApplicationException e = new DdscApplicationException(ex, this.getUserInfo());
			this.addActionError(this.getText("eP.0022", new String[] { e.getMsgCode(), this.getText(e.getMsgCode()), e.getMsgFullMessage() }));
		}
	}

	/**
	 * 檢核 - 按確定鈕(新增頁)
	 */
	public void validateCreateConfirm() {
		try {
			this.checkValidateRule();
			this.checkPrimaryKey();
		}catch (DdscApplicationException e) {
			// 取得 SQL 錯誤碼，並依多國語系設定顯示於Message box
			this.addActionError(this.getText("eP.0022", new String[] { e.getMsgCode(), this.getText(e.getMsgCode()), e.getMsgFullMessage() }));
		}catch (Exception ex) {
			DdscApplicationException e = new DdscApplicationException(ex, this.getUserInfo());
			this.addActionError(this.getText("eP.0022", new String[] { e.getMsgCode(), this.getText(e.getMsgCode()), e.getMsgFullMessage() }));
		}
	}

	/**
	 * 檢核 - 按送出鈕(更新頁)
	 */
	public void validateUpdateSubmit() {
		try {
			this.checkValidateRule();
		}catch (DdscApplicationException e) {
			// 取得 SQL 錯誤碼，並依多國語系設定顯示於Message box
			this.addActionError(this.getText("eP.0022", new String[] { e.getMsgCode(), this.getText(e.getMsgCode()), e.getMsgFullMessage() }));
		}catch (Exception ex) {
			DdscApplicationException e = new DdscApplicationException(ex, this.getUserInfo());
			this.addActionError(this.getText("eP.0022", new String[] { e.getMsgCode(), this.getText(e.getMsgCode()), e.getMsgFullMessage() }));
		}
	}

	/**
	 * 檢核 - 按確定鈕(更新頁)
	 */
	public void validateUpdateConfirm() {
		try {
			this.checkValidateRule();
		}catch (DdscApplicationException e) {
			// 取得 SQL 錯誤碼，並依多國語系設定顯示於Message box
			this.addActionError(this.getText("eP.0022", new String[] { e.getMsgCode(), this.getText(e.getMsgCode()), e.getMsgFullMessage() }));
		}catch (Exception ex) {
			DdscApplicationException e = new DdscApplicationException(ex, this.getUserInfo());
			this.addActionError(this.getText("eP.0022", new String[] { e.getMsgCode(), this.getText(e.getMsgCode()), e.getMsgFullMessage() }));
		}
	}

	/**
	 * 檢核 - 按確定鈕(刪除頁)
	 */
	public void validateDeleteConfirm() {
		try {
			this.checkValidateRule();

		}catch (DdscApplicationException e) {
			// 取得 SQL 錯誤碼，並依多國語系設定顯示於Message box
			this.addActionError(this.getText("eP.0022", new String[] { e.getMsgCode(), this.getText(e.getMsgCode()), e.getMsgFullMessage() }));
		}catch (Exception ex) {
			DdscApplicationException e = new DdscApplicationException(ex, this.getUserInfo());
			this.addActionError(this.getText("eP.0022", new String[] { e.getMsgCode(), this.getText(e.getMsgCode()), e.getMsgFullMessage() }));
		}
	}

	/**
	 * 檢核 - 按送出鈕(複製頁)
	 */
	public void validateCopySubmit() {
		try {
			this.checkValidateRule();
			this.checkPrimaryKey();
		}catch (DdscApplicationException e) {
			// 取得 SQL 錯誤碼，並依多國語系設定顯示於Message box
			this.addActionError(this.getText("eP.0022", new String[] { e.getMsgCode(), this.getText(e.getMsgCode()), e.getMsgFullMessage() }));
		}catch (Exception ex) {
			DdscApplicationException e = new DdscApplicationException(ex, this.getUserInfo());
			this.addActionError(this.getText("eP.0022", new String[] { e.getMsgCode(), this.getText(e.getMsgCode()), e.getMsgFullMessage() }));
		}
	}

	/**
	 * 檢核 - 按確定鈕(複製頁)
	 */
	public void validateCopyConfirm() {
		try {
			this.checkValidateRule();
			this.checkPrimaryKey();
		}
		catch (DdscAuthException e) {
			throw e;
		}
		catch (DdscApplicationException e) {
			// 取得 SQL 錯誤碼，並依多國語系設定顯示於Message box
			this.addActionError(this.getText("eP.0022", new String[] { e.getMsgCode(), this.getText(e.getMsgCode()), e.getMsgFullMessage() }));
		}
		catch (Exception ex) {
			DdscApplicationException e = new DdscApplicationException(ex, this.getUserInfo());
			this.addActionError(this.getText("eP.0022", new String[] { e.getMsgCode(), this.getText(e.getMsgCode()), e.getMsgFullMessage() }));
		}
	}
	
	/**
	 * 檢核ID是否重複
	 *
	 * @return
	 * @throws Exception
	 */
	private boolean checkPrimaryKey() throws Exception {
		boolean isValid = true;
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("fmCorpId",labFilmCorpMst.getFmCorpId());
		if (labFilmCorpMstService.getDataRowCountByConditions(conditions, this.getUserInfo()) > 0) {
			this.addFieldError("fmCorpId", this.getText("fmCorpId") + this.getText("eP.0004"));
			isValid = false;
		}
		return isValid;
	}
	
	/**
	 * 資料檢核
	 *
	 * @return
	 * @throws Exception
	 */
	private boolean checkValidateRule() throws Exception {
		boolean isValid = true;

		// 檢查參數代碼是否存在 (區域)
		if (null != labFilmCorpMst.getFmCtryCde() && StringUtils.isNotEmpty(labFilmCorpMst.getFmCtryCde())) {
			CommOptCde fmCtryCde = getCommOptCdeService().getByKey("N0005", labFilmCorpMst.getFmCtryCde(), this.getUserInfo());
			if (fmCtryCde == null) {
				this.addFieldError("fmCtryCde", this.getText("fmCtryCde")+ this.getText("eP.0003"));
				isValid = false;
			}
		}
		String checkdelete = this.getProgAction();
		if(checkdelete.startsWith("delete")){
			
			List<Map<String,Object>> alist =this.getLabFilmMstService().getFilmListCount(labFilmCorpMst.getFmCorpId(), this.getUserInfo());
			int filmIdCount = (Integer) alist.get(0).get("FILM_ID_COUNT");
			if(filmIdCount>0){
				this.addFieldError("fmCorpId", this.getText("fmCorpId")+ this.getText("exam.e0001"));
				isValid = false;
			}
		}
		
		return isValid;
	}
	
	
	public ILabFilmCorpMstService getLabFilmCorpMstService() {
		return labFilmCorpMstService;
	}

	public void setLabFilmCorpMstService(ILabFilmCorpMstService labFilmCorpMstService) {
		this.labFilmCorpMstService = labFilmCorpMstService;
	}

	public List<Map<String, String>> getLabFilmCorpMstListMap() {
		return labFilmCorpMstListMap;
	}

	public void setLabFilmCorpMstListMap(
			List<Map<String, String>> labFilmCorpMstListMap) {
		this.labFilmCorpMstListMap = labFilmCorpMstListMap;
	}

	public LabFilmCorpMst getLabFilmCorpMst() {
		return labFilmCorpMst;
	}

	public void setLabFilmCorpMst(LabFilmCorpMst labFilmCorpMst) {
		this.labFilmCorpMst = labFilmCorpMst;
	}

	public ICommOptCdeService getCommOptCdeService() {
		return commOptCdeService;
	}

	public void setCommOptCdeService(ICommOptCdeService commOptCdeService) {
		this.commOptCdeService = commOptCdeService;
	}

	public List<CommOptCde> getCorpAreaList() {
		if(corpAreaList == null){
			this.setCorpAreaList(this.getCommOptCdeService().getList("N0005", this.getUserInfo()));
		}
		return corpAreaList;
	}

	public void setCorpAreaList(List<CommOptCde> corpAreaList) {
		this.corpAreaList = corpAreaList;
	}

	public String getCorpArea() {
		return corpArea;
	}

	public void setCorpArea(String corpArea) {
		this.corpArea = corpArea;
	}

	public ILabFilmMstService getLabFilmMstService() {
		return LabFilmMstService;
	}

	public void setLabFilmMstService(ILabFilmMstService labFilmMstService) {
		LabFilmMstService = labFilmMstService;
	}
}
