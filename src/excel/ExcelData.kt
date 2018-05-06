package excel

import org.apache.poi.ss.usermodel.RichTextString
import java.util.*

sealed class ExcelData<DATA>(val data: DATA) {
    class BooleanData(data: Boolean) : ExcelData<Boolean>(data)
    class DoubleData(data: Double) : ExcelData<Double>(data) {
        constructor(data: Int) : this(data.toDouble())
    }

    class DateData(data: Date) : ExcelData<Date>(data)
    class StringData(data: String) : ExcelData<String>(data)
    class RichTextStringData(data: RichTextString) : ExcelData<RichTextString>(data)
}