import org.jetbrains.ktor.application.Application
import org.jetbrains.ktor.host.embeddedServer
import org.jetbrains.ktor.netty.Netty

fun main(args: Array<String>) {
    embeddedServer(Netty, 8080, module = Application::main).start()
}