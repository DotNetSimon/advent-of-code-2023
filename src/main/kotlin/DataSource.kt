import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

// specify the advent of code source
private const val urlPrefix = "https://adventofcode.com/2023/day/"
private const val urlSuffix = "/input"
// cookie header for AOC website.
private const val cookieHeaderKey = "cookie"
// set this in your env settings based on the cookie from the website, so you can get your personal data.
// set it in your Kotlin configuration for Main as AOC2023Cookie=session=<sessionID> and replace the sessionID variable
private const val cookieEnvVariable = "AOC2023Cookie"

/**
 * Read and format the input to some extent. Basically we read the blob of data and format it into lines.
 */
class DataSource {
    companion object {
        private val cookie = System.getenv(cookieEnvVariable)

        fun getDataAsList(day: Int): List<String> {
            val url = "$urlPrefix$day$urlSuffix"
            val client = HttpClient.newBuilder().build()
            val request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header(cookieHeaderKey, cookie)
                .build()

            val response = client.send(request, HttpResponse.BodyHandlers.ofString())
            return response.body().split("\n").filterNot { it == "" }
        }
    }
}
