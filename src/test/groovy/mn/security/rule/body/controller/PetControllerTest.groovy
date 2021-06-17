package mn.security.rule.body.controller

import spock.lang.Specification

import javax.inject.Inject

import io.micronaut.http.HttpMethod
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import mn.security.rule.body.domain.Pet


@MicronautTest
class PetControllerTest extends Specification {

    @Inject @Client('/')
    HttpClient client


    void 'test custom security rule that reads the request body'() {
        given: 'a pet (request body)'
        final pet = new Pet(name: 'Rex')

        when: 'do a post request'
        final request = HttpRequest.create(HttpMethod.POST, '/pet').body(pet)
        final response = client.toBlocking().exchange(request)

        then: 'the endpoint was contacted'
        response.status() == HttpStatus.OK
    }

}
