package functional

import functional.users.User
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToFlux
import reactor.test.test

class IntegrationTests {

    val port = 8181
    private val application = Application(port)
    private val client = WebClient.create("http://localhost:$port")

    @BeforeAll
    fun beforeAll() {
        application.start()
    }

    @Test
    fun `Find all users on via users endpoint`() {
        client.get().uri("/api/users")
            .accept(APPLICATION_JSON)
            .retrieve()
            .bodyToFlux<User>()
            .test()
            .expectNextMatches { it.firstname == "carmine" && it.lastname == "d" }
            .expectNextMatches { it.firstname == "eliana" && it.lastname == "g" }
            .expectNextMatches { it.firstname == "laura" && it.lastname == "h" }
            .verifyComplete()
    }

    @AfterAll
    fun afterAll() {
        application.stop()
    }
}