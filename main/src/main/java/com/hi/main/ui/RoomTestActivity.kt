package com.hi.main.ui

import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.hi.common.BaseActivity
import com.hi.common.adapter.StableAdapter
import com.hi.common.ktx.createStableAdapter
import com.hi.common.ktx.toolbar
import com.hi.common.room.STUDENT_DATA
import com.hi.common.room.entity.StudentEntity
import com.hi.common.widget.UniversalItemDecoration
import com.hi.main.R
import com.hi.main.cells.RoomCell
import com.hi.main.databinding.ActivityRoomTestBinding
import com.hi.main.vm.RoomViewModel

/**
 * @author : wbxuzhen
 * @date : 2020/11/10 17:14
 * @description :jetpack->room
 */
class RoomTestActivity : BaseActivity<ActivityRoomTestBinding>() {
    private lateinit var stableAdapter: StableAdapter
    private val roomViewModel: RoomViewModel by viewModels()

    override fun init() {
        toolbar(getString(R.string.room_test))
        stableAdapter = createStableAdapter { }

        bind.recycler.apply {
            adapter = stableAdapter
            layoutManager = LinearLayoutManager(this@RoomTestActivity)
            addItemDecoration(UniversalItemDecoration(this@RoomTestActivity, 10, 10))
        }
    }

    override fun viewDrawn() {
        roomViewModel.subscribe().observe(this, Observer {
            val itemCellList = mutableListOf<RoomCell>()
            if (it.isNullOrEmpty()) {
                itemCellList.clear()
            } else {
                it.forEach { entity ->
                    itemCellList.add(RoomCell(entity))
                }
            }
            stableAdapter.submitList(itemCellList)
        })
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
            roomViewModel.insertUpdate(student) {
                i = 0
            }
            true
        }
        R.id.clear_all -> {
            roomViewModel.delete()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

}