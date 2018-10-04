package app.giaotieptienghan

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import app.giaotieptienghan.cipher.Decryption
import app.giaotieptienghan.cipher.ObfuscatedString


class MainActivity : AppCompatActivity() {
    private var f1950d: Decryption? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.f1950d = Decryption(ObfuscatedString(longArrayOf(54233370504433150L, 4898902494104535733L, -5807597149084756791L, 8156855886985688596L)).toString())
        Log.d("Decryption", this.f1950d!!.decrypt("jxZnfVBpuzX27vCAJ9zn9l83VBGeXPFa"))
        Log.d("Decryption", this.f1950d!!.decrypt("HIWTkTesIqjUh1AyDKl+ReVIBqovx1K6"))
        Log.d("Decryption", this.f1950d!!.decrypt("rQ6wCcvuWzSUYHS2EVAHCg=="))
        Log.d("Decryption", this.f1950d!!.decrypt("YQDJY+lGjEDM1Aj7nyuduQ=="))
        Log.d("Decryption", this.f1950d!!.decrypt("pWwJnaQyK7zgBQq/wfH6iA=="))
    }
}
