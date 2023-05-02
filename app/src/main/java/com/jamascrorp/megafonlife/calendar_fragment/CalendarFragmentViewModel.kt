package com.jamascrorp.megafonlife.calendar_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jamascrorp.megafonlife.test.Day
import com.jamascrorp.megafonlife.test.Month
import java.text.SimpleDateFormat
import java.util.*

class CalendarFragmentViewModel : ViewModel() {

    var selectedStartDate: Calendar? = null
//        set(value) {
//            field = value
//            //////.......
//        }

    var selectedEndDate: Calendar? = null
//        set(value) {
//            field = value
//            /// .......
//        }

    private val _monthsLiveData: MutableLiveData<List<Month>?> = MutableLiveData()
    val monthsLiveData: LiveData<List<Month>?> = _monthsLiveData


    // -------------------------------------------------------
    //  public
    // -------------------------------------------------------

//    fun getDefaultMonths() {
//        val defaultMonths = getMonths()
//        _monthsLiveData.value = defaultMonths
//    }

    fun updateSelectionBySelectedDay(day: Day) {
        // эта функция должна:
        // 1) start date, end date или нужно все обнулить и по новой поставить start date

        val timeinmillis = day.timeInMillis
        val calendar = Calendar.getInstance()
        if (timeinmillis != null) {
            calendar.setTimeInMillis(timeinmillis)
        }

        if (selectedStartDate == null && selectedEndDate == null) {
            selectedStartDate = calendar
        } else if (selectedStartDate != null && selectedEndDate == null && selectedStartDate?.timeInMillis!! < calendar.timeInMillis) {
            selectedEndDate = calendar
        } else if (selectedStartDate != null && selectedEndDate == null && selectedStartDate?.timeInMillis!! > calendar.timeInMillis) {
            selectedEndDate = selectedStartDate
            selectedStartDate = calendar
        } else if (selectedStartDate != null && selectedEndDate != null) {
            selectedStartDate = null
            selectedEndDate = null
            selectedStartDate = calendar
        }

        // *2) обновить массив дат с селекцией

        val resultOfSelection = _monthsLiveData.value?.map { it }
        resultOfSelection?.map { month ->
            month.days.find { it.timeInMillis == selectedStartDate?.timeInMillis }?.isSelected =
                selectedStartDate != null
            month.days.find { it.timeInMillis == selectedEndDate?.timeInMillis }?.isSelected = selectedEndDate != null
            month.days.find { it.timeInMillis == selectedStartDate?.timeInMillis }?.isStart = selectedStartDate != null
            month.days.find { it.timeInMillis == selectedEndDate?.timeInMillis }?.isEnd = selectedEndDate != null
            if (selectedStartDate != null && selectedEndDate != null) {
                month.days.filter { it.isStart || it.isEnd || (it.timeInMillis != null && it.timeInMillis > selectedStartDate!!.timeInMillis && it.timeInMillis < selectedEndDate!!.timeInMillis) }.forEach { it.isSelected = true }
            } else if (selectedStartDate != null && selectedEndDate == null) {
                month.days.forEach { it.isSelected = false }
                month.days.forEach { it.isStart = false }
                month.days.forEach { it.isEnd = false }
            }
        }

        _monthsLiveData.value = if (selectedStartDate == null && selectedEndDate == null) getMonths() else resultOfSelection

        // resulOfSelection - это копия списка месяцов
        // дальнейшая работа должна проводиться с ней
        // в этом списке нужно определить:
        // найти между start and end нужные даты и установить их isSelected=True
        // кроме этого start and end тоже обладают свойством isSelected=true, но также у
        // них есть isStart = True and isEnd = True соответственно


//        _monthsLiveData.value = resultOfSelection ЭТО ПОСЛЕДНЕЕ ЧТО НУЖНО СДЕЛАТЬ!!!
    }


    fun getMonths(): List<Month> {
        val list = mutableListOf<Month>()
        for (i in 0..11) {
            val calendar = getCalendar()[i]
            val pair = getMonth(calendar)
            list.add(Month(pair.first, pair.second, pair.third))
        }
        _monthsLiveData.value = list
        return list
    }


    // -------------------------------------------------------
    //  private
    // -------------------------------------------------------

    val currentTimeMillis = System.currentTimeMillis()
    val calendar = Calendar.getInstance()
    val month = calendar.get(Calendar.MONTH)
    val maxMonth = calendar.getActualMaximum(Calendar.MONTH)
    val minMonth = calendar.getActualMinimum(Calendar.MONTH)

    private fun getCalendar(): List<Calendar> {
        val list = mutableListOf<Calendar>()
        for (i in minMonth - month..maxMonth - month) {
            val c = Calendar.getInstance()
            c.add(Calendar.MONTH, i)
            list.add(c)
        }
        return list
    }

    private fun getMonth(calendar: Calendar): Triple<Int, String, List<Day>> {
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)
        val list = mutableListOf<Day>()
        val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        val monthName = SimpleDateFormat("LLLL", Locale("ru")).format(calendar.time)

        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val add = when (calendar.get(Calendar.DAY_OF_WEEK)) {
            1 -> 6
            2 -> 0
            3 -> 1
            4 -> 2
            5 -> 3
            6 -> 4
            7 -> 5
            else -> 0
        }
        repeat(add) {
            list.add(Day(0))
        }

        for (i in 1..daysInMonth) {
            calendar.set(year, month, i, 0, 0, 0)
            calendar.set(Calendar.MILLISECOND, 0) // Обнуляем миллисекунды
            val timeInMillis =
                calendar.timeInMillis // Получаем количество миллисекунд с начала эпохи Unix
            list.add(Day(i, timeInMillis))
        }
        return Triple(month, monthName, list)
    }
}