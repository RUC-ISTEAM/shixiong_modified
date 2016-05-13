package target.iser.ruc.edu.cn;

import android.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class SecondActivity extends Activity{
	static {
		Log.d("second", "second");
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.d("second", "start");
		setContentView(target.iser.ruc.edu.cn.R.layout.activity_second);
	}
}
