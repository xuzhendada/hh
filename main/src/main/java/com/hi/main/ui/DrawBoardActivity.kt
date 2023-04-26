package com.hi.main.ui

import android.graphics.Bitmap
import android.graphics.Color
import android.view.Menu
import android.view.MenuItem
import com.hi.common.BaseActivity
import com.hi.common.ktx.toolbar
import com.hi.main.R
import com.hi.main.databinding.ActivityDrawBoardBinding
import java.io.ByteArrayOutputStream

class DrawBoardActivity : BaseActivity<ActivityDrawBoardBinding>() {
    override fun init() {
        toolbar(R.string.draw_board)
        bind.drawingBoard.setGroundColor(R.color.colorAccent)
        bind.drawingBoard.setPenColor(R.color.colorPrimary)
        bind.drawingBoard.setPenSize(20)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.draw_board, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.clear_all -> {
            bind.drawingBoard.redo()
            true
        }

        R.id.generate_img -> {
            val bitMap = bind.drawingBoard.getBitMap()
            val bao = ByteArrayOutputStream()
            bitMap.compress(Bitmap.CompressFormat.PNG,100,bao)
            val byteArray = bao.toByteArray()
            true
        }

        R.id.cancel -> {
            finish()
            true
        }

        else -> super.onOptionsItemSelected(item)
    }
}