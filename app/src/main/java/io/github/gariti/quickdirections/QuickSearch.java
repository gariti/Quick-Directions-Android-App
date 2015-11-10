package io.github.gariti.quickdirections;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hotchemi.stringpicker.StringPicker;

public class QuickSearch extends AppCompatActivity {
    private static final String TAG = QuickSearch.class.getSimpleName();
    @Bind(R.id.addressInput) EditText mAddressINput;
    @Bind(R.id.pickTransportType) StringPicker mPickTransportType;
    @Bind(R.id.submitButton) Button mSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_search);
        ButterKnife.bind(this);

        String[] modes = new String[] {"Public Transport","Car","Bicycle","Walk"};
        mPickTransportType.setValues(modes);
    }
    private void startGoogleMaps(String searchInput, String uriFlag) {
        Intent googleMaps = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?daddr=" + searchInput + "&dirflg="+ uriFlag));
        googleMaps.setPackage("com.google.android.apps.maps");
        startActivity(googleMaps);
    }
    private String getUriflag() {
        //get uri code based on picker wheel choice
        String modeSelected;
        switch (mPickTransportType.getCurrentValue()) {
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
    @OnClick(R.id.submitButton)
    public void submit(View view) {
        String searchInput = Uri.encode(mAddressINput.getText().toString());
        startGoogleMaps(searchInput, getUriflag());
    }
}
