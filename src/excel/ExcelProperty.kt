package excel

data class ExcelProperty(
        val file: String,
        val sheetName: String,
        private val excelFormat: ExcelFormat = ExcelFormat.XLSX
) {
    val fileSuffix: String = excelFormat.fileSuffix
}