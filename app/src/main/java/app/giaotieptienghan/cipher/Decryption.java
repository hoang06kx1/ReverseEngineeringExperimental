package app.giaotieptienghan.cipher;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

public class Decryption {
    /* renamed from: a */
    String f1990e;
    Cipher f1988c;
    SecretKey f1987b;
    public Decryption(String str) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, InvalidKeySpecException, UnsupportedEncodingException {
        this.f1990e = str;
        byte[] f1986a = this.f1990e.getBytes("UTF8");
        String f1991f = "DESede";
        /* renamed from: b */
        KeySpec f1989d = new DESedeKeySpec(f1986a);
        SecretKeyFactory f1992g = SecretKeyFactory.getInstance(f1991f);
        f1987b = f1992g.generateSecret(f1989d);
        f1988c = Cipher.getInstance(f1991f);
    }

    /* renamed from: a */
    public String decrypt(String str) {
        try {
            this.f1988c.init(2, this.f1987b);
            return new String(this.f1988c.doFinal(Base64.decode(str, 0)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
