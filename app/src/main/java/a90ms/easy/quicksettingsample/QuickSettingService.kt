package a90ms.easy.quicksettingsample

import a90ms.easy.quicksettingsample.Utils.printD
import a90ms.easy.quicksettingsample.Utils.toast
import android.app.AlertDialog
import android.content.Intent
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import android.view.View


class QuickSettingService : TileService() {

    /**
     * Quick Settings에 추가 되었을때,
     * */
    override fun onTileAdded() {
        super.onTileAdded()
        "onTileAdded".printD()
    }

    /**
     * Quick Settings에 제거 되었을때,
     * */
    override fun onTileRemoved() {
        super.onTileRemoved()
        "onTileRemoved".printD()
    }

    /**
     * onClick 호출 전에 호출,
     * */
    override fun onStartListening() {
        super.onStartListening()
        "onStartListening".printD()
    }

    /**
     * onClick 호출 이후 호출,
     * */
    override fun onStopListening() {
        super.onStopListening()
        "onStopListening".printD()
    }

    /**
     * Tile 클릭 호출
     * */
    override fun onClick() {
        "onClick".printD()
        "현재 화면 잠금상태는 $isLocked".printD()
        "현재 보안상태는 $isSecure".printD()

        when (qsTile.state) {
            Tile.STATE_ACTIVE -> {
                qsTile.state = Tile.STATE_INACTIVE
                "STATE_INACTIVE"
            }
            Tile.STATE_INACTIVE -> {
                qsTile.state = Tile.STATE_ACTIVE
                startDialog()
                "STATE_ACTIVE"
            }
            Tile.STATE_UNAVAILABLE -> {
                "STATE_UNAVAILABLE"
            }
            else -> "${qsTile.state}"
        }.printD()
        qsTile.updateTile()
    }

    private fun startActivity() {
        initTile()
        startActivityAndCollapse(Intent(applicationContext, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        })
    }

    private fun startDialog() {
    val dialog: AlertDialog =
            AlertDialog.Builder(applicationContext)
//                .setView(R.layout.dialog_main)
                .setTitle("QuickSetting")
                .setMessage("message")
                .setPositiveButton(
                    "Start Activity"
                ) { _, _ ->
                    "start Activity".toast(applicationContext)
                    startActivity()
                }.setNegativeButton(
                    "Cancel"
                ) { _, _ ->
                    initTile()
                    "Cancel".toast(applicationContext)
                }.create()
        showDialog(dialog)
    }

    private fun initTile() {
        qsTile.state = Tile.STATE_INACTIVE
        qsTile.updateTile()
    }
}
