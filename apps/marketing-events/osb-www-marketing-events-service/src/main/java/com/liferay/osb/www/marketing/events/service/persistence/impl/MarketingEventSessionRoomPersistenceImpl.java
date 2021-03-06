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

package com.liferay.osb.www.marketing.events.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.www.marketing.events.exception.NoSuchMarketingEventSessionRoomException;
import com.liferay.osb.www.marketing.events.model.MarketingEventSessionRoom;
import com.liferay.osb.www.marketing.events.model.impl.MarketingEventSessionRoomImpl;
import com.liferay.osb.www.marketing.events.model.impl.MarketingEventSessionRoomModelImpl;
import com.liferay.osb.www.marketing.events.service.persistence.MarketingEventSessionRoomPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the marketing event session room service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MarketingEventSessionRoomPersistence
 * @see com.liferay.osb.www.marketing.events.service.persistence.MarketingEventSessionRoomUtil
 * @generated
 */
@ProviderType
public class MarketingEventSessionRoomPersistenceImpl
	extends BasePersistenceImpl<MarketingEventSessionRoom>
	implements MarketingEventSessionRoomPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link MarketingEventSessionRoomUtil} to access the marketing event session room persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = MarketingEventSessionRoomImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MarketingEventSessionRoomModelImpl.ENTITY_CACHE_ENABLED,
			MarketingEventSessionRoomModelImpl.FINDER_CACHE_ENABLED,
			MarketingEventSessionRoomImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MarketingEventSessionRoomModelImpl.ENTITY_CACHE_ENABLED,
			MarketingEventSessionRoomModelImpl.FINDER_CACHE_ENABLED,
			MarketingEventSessionRoomImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MarketingEventSessionRoomModelImpl.ENTITY_CACHE_ENABLED,
			MarketingEventSessionRoomModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MARKETINGEVENTID =
		new FinderPath(MarketingEventSessionRoomModelImpl.ENTITY_CACHE_ENABLED,
			MarketingEventSessionRoomModelImpl.FINDER_CACHE_ENABLED,
			MarketingEventSessionRoomImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByMarketingEventId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MARKETINGEVENTID =
		new FinderPath(MarketingEventSessionRoomModelImpl.ENTITY_CACHE_ENABLED,
			MarketingEventSessionRoomModelImpl.FINDER_CACHE_ENABLED,
			MarketingEventSessionRoomImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByMarketingEventId", new String[] { Long.class.getName() },
			MarketingEventSessionRoomModelImpl.MARKETINGEVENTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_MARKETINGEVENTID = new FinderPath(MarketingEventSessionRoomModelImpl.ENTITY_CACHE_ENABLED,
			MarketingEventSessionRoomModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByMarketingEventId", new String[] { Long.class.getName() });

	/**
	 * Returns all the marketing event session rooms where marketingEventId = &#63;.
	 *
	 * @param marketingEventId the marketing event ID
	 * @return the matching marketing event session rooms
	 */
	@Override
	public List<MarketingEventSessionRoom> findByMarketingEventId(
		long marketingEventId) {
		return findByMarketingEventId(marketingEventId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the marketing event session rooms where marketingEventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarketingEventSessionRoomModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param marketingEventId the marketing event ID
	 * @param start the lower bound of the range of marketing event session rooms
	 * @param end the upper bound of the range of marketing event session rooms (not inclusive)
	 * @return the range of matching marketing event session rooms
	 */
	@Override
	public List<MarketingEventSessionRoom> findByMarketingEventId(
		long marketingEventId, int start, int end) {
		return findByMarketingEventId(marketingEventId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the marketing event session rooms where marketingEventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarketingEventSessionRoomModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param marketingEventId the marketing event ID
	 * @param start the lower bound of the range of marketing event session rooms
	 * @param end the upper bound of the range of marketing event session rooms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching marketing event session rooms
	 */
	@Override
	public List<MarketingEventSessionRoom> findByMarketingEventId(
		long marketingEventId, int start, int end,
		OrderByComparator<MarketingEventSessionRoom> orderByComparator) {
		return findByMarketingEventId(marketingEventId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the marketing event session rooms where marketingEventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarketingEventSessionRoomModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param marketingEventId the marketing event ID
	 * @param start the lower bound of the range of marketing event session rooms
	 * @param end the upper bound of the range of marketing event session rooms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching marketing event session rooms
	 */
	@Override
	public List<MarketingEventSessionRoom> findByMarketingEventId(
		long marketingEventId, int start, int end,
		OrderByComparator<MarketingEventSessionRoom> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MARKETINGEVENTID;
			finderArgs = new Object[] { marketingEventId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MARKETINGEVENTID;
			finderArgs = new Object[] {
					marketingEventId,
					
					start, end, orderByComparator
				};
		}

		List<MarketingEventSessionRoom> list = null;

		if (retrieveFromCache) {
			list = (List<MarketingEventSessionRoom>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MarketingEventSessionRoom marketingEventSessionRoom : list) {
					if ((marketingEventId != marketingEventSessionRoom.getMarketingEventId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_MARKETINGEVENTSESSIONROOM_WHERE);

			query.append(_FINDER_COLUMN_MARKETINGEVENTID_MARKETINGEVENTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(MarketingEventSessionRoomModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(marketingEventId);

				if (!pagination) {
					list = (List<MarketingEventSessionRoom>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MarketingEventSessionRoom>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first marketing event session room in the ordered set where marketingEventId = &#63;.
	 *
	 * @param marketingEventId the marketing event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching marketing event session room
	 * @throws NoSuchMarketingEventSessionRoomException if a matching marketing event session room could not be found
	 */
	@Override
	public MarketingEventSessionRoom findByMarketingEventId_First(
		long marketingEventId,
		OrderByComparator<MarketingEventSessionRoom> orderByComparator)
		throws NoSuchMarketingEventSessionRoomException {
		MarketingEventSessionRoom marketingEventSessionRoom = fetchByMarketingEventId_First(marketingEventId,
				orderByComparator);

		if (marketingEventSessionRoom != null) {
			return marketingEventSessionRoom;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("marketingEventId=");
		msg.append(marketingEventId);

		msg.append("}");

		throw new NoSuchMarketingEventSessionRoomException(msg.toString());
	}

	/**
	 * Returns the first marketing event session room in the ordered set where marketingEventId = &#63;.
	 *
	 * @param marketingEventId the marketing event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching marketing event session room, or <code>null</code> if a matching marketing event session room could not be found
	 */
	@Override
	public MarketingEventSessionRoom fetchByMarketingEventId_First(
		long marketingEventId,
		OrderByComparator<MarketingEventSessionRoom> orderByComparator) {
		List<MarketingEventSessionRoom> list = findByMarketingEventId(marketingEventId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last marketing event session room in the ordered set where marketingEventId = &#63;.
	 *
	 * @param marketingEventId the marketing event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching marketing event session room
	 * @throws NoSuchMarketingEventSessionRoomException if a matching marketing event session room could not be found
	 */
	@Override
	public MarketingEventSessionRoom findByMarketingEventId_Last(
		long marketingEventId,
		OrderByComparator<MarketingEventSessionRoom> orderByComparator)
		throws NoSuchMarketingEventSessionRoomException {
		MarketingEventSessionRoom marketingEventSessionRoom = fetchByMarketingEventId_Last(marketingEventId,
				orderByComparator);

		if (marketingEventSessionRoom != null) {
			return marketingEventSessionRoom;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("marketingEventId=");
		msg.append(marketingEventId);

		msg.append("}");

		throw new NoSuchMarketingEventSessionRoomException(msg.toString());
	}

	/**
	 * Returns the last marketing event session room in the ordered set where marketingEventId = &#63;.
	 *
	 * @param marketingEventId the marketing event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching marketing event session room, or <code>null</code> if a matching marketing event session room could not be found
	 */
	@Override
	public MarketingEventSessionRoom fetchByMarketingEventId_Last(
		long marketingEventId,
		OrderByComparator<MarketingEventSessionRoom> orderByComparator) {
		int count = countByMarketingEventId(marketingEventId);

		if (count == 0) {
			return null;
		}

		List<MarketingEventSessionRoom> list = findByMarketingEventId(marketingEventId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the marketing event session rooms before and after the current marketing event session room in the ordered set where marketingEventId = &#63;.
	 *
	 * @param marketingEventSessionRoomId the primary key of the current marketing event session room
	 * @param marketingEventId the marketing event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next marketing event session room
	 * @throws NoSuchMarketingEventSessionRoomException if a marketing event session room with the primary key could not be found
	 */
	@Override
	public MarketingEventSessionRoom[] findByMarketingEventId_PrevAndNext(
		long marketingEventSessionRoomId, long marketingEventId,
		OrderByComparator<MarketingEventSessionRoom> orderByComparator)
		throws NoSuchMarketingEventSessionRoomException {
		MarketingEventSessionRoom marketingEventSessionRoom = findByPrimaryKey(marketingEventSessionRoomId);

		Session session = null;

		try {
			session = openSession();

			MarketingEventSessionRoom[] array = new MarketingEventSessionRoomImpl[3];

			array[0] = getByMarketingEventId_PrevAndNext(session,
					marketingEventSessionRoom, marketingEventId,
					orderByComparator, true);

			array[1] = marketingEventSessionRoom;

			array[2] = getByMarketingEventId_PrevAndNext(session,
					marketingEventSessionRoom, marketingEventId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MarketingEventSessionRoom getByMarketingEventId_PrevAndNext(
		Session session, MarketingEventSessionRoom marketingEventSessionRoom,
		long marketingEventId,
		OrderByComparator<MarketingEventSessionRoom> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MARKETINGEVENTSESSIONROOM_WHERE);

		query.append(_FINDER_COLUMN_MARKETINGEVENTID_MARKETINGEVENTID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(MarketingEventSessionRoomModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(marketingEventId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(marketingEventSessionRoom);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MarketingEventSessionRoom> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the marketing event session rooms where marketingEventId = &#63; from the database.
	 *
	 * @param marketingEventId the marketing event ID
	 */
	@Override
	public void removeByMarketingEventId(long marketingEventId) {
		for (MarketingEventSessionRoom marketingEventSessionRoom : findByMarketingEventId(
				marketingEventId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(marketingEventSessionRoom);
		}
	}

	/**
	 * Returns the number of marketing event session rooms where marketingEventId = &#63;.
	 *
	 * @param marketingEventId the marketing event ID
	 * @return the number of matching marketing event session rooms
	 */
	@Override
	public int countByMarketingEventId(long marketingEventId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_MARKETINGEVENTID;

		Object[] finderArgs = new Object[] { marketingEventId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MARKETINGEVENTSESSIONROOM_WHERE);

			query.append(_FINDER_COLUMN_MARKETINGEVENTID_MARKETINGEVENTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(marketingEventId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_MARKETINGEVENTID_MARKETINGEVENTID_2 =
		"marketingEventSessionRoom.marketingEventId = ?";

	public MarketingEventSessionRoomPersistenceImpl() {
		setModelClass(MarketingEventSessionRoom.class);
	}

	/**
	 * Caches the marketing event session room in the entity cache if it is enabled.
	 *
	 * @param marketingEventSessionRoom the marketing event session room
	 */
	@Override
	public void cacheResult(MarketingEventSessionRoom marketingEventSessionRoom) {
		entityCache.putResult(MarketingEventSessionRoomModelImpl.ENTITY_CACHE_ENABLED,
			MarketingEventSessionRoomImpl.class,
			marketingEventSessionRoom.getPrimaryKey(), marketingEventSessionRoom);

		marketingEventSessionRoom.resetOriginalValues();
	}

	/**
	 * Caches the marketing event session rooms in the entity cache if it is enabled.
	 *
	 * @param marketingEventSessionRooms the marketing event session rooms
	 */
	@Override
	public void cacheResult(
		List<MarketingEventSessionRoom> marketingEventSessionRooms) {
		for (MarketingEventSessionRoom marketingEventSessionRoom : marketingEventSessionRooms) {
			if (entityCache.getResult(
						MarketingEventSessionRoomModelImpl.ENTITY_CACHE_ENABLED,
						MarketingEventSessionRoomImpl.class,
						marketingEventSessionRoom.getPrimaryKey()) == null) {
				cacheResult(marketingEventSessionRoom);
			}
			else {
				marketingEventSessionRoom.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all marketing event session rooms.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MarketingEventSessionRoomImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the marketing event session room.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MarketingEventSessionRoom marketingEventSessionRoom) {
		entityCache.removeResult(MarketingEventSessionRoomModelImpl.ENTITY_CACHE_ENABLED,
			MarketingEventSessionRoomImpl.class,
			marketingEventSessionRoom.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<MarketingEventSessionRoom> marketingEventSessionRooms) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (MarketingEventSessionRoom marketingEventSessionRoom : marketingEventSessionRooms) {
			entityCache.removeResult(MarketingEventSessionRoomModelImpl.ENTITY_CACHE_ENABLED,
				MarketingEventSessionRoomImpl.class,
				marketingEventSessionRoom.getPrimaryKey());
		}
	}

	/**
	 * Creates a new marketing event session room with the primary key. Does not add the marketing event session room to the database.
	 *
	 * @param marketingEventSessionRoomId the primary key for the new marketing event session room
	 * @return the new marketing event session room
	 */
	@Override
	public MarketingEventSessionRoom create(long marketingEventSessionRoomId) {
		MarketingEventSessionRoom marketingEventSessionRoom = new MarketingEventSessionRoomImpl();

		marketingEventSessionRoom.setNew(true);
		marketingEventSessionRoom.setPrimaryKey(marketingEventSessionRoomId);

		return marketingEventSessionRoom;
	}

	/**
	 * Removes the marketing event session room with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param marketingEventSessionRoomId the primary key of the marketing event session room
	 * @return the marketing event session room that was removed
	 * @throws NoSuchMarketingEventSessionRoomException if a marketing event session room with the primary key could not be found
	 */
	@Override
	public MarketingEventSessionRoom remove(long marketingEventSessionRoomId)
		throws NoSuchMarketingEventSessionRoomException {
		return remove((Serializable)marketingEventSessionRoomId);
	}

	/**
	 * Removes the marketing event session room with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the marketing event session room
	 * @return the marketing event session room that was removed
	 * @throws NoSuchMarketingEventSessionRoomException if a marketing event session room with the primary key could not be found
	 */
	@Override
	public MarketingEventSessionRoom remove(Serializable primaryKey)
		throws NoSuchMarketingEventSessionRoomException {
		Session session = null;

		try {
			session = openSession();

			MarketingEventSessionRoom marketingEventSessionRoom = (MarketingEventSessionRoom)session.get(MarketingEventSessionRoomImpl.class,
					primaryKey);

			if (marketingEventSessionRoom == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMarketingEventSessionRoomException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(marketingEventSessionRoom);
		}
		catch (NoSuchMarketingEventSessionRoomException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected MarketingEventSessionRoom removeImpl(
		MarketingEventSessionRoom marketingEventSessionRoom) {
		marketingEventSessionRoom = toUnwrappedModel(marketingEventSessionRoom);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(marketingEventSessionRoom)) {
				marketingEventSessionRoom = (MarketingEventSessionRoom)session.get(MarketingEventSessionRoomImpl.class,
						marketingEventSessionRoom.getPrimaryKeyObj());
			}

			if (marketingEventSessionRoom != null) {
				session.delete(marketingEventSessionRoom);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (marketingEventSessionRoom != null) {
			clearCache(marketingEventSessionRoom);
		}

		return marketingEventSessionRoom;
	}

	@Override
	public MarketingEventSessionRoom updateImpl(
		MarketingEventSessionRoom marketingEventSessionRoom) {
		marketingEventSessionRoom = toUnwrappedModel(marketingEventSessionRoom);

		boolean isNew = marketingEventSessionRoom.isNew();

		MarketingEventSessionRoomModelImpl marketingEventSessionRoomModelImpl = (MarketingEventSessionRoomModelImpl)marketingEventSessionRoom;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (marketingEventSessionRoom.getCreateDate() == null)) {
			if (serviceContext == null) {
				marketingEventSessionRoom.setCreateDate(now);
			}
			else {
				marketingEventSessionRoom.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!marketingEventSessionRoomModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				marketingEventSessionRoom.setModifiedDate(now);
			}
			else {
				marketingEventSessionRoom.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (marketingEventSessionRoom.isNew()) {
				session.save(marketingEventSessionRoom);

				marketingEventSessionRoom.setNew(false);
			}
			else {
				marketingEventSessionRoom = (MarketingEventSessionRoom)session.merge(marketingEventSessionRoom);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!MarketingEventSessionRoomModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					marketingEventSessionRoomModelImpl.getMarketingEventId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_MARKETINGEVENTID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MARKETINGEVENTID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((marketingEventSessionRoomModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MARKETINGEVENTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						marketingEventSessionRoomModelImpl.getOriginalMarketingEventId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_MARKETINGEVENTID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MARKETINGEVENTID,
					args);

				args = new Object[] {
						marketingEventSessionRoomModelImpl.getMarketingEventId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_MARKETINGEVENTID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MARKETINGEVENTID,
					args);
			}
		}

		entityCache.putResult(MarketingEventSessionRoomModelImpl.ENTITY_CACHE_ENABLED,
			MarketingEventSessionRoomImpl.class,
			marketingEventSessionRoom.getPrimaryKey(),
			marketingEventSessionRoom, false);

		marketingEventSessionRoom.resetOriginalValues();

		return marketingEventSessionRoom;
	}

	protected MarketingEventSessionRoom toUnwrappedModel(
		MarketingEventSessionRoom marketingEventSessionRoom) {
		if (marketingEventSessionRoom instanceof MarketingEventSessionRoomImpl) {
			return marketingEventSessionRoom;
		}

		MarketingEventSessionRoomImpl marketingEventSessionRoomImpl = new MarketingEventSessionRoomImpl();

		marketingEventSessionRoomImpl.setNew(marketingEventSessionRoom.isNew());
		marketingEventSessionRoomImpl.setPrimaryKey(marketingEventSessionRoom.getPrimaryKey());

		marketingEventSessionRoomImpl.setMarketingEventSessionRoomId(marketingEventSessionRoom.getMarketingEventSessionRoomId());
		marketingEventSessionRoomImpl.setCreateDate(marketingEventSessionRoom.getCreateDate());
		marketingEventSessionRoomImpl.setModifiedDate(marketingEventSessionRoom.getModifiedDate());
		marketingEventSessionRoomImpl.setMarketingEventId(marketingEventSessionRoom.getMarketingEventId());
		marketingEventSessionRoomImpl.setName(marketingEventSessionRoom.getName());
		marketingEventSessionRoomImpl.setImageFileEntryId(marketingEventSessionRoom.getImageFileEntryId());

		return marketingEventSessionRoomImpl;
	}

	/**
	 * Returns the marketing event session room with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the marketing event session room
	 * @return the marketing event session room
	 * @throws NoSuchMarketingEventSessionRoomException if a marketing event session room with the primary key could not be found
	 */
	@Override
	public MarketingEventSessionRoom findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMarketingEventSessionRoomException {
		MarketingEventSessionRoom marketingEventSessionRoom = fetchByPrimaryKey(primaryKey);

		if (marketingEventSessionRoom == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMarketingEventSessionRoomException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return marketingEventSessionRoom;
	}

	/**
	 * Returns the marketing event session room with the primary key or throws a {@link NoSuchMarketingEventSessionRoomException} if it could not be found.
	 *
	 * @param marketingEventSessionRoomId the primary key of the marketing event session room
	 * @return the marketing event session room
	 * @throws NoSuchMarketingEventSessionRoomException if a marketing event session room with the primary key could not be found
	 */
	@Override
	public MarketingEventSessionRoom findByPrimaryKey(
		long marketingEventSessionRoomId)
		throws NoSuchMarketingEventSessionRoomException {
		return findByPrimaryKey((Serializable)marketingEventSessionRoomId);
	}

	/**
	 * Returns the marketing event session room with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the marketing event session room
	 * @return the marketing event session room, or <code>null</code> if a marketing event session room with the primary key could not be found
	 */
	@Override
	public MarketingEventSessionRoom fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(MarketingEventSessionRoomModelImpl.ENTITY_CACHE_ENABLED,
				MarketingEventSessionRoomImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		MarketingEventSessionRoom marketingEventSessionRoom = (MarketingEventSessionRoom)serializable;

		if (marketingEventSessionRoom == null) {
			Session session = null;

			try {
				session = openSession();

				marketingEventSessionRoom = (MarketingEventSessionRoom)session.get(MarketingEventSessionRoomImpl.class,
						primaryKey);

				if (marketingEventSessionRoom != null) {
					cacheResult(marketingEventSessionRoom);
				}
				else {
					entityCache.putResult(MarketingEventSessionRoomModelImpl.ENTITY_CACHE_ENABLED,
						MarketingEventSessionRoomImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(MarketingEventSessionRoomModelImpl.ENTITY_CACHE_ENABLED,
					MarketingEventSessionRoomImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return marketingEventSessionRoom;
	}

	/**
	 * Returns the marketing event session room with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param marketingEventSessionRoomId the primary key of the marketing event session room
	 * @return the marketing event session room, or <code>null</code> if a marketing event session room with the primary key could not be found
	 */
	@Override
	public MarketingEventSessionRoom fetchByPrimaryKey(
		long marketingEventSessionRoomId) {
		return fetchByPrimaryKey((Serializable)marketingEventSessionRoomId);
	}

	@Override
	public Map<Serializable, MarketingEventSessionRoom> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, MarketingEventSessionRoom> map = new HashMap<Serializable, MarketingEventSessionRoom>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			MarketingEventSessionRoom marketingEventSessionRoom = fetchByPrimaryKey(primaryKey);

			if (marketingEventSessionRoom != null) {
				map.put(primaryKey, marketingEventSessionRoom);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(MarketingEventSessionRoomModelImpl.ENTITY_CACHE_ENABLED,
					MarketingEventSessionRoomImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (MarketingEventSessionRoom)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_MARKETINGEVENTSESSIONROOM_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(",");
		}

		query.setIndex(query.index() - 1);

		query.append(")");

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (MarketingEventSessionRoom marketingEventSessionRoom : (List<MarketingEventSessionRoom>)q.list()) {
				map.put(marketingEventSessionRoom.getPrimaryKeyObj(),
					marketingEventSessionRoom);

				cacheResult(marketingEventSessionRoom);

				uncachedPrimaryKeys.remove(marketingEventSessionRoom.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(MarketingEventSessionRoomModelImpl.ENTITY_CACHE_ENABLED,
					MarketingEventSessionRoomImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the marketing event session rooms.
	 *
	 * @return the marketing event session rooms
	 */
	@Override
	public List<MarketingEventSessionRoom> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the marketing event session rooms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarketingEventSessionRoomModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of marketing event session rooms
	 * @param end the upper bound of the range of marketing event session rooms (not inclusive)
	 * @return the range of marketing event session rooms
	 */
	@Override
	public List<MarketingEventSessionRoom> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the marketing event session rooms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarketingEventSessionRoomModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of marketing event session rooms
	 * @param end the upper bound of the range of marketing event session rooms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of marketing event session rooms
	 */
	@Override
	public List<MarketingEventSessionRoom> findAll(int start, int end,
		OrderByComparator<MarketingEventSessionRoom> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the marketing event session rooms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarketingEventSessionRoomModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of marketing event session rooms
	 * @param end the upper bound of the range of marketing event session rooms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of marketing event session rooms
	 */
	@Override
	public List<MarketingEventSessionRoom> findAll(int start, int end,
		OrderByComparator<MarketingEventSessionRoom> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<MarketingEventSessionRoom> list = null;

		if (retrieveFromCache) {
			list = (List<MarketingEventSessionRoom>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_MARKETINGEVENTSESSIONROOM);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MARKETINGEVENTSESSIONROOM;

				if (pagination) {
					sql = sql.concat(MarketingEventSessionRoomModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<MarketingEventSessionRoom>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MarketingEventSessionRoom>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the marketing event session rooms from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MarketingEventSessionRoom marketingEventSessionRoom : findAll()) {
			remove(marketingEventSessionRoom);
		}
	}

	/**
	 * Returns the number of marketing event session rooms.
	 *
	 * @return the number of marketing event session rooms
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MARKETINGEVENTSESSIONROOM);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return MarketingEventSessionRoomModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the marketing event session room persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(MarketingEventSessionRoomImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_MARKETINGEVENTSESSIONROOM = "SELECT marketingEventSessionRoom FROM MarketingEventSessionRoom marketingEventSessionRoom";
	private static final String _SQL_SELECT_MARKETINGEVENTSESSIONROOM_WHERE_PKS_IN =
		"SELECT marketingEventSessionRoom FROM MarketingEventSessionRoom marketingEventSessionRoom WHERE marketingEventSessionRoomId IN (";
	private static final String _SQL_SELECT_MARKETINGEVENTSESSIONROOM_WHERE = "SELECT marketingEventSessionRoom FROM MarketingEventSessionRoom marketingEventSessionRoom WHERE ";
	private static final String _SQL_COUNT_MARKETINGEVENTSESSIONROOM = "SELECT COUNT(marketingEventSessionRoom) FROM MarketingEventSessionRoom marketingEventSessionRoom";
	private static final String _SQL_COUNT_MARKETINGEVENTSESSIONROOM_WHERE = "SELECT COUNT(marketingEventSessionRoom) FROM MarketingEventSessionRoom marketingEventSessionRoom WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "marketingEventSessionRoom.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MarketingEventSessionRoom exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No MarketingEventSessionRoom exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(MarketingEventSessionRoomPersistenceImpl.class);
}