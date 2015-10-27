package io.github.gariti.quickdirections;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import hotchemi.stringpicker.StringPicker;

public class QuickSearch extends AppCompatActivity {
    private static final String TAG = QuickSearch.class.getSimpleName();
    private EditText mAddressField;
    private StringPicker pickModeWheel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_search);

        mAddressField = (EditText) findViewById(R.id.addressInput);
        Button goButton = (Button) findViewById(R.id.goButton);
        pickModeWheel = (StringPicker) findViewById(R.id.pickMode);

        String[] modes = new String[] {"Public Transport","Car","Bicycle","Walk"};
        pickModeWheel.setValues(modes);

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchInput = Uri.encode(mAddressField.getText().toString());
                startGoogleMaps(searchInput, getUriflag());
            }
        });
    }
    private void startGoogleMaps(String searchInput, String uriFlag) {
        Intent googleMaps = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?daddr=" + searchInput + "&dirflg="+ uriFlag));
        googleMaps.setPackage("com.google.android.apps.maps");
        startActivity(googleMaps);
    }
    private String getUriflag() {
        //get uri code based on picker wheel choice
        String modeSelected;
        switch (pickModeWheel.getCurrentValue()) {
            case "Public Transport":
                modeSelected = "r";
                break;
            case "Car":
                modeSelected = "d"; //test this!
                break;
            case "Bicycle":
                modeSelected = "b";
                break;
            case "Walk":
                modeSelected = "w";
                break;
            default:
                modeSelected = "r";
        }
        return modeSelected;
    }
}
