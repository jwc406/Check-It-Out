package com.example.thecheck;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.EditText;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;

public class PopupActivity_addLecture extends Activity {

    EditText tv_newlecture, tv_category, tv_deadline, tv_newtime,tv_newnumclass, tv_url;
    Button bt_addlecture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_addlecture);

        //UI 객체생성
        tv_newlecture = (EditText)findViewById(R.id.newLecture);
        tv_category = (EditText)findViewById(R.id.newCategory);
        tv_deadline = (EditText)findViewById(R.id.newDeadline);
        tv_newtime = (EditText)findViewById(R.id.newTime);
        tv_newnumclass = (EditText)findViewById(R.id.newNumcalss);
        tv_url = (EditText)findViewById(R.id.newUrl);
        bt_addlecture = (Button)findViewById(R.id.bt_addlecture);
        bt_addlecture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //데이터 전달하고 액티비티 닫기
                String newlecture = tv_newlecture.getText().toString();
                String newcategory = tv_category.getText().toString();
                String newdeadline = tv_deadline.getText().toString();
                String newtime = tv_newtime.getText().toString();
                String numclass = tv_newnumclass.getText().toString();
                String url = tv_url.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("lecture", newlecture);
                intent.putExtra("category", newcategory);
                intent.putExtra("deadline", newdeadline);
                intent.putExtra("time", newtime);
                intent.putExtra("numclass", numclass);
                intent.putExtra("url", url);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

}

