package im.het.tiny.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by het on 16/3/29.
 */
public final class ReflectUtil {
    private ReflectUtil() {
    }

    /**
     * 获取指定类的声明方法
     *
     * @param aClass      指定类
     * @param methodName  方法名
     * @param aParamTypes 参数类型
     *
     * @return 指定类的方法
     */
    public static Method getMethod(Class<?> aClass, String methodName, Class<?>... aParamTypes) {
        try {
            Method method = aClass.getDeclaredMethod(methodName, aParamTypes);
            method.setAccessible(true);
            return method;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 调用指定对象的方法
     *
     * @param aMethod       指定的方法
     * @param aObject       指定的对象
     * @param aDefaultValue 默认返回值
     * @param aArgs         方法传递参数
     *
     * @return 方法返回值
     */
    public static Object invoke(Method aMethod, Object aObject, Object aDefaultValue, Object... aArgs) {
        try {
            return aMethod.invoke(aObject, aArgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aDefaultValue;
    }

    /**
     * 获取指定类对象的属性
     *
     * @param aClass      类对象
     * @param aFileldName 属性名
     *
     * @return 属性
     */
    public static Field getField(Class<?> aClass, String aFileldName) {
        try {
            Field field = aClass.getDeclaredField(aFileldName);
            field.setAccessible(true);
            return field;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取指定类对象的属性值
     *
     * @param aField        属性
     * @param aObject       类对象
     * @param aDefaultValue 默认值
     *
     * @return 属性值
     */
    public static Object getFieldValue(Field aField, Object aObject, Object aDefaultValue) {
        try {
            return aField.get(aObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aDefaultValue;
    }

    /**
     * 获取指定类对象的属性值
     *
     * @param aObject       类对象
     * @param aFieldName    属性名
     * @param aDefaultValue 默认值
     *
     * @return 属性值
     */
    public static Object getFieldValue(Object aObject, String aFieldName, Object aDefaultValue) {
        Field field = getField(aObject.getClass(), aFieldName);
        if (field != null) {
            return getFieldValue(field, aObject, aDefaultValue);
        }
        return aDefaultValue;
    }

    /**
     * 设置指定类对象的属性值
     *
     * @param aField  类对象
     * @param aObject 属性名
     * @param aValue  属性值
     *
     * @return 设置成功则返回true，否则返回false
     */
    public static boolean setFieldValue(Field aField, Object aObject, Object aValue) {
        try {
            aField.set(aObject, aValue);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 设置指定类对象的属性值
     *
     * @param aObject    类对象
     * @param aFieldName 属性名
     * @param aValue     属性值
     *
     * @return 如果设置成功则返回true，否则返回false
     */
    public static boolean setFieldValue(Object aObject, String aFieldName, Object aValue) {
        Field field = getField(aObject.getClass(), aFieldName);
        if (field != null) {
            return setFieldValue(field, aObject, aValue);
        }
        return false;
    }

    /**
     * 创建类实例
     *
     * @param aClass      所属类
     * @param aParamTypes 构造方法参数类型
     * @param aArgs       构造方法参数
     *
     * @return 创建的类实例
     */
    public static Object newInstance(Class<?> aClass, Class<?>[] aParamTypes, Object[] aArgs) {
        try {
            Constructor<?> constructor = aClass.getDeclaredConstructor(aParamTypes);
            constructor.setAccessible(true);
            return constructor.newInstance(aArgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
