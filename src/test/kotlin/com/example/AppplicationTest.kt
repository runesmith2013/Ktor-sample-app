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
    fun tasksCanBeFoundByPriority() = testApplication {
        application {
            module()
        }

        val response = client.get("/tasks/byPriority/MEDIUM")
        val body = response.bodyAsText()

        assertEquals(HttpStatusCode.OK, response.status)
        assertContains(body, "Mow the lawn")
        assertContains(body, "Paint the fence")
    }

    @Test
    fun invalidPriorityProduces400()  = testApplication {
        application{
            module()
        }

        val response = client.get("/tasks/byPriority/VITAL")
        assertEquals(HttpStatusCode.NotFound, response.status)
    }


    @Test
    fun newTasksCanBeAdded() = testApplication {
        application {
            module()
        }

        val response1 = client.post("/tasks") {
            header(
                HttpHeaders.ContentType, ContentType.Application.FormUrlEncoded.toString()
            )
            setBody(
                listOf(
                    "name" to "swimming",
                    "description" to "Go to the beach",
                    "priority" to "Low"
                ).formUrlEncode()
            )
        }

        assertEquals(HttpStatusCode.NoContent, response1.status)

        val response2 = client.get("/tasks")
        assertEquals(HttpStatusCode.OK, response2.status)
        val body = response2.bodyAsText()
        assertContains(body, "swimming")
        assertContains(body, "Go to the beach")

    }


}