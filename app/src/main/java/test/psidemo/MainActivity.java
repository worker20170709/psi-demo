package test.psidemo;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import test.psidemo.downloader.DataDownloaderImpl;
import test.psidemo.map.IMapController;
import test.psidemo.map.IMapView;
import test.psidemo.map.MapControllerImpl;
import test.psidemo.map.MapData;
import test.psidemo.parser.DataParserImpl;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, IMapView {
    private static final String EXTRA_MAP_DATA = "EXTRA_MAP_DATA";

    private GoogleMap mMap;
    private IMapController mMapController;
    private TextView mNationalPsiView;
    private List<MapData> mMapData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mNationalPsiView = (TextView)findViewById(R.id.national_psi);
        mMapController = new MapControllerImpl(new DataDownloaderImpl(), new DataParserImpl());

        if (savedInstanceState != null) {
            mMapData = savedInstanceState.getParcelableArrayList(EXTRA_MAP_DATA);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (mMapData != null) {
            outState.putParcelableArrayList(EXTRA_MAP_DATA, new ArrayList<Parcelable>(mMapData));
        }

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng latLng = new LatLng(1.36, 103.83);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
        if (mMapData == null) {
            mMapController.getMapData(this);
        } else {
            update(mMapData);
        }
    }

    public void update(List<MapData> mapData) {
        mMapData = mapData;
        for (MapData data : mapData) {
            if (!data.getIsNational()) {
                LatLng latLng = new LatLng(data.getLat(), data.getLng());
                String psiStr = getString(R.string.psi_value, (int)data.getPsi());
                mMap.addMarker(new MarkerOptions().position(latLng).title(psiStr));
            } else {
                mNationalPsiView.setText(getString(R.string.national_psi_value, (int)data.getPsi()));
            }
        }
    }
}
