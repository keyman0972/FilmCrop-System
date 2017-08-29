package com.ddsc.km.lab.service.impl;

import java.util.List;
import java.util.Map;

import com.ddsc.core.entity.UserInfo;
import com.ddsc.core.exception.DdscApplicationException;
import com.ddsc.core.util.Pager;
import com.ddsc.km.lab.dao.ILabFilmCorpMstDao;
import com.ddsc.km.lab.entity.LabFilmCorpMst;
import com.ddsc.km.lab.service.ILabFilmCorpMstService;

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

public class LabFilmCorpMstServiceImpl implements ILabFilmCorpMstService {
	
	private ILabFilmCorpMstDao labFilmCorpMstDao;
	
	@Override
	public LabFilmCorpMst create(LabFilmCorpMst entity, UserInfo info) throws DdscApplicationException {
		try{
			labFilmCorpMstDao.save(entity, info);
			return entity;
		}catch (DdscApplicationException e) {
			throw e;
		}catch (Exception e) {
			throw new DdscApplicationException(e, info);
		}
	}

	@Override
	public LabFilmCorpMst update(LabFilmCorpMst entity, UserInfo info) throws DdscApplicationException {
		try{
			return labFilmCorpMstDao.update(entity, info);
		}catch (DdscApplicationException e) {
			throw e;
		}catch (Exception e) {
			throw new DdscApplicationException(e, info);
		}
	}

	@Override
	public LabFilmCorpMst delete(LabFilmCorpMst entity, UserInfo info) throws DdscApplicationException {
		try{
			labFilmCorpMstDao.delete(entity, info);
			return entity;
		}catch (DdscApplicationException e) {
			throw e;
		}catch (Exception e) {
			throw new DdscApplicationException(e, info);
		}
	}
	
	@Override
	public LabFilmCorpMst get(String id, UserInfo info) throws DdscApplicationException {
		try{
			return labFilmCorpMstDao.get(id, info);
		}catch (DdscApplicationException e) {
			throw e;
		}catch (Exception e) {
			throw new DdscApplicationException(e, info);
		}
	}
	
	@Deprecated
	@Override
	public List<LabFilmCorpMst> searchAll(UserInfo info) throws DdscApplicationException {
		return null;
	}

	@Override
	public Pager searchByConditions(Map<String, Object> conditions, Pager pager, UserInfo userInfo) throws DdscApplicationException {
		try{
			return labFilmCorpMstDao.searchByConditions(conditions, pager, userInfo);
		}catch (DdscApplicationException e) {
			throw e;
		}catch (Exception e) {
			throw new DdscApplicationException(e, userInfo);
		}
	}
	
	@Deprecated
	@Override
	public List<LabFilmCorpMst> searchByConditions(Map<String, Object> conditions, UserInfo userInfo) throws DdscApplicationException {
		return null;
	}
	
	@Deprecated
	@Override
	public List<Object> queryDataByParamsByService(Map<String, Object> conditions, UserInfo userInfo) throws DdscApplicationException {
		return null;
	}

	@Override
	public int getDataRowCountByConditions(Map<String, Object> conditions, UserInfo info) throws DdscApplicationException {
		try{
			return this.labFilmCorpMstDao.getDataRowCountByConditions(conditions, info);			
		}catch (DdscApplicationException e) {
			throw e;
		}catch (Exception e) {
			throw new DdscApplicationException(e, info);
		}
	}
	
	public ILabFilmCorpMstDao getLabFilmCorpMstDao() {
		return labFilmCorpMstDao;
	}

	public void setLabFilmCorpMstDao(ILabFilmCorpMstDao labFilmCorpMstDao) {
		this.labFilmCorpMstDao = labFilmCorpMstDao;
	}

}
