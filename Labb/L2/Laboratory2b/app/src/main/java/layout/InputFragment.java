package layout;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mah.ag0071.laboratory2b.Controller;
import com.mah.ag0071.laboratory2b.R;


public class InputFragment extends Fragment {

    private Controller controller;
    private Button btnNext;
    private Button btnPrev;
    private TextView tvWhatToDo;
    private TextView tvContent;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);
        controller = new Controller(this);
        initializeComponents(view);
        initializeResources();
        return view;
    }

    private void initializeResources() {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.nextClick();
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.previousClick();
            }
        });

    }

    private void initializeComponents(View view){
        btnNext = (Button) view.findViewById(R.id.btnNext);
        btnPrev = (Button) view.findViewById(R.id.btnPrev);
        tvWhatToDo = (TextView) view.findViewById(R.id.tvWhatToDo);
        tvContent = (TextView) view.findViewById(R.id.tvContent);
    }

    public void setWhatToDo(String whatToDo) {
        tvWhatToDo.setText(whatToDo);
    }

    public void setContent(String content){
        tvContent.setText(content);
    }
}
