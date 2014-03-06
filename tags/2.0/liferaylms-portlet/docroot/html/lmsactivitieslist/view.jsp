<%@page import="com.liferay.portal.kernel.util.PropsUtil"%>
<%@page import="com.liferay.portal.kernel.servlet.SessionErrors"%>
<%@page import="com.liferay.portal.kernel.util.HttpUtil"%>
<%@page import="com.liferay.lms.learningactivity.LearningActivityTypeRegistry"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayPortletRequest"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayPortletResponse"%>
<%@page import="com.liferay.lms.service.LearningActivityLocalServiceUtil"%>
<%@page import="com.liferay.lms.model.LearningActivity"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayPortletURL"%>
<%@page import="com.liferay.portal.kernel.repository.model.FileEntry"%>
<%@page import="com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil"%>
<%@page import="com.liferay.lms.model.ModuleResult"%>
<%@page import="com.liferay.lms.service.CourseLocalServiceUtil"%>
<%@page import="com.liferay.lms.model.Course"%>
<%@page import="com.liferay.lms.service.ModuleResultLocalServiceUtil"%>
<%@page import="com.liferay.lms.service.LearningActivityResultLocalServiceUtil"%>
<%@page import="com.liferay.portal.service.ImageLocalServiceUtil"%>
<%@page import="com.liferay.portlet.asset.model.AssetRenderer"%>
<%@page import="com.liferay.portlet.asset.AssetRendererFactoryRegistryUtil"%>
<%@page import="com.liferay.portlet.asset.model.AssetRendererFactory"%>
<%@page import="com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil"%>
<%@page import="com.liferay.portlet.asset.model.AssetEntry"%>
<%@page import="com.liferay.lms.service.ModuleLocalServiceUtil"%>
<%@page import="com.liferay.lms.model.Module"%>
<%@page import="com.liferay.portal.security.permission.ResourceActionsUtil"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>

<%@ include file="/init.jsp"%>

<%if (SessionErrors.contains(renderRequest, "activities-in-module")) { %>
	<script type="text/javascript">
		<!--
			AUI().ready(function(A) {
				alert('<%=LanguageUtil.get(pageContext," module.delete.module.with.activity")%>');
			});
		//-->
	</script>
<% } %>
<%
long moduleId = ParamUtil.getLong(request, "moduleId", 0);
boolean actionEditing = ParamUtil.getBoolean(request, "actionEditing", false);
long actId = ParamUtil.getLong(request, "actId", 0);
if (moduleId == 0){
	java.util.List<Module> modules = ModuleLocalServiceUtil.findAllInGroup(themeDisplay.getScopeGroupId());
	if (modules.size() > 0) {
		Module theModule = modules.get(0);
		moduleId = theModule.getModuleId();
	}
}

String currentActivityPortletId =  null;
if(actId!=0) {				
	LearningActivity currentLeaningActivity = LearningActivityLocalServiceUtil.getLearningActivity(actId);
    currentActivityPortletId = HttpUtil.getParameter(new LearningActivityTypeRegistry().getLearningActivityType(currentLeaningActivity.getTypeId()).getAssetRenderer(currentLeaningActivity).
					getURLViewInContext((LiferayPortletRequest) renderRequest, (LiferayPortletResponse) renderResponse,StringPool.BLANK), "p_p_id",false);
}

%>
<script type="text/javascript">
<!--

AUI().ready('event', 'node','aui-base','aui-dialog','aui-dialog-iframe','anim','json',function(A) {
	
	A.one(window).on('message', 
		function(event){

			var html5Event=event._event;

			if(A.Lang.isString(html5Event.data)){
				html5Event={data:JSON.parse(html5Event.data)};
			}
	
			if(html5Event.data.name=='reloadModule'){
				<% if(moduleId!=0){ %>
				if(html5Event.data.moduleId==<%=Long.toString(moduleId)%>)
				<% } %>
				{
					var moduleTitlePortlet=A.one('#p_p_id<%=StringPool.UNDERLINE+PortalUtil.getJsSafePortletId("ModuleTitle"+
							PortletConstants.WAR_SEPARATOR+portletConfig.getPortletContext().getPortletContextName())+StringPool.UNDERLINE %>');
					if(moduleTitlePortlet!=null) {
						Liferay.Portlet.refresh(moduleTitlePortlet);
					}

					var moduleDescriptionPortlet=A.one('#p_p_id<%=PortalUtil.getJsSafePortletId(StringPool.UNDERLINE+"moduleDescription"+
							PortletConstants.WAR_SEPARATOR+portletConfig.getPortletContext().getPortletContextName())+StringPool.UNDERLINE %>');
					if(moduleDescriptionPortlet!=null) {
						Liferay.Portlet.refresh(moduleDescriptionPortlet);
					}

					var activityNavigatorPortlet=A.one('#p_p_id<%=PortalUtil.getJsSafePortletId(StringPool.UNDERLINE+"activityNavigator"+
							PortletConstants.WAR_SEPARATOR+portletConfig.getPortletContext().getPortletContextName())+StringPool.UNDERLINE %>');
					if(activityNavigatorPortlet!=null) {
						Liferay.Portlet.refresh(activityNavigatorPortlet);
					}
				}

				Liferay.Portlet.refresh(A.one('#p_p_id<portlet:namespace />'));	
  
			}
			else if(html5Event.data.name=='closeModule'){
				A.DialogManager.closeByChild('#editModule');
			} 
			else if(html5Event.data.name=='closeActivity'){
				A.DialogManager.closeByChild('#editlesson');
			}
			else if(html5Event.data.name=='setTitleActivity'){
				A.DialogManager.findByChild('#editlesson').set('title',html5Event.data.title);
			}
			else if((html5Event.data.name=='resizeWidthActivity')&&(!!html5Event.data.width)){
				var editLessonDiv = A.one('#editlesson');
				if((editLessonDiv!=null)&&(editLessonDiv.one('iframe.aui-resizeiframe-monitored-height')!=null)) {
					editLessonDiv.setStyle('width',html5Event.data.width);
				}
			}

		});

});

//-->
</script>
<div class="modulo portlet-toolbar search-form lms-tree">
	<ul>
		<%
		renderRequest.setAttribute("moduleId", Long.toString(moduleId));
		boolean registrado=UserLocalServiceUtil.hasGroupUser(themeDisplay.getScopeGroupId(),themeDisplay.getUserId());
		Course course=CourseLocalServiceUtil.fetchByGroupCreatedId(themeDisplay.getScopeGroupId());
		if(course!=null && permissionChecker.hasPermission(course.getGroupCreatedId(),  Course.class.getName(),course.getCourseId(),ActionKeys.VIEW) && !course.isClosed()){
			java.util.List<Module> theModules=ModuleLocalServiceUtil.findAllInGroup(themeDisplay.getScopeGroupId());
			Date today=new java.util.Date(System.currentTimeMillis());
			long currentThemeId=0;
			for(Module theModule:theModules){
				currentThemeId++;
				if(moduleId == theModule.getModuleId()) 
					break;
			}
			
			long modulesCount=theModules.size();
			long themeId=0;
			for(Module theModule:theModules){
				themeId++;
				%>
				
				<%--li class='<%= moduleId == theModule.getModuleId() ? "option-less" : "option-more"%>' --%>
				<li class='option-none  <%=theModule.getModuleId() == moduleId ? "option-less":"" %>'>
					<%
					if(theModule.getModuleId() == moduleId)
					{
						%>
						<span class="desplegar"></span>
						<%
					}
					%>
										
					<%
					ModuleResult moduleResult=ModuleResultLocalServiceUtil.getByModuleAndUser(theModule.getModuleId(),themeDisplay.getUserId());
					long done=0;
					if(moduleResult!=null){
						done=moduleResult.getResult();
					}
					java.util.Date now=new java.util.Date(System.currentTimeMillis());
					if(!ModuleLocalServiceUtil.isLocked(theModule.getModuleId(),themeDisplay.getUserId())||actionEditing||
							permissionChecker.hasPermission(themeDisplay.getScopeGroupId(), "com.liferay.lms.model", themeDisplay.getScopeGroupId() , "ACCESSLOCK")){
						LiferayPortletURL  gotoModuleURL = (LiferayPortletURL)renderResponse.createActionURL();	
						gotoModuleURL.setParameter(ActionRequest.ACTION_NAME, "goToModule");
					    gotoModuleURL.removePublicRenderParameter("actId");
					    gotoModuleURL.setWindowState(WindowState.NORMAL);
					    gotoModuleURL.setParameter("moduleId", Long.toString(theModule.getModuleId()));
					    gotoModuleURL.setParameter("themeId", Long.toString(themeId));
					    gotoModuleURL.setPlid(themeDisplay.getPlid());
						%>
						<a href="<%=gotoModuleURL.toString() %>">
						<%
					if(theModule.getModuleId() != moduleId)
					{
						if(ModuleLocalServiceUtil.isLocked(theModule.getModuleId(),themeDisplay.getUserId()))
						{
							%>
							<span class="locked"></span>
							<%
						}
						else
						{
							
						
						%>
						<span class="desplegar"></span>
						<%
						}
					}
					%>
						<%=(GetterUtil.getBoolean(PropsUtil.get("module.show.number"),true))?
								LanguageUtil.format(pageContext, "moduleTitle.chapter", new Object[]{themeId,theModule.getTitle(themeDisplay.getLocale())}):
									theModule.getTitle(themeDisplay.getLocale()) %></a>
						<%if(actionEditing){%>
							<div class="iconsedit"><%@ include file="/JSPs/module/edit_actions.jspf" %></div>
						<%}
					}else
					{%>
						<span class="locked"></span><%=(GetterUtil.getBoolean(PropsUtil.get("module.show.number"),true))?
															LanguageUtil.format(pageContext, "moduleTitle.chapter", new Object[]{themeId,theModule.getTitle(themeDisplay.getLocale())}):
																theModule.getTitle(themeDisplay.getLocale())  %> <span class="module-percent"><%=done %>%</span>
						<%if(actionEditing){}
					}
					if((theModule.getModuleId()==moduleId)&&(ParamUtil.getBoolean(renderRequest, "viewCurrentModule",true))){%>
						<div class="lms-desplegable" style="overflow: hidden;" >
							<jsp:include page="/html/lmsactivitieslist/viewactivities.jsp"></jsp:include>
						</div>
					<%}	%>		
				</li>
			<%}%>
			</ul>
			</div>
		<%}else{
			if(course==null){%>
				<jsp:include page="/html/lmsactivitieslist/viewactivities.jsp"></jsp:include>
			<%}else{
				renderRequest.setAttribute(WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, Boolean.FALSE);
			}
		}%>