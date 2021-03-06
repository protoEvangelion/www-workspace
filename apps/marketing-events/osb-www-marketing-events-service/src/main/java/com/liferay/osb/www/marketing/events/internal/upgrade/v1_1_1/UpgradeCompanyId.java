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

package com.liferay.osb.www.marketing.events.internal.upgrade.v1_1_1;

import com.liferay.portal.kernel.upgrade.BaseUpgradeCompanyId;

/**
 * @author Allen Ziegenfus
 */
public class UpgradeCompanyId extends BaseUpgradeCompanyId {

	@Override
	protected TableUpdater[] getTableUpdaters() {
		return new TableUpdater[] {
			new TableUpdater(
				"OSB_MarketingEventSession", "OSB_MarketingEvent",
				"marketingEventId"),
			new TableUpdater(
				"OSB_MarketingEventUser", "OSB_MarketingEvent",
				"marketingEventId"),
			new TableUpdater(
				"OSB_MarketingEventSessions_MarketingEventUsers",
				"OSB_MarketingEventSession", "marketingEventSessionId")
		};
	}

}