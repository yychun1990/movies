import java.io.FileOutputStream
import org.apache.poi.ss.usermodel.IndexedColors
import excel.ExcelConstants
import excel.ExcelData
import excel.ExcelProperty
import excel.IExcelReadWriteContract
import org.apache.poi.openxml4j.exceptions.InvalidFormatException
import org.apache.poi.ss.usermodel.RichTextString
import org.apache.poi.ss.util.CellUtil.setFont
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.IOException
import java.util.*

object ExcelWriter {

    @Throws(IOException::class, InvalidFormatException::class)
    fun writeExcel(property: ExcelProperty, vararg excelData: IExcelReadWriteContract) {
        val file = File("${ExcelConstants.EXCEL_FOLDER}/${property.file}.${property.fileSuffix}")
        val isFileExist = file.exists()

        // Create a Workbook
        val workbook = if (isFileExist) XSSFWorkbook(file) else XSSFWorkbook()

        /* CreationHelper helps us create instances for various things like DataFormat,
           Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */

        // Create a Sheet
        val sheetName = property.sheetName
        val isSheetExist = workbook.getSheetIndex(sheetName) != -1
        val sheet = if (isSheetExist) workbook.getSheet(sheetName) else workbook.createSheet(sheetName)

        val columns = excelData.first().columns
        if (!isSheetExist) {
            // Create a Font for styling header cells
            val headerFont = workbook.createFont().apply {
                bold = true
                fontHeightInPoints = 14.toShort()
                color = IndexedColors.RED.index
            }

            // Create a CellStyle with the font
            val headerCellStyle = workbook.createCellStyle().apply {
                setFont(headerFont)
            }

            // Create a Row
            val headerRow = sheet.createRow(0)

            // Creating cells
            columns.withIndex().forEach {
                with(headerRow.createCell(it.index)) {
                    setCellValue(it.value)
                    cellStyle = headerCellStyle
                }
            }
        }

        // Create Cell Style for formatting Date
        val dateCellStyle = workbook.createCellStyle().apply {
            dataFormat = workbook.creationHelper.createDataFormat().getFormat(ExcelConstants.DATE_FORMAT)
        }

        // Create Other rows and cells with employees data
        excelData.withIndex().forEach {
            with(sheet.createRow(it.index + 1)) {
                it.value.writeMap.entries.forEach {
                    val columnIndex = columns.indexOf(it.key)
                    println("column: ${it.key}")
                    println("columnIndex: $columnIndex")
                    when (it.value) {
                        is ExcelData.BooleanData -> createCell(columnIndex).setCellValue(it.value.data as Boolean)
                        is ExcelData.DoubleData -> createCell(columnIndex).setCellValue(it.value.data as Double)
                        is ExcelData.DateData -> createCell(columnIndex).apply {
                            setCellValue(it.value.data as Date)
                            cellStyle = dateCellStyle
                        }
                        is ExcelData.StringData -> createCell(columnIndex).setCellValue(it.value.data as String)
                        is ExcelData.RichTextStringData -> createCell(columnIndex).setCellValue(it.value.data as RichTextString)
                    }
                }
            }
        }

        // Resize all columns to fit the content size
        for (i in columns.indices) {
            sheet.autoSizeColumn(i)
        }

        // Write the output to a file
        if (!isFileExist) {
            if (!file.parentFile.exists()) {
                file.parentFile.mkdirs()
            }
            file.createNewFile()
        }
        val fileOut = FileOutputStream(file)
        workbook.write(fileOut)
        fileOut.close()

        // Closing the workbook
        workbook.close()
    }
}