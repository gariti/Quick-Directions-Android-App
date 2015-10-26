package io.github.gariti.quickdirections;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class QuickSearch extends AppCompatActivity {
    private EditText mAddressField;
    private Button mGoButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_search);

        mAddressField = (EditText) findViewById(R.id.addressInput);
        mGoButton = (Button) findViewById(R.id.goButton);

        mGoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchInput = Uri.encode(mAddressField.getText().toString());

                startGoogleMaps(searchInput);
            }
        });
    }
    private void startGoogleMaps(String searchInput) {
        Intent googleMaps = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?daddr=" + searchInput + "&dirflg=r"));
        googleMaps.setPackage("com.google.android.apps.maps");
        startActivity(googleMaps);
    }
}
