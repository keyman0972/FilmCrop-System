<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/pages/include/include.Taglib.jsp"%>
<html>
<head>
<title></title>
<s:include value="/WEB-INF/pages/include/include.Scripts.jsp" />
<script type="text/javascript" src="<s:url value="/jquery/ui/jquery.ui.datepicker.min.js"/>"></script>
<script type="text/javascript" src="<s:url value="/jquery/jquery.alphanumeric.js"/>"></script>
<script type="text/javascript" src="<s:url value="/ddscPlugin/ddsc.gridList.plugin.js"/>"></script>
<script type="text/javascript" src="<s:url value="/ddscPlugin/ddsc.popupWindow.plugin.js"/>"></script>	
<script type="text/javascript" src="<s:url value="/js/ddsc.input.js"/>"></script>
<script type="text/javascript" src="<s:url value="/ddscPlugin/ddsc.validation.plugin.js"/>"></script>
<script type="text/javascript">
function getParameter(actType) {
	<%--
	if(actType.startsWith("delete")){
		var filmIdCount = $("#tblGrid").getSelectedRow().find('td').eq(7).find('#filmIdCount').text().toNumber();
		if(filmIdCount !=0){
			alert('<s:text name="exam.e0001"><s:param value="getText(\"filmId\")"/></s:text>');
			return;
		}
	}
	 --%>
	var param = "labFilmCorpMst.fmCorpId=" + $("#tblGrid").getSelectedRow().find('td').eq(7).find('#fmCorpId').text();
	return param;
}
$(document).ready(function() {
	$("#tblGrid").initGrid({lines:3});
	$('#tb').initPopupWindow({dailogWidth:'960', dailogHeight:'640'});

});
</script>
</head>
<body> 
<s:form id="frmlab0A001K" theme="simple" action="%{progAction}" >
	<div class="progTitle">
  		<s:include value="/WEB-INF/pages/include/include.Title.jsp" />
	</div>
	<div id="tb">
		<fieldset id="listFieldset">
		<table width="100%" border="0" cellpadding="2" cellspacing="0">
			<tr class="trBgOdd">
				<td width="20%" class="colNameAlign">&nbsp;<s:text name="fmCorpId"/>：</td>
				<td width="30%">
					<s:textfield name="labFilmCorpMst.fmCorpId" cssClass="enKey condition validate" maxlength="16" size="16" />
				</td>
				<td width="20%" class="colNameAlign">&nbsp;<s:text name="fmCorpName"/>：</td>
				<td width="30%">
					<s:textfield name="labFilmCorpMst.fmCorpName" cssClass="condition validate" maxlength="64" size="32"/>
				</td>
			</tr>
		</table>
		<!-- 按鍵組合 --><s:include value="/WEB-INF/pages/include/include.ListButton.jsp" /><!-- 按鍵組合 --> 
		</fieldset>
		<table id="tblGrid" class ="labDcMstListMap" width="100%" border="0" cellpadding="2" cellspacing="1">
			<thead>
				<tr align="center" bgcolor="#e3e3e3">
					<th width="3%"><s:text name="fix.00164" /></th>
					<th width="10%"><s:text name="fix.00090" /></th>
					<th width="20%"><s:text name="fmCorpName" /></th>   
					<th width="22%"><s:text name="fmCorpArea" /></th>
					<th width="18%"><s:text name="fmCorpAddr" /></th>
					<th width="18%"><s:text name="fmCorpTel" /></th>
					<th><s:text name="fmCorpHomepage" /></th>
					<th class="ctrlHide">&nbsp;</th>
				</tr>
			 </thead>
			 <tbody>
				 <s:iterator value="labFilmCorpMstListMap" status="status">
				 	<tr>
						<td width="3%" id="sn" align="center"><s:property value="#status.index+1" /></td>
						<!-- 表單按鍵 --> 
						<td width="10%"><s:include value="/WEB-INF/pages/include/include.actionButton.jsp" /></td>
						<!-- 表單按鍵 -->
						<td width="20%"><label><s:property value="FM_CORP_ID" />&nbsp;-&nbsp;<s:property value="FM_CORP_NAME" /></label></td>
						<td width="22%">
							<label>
								<s:if test="FM_CORP_AREA == \"0\"">
									&nbsp;<s:text name="acnoUseCtg.1" />
								</s:if>
								<s:if test="FM_CORP_AREA == \"1\"">
									&nbsp;<s:text name="acnoUseCtg.2" />&nbsp;(&nbsp;<s:property value="OPT_CITY_CDE_NAM" />&nbsp;)&nbsp;
								</s:if>
							</label>
						</td>
						<td width="18%">
							<label>
								<s:if test="FM_CORP_AREA == \"0\"">
									<s:property value="FM_AREA_ADDR" />
								</s:if>
								<s:if test="FM_CORP_AREA == \"1\"">
									<s:property value="FM_CORP_ADDRESS" />
								</s:if>
							</label>
						</td>	
						<td width="18%"><label><s:property value="FM_CORP_TEL" /></label></td>
						<td><label><s:property value="FM_CORP_HOMEPAGE" /></label></td>
						<td class="ctrlHide">
				 			<label id="fmCorpId"><s:property value="FM_CORP_ID" /></label>
				 			<label id="filmIdCount"><s:property value="FILM_ID_COUNT" /></label>
				 		</td>
					</tr>
				 </s:iterator>
			 </tbody>
		</table>
	</div>
	<!-- 分頁按鍵列 --><s:include value="/WEB-INF/pages/include/include.PaginationBar.jsp" /><!-- 分頁按鍵列 -->
</s:form>
</body>
</html>