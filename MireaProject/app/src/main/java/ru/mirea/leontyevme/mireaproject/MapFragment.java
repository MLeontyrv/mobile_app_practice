package ru.mirea.leontyevme.mireaproject;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import ru.mirea.leontyevme.mireaproject.databinding.FragmentMapBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapFragment extends Fragment {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 100;
    private boolean isLocationPermissionGranted = false;
    private MapView mapView;
    private FragmentMapBinding binding;
    private MyLocationNewOverlay myLocationOverlay;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapFragment newInstance(String param1, String param2) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration.getInstance().load(getContext(), PreferenceManager.getDefaultSharedPreferences(getContext()));
        checkAndRequestPermissions();
    }

    @Nullable
    @Override
    public android.view.View onCreateView(@NonNull android.view.LayoutInflater inflater,
                                          @Nullable android.view.ViewGroup container,
                                          @Nullable Bundle savedInstanceState) {
        binding = FragmentMapBinding.inflate(inflater, container, false);
        initializeMapView();
        return binding.getRoot();
    }

    private void initializeMapView() {
        mapView = binding.mapView;
        mapView.setZoomRounding(true);
        mapView.setMultiTouchControls(true);

        IMapController mapController = mapView.getController();
        mapController.setZoom(15.0);
        mapController.setCenter(new GeoPoint(55.794229, 37.700772));

        setupLocationOverlay();
        setupCompassOverlay();
        setupScaleBarOverlay();
        addMarkers();
    }

    private void setupLocationOverlay() {
        myLocationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(getContext()), mapView);
        myLocationOverlay.enableMyLocation();
        mapView.getOverlays().add(myLocationOverlay);
    }

    private void setupCompassOverlay() {
        CompassOverlay compassOverlay = new CompassOverlay(getContext(), new InternalCompassOrientationProvider(getContext()), mapView);
        compassOverlay.enableCompass();
        mapView.getOverlays().add(compassOverlay);
    }

    private void setupScaleBarOverlay() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        ScaleBarOverlay scaleBarOverlay = new ScaleBarOverlay(mapView);
        scaleBarOverlay.setCentred(true);
        scaleBarOverlay.setScaleBarOffset(displayMetrics.widthPixels / 2, 10);
        mapView.getOverlays().add(scaleBarOverlay);
    }

    private void addMarkers() {
        addMarker(55.758817, 37.645529, "Зинзивер", "Title");
        addMarker(55.756430, 37.635269, "Пестики-тычинки", null);
        addMarker(55.758506, 37.632788, "Пропаганда", null);
    }

    private void addMarker(double latitude, double longitude, String toastMessage, @Nullable String title) {
        Marker marker = new Marker(mapView);
        marker.setPosition(new GeoPoint(latitude, longitude));
        marker.setOnMarkerClickListener((m, mapView) -> {
            Toast.makeText(getContext(), toastMessage, Toast.LENGTH_SHORT).show();
            return true;
        });
        if (title != null) {
            marker.setTitle(title);
        }
        marker.setIcon(ResourcesCompat.getDrawable(getResources(), org.osmdroid.library.R.drawable.osm_ic_follow_me_on, null));
        mapView.getOverlays().add(marker);
    }

    @Override
    public void onResume() {
        super.onResume();
        Configuration.getInstance().load(getContext(), PreferenceManager.getDefaultSharedPreferences(getContext()));
        if (mapView != null) {
            mapView.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Configuration.getInstance().save(getContext(), PreferenceManager.getDefaultSharedPreferences(getContext()));
        if (mapView != null) {
            mapView.onPause();
        }
    }

    private void checkAndRequestPermissions() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            isLocationPermissionGranted = true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                isLocationPermissionGranted = true;
            } else {
                Toast.makeText(getContext(), "Разрешение на определение местоположения не предоставлено", Toast.LENGTH_SHORT).show();
            }
        }
    }
}