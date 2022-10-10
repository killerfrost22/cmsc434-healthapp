package com.lengyue.whosthere.ui.map

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.dialog.MaterialAlertDialogBuilder


// Implement onMapReadyCallback to handle the GoogleMap object when it's available.
class MapFragment: SupportMapFragment() {
    companion object {
        private const val TAG = "MapFragment"
    }

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>
    private var lastLocation: LatLng? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireContext())
        requestLocationPermission()
    }

    private fun checkLocationPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermission() {
        requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            Log.i(TAG, "onRequestPermissionResult $isGranted")
            if (isGranted) {
                setUpLocationListener()
            } else {
                // Show a dialog to ask for permission
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Location Permission")
                    .setMessage("This app needs location permission to work, please grant it in the settings.")
                    .setPositiveButton("OK") { _, _ ->
                        requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                    }
                    .setNegativeButton("Reject") { _, _ ->
                        requireActivity().finish()
                    }
                    .show()
            }
        }

        requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    private fun setUpLocationListener() {
        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
            getMapAsync { googleMap ->
                googleMap.isMyLocationEnabled = true
                googleMap.uiSettings.isMyLocationButtonEnabled = true
            }
            if (location != null) {
                val obtainedLocation = LatLng(location.latitude, location.longitude)
                if (lastLocation == null) {
                    getMapAsync { googleMap ->
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(obtainedLocation, 15f))
                    }
                }
                lastLocation = obtainedLocation
            }
        }

        if (!checkLocationPermission()) {
            requestLocationPermission()
        }
    }
}
