package rana.schoit;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;

import java.io.PrintWriter;


public class CardFlipActivity extends Activity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private static final int PLACE_PICKER_REQUEST = 1;
    GoogleApiClient mGoogleApiClient;
    Location lastLocation;
    private boolean mshowingback = false;
    private int PLACE_PICKER_REQUEST_TUTOR=2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.startscreen);


        if (savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new CardFrontFragment())
                    .commit();
        }
        buildGoogleApiClient();
        mGoogleApiClient.connect();
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .build();

    }

    public void tutor_near_by(View view) {

        getFragmentManager().beginTransaction()
                .setCustomAnimations(
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out
                )
                .replace(R.id.container, new CardBackFragmentSearchTutor())
                .addToBackStack(null)
                .commit();
    }

    public void school_near_by(View view) {

        getFragmentManager().beginTransaction()
                .setCustomAnimations(
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out
                )
                .replace(R.id.container, new CardBackFragmentSearchSchool())
                .addToBackStack(null)
                .commit();
    }

    public void login_register(View view) {

        getFragmentManager().beginTransaction()
                .setCustomAnimations(
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out
                )
                .replace(R.id.container, new CardBackFragment_LoginRegister())
                .addToBackStack(null)
                .commit();
    }

    public void stu_corner(View view) {

        getFragmentManager().beginTransaction()
                .setCustomAnimations(
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out
                )
                .replace(R.id.container, new CardBackFragment_StudentCorner())
                .addToBackStack(null)
                .commit();
    }

    public void help(View view) {

        getFragmentManager().beginTransaction()
                .setCustomAnimations(
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out
                )
                .replace(R.id.container, new CardBackFragment_Help())
                .addToBackStack(null)
                .commit();
    }

    //button in tutoractivit
//    public void s_tutor_pickplace(View view) {
//        Toast.makeText(getApplicationContext(), "Pick Place", Toast.LENGTH_LONG).show();
//
//    }

    //opens register a new tutor screen
    public void register_tutor(View view) {

        getFragmentManager().beginTransaction()
                .setCustomAnimations(
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out
                )
                .replace(R.id.container, new RegisterTutor())
                .addToBackStack(null)
                .commit();
    }

    public void tutor_search_pickplace(View view){
        PlacePicker.IntentBuilder intentBuilder=new PlacePicker.IntentBuilder();
        Intent intent= null;
        try {
            intent = intentBuilder.build(getApplicationContext());

        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
        switch (view.getId()){
            case R.id.choose_place:
                startActivityForResult(intent,PLACE_PICKER_REQUEST);
                break;
            case R.id.but_st_pickplace:
                startActivityForResult(intent,PLACE_PICKER_REQUEST_TUTOR);
                break;
            default:
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PLACE_PICKER_REQUEST){
            if(resultCode==RESULT_OK){
                Place place=PlacePicker.getPlace(data,this);
                LatLng placeLatLng=place.getLatLng();
                String sttr=String.valueOf(placeLatLng.longitude)+String.valueOf(placeLatLng.latitude);
                Toast.makeText(getApplicationContext(),sttr,Toast.LENGTH_LONG).show();
                //do something here
            }
            if (resultCode==PLACE_PICKER_REQUEST_TUTOR){
                if(resultCode==RESULT_OK){
                    Place place=PlacePicker.getPlace(data,this);
                    LatLng placeLatLng=place.getLatLng();
                    String sttr=String.valueOf(placeLatLng.longitude)+String.valueOf(placeLatLng.latitude);
                    Toast.makeText(getApplicationContext(),sttr,Toast.LENGTH_LONG).show();
                    //do something here
                }
            }
        }
    }


    @Override
    public void onConnected(Bundle bundle) {
        lastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (lastLocation != null) {
            Toast.makeText(getApplicationContext(), String.valueOf(lastLocation.getLatitude()) + String.valueOf(lastLocation.getLongitude()), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(getApplicationContext(), "Connection failed to google api client", Toast.LENGTH_LONG).show();
    }

}
