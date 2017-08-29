package com.ddsc.km.lab.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NamedQuery;

import com.ddsc.core.entity.BaseEntity;
import com.ddsc.core.util.LocaleDataHelper;

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

@Entity
@Table(name= "LAB_FILM_CORP_MST")
@NamedQuery(name="findLabfmCorpMstkey", query="select lfcm.fmCorpId, lfcm.fmCorpName_lang1 as name from LabFilmCorpMst lfcm where lfcm.fmCorpId=:fmCorpId")
public class LabFilmCorpMst extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 2598131370182669998L;
	
	private String fmCorpId;
	private String fmCorpName;
	private String fmCorpName_lang1;
	private String fmCorpName_lang2;
	private String fmCorpName_lang3;
	private String fmCorpAddress;
	private String fmCorpTel;
	private String fmCorpHomePage;
	private String fmCorpArea;
	private String fmCtryCde;
	private String fmAreaAddr;
	//總筆數
	private String filmIdCount;
	
	
	@Id
	@Column(name="FM_CORP_ID")
	public String getFmCorpId() {
		return fmCorpId;
	}
	
	public void setFmCorpId(String fmCorpId) {
		this.fmCorpId = fmCorpId;
	}
	
	@Transient
	public String getFmCorpName() {
		if(null != this.fmCorpName && this.fmCorpName.length()==0){
			return null;
		}else{
			return fmCorpName;			
		}
	}
	
	public void setFmCorpName(String fmCorpName) {
		this.fmCorpName = fmCorpName;
	}
	
	@Column(name="FM_CORP_NAME_LANG1")
	public String getFmCorpName_lang1() {
		if (LocaleDataHelper.equalToLocale(LocaleDataHelper.LANGUAGE_1)) {
			return this.getFmCorpName();
		}else{
			return fmCorpName_lang1;			
		}
	}
	
	public void setFmCorpName_lang1(String fmCorpName_lang1) {
		this.fmCorpName_lang1 = fmCorpName_lang1;
		if (LocaleDataHelper.equalToLocale(LocaleDataHelper.LANGUAGE_1)) {
			this.fmCorpName = fmCorpName_lang1;
		}
	}
	
	@Column(name="FM_CORP_NAME_LANG2")
	public String getFmCorpName_lang2() {
		if (LocaleDataHelper.equalToLocale(LocaleDataHelper.LANGUAGE_2)) {
			return this.getFmCorpName();
		}else{
			return fmCorpName_lang2;			
		}
	}
	
	public void setFmCorpName_lang2(String fmCorpName_lang2) {
		this.fmCorpName_lang2 = fmCorpName_lang2;
		if (LocaleDataHelper.equalToLocale(LocaleDataHelper.LANGUAGE_2)) {
			this.fmCorpName = fmCorpName_lang2;
		}
	}
	
	@Column(name="FM_CORP_NAME_LANG3")
	public String getFmCorpName_lang3() {
		if (LocaleDataHelper.equalToLocale(LocaleDataHelper.LANGUAGE_3)) {
			return this.getFmCorpName();
		}else{
			return fmCorpName_lang3;			
		}
	}
	
	public void setFmCorpName_lang3(String fmCorpName_lang3) {
		this.fmCorpName_lang3 = fmCorpName_lang3;
		if (LocaleDataHelper.equalToLocale(LocaleDataHelper.LANGUAGE_3)) {
			this.fmCorpName = fmCorpName_lang3;
		}
	}
	
	@Column(name="FM_CORP_ADDRESS")
	public String getFmCorpAddress() {
		return fmCorpAddress;
	}
	public void setFmCorpAddress(String fmCorpAddress) {
		this.fmCorpAddress = fmCorpAddress;
	}
	
	@Column(name="FM_CORP_TEL")
	public String getFmCorpTel() {
		return fmCorpTel;
	}
	
	public void setFmCorpTel(String fmCorpTel) {
		this.fmCorpTel = fmCorpTel;
	}
	
	@Column(name="FM_CORP_HOMEPAGE")
	public String getFmCorpHomePage() {
		return fmCorpHomePage;
	}
	
	public void setFmCorpHomePage(String fmCorpHomePage) {
		this.fmCorpHomePage = fmCorpHomePage;
	}
	
	@Column(name="FM_CORP_AREA")
	public String getFmCorpArea() {
		return fmCorpArea;
	}
	
	public void setFmCorpArea(String fmCorpArea) {
		this.fmCorpArea = fmCorpArea;
	}
	
	@Column(name="FM_CTRY_CDE")
	public String getFmCtryCde() {
		return fmCtryCde;
	}
	
	public void setFmCtryCde(String fmCtryCde) {
		this.fmCtryCde = fmCtryCde;
	}
	
	@Column(name="FM_AREA_ADDR")
	public String getFmAreaAddr() {
		return fmAreaAddr;
	}
	
	public void setFmAreaAddr(String fmAreaAddr) {
		this.fmAreaAddr = fmAreaAddr;
	}
	
	@Transient
	public String getFilmIdCount() {
		return filmIdCount;
	}

	public void setFilmIdCount(String filmIdCount) {
		this.filmIdCount = filmIdCount;
	}
	
}
