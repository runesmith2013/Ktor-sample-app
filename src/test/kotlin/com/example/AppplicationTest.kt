import com.example.module
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.test.assertContains
import kotlin.test.assertTrue

class ApplicationTest {

    @Test
    fun testRoot() = testApplication {
        application {
            module()
        }

        val response = client.get("/")
        assertEquals (response.bodyAsText(), "Hello World!")

        assertEquals (response.status, HttpStatusCode.OK)

    }

    @Test
    fun testNewEndPoint() = testApplication {
        application {
            module()
        }

        val response = client.get("/hello")

        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("html", response.contentType()?.contentSubtype)
        assertContains(response.bodyAsText(), "Hello ktor!")
    }


}