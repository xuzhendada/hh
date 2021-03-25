package com.hi.main.ui

import android.view.Menu
import android.view.MenuItem
import com.hi.common.BaseActivity
import com.hi.common.ktx.toast
import com.hi.common.ktx.toolbar
import com.hi.common.room.HhDataBase
import com.hi.common.room.STUDENT_DATA
import com.hi.common.room.entity.StudentEntity
import com.hi.main.R
import com.hi.main.databinding.ActivityRoomTestBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

/**
 * @author : wbxuzhen
 * @date : 2020/11/10 17:14
 * @description :jetpack->room
 */
class RoomTestActivity : BaseActivity<ActivityRoomTestBinding>() {

    override fun init() {
        toolbar(getString(R.string.room_test))

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.room_activity_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.add_data -> {
            val student = mutableListOf<StudentEntity>()
            var i = 0
            STUDENT_DATA.forEach {
                student.add(StudentEntity(it))
                i++
            }
            thread {

            }
            GlobalScope.launch {
                HhDataBase.getDataBase(this@RoomTestActivity).studentDao()
                    .insert(studentEntity = student)
            }
            true
        }
        R.id.clear_all -> {
            GlobalScope.launch {
                HhDataBase.getDataBase(this@RoomTestActivity).studentDao()
                    .deleteAll()
            }
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

}