package broker.iser.ruc.edu.cn;

import java.lang.reflect.Method;
import java.lang.reflect.Field;

import android.content.Context;

public class Reflect {

	public static Object invokeMethod(String className, String methodName, Object receiver, Object...args) {
		Object result = null;
		
		Class<?> [] argTypes = null;
		if (args != null) { 
			argTypes = new Class<?>[args.length ];
			for (int i = 0; i < args.length; i++) {
				if (args[i] instanceof Context) argTypes[i] = Context.class;
				else argTypes[i] = args[i].getClass();
			}
		}
		
		try {
			Class<?> clazz = Class.forName(className);
			System.out.println("Class: " + clazz);
			Method method = clazz.getDeclaredMethod(methodName, argTypes);
			System.out.println("Method: " + method);
			method.setAccessible(true);
			result = method.invoke(receiver, args);
			System.out.println("result: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static Object getField(String className, String fieldName, Object receiver) {
		Object result = null;
		
		try {
			Class<?> clazz = Class.forName(className);
			System.out.println("Class: " + clazz);
			Field field = clazz.getDeclaredField(fieldName);
			System.out.println("Field: " + field);
			field.setAccessible(true);
			result = field.get(receiver);
			System.out.println("result: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static void setField(String className, String fieldName, Object receiver, Object value) {
		try {
			Class<?> clazz = Class.forName(className);
			System.out.println("Class: " + clazz);
			Field field = clazz.getDeclaredField(fieldName);
			System.out.println("Field: " + field);
			field.setAccessible(true);
			field.set(receiver, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
