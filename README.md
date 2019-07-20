
This application has been reversed engineering using [JADX](https://github.com/skylot/jadx) tool
----------------------
Android has Progruard for further protecting source code from being reversed engineering. 
But it just makes the process harder, rather than makes it impossible.

**After using JADX**

* Source code is quite readable.
* Graphic materials, database is recovered into original state! Seems there is no way to keep it from thief.

**Last result**

* After some trial & error, I can makes it run almost perfectly.
* Sensitive string is converted by ObsfucateString lib into byte and binary operators. But as long as you figure out what's lib they are using, everything is in your hand!

