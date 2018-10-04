

   /*
    * Copyright (C) 2005-2015 Schlichtherle IT Services.
    * All rights reserved. Use is subject to license terms.
    */

   package app.giaotieptienghan.cipher;

   import java.nio.ByteBuffer;
   import java.nio.charset.Charset;
   import java.util.Arrays;
   import java.util.Random;
   import java.util.concurrent.Callable;

   /**
    * A thread-safe representation of an obfuscated string.
    * <p>
    * <h3>Motivation</h3>
    * <p>
    * Software developers may wish to apply some licensing scheme to protect their
    * Intellectual Property (IP).
    * When implementing the licensing scheme, it is vital to obfuscate the Java
    * byte code.
    * Otherwise, a reverse engineer can simply replace the licensing code with a
    * stub in order to break the licensing scheme.
    * <p>
    * Likewise, all constant string expressions which are used to interact with
    * the licensing code need to get obfuscated, too.
    * Otherwise, a reverse engineer can simply search for known strings in the
    * Java byte code in order to identify the obfuscated licensing code or replace
    * its key strings with self-generated key strings.
    * <p>
    * <h3>Usage</h3>
    * <p>
    * This class specifically addresses the need to obfuscate constant string
    * expressions in Java byte code.
    * Note that general Java byte code obfuscation is out of scope - there are
    * other tools required to do this!
    * <p>
    * To obfuscate a constant string expression you need to pass it to the static
    * method {@link #obfuscate} at design time and replace it with the generated
    * Java source code expression.
    * At runtime, a duplicate of the original constant string expression will get
    * computed by the Java source code expression.
    * If you need to preserve the identity relation of constant string expressions,
    * then you need to add a call to {@link String#intern}()} to the duplicated
    * string.
    * <p>
    * A tool for Java byte code transformation (e.g. the TrueLicense Maven Plugin)
    * may automate some or all of these steps.
    * However, it needs to call {@link #array} instead of {@link #obfuscate} at
    * build time.
    * <p>
    * <h3>Security Considerations</h3>
    * <p>
    * Note that obfuscation is <em>not</em> equal to encryption:
    * In contrast to the application of the simple and cheap obfuscation scheme in
    * this class, strong encryption would be comparably slow and expensive in
    * terms of resources - no matter what encryption algorithm would actually get
    * used.
    * Moreover, encrypting constant string expressions in Java byte code does not
    * effectively increase the security level when compared to simple obfuscation.
    * This is because an encryption key still needs to be made available to the
    * Java byte code and so could get eavesdropped by tracing JVM calls with the
    * standard JDK tool chain.
    * Thus, the simple obfuscation scheme implemented by this class is preferred
    * over complex encryption.
    * Conversely, if someone is proposing to encrypt constant string expressions
    * then please check if (s)he is trying to sell
    * <a href="">snake oil</a>.
    *
    * @author Christian Schlichtherle
    */
   // All constant string expressions in this class which are longer than two
   // characters need to get obfuscated as either an ObfuscatedString or, where
   // this would result in a stack overflow, as an array of Unicode characters.
   // This should help to prevent location of this class in obfuscated Java byte
   // code.
   public final class ObfuscatedString {
       /**
        * The UTF-8 character set.
        */
       private static final Charset charset = Charset.forName(
               new String(new char[]{'\u0055', '\u0054', '\u0046', '\u0038'})); // => "UTF8"

       /**
        * Returns a string containing an obfuscated Java source code expression
        * which computes a duplicate of the given string again.
        * This is equivalent to calling
        * <code>{@link #java}(long[])}({@link #array}(String)}(s))</code>.
        * <p>
        * As an example, calling this method with {@code "Hello world!"} as
        * its parameter may produce the result
        * {@code "new net.truelicense.obfuscate.runtime.ObfuscatedString(new long[] {
        * 0x39e61a665e397740l, 0xb681642064a96eael, 0xb8eb509886cc10f9l }).toString()"}.
        * If this Java source code is compiled and executed, it will
        * reproduce the original string {@code "Hello world!"} again.
        *
        * @param s the string to obfuscate.
        *          This may not contain null characters.
        * @return Some obfuscated Java source code to produce the given string
        * again.
        * @throws IllegalArgumentException If {@code s} contains a null character.
        */
       public static String obfuscate(final String s) {
           return java(array(s));
       }

       /**
        * Returns a string containing an obfuscated Java source code expression
        * which computes a duplicate from the given array representation of the
        * original string.
        *
        * @param obfuscated the obfuscated array representation of the original
        *                   string.
        * @return a string containing an obfuscated Java source code expression
        * which computes a duplicate from the given array representation
        * of the original string.
        */
       public static String java(final long[] obfuscated) {
           // Create and initialize a string builder to hold the generated Java
           // source code.
           final StringBuilder code = new StringBuilder(new ObfuscatedString(new long[]{0x863f69cc8f495927l, 0x894754c8409c2b0cl, 0x64666bb6f4cda0el, 0x9b695ff4e3921eecl, 0x1e9addfdf2963b0al, 0x5e7912c8c72d6693l, 0xa9ed4173e692aa6bl, 0xaaf5ddee591bb444l, 0xc71fddd23ad5e640l}).toString());

           // Append the seed as the first array element of the obfuscated string.
           // All values are represented in hexadecimal in order to keep the
           // Java source code as short as possible.
           appendHexLiteral(code, obfuscated[0]);
           for (int i = 1; i < obfuscated.length; i++)
               appendHexLiteral(code.append(", "), obfuscated[i]);

           return code
                   .append(new ObfuscatedString(new long[]{0xc88560ca767220a0l, 0xbafc124667dcdcd1l, 0x386510ee9b166120l, 0x9169ff211fbee2d6l}).toString())
                   .append(literal(new ObfuscatedString(obfuscated).toString()).replace("*/", "*\\/")) // escape */ in comment
                   .append(new ObfuscatedString(new long[]{0xd5652c246ddf207fl, 0x9320596ee2d22c6l}).toString())
                   .toString();
       }

       /**
        * Encodes the given string to a valid string literal in Java source code.
        *
        * @param s the string to quote and escape.
        * @return The transformed string.
        */
       public static String literal(String s) {
           return '"' +
                   s.replace("\\", new ObfuscatedString(new long[]{0x7039332787b3746el, 0xc0b41fa378b58968l}).toString())
                           .replace("\"", new ObfuscatedString(new long[]{0xb5008b1f587b395bl, 0x32d85afbb209412cl}).toString()) +
                   '"';

       }

       /**
        * Returns an obfuscated array representation of the given string.
        * Obfuscation is performed by encoding the given string into UTF8 and then
        * XOR-ing a sequence of pseudo random numbers to it.
        * The result is decoded into an array of long integers as the obfuscated
        * array representation of the given string.
        * The sequence of pseudo random numbers is seeded with a random
        * number in order to provide a non-deterministic result for the obfuscated
        * array representation.
        * Hence, two subsequent calls with an equal string will produce equal
        * results by a chance of only 1/(2<sup>48</sup> - 1) - zero is not used as
        * a seed.
        *
        * @param s the string to obfuscate.
        *          This may not contain null characters.
        * @return an obfuscated array representation for the given string.
        * @throws IllegalArgumentException If {@code s} contains a null character.
        */
       public static long[] array(final String s) {
           if (-1 != s.indexOf(0)) {
               throw new IllegalArgumentException(new ObfuscatedString(new long[]{0x28cadfbe1893c3bl, 0xea4838eea01492f5l, 0x1f4255b3fff19724l, 0x4302783991533b0cl, 0x2b3b931b9908c7a4l}).toString());
           }

           // Encode the string to a byte array using UTF-8.
           final byte[] encoded = s.getBytes(charset);

           // Create the array to hold the result.
           final int el = encoded.length;
           final long[] obfuscated = new long[1 + (el + 7) / 8];

           // Obtain a random long as the key, seed a new PRNG with it and save it.
           final long key = key();
           final Random prng = new Random(key);
           obfuscated[0] = key;

           // Obfuscate the encoded string.
           // This will produce some null high order bytes in the last array
           // element iff the string was not encoded to a multiple of eight bytes.
           for (int i = 0, j = 0; i < el; i += 8)
               obfuscated[++j] = decode(encoded, i) ^ prng.nextLong();

           return obfuscated;
       }

       private static long key() {
           final Random prng = new Random();
           for (long key; ; ) if (0 != (key = prng.nextLong())) return key;
       }

       private static void appendHexLiteral(final StringBuilder sb, final long l) {
           // Obfuscation of the following character literals is futile because
           // they are too short to get found with a string search anyway.
           sb.append('\u0030').append('\u0078')
                   .append(Long.toHexString(l)).append('\u006C'); // => "0x" + ... + "l"
       }

       /**
        * Decodes a long value from eight bytes in little endian order,
        * beginning at index {@code off}.
        * This is the inverse of {@link #encode(long, byte[], int)}.
        * If less than eight bytes are remaining in the array,
        * only these low order bytes are processed and the complementary high
        * order bytes of the returned value are set to zero.
        *
        * @param bytes the array containing the bytes to decode in little endian
        *              order.
        * @param off   the offset of the bytes in the array.
        * @return The decoded long value.
        */
       private static long decode(final byte[] bytes, final int off) {
           final int end = Math.min(bytes.length, off + 8);
           long value = 0;
           for (int i = end; --i >= off; ) {
               value <<= 8;
               value |= bytes[i] & 0XFF;
           }
           return value;
       }

       /**
        * Encodes a long value to eight bytes in little endian order,
        * beginning at index {@code off}.
        * This is the inverse of {@link #decode}(byte[], int)}.
        * If less than eight bytes are remaining in the array,
        * only these low order bytes of the long value are processed and the
        * complementary high order bytes are ignored.
        *
        * @param value the long value to encode.
        * @param bytes the array which holds the encoded bytes upon return.
        * @param off   the offset of the bytes in the array.
        */
       private static void encode(long value, final byte[] bytes, final int off) {
           final int end = Math.min(bytes.length, off + 8);
           for (int i = off; i < end; i++) {
               bytes[i] = (byte) value;
               value >>= 8;
           }
       }

       private final long[] obfuscated;

       /**
        * Constructs an obfuscated string from the given array.
        *
        * @param obfuscated the array representation of the obfuscated string.
        * @throws ArrayIndexOutOfBoundsException If the provided array does not
        *                                        contain at least one element.
        * @see #toString
        */
       public ObfuscatedString(final long[] obfuscated) {
           this.obfuscated = obfuscated.clone();
           this.obfuscated[0] = obfuscated[0]; // fail fast check
       }

       /**
        * Reproduces a copy of the original string from the obfuscated array
        * representation provided to the constructor.
        *
        * @return A copy of the original string in a new char array.
        */
       public char[] toCharArray() {
           return new Codec<char[]>() {
               @Override
               char[] decode(byte[] encoded, int length) throws Exception {
                   return charset
                           .newDecoder()
                           .decode(ByteBuffer.wrap(encoded, 0, length))
                           .array();
               }
           }.call();
       }

       /**
        * Reproduces a copy of the original string from the obfuscated array
        * representation provided to the constructor.
        *
        * @return A copy of the original string.
        */
       @Override
       public String toString() {
           return new Codec<String>() {
               @Override
               String decode(byte[] encoded, int length) throws Exception {
                   return new String(encoded, 0, length, charset);
               }
           }.call();
       }

       /**
        * Equivalent to
        * <code>{@link #toString}() toString()}.{@link String#intern}() intern()}</code>.
        *
        * @return A canonical representation of the original string.
        */
       public String toStringIntern() {
           return toString().intern();
       }

       @Override
       protected void finalize() throws Throwable {
           Arrays.fill(obfuscated, 0L);
           super.finalize();
       }

       private abstract class Codec<V> implements Callable<V> {
           @Override
           public V call() {
               // Create the array to hold the encoded string.
               final long[] obfuscated = ObfuscatedString.this.obfuscated;
               final int ol = obfuscated.length;
               final byte[] encoded = new byte[8 * (ol - 1)];

               // Load the key and seed a new PRNG with it.
               final long key = obfuscated[0];
               final Random prng = new Random(key);

               // Encode the obfuscated string.
               // This will leave some null bytes at the end of the array iff the
               // original string was not encoded to a multiple of eight bytes.
               for (int i = 1; i < ol; i++)
                   encode(obfuscated[i] ^ prng.nextLong(), encoded, 8 * (i - 1));

               // Cut-off any null bytes and decode the string from the byte array
               // using UTF-8.
               int el = encoded.length;
               for (int j = el; 0 < j && 0 == encoded[--j]; el = j) {
               }

               try {
                   return decode(encoded, el);
               } catch (final RuntimeException ex) {
                   throw ex;
               } catch (final Exception ex) {
                   throw new IllegalStateException(ex);
               } finally {
                   Arrays.fill(encoded, 0, el, (byte) 0);
               }
           }

           abstract V decode(byte[] encoded, int length) throws Exception;
       }
   }

