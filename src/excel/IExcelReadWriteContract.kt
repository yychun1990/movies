package excel

interface IExcelReadWriteContract {
    val columns: Collection<String>

    val writeMap: Map<String, ExcelData<*>>
}