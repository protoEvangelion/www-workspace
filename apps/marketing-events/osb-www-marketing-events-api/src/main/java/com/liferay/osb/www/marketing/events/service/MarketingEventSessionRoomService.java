/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.www.marketing.events.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.www.marketing.events.model.MarketingEventSessionRoom;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.List;

/**
 * Provides the remote service interface for MarketingEventSessionRoom. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see MarketingEventSessionRoomServiceUtil
 * @see com.liferay.osb.www.marketing.events.service.base.MarketingEventSessionRoomServiceBaseImpl
 * @see com.liferay.osb.www.marketing.events.service.impl.MarketingEventSessionRoomServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(property =  {
	"json.web.service.context.name=osb", "json.web.service.context.path=MarketingEventSessionRoom"}, service = MarketingEventSessionRoomService.class)
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface MarketingEventSessionRoomService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MarketingEventSessionRoomServiceUtil} to access the marketing event session room remote service. Add custom service methods to {@link com.liferay.osb.www.marketing.events.service.impl.MarketingEventSessionRoomServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@AccessControlled(guestAccessEnabled = true)
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public MarketingEventSessionRoom fetchMarketingEventSessionRoom(
		long marketingEventSessionRoomId) throws PortalException;

	@AccessControlled(guestAccessEnabled = true)
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MarketingEventSessionRoom> getMarketingEventSessionRooms(
		long marketingEventId, int start, int end) throws PortalException;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();
}