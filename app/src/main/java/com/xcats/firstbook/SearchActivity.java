package com.xcats.firstbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by alfs on 2/20/16.
 */
public class SearchActivity extends Activity {
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    String subTeam = "None";

    EditText name;
    EditText teamNum;
    TextView subTeamMarker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        RadioButton unknown = (RadioButton)findViewById(R.id.unknown);
        unknown.setChecked(true);

        subTeamMarker = (TextView)findViewById(R.id.subteamMarker);
        name = (EditText)findViewById(R.id.search_name);
        teamNum = (EditText)findViewById(R.id.team_num);




        spinner = (Spinner)findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.subteam_names, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                subTeam = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        subTeamMarker.setVisibility(View.GONE);
        spinner.setVisibility(View.GONE);
    }

    public void search(View view){

        int selection = ((RadioGroup)findViewById(R.id.types)).getCheckedRadioButtonId();
        String type = ((RadioButton)findViewById(selection)).getText().toString().trim();
        String studentName = name.getText().toString();
        String studentNum = teamNum.getText().toString();

        Intent search = new Intent(this, SearchPartTwoActivity.class);
        search.putExtra("name", studentName);
        search.putExtra("teamNum", studentNum);
        search.putExtra("subTeam", subTeam);
        search.putExtra("type", type);
        this.startActivity(search);
    }
}
