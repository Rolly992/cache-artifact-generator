import java.io.RandomAccessFile
import java.util.*

plugins {
    id("base")
}

val numberOfEntries = 50
val entrySizeMb = 150
var random = Random()
val generateAll = tasks.register("generate")

(1..numberOfEntries).forEach {
    val generateTask = tasks.register("generate8CacheEntry$it") {
        val outputDir = layout.buildDirectory.dir(name)
        outputs.dir(outputDir)
        outputs.cacheIf { true }
        inputs.property("entrySize", entrySizeMb)
        inputs.property("new8", it)

        doLast {
            val target = outputDir.get().file("entry-$it").asFile
            val file = RandomAccessFile(target, "rw")
            var byteArray = ByteArray (1024 * 1024);
            for (i in 0..entrySizeMb) {
                random.nextBytes(byteArray)
                file.write(byteArray)
            }
        }
    }

    generateAll.configure {
        dependsOn(generateTask)
    }
}
