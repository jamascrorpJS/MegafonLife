//import java.text.DateFormatSymbols
//import java.text.SimpleDateFormat
//import java.util.*
//
////package com.jamascrorp.megafonlife.calendar_fragment
////
////import android.util.Log
////import androidx.lifecycle.LiveData
////import androidx.lifecycle.MutableLiveData
////import androidx.lifecycle.ViewModel
////import androidx.lifecycle.viewModelScope
////import com.jamascrorp.megafonlife.test.Day
////import com.jamascrorp.megafonlife.test.DaysAdapter
////import com.jamascrorp.megafonlife.test.Month
////import com.jamascrorp.megafonlife.test.MonthsAdapter
////import kotlinx.coroutines.launch
////import java.time.LocalDate
////import java.util.*
////
////class CalendarFragmentViewModel : ViewModel() {
//
//val timeInMillis = System.currentTimeMillis()
//
//val calendar = Calendar.getInstance()
//calendar.timeInMillis = timeInMillis
//
//val year = calendar.get(Calendar.YEAR)
//val month = calendar.get(Calendar.MONTH)
//val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
//val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)
//val maxMonth = calendar.getActualMaximum(Calendar.MONTH)
//val minMonth = calendar.getActualMinimum(Calendar.MONTH)
//val list = mutableListOf<Calendar>()
//
//val monthNames = DateFormatSymbols().months
//
//val monthName = monthNames[month]
//
//val monthName1 = SimpleDateFormat("LLLL", Locale("ru")).format(calendar.time)
//
//calendar.set(
//2023,
//6,
//28,
//11,
//0,
//0
//) // Устанавливаем нужную дату без времени (28 апреля 2023 года)
//calendar.set(Calendar.MILLISECOND, 0) // Обнуляем миллисекунды
//val time = calendar.timeInMillis
//
//val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
//
//val dayList = mutableListOf<Date>()
//val dayList1 = mutableListOf<Int>()
//for (dayOfMonth in 1..daysInMonth) {
//    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
//    val day = SimpleDateFormat("d").format(calendar.time).toInt()
//    dayList1.add(day)
//    dayList.add(calendar.time)
//}
//
//Log.d(
//"TAG",
//"onViewCreated: $year $month $dayOfMonth $time $timeInMillis $hourOfDay $monthName $monthName1 ${calendar.time} ${viewModel.getMonths()}}"
//)
////
////    var selectedStartDate: Calendar? = null
////        set(value) {
////            field = value
////
////            //////.......
////        }
////
////    var selectedEndDate: Calendar? = null
////        set(value) {
////            field = value
////            /// .......
////        }
////
////    private val _monthsLiveData: MutableLiveData<List<Month>> = MutableLiveData()
////    val monthsLiveData: LiveData<List<Month>> = _monthsLiveData
////
////    val array = mutableListOf<Day>()
////
////
////    private val months = mutableListOf<Calendar>()
////    private val calendar = Calendar.getInstance()
////    private val maxMonth = calendar.getActualMaximum(Calendar.MONTH)
////    private val minMonth = calendar.getActualMinimum(Calendar.MONTH)
////    private val currentMonth = calendar[Calendar.MONTH]
////    private val year = LocalDate.now().year
////    private val dayOfWeek = LocalDate.of(year, 1, 1).dayOfWeek.value
////
////    var startDate: Day? = null
////    var endDate: Day? = null
////    var list1: List<Day>? = null
////
////     -------------------------------------------------------
////      public
////     -------------------------------------------------------
////
////    fun getDefaultMonths() {
////        val defaultMonths = getMonths()
////        _monthsLiveData.value = defaultMonths
////    }
////
////
////
////
////
////    fun updateSelectionBySelectedDay(day: Day) {
////        // эта функция должна:
////        // *1) start date, end date или нужно все обнулить и по новой поставить start date
////        // 2) обновить массив дат с селекцией
////    }
////
////
////
////    // -------------------------------------------------------
////    //  private
////    // -------------------------------------------------------
////
////    private fun getMonths(): List<Month> {
////        val january = mutableListOf<Day>()
////        val february = mutableListOf<Day>()
////        val march = mutableListOf<Day>()
////        val april = mutableListOf<Day>()
////        val may = mutableListOf<Day>()
////        val june = mutableListOf<Day>()
////        val july = mutableListOf<Day>()
////        val august = mutableListOf<Day>()
////        val september = mutableListOf<Day>()
////        val october = mutableListOf<Day>()
////        val november = mutableListOf<Day>()
////        val december = mutableListOf<Day>()
////
////        repeat(getDays(1) - 1) {
////            january.add(Day(0))
////        }
////        (1..getMonthss().getValue("January")).forEach {
////            january.add(Day(it))
////        }
////        repeat(getDays(2) - 1) {
////            february.add(Day(0))
////        }
////        (1..getMonthss().getValue("February")).forEach {
////            february.add(Day(it))
////        }
////        repeat(getDays(3) - 1) {
////            march.add(Day(0))
////        }
////        (1..getMonthss().getValue("March")).forEach {
////            march.add(Day(it))
////        }
////        repeat(getDays(4) - 1) {
////            april.add(Day(0))
////        }
////        (1..getMonthss().getValue("April")).forEach {
////            april.add(Day(it))
////        }
////        repeat(getDays(5) - 1) {
////            may.add(Day(0))
////        }
////        (1..getMonthss().getValue("May")).forEach {
////            may.add(Day(it))
////        }
////        repeat(getDays(6) - 1) {
////            june.add(Day(0))
////        }
////        (1..getMonthss().getValue("June")).forEach {
////            june.add(Day(it))
////        }
////        repeat(getDays(7) - 1) {
////            july.add(Day(0))
////        }
////        (1..getMonthss().getValue("July")).forEach {
////            july.add(Day(it))
////        }
////        repeat(getDays(8) - 1) {
////            august.add(Day(0))
////        }
////        (1..getMonthss().getValue("August")).forEach {
////            august.add(Day(it))
////        }
////        repeat(getDays(9) - 1) {
////            september.add(Day(0))
////        }
////        (1..getMonthss().getValue("September")).forEach {
////            september.add(Day(it))
////        }
////        repeat(getDays(10) - 1) {
////            october.add(Day(0))
////        }
////        (1..getMonthss().getValue("October")).forEach {
////            october.add(Day(it))
////        }
////        repeat(getDays(11) - 1) {
////            november.add(Day(0))
////        }
////        (1..getMonthss().getValue("November")).forEach {
////            november.add(Day(it))
////        }
////        repeat(getDays(12) - 1) {
////            december.add(Day(0))
////        }
////        (1..getMonthss().getValue("December")).forEach {
////            december.add(Day(it))
////        }
////        val month = listOf(
////            Month(
////                "Январь", january
////            ),
////            Month(
////                "Февраль", february
////            ),
////            Month(
////                "Март", march
////            ),
////            Month(
////                "Апрель", april
////            ),
////            Month(
////                "Май", may
////            ),
////            Month(
////                "Июнь", june
////            ),
////            Month(
////                "Июль", july
////            ),
////            Month(
////                "Август", august
////            ),
////            Month(
////                "Сентябрь", september
////            ),
////            Month(
////                "Октябрь", october
////            ),
////            Month(
////                "Ноябрь", november
////            ),
////            Month(
////                "Декабрь", december
////            )
////        )
////        return month
////    }
////
////    private fun getMonthss(): Map<String, Int> {
////        Log.d("TAG", "getMonthss: $dayOfWeek")
////        val month = mutableMapOf<String, Int>()
////        (minMonth - currentMonth..maxMonth - currentMonth).forEach {
////            val c1 = Calendar.getInstance()
////            months.add(c1)
////            c1.add(Calendar.MONTH, it)
////        }
////        months.forEach {
////            Log.d("TAG", "getMonthss: $dayOfWeek")
////            month.put(
////                it.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US),
////                it.getActualMaximum(Calendar.DAY_OF_MONTH)
////            )
////        }
////        return month
////    }
////
////    private fun getDays(month: Int): Int {
////        return LocalDate.of(year, month, 1).dayOfWeek.value
////    }
////}