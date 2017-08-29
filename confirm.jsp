<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/include/include.Taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<base target="_self" />
<s:include value="/WEB-INF/pages/include/include.Scripts.jsp" />
<script type="text/javascript" src="<s:url value="/jquery/ui/jquery.ui.datepicker.min.js"/>"></script>
<script type="text/javascript" src="<s:url value="/ddscPlugin/ddsc.gridList.plugin.js"/>"></script>
<script language="javascript">
$(document).ready(function() {
    $("#tblGrid").initGrid({height:'480'});   
});
</script>
</head>
<body>
<s:form method="post" theme="simple" action="%{progAction}">
	<s:hidden name="labFilmCorpMst.ver" />
	<div class="progTitle"> 
       <!-- 程式標題 --> <s:include value="/WEB-INF/pages/include/include.ConfirmTitle.jsp" /> <!-- 程式標題 -->
    </div>
    <div id="tb">
    <table width="100%" border="0" cellpadding="4" cellspacing="0" >
			<tr class="trBgOdd">
				<td width="20%" class="colNameAlign required">*<s:text name="fmCorpId" />：</td>
				<td width="30%">
					<s:property  value="labFilmCorpMst.fmCorpId" />
					<s:hidden name="labFilmCorpMst.fmCorpId"/></td>
				<td width="20%" class="colNameAlign required">*<s:text name="fmCorpName" />：</td>
				<td width="30%">
					<s:property value="labFilmCorpMst.fmCorpName" />
					<s:hidden name="labFilmCorpMst.fmCorpName"/></td>
			</tr>
			<tr class="trBgEven">
				<td width="20%" class="colNameAlign">&nbsp;<s:text name="fmCorpTel" />：</td>
				<td width="30%">
					<s:property  value="labFilmCorpMst.fmCorpTel" />
					<s:hidden name="labFilmCorpMst.fmCorpTel"/>
				</td>
				<td width="20%" class="colNameAlign required">*<s:text name="fmCorpArea" />：</td>
				<td width="30%">
					<input type="radio" id="fmCorpArea0" name="labFilmCorpMst.fmCorpArea" value="0" disabled
					<s:if test='labFilmCorpMst.fmCorpArea == "0"'>checked</s:if> /><s:label for="fmCorpArea0">境內</s:label>
					<input type="radio" id="fmCorpArea1" name="labFilmCorpMst.fmCorpArea" value="1" disabled
					<s:if test='labFilmCorpMst.fmCorpArea == "1"'>checked</s:if> /><s:label for="fmCorpArea1">境外</s:label>
					<s:hidden name="labFilmCorpMst.fmCorpArea" value="%{labFilmCorpMst.fmCorpArea}" />
				</td>
			</tr>
			<tr class="trBgOdd">
				<td width="20%" class="colNameAlign">&nbsp;<s:text name="fmCorpAddr" />：</td>
				<td colspan="3">
					<s:property value="labFilmCorpMst.fmCorpAddress"/>
					<s:hidden name="labFilmCorpMst.fmCorpAddress" value="%{labFilmCorpMst.fmCorpAddress}" />
				</td>
			</tr>
			<tr class="trBgEven">
				<td width="20%" class="colNameAlign">&nbsp;<s:text name="fmCorpHomepage" />：</td>
				<td colspan="3">
					<s:property value="labFilmCorpMst.fmCorpHomePage"/>
					<s:hidden name="labFilmCorpMst.fmCorpHomePage" value="%{labFilmCorpMst.fmCorpHomePage}" />
				</td>
			</tr>
			<tr class="trBgOdd <s:if test='labFilmCorpMst.fmCorpArea == "0"'>ctrlHide</s:if>" >
				<td width="20%" class="colNameAlign required">*<s:text name="fmAreaAddr" />：</td>
				<td colspan="3">
					<s:property value="labFilmCorpMst.fmCtryCde"/>&nbsp;
					<s:property value="labFilmCorpMst.fmAreaAddr"/>
					<s:hidden name="labFilmCorpMst.fmCtryCde" value="%{labFilmCorpMst.fmCtryCde}" />
					<s:hidden name="labFilmCorpMst.fmAreaAddr" value="%{labFilmCorpMst.fmAreaAddr}" />
				</td>
			</tr>
		</table>
    </div>
    <!-- 按鍵組合 -->
        <s:include value="/WEB-INF/pages/include/include.ConfirmButton.jsp" /> 
    <!-- 按鍵組合 -->
</s:form>
</body>
</html>