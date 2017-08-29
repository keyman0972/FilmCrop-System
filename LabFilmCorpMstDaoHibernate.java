package com.ddsc.km.lab.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;

import com.ddsc.core.dao.hibernate.GenericDaoHibernate;
import com.ddsc.core.entity.UserInfo;
import com.ddsc.core.exception.DdscApplicationException;
import com.ddsc.core.util.HibernateScalarHelper;
import com.ddsc.core.util.LocaleDataHelper;
import com.ddsc.core.util.Pager;
import com.ddsc.km.lab.dao.ILabFilmCorpMstDao;
import com.ddsc.km.lab.entity.LabFilmCorpMst;

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

public class LabFilmCorpMstDaoHibernate  extends GenericDaoHibernate<LabFilmCorpMst,String> implements ILabFilmCorpMstDao {

	@Override
	public Pager searchByConditions(Map<String, Object> conditions, Pager pager, UserInfo userInfo) throws DdscApplicationException {
		
		String fmCorpName_lang = LocaleDataHelper.getPropertityWithLocalUpper("FM_CORP_NAME", userInfo.getLocale());
		String optCdeNam_lang = LocaleDataHelper.getPropertityWithLocalUpper("OPT_CDE_NAM", userInfo.getLocale());
		StringBuffer sbsql = new StringBuffer();
		sbsql.append("SELECT LFCM.FM_CORP_ID, LFCM."+fmCorpName_lang+" AS FM_CORP_NAME, ");
		sbsql.append("	LFCM.FM_CORP_ADDRESS, LFCM.FM_CORP_TEL, LFCM.FM_CORP_HOMEPAGE, ");
		sbsql.append("	LFCM.FM_CORP_AREA, LFCM.FM_AREA_ADDR, LFCM.FM_CTRY_CDE, ");
		sbsql.append("	COC."+optCdeNam_lang+" AS OPT_CITY_CDE_NAM, COUNT(LFM.FILM_ID) AS FILM_ID_COUNT ");
		sbsql.append("FROM LAB_FILM_CORP_MST LFCM ");
		sbsql.append("LEFT JOIN LAB_FILM_MST LFM ON LFCM.FM_CORP_ID = LFM.FM_CORP_ID ");
		sbsql.append("LEFT JOIN COMM_OPT_CDE COC ON LFCM.FM_CTRY_CDE = COC.OPT_CDE AND COC.OPT_CTG_CDE = 'N0005' ");
		
		String keyword = " WHERE ";
		List<Object> value = new ArrayList<Object>();
		if (StringUtils.isNotEmpty((String) conditions.get("fmCorpId"))) {
			sbsql.append(keyword + "LFCM.FM_CORP_ID LIKE ?");
			value.add(conditions.get("fmCorpId") + "%");
			keyword = " AND ";
		}
		if (StringUtils.isNotEmpty((String) conditions.get("fmCorpName"))) {
			sbsql.append(keyword + "LFCM."+fmCorpName_lang+" LIKE ?");
			value.add("%"+conditions.get("fmCorpName") + "%");
			keyword = " AND ";
		}
		
		sbsql.append("GROUP BY LFCM.FM_CORP_ID, LFCM."+fmCorpName_lang+", LFCM.FM_CORP_ADDRESS, ");
		sbsql.append("	LFCM.FM_CORP_TEL, LFCM.FM_CORP_HOMEPAGE, LFCM.FM_CORP_AREA, LFCM.FM_CTRY_CDE, ");
		sbsql.append("	LFCM.FM_AREA_ADDR, COC."+optCdeNam_lang+" ");
		sbsql.append("ORDER BY LFCM.FM_CORP_ID");
		
		List<HibernateScalarHelper> scalarList = new ArrayList<HibernateScalarHelper>();
		scalarList.add(new HibernateScalarHelper("FM_CORP_ID", Hibernate.STRING));
		scalarList.add(new HibernateScalarHelper("FM_CORP_NAME", Hibernate.STRING));
		scalarList.add(new HibernateScalarHelper("FM_CORP_ADDRESS", Hibernate.STRING));
		scalarList.add(new HibernateScalarHelper("FM_CORP_TEL", Hibernate.STRING));
		scalarList.add(new HibernateScalarHelper("FM_CORP_HOMEPAGE", Hibernate.STRING));
		scalarList.add(new HibernateScalarHelper("FM_CORP_AREA", Hibernate.STRING));
		scalarList.add(new HibernateScalarHelper("FM_CTRY_CDE", Hibernate.STRING));
		scalarList.add(new HibernateScalarHelper("FM_AREA_ADDR", Hibernate.STRING));
		scalarList.add(new HibernateScalarHelper("OPT_CITY_CDE_NAM", Hibernate.STRING));
		scalarList.add(new HibernateScalarHelper("FILM_ID_COUNT", Hibernate.STRING));
		
		return super.findBySQLQueryMapPagination(sbsql.toString(), pager, scalarList, value, userInfo);
	}
}
