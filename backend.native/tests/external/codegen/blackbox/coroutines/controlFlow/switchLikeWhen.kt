// WITH_RUNTIME
// WITH_COROUTINES
import kotlin.coroutines.*


class Controller {
    var result = ""

    suspend fun <T> suspendWithResult(value: T): T = CoroutineIntrinsics.suspendCoroutineOrReturn { c ->
        result += "["
        c.resume(value)
        CoroutineIntrinsics.SUSPENDED
    }
}

fun builder(c: suspend Controller.() -> Unit): String {
    val controller = Controller()
    c.startCoroutine(controller, EmptyContinuation)
    return controller.result
}

fun box(): String {
    var value = builder {
        for (v in listOf("A", "B", "C")) {
            when (v) {
                "A" -> result += "A;"
                "B" -> result += suspendWithResult(v) + "]"
                else -> result += suspendWithResult(v) + "]!"
            }
        }
    }
    if (value != "A;B]C]!") return "fail: suspend as if condition: $value"

    return "OK"
}
