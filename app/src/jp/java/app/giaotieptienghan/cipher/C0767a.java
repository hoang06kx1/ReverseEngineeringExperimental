package app.giaotieptienghan.cipher;

import android.util.Base64;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/* renamed from: com.example.english.c.a */
public class C0767a {
    /* renamed from: a */
    private Cipher f1955a = null;
    /* renamed from: b */
    private SecretKey f1956b = null;

    public C0767a() {
        try {
            this.f1956b = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec("fuck_you".getBytes("UTF8")));
            this.f1955a = Cipher.getInstance("DES");
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        } catch (NoSuchAlgorithmException e3) {
            e3.printStackTrace();
        } catch (InvalidKeySpecException e4) {
            e4.printStackTrace();
        } catch (NoSuchPaddingException e5) {
            e5.printStackTrace();
        }
    }

    /* renamed from: a */
    public String mo2891a(String str) {
        byte[] decode = Base64.decode(str, 0);
        try {
            this.f1955a.init(2, this.f1956b);
            return new String(this.f1955a.doFinal(decode), "UTF8");
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalBlockSizeException e2) {
            e2.printStackTrace();
            return null;
        } catch (BadPaddingException e3) {
            e3.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e4) {
            e4.printStackTrace();
            return null;
        }
    }
}
