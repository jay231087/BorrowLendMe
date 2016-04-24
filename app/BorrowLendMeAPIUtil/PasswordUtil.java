package BorrowLendMeAPIUtil;

import static java.lang.Math.abs;
import static java.lang.Math.min;
import static java.lang.Math.pow;
import static java.lang.Math.random;
import static java.lang.Math.round;
import static org.apache.commons.lang.StringUtils.leftPad;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;

public class PasswordUtil {
	
	public static String generatePin() {
		int n = randomPin(5, 5);
		byte b[] = new byte[n];
		for (int i = 0; i < n; i++) {
			b[i] = (byte)randomPin('1', '9');
		}
		return new String(b, 0);
	}

	public static boolean pinValid(String pin) {
		return ((pin != null) && (pin.length() == 5));
	}

	private static int randomPin(int s, int e){
		Random rendom = new Random();
		int n = s - e + 1;
		int i = rendom.nextInt() % n;
		if (i < 0) {
			i = -i;
		}
		return s + i;
	}
	public static String encrypt(String password) {
		MessageDigest digester = null;
		try {
			digester = MessageDigest.getInstance("SHA-1");
			digester.update(password.getBytes("UTF-8"));
			byte[] bytes = digester.digest();
			String newPassword = byteArrayToHexString(bytes);
			return newPassword;
		} catch (NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
		
	public static String byteArrayToHexString(byte[] b){
		StringBuffer sb=new StringBuffer(b.length *2);
		for(int i=0;i<b.length;i++){
			int v=b[i] & 0xff;
			if(v<16){
				sb.append('0');
			}
			sb.append(Integer.toHexString(v));
		}
		return sb.toString().toUpperCase();				
	}
	
	public static String gen(int length) {
		StringBuffer sb = new StringBuffer();
		for (int i = length; i > 0; i -= 12) {
			int n = min(12, abs(i));
			sb.append(leftPad(Long.toString(round(random() * pow(36, n)), 36),n, '0'));
		}
		return sb.toString();
	}
	
	public static String getResetPasswordToken() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
