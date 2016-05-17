package broker.iser.ruc.edu.cn;

public class Native {
	static{
		System.loadLibrary("text");
	}
	public native void Mod();
}
