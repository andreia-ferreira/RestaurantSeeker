package pt.andreia.restaurantseeker.utils

import android.content.Context
import java.io.*
import java.nio.charset.Charset
import java.nio.file.Files.createFile

object FileUtils {

    @JvmStatic
    fun existsInInternalStorage(context: Context?, nameFile: String?): Boolean {
        if (context == null || nameFile == null) return false
        val storageDir = context.filesDir
        val path = "$storageDir/$nameFile"
        val file = File(path)
        return file.exists()
    }

    @JvmStatic
    fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            context.assets.close()
            return null
        }
        return jsonString
    }

    @JvmStatic
    fun saveToInternalStorage(context: Context?, nameFile: String?, fileContents: String?): String {
        var filePath = ""
        if (context != null && nameFile != null && fileContents != null) {
            val file = createFile(context, nameFile)
            try {
                val os = FileOutputStream(file, false)
                os.write(fileContents.toByteArray())
                os.flush()
                os.close()
                filePath = file.absolutePath
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return filePath
    }

    @JvmStatic
    fun loadFromInternalStorage(context: Context?, file: String?): String? {
        var value: String? = null

        if (context != null && file != null) {
            try {
                val fis = context.openFileInput(file)
                val isr = InputStreamReader(fis)
                val bufferedReader = BufferedReader(isr)
                val sb = StringBuilder()
                var line: String?
                while (bufferedReader.readLine().also { line = it } != null) {
                    sb.append(line)
                }
                bufferedReader.close()
                value = sb.toString()
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        return value
    }

    @JvmStatic
    fun createFile(context: Context, nameFile: String): File {
        val storageDir = context.filesDir
        val path = storageDir.toString() + nameFile
        if (existsInInternalStorage(context, nameFile)) {
            deleteFile(path)
        }
        return File(storageDir, nameFile)
    }

    @JvmStatic
    fun deleteFile(path: String?): Boolean {
        var value = false
        if (path != null) {
            val fDelete = File(path)
            if (fDelete.exists()) {
                value = fDelete.delete()
            }
        }
        return value
    }


}