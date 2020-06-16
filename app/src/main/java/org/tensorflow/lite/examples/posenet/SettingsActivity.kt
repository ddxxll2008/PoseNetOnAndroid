package org.tensorflow.lite.examples.posenet

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_settings.*
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions


class SettingsActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {
    val TAG = SettingsActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        cameraTask()

        btn_start.setOnClickListener {
            //因为前置镜头是反向的，所以左右相反
            var isLeftHand = true
            if (rb_left.isChecked) {
                isLeftHand = false
            }
            var intent = Intent()
            intent.putExtra(IS_LEFT_HAND, isLeftHand)
            intent.setClass(this, PosenetActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun hasCameraPermission(): Boolean {
        return EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA)
    }

    @AfterPermissionGranted(REQUEST_CAMERA_PERMISSION)
    fun cameraTask() {
        if (hasCameraPermission()) {
            Log.d(TAG, "camera permission granted")
        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions(
                this,
                getString(R.string.rationale_camera),
                REQUEST_CAMERA_PERMISSION,
                Manifest.permission.CAMERA);
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        Log.d(TAG, "onPermissionsDenied:" + requestCode + ":" + perms.size)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        Log.d(TAG, "onPermissionsGranted:" + requestCode + ":" + perms.size)

        // (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
        // This will display a dialog directing them to enable the permission in app settings.
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        }
    }
}