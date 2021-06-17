package mn.security.rule.body.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import mn.security.rule.body.domain.Pet;

@Controller("/pet")
class PetController {

    @Post("/")
    public HttpResponse post(Pet pet) {
        return HttpResponse.ok();
    }

}