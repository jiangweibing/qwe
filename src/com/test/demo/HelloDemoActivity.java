package com.test.demo;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.iflytek.speech.RecognizerResult;
import com.iflytek.speech.SpeechConfig.RATE;
import com.iflytek.speech.SpeechError;
import com.iflytek.ui.RecognizerDialog;
import com.iflytek.ui.RecognizerDialogListener;

 
public class HelloDemoActivity extends Activity implements OnClickListener {
	protected static final String TAG = "ThirdActivity";
	private EditText txt_result;
	private RecognizerDialog rd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.  layout.demo);
		
		findView();
		//RecognizerDialog(Context context, String params); "appid=1234567,usr=test,pwd=12345"  usr��pwd���Ǳ�ѡ��
		//��������ʶ��dailog����appid��Ѷ�ɾ�ע���ȡ
		rd = new RecognizerDialog(this ,"appid=5791bf10");
	}

	private void findView() {
		txt_result = (EditText) findViewById(R.id.txt_result);
		findViewById(R.id.bt_search).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_search:
			showReconigizerDialog();
			break;

		default:
			break;
		}
	}

	private void showReconigizerDialog() {
		//setEngine(String engine,String params,String grammar);
		/**
		 * ʶ������ѡ��Ŀǰ֧����������
			��sms������ͨ�ı�תд
			��poi������������
			��vsearch�����ȴ�����
			��vsearch�����ȴ�����
			��video������Ƶ��������
			��asr���������ʶ��
			
			params	������������б�
			���Ӳ����б�ÿ���м��Զ��ŷָ������ڵ�ͼ����ʱ��ָ���������򣺡�area=����ʡ�Ϸ��С����޸��Ӳ�����null
		 */
		rd.setEngine("sms", null, null);
		
		//���ò���Ƶ�ʣ�Ĭ����16k��android�ֻ�һ��ֻ֧��8k��16k.Ϊ�˸��õ�ʶ��ֱ��Ū��16k���ɡ�
		rd.setSampleRate(RATE.rate16k);
		
		final StringBuilder sb = new StringBuilder();
		Log.i(TAG, "ʶ��׼����ʼ.............");
		
		//����ʶ���Ļص����
		rd.setListener(new RecognizerDialogListener() {
			@Override
			public void onResults(ArrayList<RecognizerResult> result, boolean isLast) {
				for (RecognizerResult recognizerResult : result) {
					sb.append(recognizerResult.text);
					Log.i(TAG, "ʶ��һ�����Ϊ::"+recognizerResult.text);
				}
			}
			@Override
			public void onEnd(SpeechError error) {
				Log.i(TAG, "ʶ�����.............");
				txt_result.setText(sb.toString());
				Log.i(TAG, "ʶ�����:"+txt_result.getText().toString());
			}
		});
		
		txt_result.setText(""); //������Ϊ�գ���ʶ����ɺ���������
		rd.show();
	}

}