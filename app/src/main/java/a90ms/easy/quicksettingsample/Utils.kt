package a90ms.easy.quicksettingsample

import android.content.Context
import android.util.Log
import android.widget.Toast

object Utils {

    private val TAG = QuickSettingService::class.java.simpleName

    fun String.printD() {
        Log.d(TAG, this)
    }

    fun String.toast(context: Context) {
        Toast.makeText(
            context,
            this,
            Toast.LENGTH_SHORT
        ).show()
    }
}
