package com.hi.main.ui

import android.view.Menu
import android.view.MenuItem
import com.hi.common.BaseActivity
import com.hi.common.ktx.toast
import com.hi.common.ktx.toolbar
import com.hi.main.R

/**
 * @author : wbxuzhen
 * @date : 2020/11/10 17:14
 * @description :jetpack->room
 */
class RoomTestActivity : BaseActivity() {

    override fun layoutId() = R.layout.activity_room_test

    override fun init() {
        toolbar(getString(R.string.room_test))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.room_activity_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.add_data -> {
            toast("加载数据")
            true
        }
        R.id.clear_all -> {
            toast("清空数据")
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

}