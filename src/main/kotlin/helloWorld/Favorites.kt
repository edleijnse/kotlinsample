package helloWorld

import com.thoughtworks.xstream.XStream

import java.io.*
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.ArrayList

/**
 * Created by edleijnse on 15.06.17.
 */
class Favorites {
    var directories = ArrayList<String>()

    fun setDirectoriesFromFile(fileName: String) {
        println("Directories from File: " + fileName)
        var directoriesFromFile = ArrayList<String>()


        val path = Paths.get(fileName)
        try {
            Files.newBufferedReader(path, Charset.defaultCharset()).use { reader ->
                var line: String?
                line = reader.readLine()
                while (line != null) {
                    println(line)
                    directoriesFromFile.add(element = line)
                    line = reader.readLine()
                }

            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        this.directories = directoriesFromFile


    }

    fun writeFavoriteDirectoriesToFile(fileName: String) {
        try {
            val writer = PrintWriter(fileName, "UTF-8")
            for (myDirectory in directories) {
                println("myDirectory: " + myDirectory)
                writer.println(myDirectory)
            }
            writer.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

    }

    fun addDirectory(newDirectory: String) {
        directories.add(newDirectory)
    }

    fun removeDirectory(newDirectory: String) {
        directories.remove(newDirectory)
    }

    companion object {


        @JvmStatic
        fun main(args: Array<String>) {

            val favorites = Favorites()
            println("Favorites started")
            val someDirectories = ArrayList<String>()
            someDirectories.add("Documents")
            someDirectories.add("Downloads")
            favorites.directories = someDirectories
            favorites.addDirectory("another")

            val xstream = XStream()

            System.out.println("favorites: " + xstream.toXML(favorites.directories))

            favorites.writeFavoriteDirectoriesToFile("favoriteDirectoriesFile")

            favorites.setDirectoriesFromFile("favoriteDirectoriesFile")
            System.out.println("favorites 2: " + xstream.toXML(favorites.directories))
            favorites.removeDirectory("Downloads")
            System.out.println("favorites 2: " + xstream.toXML(favorites.directories))

        }
    }
}
