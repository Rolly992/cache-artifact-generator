import java.io.RandomAccessFile

plugins {
    id("base")
}

val numberOfEntries = 200
val entrySizeMb = 200

val generateAll = tasks.register("generate")

(1..numberOfEntries).forEach {
    val generateTask = tasks.register("generateCacheEntry$it") {
        val outputDir = layout.buildDirectory.dir(name)
        outputs.dir(outputDir)
        outputs.cacheIf { true }
        inputs.property("entrySize", entrySizeMb)

        doLast {
            val target = outputDir.get().file("entry-$it").asFile
            val file = RandomAccessFile(target, "rw")
            file.setLength((1024 * 1024 * entrySizeMb).toLong())
        }
    }

    generateAll.configure {
        dependsOn(generateTask)
    }
}