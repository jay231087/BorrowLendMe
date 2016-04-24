package BorrowLendMeAPIUtil;

import org.apache.log4j.Logger;

public class GeneralUtil {
	
	public static boolean isEmpty(Object object) {
		if (null != object && !"".equals(object)) {
			return false;
		}
		return true;
	}

	public static boolean isNull(Object object) {
		if (null != object) {
			return false;
		}
		return true;
	}
	
	public static <T> void logStackTrace(final Throwable t, final Class<T> className) {
		StackTraceElement [] elements = t.getStackTrace();
		Logger log = Logger.getLogger(className);
		StringBuilder builder = new StringBuilder();
		builder.append(t.getMessage()).append("\n\t");
		for (StackTraceElement element : elements) {
			builder.append(element.getClassName()).append("-");
			builder.append(element.getMethodName()).append("()-Line Number:");
			builder.append(element.getLineNumber()).append("\n\t");
		}
		log.error(builder);
	}
}
