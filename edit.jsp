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
<script type="text/javascript" src="<s:url value="/ddscPlugin/ddsc.gridEditList.plugin.js"/>"></script>
<script type="text/javascript" src="<s:url value="/ddscPlugin/ddsc.validation.plugin.js"/>"></script>
<script type="text/javascript" src="<s:url value="/jquery/jquery.alphanumeric.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/ddsc.input.js"/>"></script>
<script language="javascript">
var oTable;
//畫面欄位檢核
function validate() {
	$("#frmlab0A001K").validate("clearPrompt"); 
	
	$("#fmCorpId").validateRequired({fieldText:'<s:text name="fmCorpId" />'});
	$("#fmCorpName").validateRequired({fieldText:'<s:text name="fmCorpName" />'});
	$("#fmCorpArea0").validateRequired({fieldText:'<s:text name="fmCorpArea0" />'});
	
	var fmCorpHomePage = $("#fmCorpHomepage").val();
	if(fmCorpHomePage != ""){
		if(!fmCorpHomePage.startsWith("http://")){
			$("#fmCorpHomepage").validate("sendPrompt", {message:'<s:text name="eC.0013"><s:param value="getText(\"fmCorpHomepage\")"/></s:text>'});
		}
	}
	if($("#fmCorpArea1").prop("checked")){
		$("#fmCtryCde").validateRequired({fieldText:'<s:text name="fmCtryCde" />'});
		$("#fmAreaAddr").validateRequired({fieldText:'<s:text name="fmAreaAddr" />'});
	}
	<%--
	 --%>
    return $("#frmlab0A001K").validate("showPromptWithErrors");
}
$(document).ready(function(){
	oTable = $('#tblGrid').initEditGrid({height:'250'});
	$("#fmCorpArea0").bind("click", function(){
		$("#trArea").hide();
	});
	$("#fmCorpArea1").bind("click", function(){
		$("#trArea").show();
	});
	
});
</script>
</head>
<body>
<s:form id="frmlab0A001K" method="post" theme="simple" action="%{progAction}" target="ifrConfirm">
<s:hidden name="labFilmCorpMst.ver" />
 	<div class="progTitle"> 
		<s:include value="/WEB-INF/pages/include/include.EditTitle.jsp" />
    </div>
    <div id="tb">
    <table width="100%" border="0" cellpadding="4" cellspacing="0" >
		<tr class="trBgOdd">
			<td width="20%" class="colNameAlign required">*<s:text name="fmCorpId" />：</td>
			<td width="30%">
				<s:textfield id="fmCorpId" name="labFilmCorpMst.fmCorpId" cssClass="enKey" readonly="progAction == 'updateSubmit'" maxlength="16" size="16" />
			</td>
			<td width="20%" class="colNameAlign required">*<s:text name="fmCorpName" />：</td>
			<td width="30%">
				<s:textfield id="fmCorpName" name="labFilmCorpMst.fmCorpName" maxlength="64" size="32" />
			</td>
			</tr>
			<tr class="trBgEven">
				<td width="20%" class="colNameAlign">&nbsp;<s:text name="fmCorpTel" />：</td>
				<td width="30%">
					<s:textfield name="labFilmCorpMst.fmCorpTel" maxlength="20" size="15" cssClass="numKey" />
				</td>
				<td width="20%" class="colNameAlign required">*<s:text name="fmCorpArea" />：</td>
				<td width="30%">
					<input type="radio" id="fmCorpArea0" name="labFilmCorpMst.fmCorpArea" value="0" class="fmCorpArea" <s:if test='labFilmCorpMst.fmCorpArea == "0"'>checked</s:if> /><s:label>境內</s:label>
					<input type="radio" id="fmCorpArea1" name="labFilmCorpMst.fmCorpArea" value="1" class="fmCorpArea" <s:if test='labFilmCorpMst == null || labFilmCorpMst.fmCorpArea == "1"'>checked</s:if> /><s:label>境外</s:label>
				</td>
			</tr>
			<tr class="trBgOdd">
				<td width="20%" class="colNameAlign">&nbsp;<s:text name="fmCorpAddr" />：</td>
				<td colspan="3">
					<s:textfield name="labFilmCorpMst.fmCorpAddress" maxlength="256" size="80" />
				</td>
			</tr>
			<tr class="trBgEven">
				<td width="20%" class="colNameAlign">&nbsp;<s:text name="fmCorpHomepage" />：</td>
				<td colspan="3">
					<s:textfield id="fmCorpHomepage" name="labFilmCorpMst.fmCorpHomePage" maxlength="128" size="80" />
				</td>
			</tr>
			<tr id="trArea" class="trBgOdd <s:if test='labFilmCorpMst.fmCorpArea == "0"'>ctrlHide</s:if>">
				<td width="20%" class="colNameAlign required">*<s:text name="fmAreaAddr" />：</td>
				<td colspan="3">
					<s:select id="fmCtryCde" name="labFilmCorpMst.fmCtryCde" headerValue="%{getText('fix.00162')}" headerKey="" 
 					list="corpAreaList" listKey="optCde" listValue="optCde + '-' + optCdeNam" value="labFilmCorpMst.fmCtryCde" />
					<s:textfield id="fmAreaAddr" name="labFilmCorpMst.fmAreaAddr" maxlength="256" size="50" />
				</td>
			</tr>
			
	</table>
    </div>
	<!-- 按鍵組合 --> 
	<s:include value="/WEB-INF/pages/include/include.EditButton.jsp" />
	<!-- 按鍵組合 -->
</s:form>
<iframe id="ifrConfirm" name="ifrConfirm" width="100%" height="768" frameborder="0" marginwidth="0" marginheight="0" scrolling="no" style="display:none; border: 0px none"></iframe>
</body>
</html>