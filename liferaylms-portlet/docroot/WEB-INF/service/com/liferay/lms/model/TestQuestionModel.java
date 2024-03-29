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
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the TestQuestion service. Represents a row in the &quot;Lms_TestQuestion&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.lms.model.impl.TestQuestionModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.lms.model.impl.TestQuestionImpl}.
 * </p>
 *
 * @author TLS
 * @see TestQuestion
 * @see com.liferay.lms.model.impl.TestQuestionImpl
 * @see com.liferay.lms.model.impl.TestQuestionModelImpl
 * @generated
 */
public interface TestQuestionModel extends BaseModel<TestQuestion> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a test question model instance should use the {@link TestQuestion} interface instead.
	 */

	/**
	 * Returns the primary key of this test question.
	 *
	 * @return the primary key of this test question
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this test question.
	 *
	 * @param primaryKey the primary key of this test question
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this test question.
	 *
	 * @return the uuid of this test question
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this test question.
	 *
	 * @param uuid the uuid of this test question
	 */
	public void setUuid(String uuid);

	/**
	 * Returns the question ID of this test question.
	 *
	 * @return the question ID of this test question
	 */
	public long getQuestionId();

	/**
	 * Sets the question ID of this test question.
	 *
	 * @param questionId the question ID of this test question
	 */
	public void setQuestionId(long questionId);

	/**
	 * Returns the act ID of this test question.
	 *
	 * @return the act ID of this test question
	 */
	public long getActId();

	/**
	 * Sets the act ID of this test question.
	 *
	 * @param actId the act ID of this test question
	 */
	public void setActId(long actId);

	/**
	 * Returns the text of this test question.
	 *
	 * @return the text of this test question
	 */
	@AutoEscape
	public String getText();

	/**
	 * Sets the text of this test question.
	 *
	 * @param text the text of this test question
	 */
	public void setText(String text);

	/**
	 * Returns the question type of this test question.
	 *
	 * @return the question type of this test question
	 */
	public long getQuestionType();

	/**
	 * Sets the question type of this test question.
	 *
	 * @param questionType the question type of this test question
	 */
	public void setQuestionType(long questionType);

	/**
	 * Returns the weight of this test question.
	 *
	 * @return the weight of this test question
	 */
	public long getWeight();

	/**
	 * Sets the weight of this test question.
	 *
	 * @param weight the weight of this test question
	 */
	public void setWeight(long weight);

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

	public int compareTo(TestQuestion testQuestion);

	public int hashCode();

	public CacheModel<TestQuestion> toCacheModel();

	public TestQuestion toEscapedModel();

	public String toString();

	public String toXmlString();
}