package com.hi.common.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hi.common.room.dao.StudentDao
import com.hi.common.room.entity.StudentEntity

/**
 * @author : wbxuzhen
 * @date : 2020/11/10 16:49
 * @description :
 */
@Database(entities = [StudentEntity::class], version = 1)
abstract class HhDataBase : RoomDatabase() {
    companion object {
        @Synchronized
        fun getDataBase(context: Context): HhDataBase {
            return Room.databaseBuilder(
                context.applicationContext,
                HhDataBase::class.java,
                "hh_database"
            ).build()
        }
    }

    abstract fun studentDao(): StudentDao
}

// 学生姓名
private val STUDENT_DATA = arrayListOf(
    "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
    "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag",
    "Airedale", "Aisy Cendre", "Allgauer Emmentaler", "Alverca", "Ambert",  // 15
    "American Cheese", "Ami du Chambertin", "Anejo Enchilado", "Anneau du Vic-Bilh", "Anthoriro",
    "Appenzell", "Aragon", "Ardi Gasna", "Ardrahan", "Armenian String",
    "Aromes au Gene de Marc", "Asadero", "Asiago", "Aubisque Pyrenees", "Autun", // 30
    "Avaxtskyr", "Baby Swiss", "Babybel", "Baguette Laonnaise", "Bakers",
    "Baladi", "Balaton", "Bandal", "Banon", "Barry's Bay Cheddar", "Basing", "Basket Cheese", "Bath Cheese", "Bavarian Bergkase",
    "Baylough", "Beaufort", "Beauvoorde", "Beenleigh Blue", "Beer Cheese", "Bel Paese",
    "Bergader", "Bergere Bleue", "Berkswell", "Beyaz Peynir", "Bierkase", "Bishop Kennedy",
    "Blarney", "Bleu d'Auvergne", "Bleu de Gex", "Bleu de Laqueuille",
    "Bleu de Septmoncel", "Bleu Des Causses", "Blue", "Blue Castello", "Blue Rathgore",
    "Blue Vein (Australian)", "Blue Vein Cheeses", "Bocconcini", "Bocconcini (Australian)"
)