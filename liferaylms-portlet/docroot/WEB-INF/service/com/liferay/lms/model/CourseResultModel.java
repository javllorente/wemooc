/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.lms.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the CourseResult service. Represents a row in the &quot;Lms_CourseResult&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.lms.model.impl.CourseResultModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.lms.model.impl.CourseResultImpl}.
 * </p>
 *
 * @author TLS
 * @see CourseResult
 * @see com.liferay.lms.model.impl.CourseResultImpl
 * @see com.liferay.lms.model.impl.CourseResultModelImpl
 * @generated
 */
public interface CourseResultModel extends BaseModel<CourseResult> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a course result model instance should use the {@link CourseResult} interface instead.
	 */

	/**
	 * Returns the primary key of this course result.
	 *
	 * @return the primary key of this course result
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this course result.
	 *
	 * @param primaryKey the primary key of this course result
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the cr ID of this course result.
	 *
	 * @return the cr ID of this course result
	 */
	public long getCrId();

	/**
	 * Sets the cr ID of this course result.
	 *
	 * @param crId the cr ID of this course result
	 */
	public void setCrId(long crId);

	/**
	 * Returns the course ID of this course result.
	 *
	 * @return the course ID of this course result
	 */
	public long getCourseId();

	/**
	 * Sets the course ID of this course result.
	 *
	 * @param courseId the course ID of this course result
	 */
	public void setCourseId(long courseId);

	/**
	 * Returns the result of this course result.
	 *
	 * @return the result of this course result
	 */
	public long getResult();

	/**
	 * Sets the result of this course result.
	 *
	 * @param result the result of this course result
	 */
	public void setResult(long result);

	/**
	 * Returns the comments of this course result.
	 *
	 * @return the comments of this course result
	 */
	@AutoEscape
	public String getComments();

	/**
	 * Sets the comments of this course result.
	 *
	 * @param comments the comments of this course result
	 */
	public void setComments(String comments);

	/**
	 * Returns the user ID of this course result.
	 *
	 * @return the user ID of this course result
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this course result.
	 *
	 * @param userId the user ID of this course result
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this course result.
	 *
	 * @return the user uuid of this course result
	 * @throws SystemException if a system exception occurred
	 */
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this course result.
	 *
	 * @param userUuid the user uuid of this course result
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the passed of this course result.
	 *
	 * @return the passed of this course result
	 */
	public boolean getPassed();

	/**
	 * Returns <code>true</code> if this course result is passed.
	 *
	 * @return <code>true</code> if this course result is passed; <code>false</code> otherwise
	 */
	public boolean isPassed();

	/**
	 * Sets whether this course result is passed.
	 *
	 * @param passed the passed of this course result
	 */
	public void setPassed(boolean passed);

	/**
	 * Returns the passed date of this course result.
	 *
	 * @return the passed date of this course result
	 */
	public Date getPassedDate();

	/**
	 * Sets the passed date of this course result.
	 *
	 * @param passedDate the passed date of this course result
	 */
	public void setPassedDate(Date passedDate);

	/**
	 * Returns the allow start date of this course result.
	 *
	 * @return the allow start date of this course result
	 */
	public Date getAllowStartDate();

	/**
	 * Sets the allow start date of this course result.
	 *
	 * @param allowStartDate the allow start date of this course result
	 */
	public void setAllowStartDate(Date allowStartDate);

	/**
	 * Returns the allow finish date of this course result.
	 *
	 * @return the allow finish date of this course result
	 */
	public Date getAllowFinishDate();

	/**
	 * Sets the allow finish date of this course result.
	 *
	 * @param allowFinishDate the allow finish date of this course result
	 */
	public void setAllowFinishDate(Date allowFinishDate);

	public boolean isNew();

	public void setNew(boolean n);

	public boolean isCachedModel();

	public void setCachedModel(boolean cachedModel);

	public boolean isEscapedModel();

	public Serializable getPrimaryKeyObj();

	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	public ExpandoBridge getExpandoBridge();

	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	public Object clone();

	public int compareTo(CourseResult courseResult);

	public int hashCode();

	public CacheModel<CourseResult> toCacheModel();

	public CourseResult toEscapedModel();

	public String toString();

	public String toXmlString();
}