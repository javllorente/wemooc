package com.liferay.lms.learningactivity.courseeval;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.Map;

import com.liferay.lms.model.Course;
import com.liferay.lms.model.CourseClp;
import com.liferay.lms.model.ModuleResult;
import com.liferay.lms.model.ModuleResultClp;
import com.liferay.lms.service.ClpSerializer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.xml.DocumentException;

public class CouseEvalClp implements CourseEval {

	private ClassLoaderProxy clp;
	
	public CouseEvalClp(ClassLoaderProxy clp) {
		this.clp = clp;
	}
		
	@Override
	@SuppressWarnings("deprecation")
	public long getTypeId() {
		Object returnObj = null;

		try {
			returnObj = clp.invoke("getTypeId",	new Object[] {});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Long)returnObj).longValue();
	}
		
	@Override
	@SuppressWarnings("deprecation")
	public String getName() {
		Object returnObj = null;

		try {
			returnObj = clp.invoke("getName", new Object[] {});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((String)returnObj);
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public String getName(Locale locale) {
		Object returnObj = null;

		try {
			Method getNameMethod = CourseEval.class.getMethod("getName", Locale.class); 
			returnObj = clp.invoke(new MethodHandler(getNameMethod, locale));
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((String)returnObj);
	}
	
	@SuppressWarnings("deprecation")
	private Object translateCourse(Course course) {
		Object courseObj = null;
		try {
			courseObj = Class.forName(CourseClp.class.getName(), true, clp.getClassLoader()).newInstance();
			
			ClassLoaderProxy clp2 = new ClassLoaderProxy(courseObj, CourseClp.class.getName(), clp.getClassLoader());
			clp2.invoke("setModelAttributes", new Object[] {course.getModelAttributes()});
		} catch(ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
		return courseObj;
	}
	
	@SuppressWarnings("deprecation")
	private Object translateModuleResult(ModuleResult moduleResult) {
		Object moduleResultObj = null;
		try {
			moduleResultObj = Class.forName(ModuleResultClp.class.getName(), true, clp.getClassLoader()).newInstance();
			
			ClassLoaderProxy clp2 = new ClassLoaderProxy(moduleResultObj, ModuleResultClp.class.getName(), clp.getClassLoader());
			clp2.invoke("setModelAttributes", new Object[] {moduleResult.getModelAttributes()});
		} catch(ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
		return moduleResultObj;
	}
		
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	public void updateCourse(Course course, ModuleResult moduleResult)
			throws SystemException {
		try {
			ClassLoader classLoader = clp.getClassLoader();
			
			Class courseEvalClass = Class.forName(CourseEval.class.getName(),true, classLoader);
			Class courseClass = Class.forName(Course.class.getName(),true, classLoader);
			Class moduleResultClass = Class.forName(ModuleResult.class.getName(),true, classLoader);
			
			Method updateCourseMethod = courseEvalClass.getMethod("updateCourse",courseClass,moduleResultClass);    
			Object courseObj = translateCourse(course);
			Object moduleResultObj = translateModuleResult(moduleResult);
			clp.invoke(new MethodHandler(updateCourseMethod, courseObj, moduleResultObj));
			ClassLoaderProxy courseClassLoaderProxy = new ClassLoaderProxy(courseObj, clp.getClassLoader());
			course.setModelAttributes((Map<String, Object>) courseClassLoaderProxy.invoke("getModelAttributes", new Object[]{}));
			ClassLoaderProxy moduleResultClassLoaderProxy = new ClassLoaderProxy(moduleResultObj, clp.getClassLoader());
			moduleResult.setModelAttributes((Map<String, Object>) moduleResultClassLoaderProxy.invoke("getModelAttributes", new Object[]{}));
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);
						
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
		
	}
	
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	public boolean updateCourse(Course course,long userId)
			throws SystemException {
		
		try {
			Object returnObj = null;
			
			ClassLoader classLoader = clp.getClassLoader();
			
			Class courseEvalClass = Class.forName(CourseEval.class.getName(),true, classLoader);
			Class courseClass = Class.forName(Course.class.getName(),true, classLoader);
			
			Method updateCourseMethod = courseEvalClass.getMethod("updateCourse",courseClass,Long.class);    
			Object courseObj = translateCourse(course);
			returnObj = clp.invoke(new MethodHandler(updateCourseMethod, courseObj, userId));
			ClassLoaderProxy courseClassLoaderProxy = new ClassLoaderProxy(courseObj, clp.getClassLoader());
			course.setModelAttributes((Map<String, Object>) courseClassLoaderProxy.invoke("getModelAttributes", new Object[]{}));
			return ((Boolean)returnObj).booleanValue();
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);
						
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
		
	}
	
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	public boolean updateCourse(Course course)
			throws SystemException {
		
		try {
			Object returnObj = null;
			
			ClassLoader classLoader = clp.getClassLoader();
			
			Class courseEvalClass = Class.forName(CourseEval.class.getName(),true, classLoader);
			Class courseClass = Class.forName(Course.class.getName(),true, classLoader);
			
			Method updateCourseMethod = courseEvalClass.getMethod("updateCourse",courseClass,Long.class);    
			Object courseObj = translateCourse(course);
			returnObj = clp.invoke(new MethodHandler(updateCourseMethod, courseObj));
			ClassLoaderProxy courseClassLoaderProxy = new ClassLoaderProxy(courseObj, clp.getClassLoader());
			course.setModelAttributes((Map<String, Object>) courseClassLoaderProxy.invoke("getModelAttributes", new Object[]{}));
			return ((Boolean)returnObj).booleanValue();
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);
						
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
		
	}

	@Override
	@SuppressWarnings("deprecation")
	public boolean getNeedPassAllModules() {
		Object returnObj = null;

		try {
			returnObj = clp.invoke("getNeedPassAllModules",	new Object[] {});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Boolean)returnObj).booleanValue();
	}

	@Override
	@SuppressWarnings("deprecation")
	public boolean getNeedPassPuntuation() {
		Object returnObj = null;

		try {
			returnObj = clp.invoke("getNeedPassPuntuation",	new Object[] {});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Boolean)returnObj).booleanValue();
	}
	
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public long getPassPuntuation(Course course)
			throws DocumentException {
		Object returnObj = null;
		
		try {
			ClassLoader classLoader = clp.getClassLoader();
			Class courseEvalClass = Class.forName(CourseEval.class.getName(),true, classLoader);
			Class courseClass = Class.forName(Course.class.getName(),true, classLoader);
			Method getPassPuntuationMethod = courseEvalClass.getMethod("getPassPuntuation",courseClass);    
			Object courseObj = translateCourse(course);
			returnObj = clp.invoke(new MethodHandler(getPassPuntuationMethod, courseObj));

		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);
						
			if (t instanceof DocumentException) {
				throw (DocumentException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Number)returnObj).longValue();		
	}
	
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JSONObject getEvaluationModel(Course course)
			throws SystemException {
		Object returnObj = null;
		
		try {
			ClassLoader classLoader = clp.getClassLoader();
			
			Class courseEvalClass = Class.forName(CourseEval.class.getName(),true, classLoader);
			Class courseClass = Class.forName(Course.class.getName(),true, classLoader);
			
			Method getEvaluationModelMethod = courseEvalClass.getMethod("getEvaluationModel",courseClass);    
			Object courseObj = translateCourse(course);
			returnObj = clp.invoke(new MethodHandler(getEvaluationModelMethod, courseObj));
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);
						
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((JSONObject)returnObj);	
	}
	
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation"})
	public void setEvaluationModel(Course course,JSONObject model) 
			throws PortalException,SystemException,DocumentException,IOException {
		try {
			ClassLoader classLoader = clp.getClassLoader();
			Class courseEvalClass = Class.forName(CourseEval.class.getName(),true, classLoader);
			Class courseClass = Class.forName(Course.class.getName(),true, classLoader);
			
			Method setEvaluationModelMethod = courseEvalClass.getMethod("setEvaluationModel",courseClass,JSONObject.class);    
			Object courseObj = translateCourse(course);
			clp.invoke(new MethodHandler(setEvaluationModelMethod, courseObj, model));
			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(courseObj, clp.getClassLoader());
			course.setModelAttributes((Map<String, Object>) classLoaderProxy.invoke("getModelAttributes", new Object[]{}));
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);
			
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}
			
			if (t instanceof IOException) {
				throw (IOException)t;
			}
			
			if (t instanceof DocumentException) {
				throw (DocumentException)t;
			}
			
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

	}

}