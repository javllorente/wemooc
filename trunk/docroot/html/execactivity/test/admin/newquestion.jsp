<%@page import="com.liferay.util.JavaScriptUtil"%>

<%@ include file="/html/execactivity/test/admin/init.jsp" %>

<portlet:actionURL var="addquestionURL" name="addquestion" />
<portlet:renderURL var="backToQuestionsURL">
	<portlet:param name="jspPage" value="/html/execactivity/test/admin/edit.jsp"></portlet:param>
	<portlet:param name="actId" value="<%=Long.toString(learnact.getActId()) %>" />
</portlet:renderURL>

<aui:form name="qfm" action="<%=addquestionURL%>"  method="post">
	<aui:input name="actId" type="hidden" value="<%= learnact.getActId()%>"></aui:input>
	<aui:field-wrapper label="enunciation">
		<liferay-ui:input-editor name="DescripcionRichTxt"  initMethod="initEditor" width="80%" />
		<div id="<portlet:namespace/>DescripcionRichTxt"></div>
		<aui:input name="text" type="hidden" />
		<script type="text/javascript">
	    <!--

			function <portlet:namespace />initEditor() {
				return "";
			}
	
		    function <portlet:namespace />extractCodeFromEditor()
		    {
				try {
					document.<portlet:namespace />qfm['<portlet:namespace />text'].value = window['<portlet:namespace />DescripcionRichTxt'].getHTML();
				}
				catch (e) {
				}
		    	
		    }

	        //-->
	    </script>
	</aui:field-wrapper>
	<aui:select name="typeId" label="qtype">
		<aui:option value="0" label="options"></aui:option>
	</aui:select>
	<aui:button-row>
<% 
	String extractCodeFromEditor = renderResponse.getNamespace() + "extractCodeFromEditor()";
%>		
		<aui:button type="submit" onClick="<%=extractCodeFromEditor%>">Submit</aui:button>
		<aui:button onclick="<%= backToQuestionsURL.toString() %>" type="cancel" />
	</aui:button-row>
</aui:form>