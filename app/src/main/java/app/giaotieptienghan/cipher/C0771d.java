package app.giaotieptienghan.cipher;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Callable;

/* renamed from: com.example.english.c.d */
public final class C0771d {
    /* renamed from: a */
    private static final Charset f1965a = Charset.forName(new String(new char[]{'U', 'T', 'F', '8'}));
    /* renamed from: b */
    private final long[] f1966b;

    /* renamed from: com.example.english.c.d$a */
    private abstract class C0770a<V> implements Callable<V> {
        private C0770a() {
        }

        /* synthetic */ C0770a(C0771d c0771d, C07721 c07721) {
            this();
        }

        /* renamed from: b */
        abstract V mo2892b(byte[] bArr, int i);

        public V call() {
            long[] a = C0771d.this.f1966b;
            int length = a.length;
            byte[] bArr = new byte[((length - 1) * 8)];
            Random random = new Random(a[0]);
            for (int i = 1; i < length; i++) {
                C0771d.m3000b(a[i] ^ random.nextLong(), bArr, (i - 1) * 8);
            }
            int length2 = bArr.length;
            while (length2 > 0) {
                int i2 = length2 - 1;
                if (bArr[i2] == (byte) 0) {
                    length2 = i2;
                }
            }
            try {
                V b = mo2892b(bArr, length2);
                Arrays.fill(bArr, 0, length2, (byte) 0);
                return b;
            } catch (RuntimeException e) {
                throw e;
            } catch (Throwable e2) {
                throw new IllegalStateException(e2);
            } finally {
                Arrays.fill(bArr, 0, length2, (byte) 0);
            }
        }
    }

    /* renamed from: com.example.english.c.d$1 */
    class C07721 extends C0770a<String> {
        C07721() {
            super(C0771d.this, null);
        }

        /* renamed from: a */
        String mo2892b(byte[] bArr, int i) {
            return new String(bArr, 0, i, C0771d.f1965a);
        }
    }

    public C0771d(long[] jArr) {
        this.f1966b = (long[]) jArr.clone();
        this.f1966b[0] = jArr[0];
    }

    /* renamed from: b */
    private static void m3000b(long j, byte[] bArr, int i) {
        int min = Math.min(bArr.length, i + 8);
        while (i < min) {
            bArr[i] = (byte) ((int) j);
            j >>= 8;
            i++;
        }
    }

    public String toString() {
        return (String) new C07721().call();
    }
}

