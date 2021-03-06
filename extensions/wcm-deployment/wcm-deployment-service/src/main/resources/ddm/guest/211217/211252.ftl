<#assign dl_file_entry_local_service = serviceLocator.findService("com.liferay.document.library.kernel.service.DLFileEntryLocalService")>
<#assign journal_article_local_service = serviceLocator.findService("com.liferay.journal.service.JournalArticleLocalService") />

<#assign service_context = objectUtil("com.liferay.portal.kernel.service.ServiceContextThreadLocal").getServiceContext() />
<#assign http_servlet_request = service_context.getRequest() />

<#assign theme_display = request["theme-display"] />
<#assign plid = theme_display["plid"]?number />

<#assign folder_id = paramUtil.getLong(http_servlet_request, "folderId") />
<#assign resource_id = paramUtil.getLong(http_servlet_request, "resourceId") />
<#assign title = paramUtil.getString(http_servlet_request, "title") />
<#assign passed_gated = paramUtil.getString(http_servlet_request, "gt") />

<#include "${templatesPath}/1561886" />

<#import "${templatesPath}/231368766" as article_utilities>

<#assign has_update_permissions = article_utilities.has_update_permissions() />

<p class="standard-padding">
	<a class="cta" href="/resources">${languageUtil.format(locale, "back-to-x", languageUtil.get(locale, "resources"))}</a>
</p>

<div class="resource-display">
	<#if dl_file_entry_local_service.fetchFileEntry(groupId, folder_id, title)??>
		<#assign dl_file_entry = dl_file_entry_local_service.fetchFileEntry(groupId, folder_id, title) />
	<#elseif dl_file_entry_local_service.fetchDLFileEntry(resource_id)??>
		<#assign dl_file_entry = dl_file_entry_local_service.fetchDLFileEntry(resource_id) />
	</#if>

	<#if journal_article_local_service.fetchArticleByUrlTitle(groupId, title)??>
		<#assign article = journal_article_local_service.fetchArticleByUrlTitle(groupId, title) />
	<#elseif journal_article_local_service.fetchArticle(groupId, resource_id?string)??>
		<#assign article = journal_article_local_service.fetchArticle(groupId, resource_id?string) />
	</#if>

	<#if article??>
		<#assign age_of_article = (.now?long - article.displayDate?long) / (1000 * 60 * 60 * 24) />
		<#assign hubspot_form_article_id = "230289858" />

		<#assign document = saxReaderUtil.read(article.getContent()) />

		<#assign company_name = document.selectSingleNode("//dynamic-element[@name='company_name']/dynamic-content")! />
		<#assign gated_teaser_content = document.selectSingleNode("//dynamic-element[@name='gated_teaser_content']/dynamic-content")! />
		<#assign is_gated = document.selectSingleNode("//dynamic-element[@name='is_gated']/dynamic-content")! />

		<#if (age_of_article < 90) && ((passed_gated != "true") && ((is_gated.data)?? && (is_gated.data == "true")))>
			<div class="block-container justify-center large-padding max-lg">
				<div class="block left-block text-center title-image w30">
					<@resource_icon />
				</div>

				<div class="block right-block w70">
					<#if company_name.data?has_content>
						<h1 class="title">
							${company_name.data}
						</h1>
					</#if>

					<#if gated_teaser_content.data?has_content>
						<div class="gated-teaser-content">
							${gated_teaser_content.data}
						</div>
					</#if>

					<#assign custom_resource_url = "/resource/" + title + "?gt=true" />

					<runtime-portlet instance="${hubspot_form_article_id}" name="56" queryString="customResourceUrl=${htmlUtil.escapeURL(custom_resource_url)}" />
				</div>

				<#if has_update_permissions>
					<#assign current_url = request.attributes.CURRENT_COMPLETE_URL! />

					<#assign edit_url = portletURLFactory.create(http_servlet_request, "20", plid, "0") />
					<#assign VOID = edit_url.setParameter("p_p_state", "maximized") />
					<#assign VOID = edit_url.setParameter("p_p_lifecycle", "0") />
					<#assign VOID = edit_url.setParameter("groupId", "${groupId}") />
					<#assign VOID = edit_url.setParameter("struts_action", "/document_library/edit_file_entry") />
					<#assign VOID = edit_url.setParameter("redirect", "${current_url}") />

					<span class="lfr-icon-action lfr-icon-action-edit lfr-meta-actions pull-right">
						<a class="taglib-icon" href="${edit_url}">
							<img alt="Edit" src="/osb-community-theme/images/spacer.png" style="background-image: url('/osb-community-theme/sprite/images/common/_sprite.png'); background-position: 50% -608px; background-repeat: no-repeat; height: 16px; width: 16px;">
							<span class="taglib-text">Edit</span>
						</a>
					</span>
				</#if>
			</div>
		<#else>
			<@article_utilities.embed_journal_article article />
		</#if>
	<#elseif dl_file_entry??>
		<#assign dl_file_entry_url = "/documents/" + groupId + "/" + dl_file_entry.getFolderId() + "/" + dl_file_entry.getTitle() />
		<#assign hubspot_form_article_id = "691288" />

		<div class="align-center block-container justify-center large-padding max-lg">
			<div class="block left-block text-center title-image w30">
				<@resource_icon />
			</div>

			<div class="block right-block w70">
				<#assign ddm_form_values_map = dl_file_entry.getDDMFormValuesMap(dl_file_entry.getFileVersion().getFileVersionId()) />
				<#assign MARKETING_ASSET_STRUCTURE_KEY = "302980">
				<#assign ddm_form_field_values_map = ddm_form_values_map[MARKETING_ASSET_STRUCTURE_KEY]!{}>		

				<#assign title = dl_file_entry.getTitle() />
				<#assign display_title_values = ddm_form_field_values_map.getDDMFormFieldValuesMap()["display_title"]! [] />

				<#list display_title_values as display_title>
					<#if display_title?has_content && display_title.getValue()?has_content>
						<#assign title = display_title.getValue().getString(locale) />
					</#if>
				</#list>

				<h1 class="title">${title}</h1>

				<p class="description">${dl_file_entry.getDescription()}</p>

				<#assign embed_asset_id = dl_file_entry.getFileEntryId() />

				<runtime-portlet instance="${hubspot_form_article_id}" name="56" queryString="assetId=${embed_asset_id}" />
			</div>

			<#if has_update_permissions>
				<#assign current_url = request.attributes.CURRENT_COMPLETE_URL! />

				<#assign edit_url = portletURLFactory.create(http_servlet_request, "20", plid, "0") />
				<#assign VOID = edit_url.setParameter("p_p_state", "maximized") />
				<#assign VOID = edit_url.setParameter("p_p_lifecycle", "0") />
				<#assign VOID = edit_url.setParameter("groupId", "${groupId}") />
				<#assign VOID = edit_url.setParameter("struts_action", "/document_library/edit_file_entry") />
				<#assign VOID = edit_url.setParameter("redirect", "${current_url}") />
				<#assign VOID = edit_url.setParameter("folderId", "${dl_file_entry.getFolderId()?string}") />
				<#assign VOID = edit_url.setParameter("fileEntryId", "${dl_file_entry.getFileEntryId()?string}") />

				<span class="lfr-icon-action lfr-icon-action-edit lfr-meta-actions pull-right">
					<a class="taglib-icon" href="${edit_url}">
						<img alt="Edit" src="/osb-community-theme/images/spacer.png" style="background-image: url('/osb-community-theme/sprite/images/common/_sprite.png'); background-position: 50% -608px; background-repeat: no-repeat; height: 16px; width: 16px;">
						<span class="taglib-text">Edit</span>
					</a>
				</span>
			</#if>
		</div>
	<#else>
		<p class="standard-padding">
			Resource not found.
		</p>
	</#if>
</div>

<#macro resource_icon>
	<svg viewBox="0 0 145 188" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
		<path class="secondary-fill" d="M144,118l-69,69h66.1c1.6,0,2.9-1.3,2.9-2.9V118z"/>
		<polygon class="alt-secondary-fill" points="75,187 135.3,187 105.1,156.9 "/>
		<path class="primary-fill" d="M83,1l61,61V4c0-1.7-1.3-3-3-3H83z"/>
		<polygon class="accent-fill" points="144,62 144,25.1 125.5,43.5 "/>
		<path class="element-stroke transparent-fill" d="M141.5,187.5H3.5c-1.6,0-3-1.4-3-3V3.5c0-1.6,1.4-3,3-3h138c1.6,0,3,1.4,3,3v181 C144.5,186.1,143.1,187.5,141.5,187.5z"/>

		<svg viewBox="0 0 152 36" width="80%" x="10%">
			<use xlink:href="#liferayLogo-liferay"></use>
			<use class="logo-border" xlink:href="#liferayLogo-logoBorder"></use>
			<use class="light-fill" xlink:href="#liferayLogo-gradientLightest"></use>
			<use class="logo-gradient-light" xlink:href="#liferayLogo-gradientLight"></use>
			<use class="logo-gradient-dark" xlink:href="#liferayLogo-gradientDark"></use>
			<use class="logo-gradient-darkest" xlink:href="#liferayLogo-gradientDarkest"></use>
		</svg>
	</svg>
</#macro>