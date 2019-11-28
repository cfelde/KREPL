package krepl

import java.io.BufferedReader
import java.io.InputStreamReader
import javax.script.ScriptEngineManager

// Links used to figure out how to make this work:
// https://github.com/JetBrains/kotlin/blob/master/libraries/examples/kotlin-jsr223-local-example/src/test/kotlin/org/jetbrains/kotlin/script/jsr223/KotlinJsr223ScriptEngineIT.kt
// https://youtrack.jetbrains.com/issue/KT-30986
// https://discuss.kotlinlang.org/t/how-to-use-scripting-in-1-3-30/12580

class KREPL {
    fun main() {
        //System.setProperty("idea.use.native.fs.for.win", "false")
        val engine = ScriptEngineManager().getEngineByExtension("kts") ?: return

        BufferedReader(InputStreamReader(System.`in`)).use { reader ->
            println("KREPL ready..")
            println()

            while (true) {
                print(":> ")

                val line = reader.readLine() ?: break

                try {
                    val result = engine.eval(line)
                    if (result != null) println(result)
                } catch (ex: Exception) {
                    println(ex.message)
                }
            }
        }
    }
}
